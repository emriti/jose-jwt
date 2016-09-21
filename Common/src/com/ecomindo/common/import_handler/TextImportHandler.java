package com.ecomindo.common.import_handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ecomindo.common.exception.DAOException;

public abstract class TextImportHandler {

	private static Logger logger = Logger.getLogger(TextImportHandler.class);

	protected String jenisFile = "";
	protected BigDecimal idMitra;
	protected BigDecimal bulanDapem;
	protected String login;
	protected String ip;

	protected Connection conn;
	protected PreparedStatement preparedStatement;
	protected String sqlInsertQuery;
	protected final int bufferSize = 1000;

	// get total row saved
	long _totalRowSaved = 0;

	// hold total error occured
	private int _totalError = 0, _maxErrorAllowed = 0;

	public abstract void onLoadStarted();

	public abstract void onLoadCompleted(StringBuilder message);

	public abstract void onLoadTerminated();

	public abstract void onRowError(String message);

	public abstract void onRowWarning(String message);

	public abstract void onReadCompleted(long totalRowSaved);

	// protected EntityManager transaction;

	abstract protected String getRowType(Object rowValue);

	abstract protected void Parse(String rawData, List<String> errorMessage, List<String> warningMessage);

	public long get_totalRowSaved() {
		return _totalRowSaved;
	}

	public void set_totalRowSaved(long _totalRowSaved) {
		this._totalRowSaved = _totalRowSaved;
	}

	public int get_maxErrorAllowed() {
		return _maxErrorAllowed;
	}

	public void set_maxErrorAllowed(int _maxErrorAllowed) {
		this._maxErrorAllowed = _maxErrorAllowed;
	}

	public TextImportHandler(String jenisFile, BigDecimal idMitra, BigDecimal bulanDapem, String sqlInsertQuery,
			String login, String ip) {
		this.jenisFile = jenisFile;
		this.idMitra = idMitra;
		this.bulanDapem = bulanDapem;
		this.sqlInsertQuery = sqlInsertQuery;
		this.login = login;
		this.ip = ip;
	}

	/***
	 * Main function for import process
	 * 
	 * @param sourceFilename
	 * @throws Exception
	 */
	public void Import(File sourceFilename, Connection conn) throws Exception {

		StringBuilder logMessage = new StringBuilder();

		// begin transaction
		try {

			if (conn != null) {
				conn.setAutoCommit(false);
				this.conn = conn;
			} else {
				logMessage.append("Tidak ada transaksi yang sedang aktif. \r\n");
			}

		} catch (Exception e) {
			logMessage.append("Gagal memulai transaksi. \r\n");
		}

		try {

			// call user start event
			try {
				onLoadStarted();
			} catch (Exception e) {
				logMessage.append("Gagal memulai proses import. \r\n");
			}

			// hold source file total lines
			long totalLines = 0;

			// get number of rows to be proceed
			try {

				// open file
				FileReader file = null;
				try {
					file = new FileReader(sourceFilename);
				} catch (Exception e) {
					logMessage.append("Gagal membuka file. \r\n");
				}

				// read file and count total lines
				BufferedReader brLineCounter = new BufferedReader(file);

				try {
					while (brLineCounter.readLine() != null) {
						totalLines++;
					}
				} finally {
					// close buffer and file handler
					brLineCounter.close();
					file.close();
				}

			} catch (Exception e) {
				logMessage.append("Gagal menghitung jumlah baris. \r\n");
			}

			// read data
			readData(sourceFilename, totalLines);

			// notify caller that read already completed
			onReadCompleted(totalLines);

			if (_totalError > 0) {
				throw new DAOException(logger, "Gagal upload file.\r\n");
			} else {

				// notify load finished
				onLoadCompleted(logMessage.append("Upload berhasil."));

			}

		} catch (Exception e) {

			// notify caller
			onLoadCompleted(logMessage.append((e.getMessage() == null ? "" : e.getMessage())));

			// rethrown child error
			throw e;

		} finally {

			// notify load terminated
			onLoadTerminated();

			// close ps
			preparedStatement.close();
		}
	}

	private void readData(File sourceFilename, long totalLines) throws Exception {

		// Open file
		FileReader file = null;
		try {
			file = new FileReader(sourceFilename);
		} catch (Exception e) {
			onRowWarning("Gagal membuka file");
		}

		// prepare buffer
		BufferedReader br = new BufferedReader(file);
		try {

			// reset total error
			_totalError = 0;

			// create prepared statement
			preparedStatement = conn.prepareStatement(sqlInsertQuery);

			// process all lines
			for (long lineNo = 0; lineNo < totalLines; lineNo++) {

				try {

					// read the line
					String newRaw = br.readLine();

					// get row type
					String rowType = getRowType(newRaw);

					if (rowType != "") {

						// prepare vars to get error message in parsing process
						List<String> errorMessages = new ArrayList<String>();
						List<String> warningMessages = new ArrayList<String>();

						// parse and update parsing status
						Parse(newRaw, errorMessages, warningMessages);

						// trigger error event when necessary
						if (errorMessages.size() > 0) {

							// increase total error
							_totalError += 1;

							// notify user when handler defined
							String errorMessageText = "";
							for (int ii = 0; ii < errorMessages.size(); ii++) {
								errorMessageText += (errorMessageText != "" ? ", " : "") + errorMessages.get(ii);
							}
							onRowError("Error pada baris-" + (lineNo + 1) + ": " + errorMessageText + "\r\n");
						}

						// trigger warning event when necessary
						String warningMessageText = "";
						if (warningMessages.size() > 0) {
							for (int ii = 0; ii < warningMessages.size(); ii++) {
								warningMessageText += (warningMessageText != "" ? "," : "") + warningMessages.get(ii);
							}
							onRowWarning("Peringatan pada baris-" + (lineNo + 1) + ": " + warningMessageText + "\r\n");
						}

						if (_totalError >= 200) {
							onRowError("Input file memiliki 200 error.\nHarap cek kembali input file.\r\n");
							throw new Exception();
						}
					}

				} catch (Exception e) {
					if (!(_totalError >= 200)) {
						onRowWarning("Gagal memproses baris " + lineNo + "\r\n");
					}
					throw e;
				}

				// execute batch per buffer size
				if (lineNo % bufferSize == 0) {
					preparedStatement.executeBatch();
				}
			}

			// execute the remainder of the batch
			preparedStatement.executeBatch();

		} finally {

			// close buffer and file handler
			try {
				br.close();
				file.close();
			} catch (Exception e) {
				onRowWarning("Gagal menutup file" + "\r\n");
			}

		}
	}

}