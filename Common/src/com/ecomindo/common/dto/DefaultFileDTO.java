package com.ecomindo.common.dto;

public class DefaultFileDTO {
	private int intId;
	private long id;
	private String fileName;
	private String fileType;
	private byte[] blobValue;

	public DefaultFileDTO() {

	}

	public DefaultFileDTO(long id, String fileName, String fileType, byte[] blobValue) {
		this.id = id;
		this.fileName = fileName;
		this.blobValue = blobValue;
		this.fileType = fileType;
	}
	
	public DefaultFileDTO(int intId, String fileName, String fileType, byte[] blobValue) {
		this.intId = intId;
		this.fileName = fileName;
		this.blobValue = blobValue;
		this.fileType = fileType;
	}

	public int getIntId() {
		return intId;
	}

	public void setIntId(int intId) {
		this.intId = intId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getBlobValue() {
		return blobValue;
	}

	public void setBlobValue(byte[] blobValue) {
		this.blobValue = blobValue;
	}

}
