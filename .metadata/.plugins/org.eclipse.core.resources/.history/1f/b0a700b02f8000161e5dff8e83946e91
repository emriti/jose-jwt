package com.ecomindo.common.export_handler;

public abstract class ExportHandler {

	/* Must Override */
	public abstract void initializeExport();

	public abstract void deinitializeExport();

	public abstract void startExport();

	public abstract boolean isFinish();

	public abstract void processExport();

	/* property */
	private boolean isCancel = false;
	private int currentRow = 0;
	private int totalRow = 0;
	private int totalError = 0;
	private int totalSucces = 0;
	private String dataRow = "";
	private long currentProgress = 0;
	private long totalProgress = 0;

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getTotalError() {
		return totalError;
	}

	public void setTotalError(int totalError) {
		this.totalError = totalError;
	}

	public int getTotalSucces() {
		return totalSucces;
	}

	public void setTotalSucces(int totalSucces) {
		this.totalSucces = totalSucces;
	}

	public String getDataRow() {
		return dataRow;
	}

	public void setDataRow(String dataRow) {
		this.dataRow = dataRow;
	}

	public long getCurrentProgress() {
		return currentProgress;
	}

	public void setCurrentProgress(long currentProgress) {
		this.currentProgress = currentProgress;
	}

	public long getTotalProgress() {
		return totalProgress;
	}

	public void setTotalProgress(long totalProgress) {
		this.totalProgress = totalProgress;
	}

	/* Constructor */
	public ExportHandler() {
	}

	public void export() throws Exception {
		// Initial export
		try {
			initializeExport();
		} catch (Exception ex) {
			throw new Exception("Failed to initialize import.", ex);
		}

		// Process export
		isCancel = false;
		while (!isFinish() && !isCancel) {
			try {
				// RaiseEvent ExportProgress(Me, New
				// ExportProgressEventArgs(CurrentProgress, TotalProgress))

				currentRow += 1;

				startExport();

				totalSucces += 1;
			} catch (Exception ex) {
				totalError += 1;
				throw new Exception("", ex);
			} finally {
				// RaiseEvent RowError(Me, New RowErrorEventArgs(ex, CurrentRow,
				// DataRow))
				// RaiseEvent RowErrorEventHandler(Me, New RowErrorEventArgs(ex,
				// CurrentRow, DataRow))
				totalRow += 1;

			}
		}

		// Deinitial export
		try {
			deinitializeExport();
		} catch (Exception ex) {
			throw new Exception("Failed to deinitialize export.", ex);
		}

		// RaiseEvent ExportSummary(Me, New ExportSummaryEventArgs(TotalRow,
		// TotalError, TotalSuccess))

		try {
			processExport();
		} catch (Exception ex) {
			throw new Exception("Failed to process export.", ex);
		}
	}
}
