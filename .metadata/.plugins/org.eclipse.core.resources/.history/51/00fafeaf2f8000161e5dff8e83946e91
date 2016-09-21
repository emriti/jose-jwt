package com.ecomindo.common.export_handler;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecomindo.common.util.ObjectConverterHelper;

public abstract class DelimiterTextExportHandler {

	public abstract void startExport() throws Exception;

	private String exportType;
	private String fileName;
	private OutputStream fileStream;
	private ByteArrayOutputStream baoStream;

	private String delimiter = ",";
	private OutputStreamWriter swText;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public void DelimiterText(String fileName, String delimiter) throws Exception {
		if (fileName == null || "".equals(fileName)) {
			throw new Exception("fileName is required");
		}
		this.fileName = fileName;
		this.delimiter = delimiter;
		this.exportType = "File";
	}

	public void DelimiterText(ByteArrayOutputStream baoStream, String delimiter) throws Exception {
		if (baoStream == null) {
			throw new Exception("Stream is required");
		}
		this.baoStream = baoStream;
		this.delimiter = delimiter;
		this.exportType = "Stream";
	}

	public void DelimiterText(String fileName) throws Exception {
		DelimiterText(fileName, ",");
	}

	public void initializeExport() throws FileNotFoundException, Exception {
		// Initiate stream writer
		if ("File".equals(exportType)) {
			fileStream = new FileOutputStream(fileName);
			swText = new OutputStreamWriter(fileStream);
		} else if ("Stream".equals(exportType)) {
			swText = new OutputStreamWriter(baoStream);
		} else {
			throw new Exception("Unknown export type");
		}
	}

	public void writeLine(String line) throws IOException {
		if (swText != null) {
			swText.write(line + "\r\n");
		}
	}

	public void deinitializeExport() throws IOException {
		// copy stream to output memory stream before close
		swText.flush();
	}

	public void export() throws Exception {
		try {
			initializeExport();

			startExport();

			deinitializeExport();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		} finally {
			if (swText != null) {
				swText.close();
				if (fileStream != null) {
					fileStream.close();
				}
			}
		}
	}

	public String headerToDataLine(Class<?> type) throws Exception {
		return ObjectConverterHelper.dataHeadertoLine(type, delimiter, 1);
	}

	public String rowToDataLine(Object dr) throws Exception {
		return ObjectConverterHelper.dataRowToDelimiterDataLine(dr, delimiter);
	}

	public String resultSetToDataLine(ResultSet rs, List<Integer> lstRowSkip, Class<?> type) throws Exception {
		return ObjectConverterHelper.resultSetToDelimiterDataLine(type, rs, delimiter, lstRowSkip);
	}

	public String resultSetToDataLine(ResultSet rs, int rowSkip, Class<?> type) throws Exception {
		List<Integer> lstRowSkip = new ArrayList<Integer>();
		lstRowSkip.add(new Integer(rowSkip));
		return resultSetToDataLine(rs, lstRowSkip, type);
	}

	public String resultSetToDataLine(ResultSet rs, Class<?> type) throws Exception {
		return ObjectConverterHelper.resultSetToDelimiterDataLine(type, rs, delimiter, 1);
	}

	public String rowToLine(Object dr) throws Exception {
		return ObjectConverterHelper.dataRowToDelimiterLine(dr, delimiter);
	}

}
