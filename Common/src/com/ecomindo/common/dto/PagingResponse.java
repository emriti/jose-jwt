package com.ecomindo.common.dto;

import java.util.List;

public class PagingResponse {
	private List<Object> data;
	private Long numberOfPages;

	public PagingResponse() {
		this.data = null;
		this.numberOfPages = new Long(0);
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public Long getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Long numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
}
