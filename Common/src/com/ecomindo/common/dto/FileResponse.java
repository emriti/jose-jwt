package com.ecomindo.common.dto;

import javax.ws.rs.core.Response.Status;

public class FileResponse {
	private String filename;
	private byte[] byteFile;
	private ErrorMessage errorMessage;
	private Status responseStatus;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getByteFile() {
		return byteFile;
	}

	public void setByteFile(byte[] byteFile) {
		this.byteFile = byteFile;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Status getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(Status responseStatus) {
		this.responseStatus = responseStatus;
	}

}
