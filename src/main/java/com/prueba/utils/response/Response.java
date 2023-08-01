package com.prueba.utils.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class Response extends ResponseEntity<Object> {
	
	public Response() {
		super(HttpStatus.OK);
	}

	public Response(Object body) {
		super(body, HttpStatus.OK);
	}

	public Response(Object body, HttpStatus statusCode) {
		super(body, statusCode);
	}
	
	public Response(Object body, HttpStatusCode statusCode) {
		super(body, statusCode);
	}

}
