package com.prueba.utils.response;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonView;
import com.prueba.utils.resolver.ResolverError;
import com.prueba.utils.views.Views;

public class ResponsePage<T> extends ResponseData<List<T>> {
	
	@JsonView(Views.Pub.class)
	private List<T> data;

	@JsonView(Views.Pub.class)
	private Long allRecords;

	@JsonView(Views.Pub.class)
	private int allPage;

	@JsonView(Views.Pub.class)
	private int page;

	@JsonView(Views.Pub.class)
	private int recordPerPage;

	public ResponsePage() {
		super(null);
	}

	public ResponsePage(ResponseSuccess responseSuccess) {
		super(responseSuccess);
	}

	public List<T> getData() {
		return this.data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Long getAllRecords() {
		return allRecords;
	}

	public void setAllRecords(Long allRecords) {
		this.allRecords = allRecords;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	
	public ResponsePage<T> ToResponsePage(Page<T> page) {
		ResponsePage<T> response = this;
		if (page == null) {
			response.setMessage(null);
			response.addError(ResolverError.NO_FOUND);
			return response;
		}
		response.setData(page.toList());
		response.setAllPage(page.getTotalPages());
		response.setRecordPerPage(page.getSize());
		response.setPage(page.getNumber() + 1);
		response.setAllRecords(page.getTotalElements());
		return response;
	}

}
