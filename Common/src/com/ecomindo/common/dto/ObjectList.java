package com.ecomindo.common.dto;

import java.util.List;

public class ObjectList {
	private List<String> listString;
	private List<Long> listLong;
	private List<Integer> listInteger;
	private String sessionId;
	private String remoteIpAddress;

	public ObjectList() {

	}

	public List<String> getListString() {
		return listString;
	}

	public void setListString(List<String> listString) {
		this.listString = listString;
	}

	public List<Long> getListLong() {
		return listLong;
	}

	public void setListLong(List<Long> listLong) {
		this.listLong = listLong;
	}

	public List<Integer> getListInteger() {
		return listInteger;
	}

	public void setListInteger(List<Integer> listInteger) {
		this.listInteger = listInteger;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRemoteIpAddress() {
		return remoteIpAddress;
	}

	public void setRemoteIpAddress(String remoteIpAddress) {
		this.remoteIpAddress = remoteIpAddress;
	}
}
