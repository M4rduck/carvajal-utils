package com.prueba.utils.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.prueba.utils.views.Views;

public class ResponseData<T> extends ResponseGeneral {
	
	@JsonView(Views.Pub.class)
	private T data;

	public ResponseData() {
		super(null);
	}

	public ResponseData(ResponseSuccess responseSuccess) {
		super(responseSuccess);
	}
	
	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public Response toResponse() {
		return new Response(this, super.toResponse().getStatusCode());
	}

}
