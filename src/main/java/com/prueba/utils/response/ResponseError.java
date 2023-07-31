package com.prueba.utils.response;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonView;
import com.prueba.utils.views.Views;

public class ResponseError {
	
	@JsonView(Views.Pub.class)
	private String description;

	@JsonView(Views.Pub.class)
	private String origin;

	@JsonView(Views.Pub.class)
	private HttpStatus typeError;

	@JsonView(Views.Pub.class)
	private String title;

	@JsonView(Views.Pub.class)
	private Map<String, Object> args;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public HttpStatus getTypeError() {
		return typeError;
	}

	public void setTypeError(HttpStatus typeError) {
		this.typeError = typeError;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, Object> getArgs() {
		return args;
	}

	public void setArgs(Map<String, Object> args) {
		this.args = args;
	}
}
