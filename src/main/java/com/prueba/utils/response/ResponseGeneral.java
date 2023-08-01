package com.prueba.utils.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.prueba.utils.views.Views;

public class ResponseGeneral {
	
	@JsonView(Views.Pub.class)
	private String message;
	
	@JsonView(Views.Pub.class)
	private Integer status;
	
	@JsonView(Views.Pub.class)
	private List<ResponseError> errors;
	
	@JsonView(Views.Pub.class)
	public boolean isSuccess() {
		return getErrors() == null || getErrors().isEmpty();
	}
	
	public ResponseGeneral() {
		super();
	}
	
	public ResponseGeneral(ResponseSuccess responseSuccess) {
		super();
		if (responseSuccess != null) {
			this.message = responseSuccess.getMessage();
			this.status = 200;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ResponseError> getErrors() {
		return errors;
	}

	public void setErrors(List<ResponseError> errors) {
		this.errors = errors;
	}
	
	public ResponseGeneral addError(ResponseError errors) {
		if (this.errors == null) {
			this.errors = new ArrayList<ResponseError>();
		}
		this.errors.add(errors);
		this.message = null;
		return this;
	}

	public ResponseGeneral addErrors(List<ResponseError> errors) {
		if (this.errors == null) {
			this.errors = new ArrayList<ResponseError>();
		}
		this.errors.addAll(errors);
		this.message = null;
		return this;
	}
	
	public Response toResponse() {
		Response response = new Response(this);
		if (!this.isSuccess()) {
			ResponseError firstErr = this.getErrors().stream().findFirst().orElse(null);
			this.setStatus(firstErr.getTypeError().value());
			this.setMessage(firstErr.getTitle());
			return new Response(this, firstErr.getTypeError());
		}
		this.setStatus(this.status == null ? 200 : this.status);
		this.setMessage(this.message == null ? "Success" : this.message);
		return response;
	}
}
