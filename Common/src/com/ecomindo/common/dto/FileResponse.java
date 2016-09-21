package com.ecomindo.common.dto;

import org.springframework.http.HttpStatus;

public class FileResponse {
	private String filename;
	private byte[] byteFile;
	private ErrorMessage errorMessage;
	private HttpStatus responseStatus;

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

	public HttpStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(HttpStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

}
