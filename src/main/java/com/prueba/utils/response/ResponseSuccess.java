package com.prueba.utils.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.prueba.utils.views.Views;

public class ResponseSuccess {
	
	@JsonView(Views.Pub.class)
	private String message;

	public ResponseSuccess(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
