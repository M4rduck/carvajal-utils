package com.prueba.utils.crud;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.utils.response.Response;

import reactor.core.publisher.Mono;

public abstract class CrudController<O extends Serializable, ID extends Serializable> {
	
	protected abstract CrudService<O, ID> getService();
	
	private ObjectMapper objectMapper;
	
	@RequestMapping(method = RequestMethod.POST)
	public Mono<Response> create(@RequestBody String object) {
		return Mono.fromCallable(() -> {
			O cObject = null;
			try {
				cObject = this.getObjectMapper().readValue(object, this.getClassObject());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cObject;
		}).flatMap(cObject -> Mono.just(getService().createObject(cObject).toResponse()));
	}
	
	protected ObjectMapper getObjectMapper() {
		if (this.objectMapper == null) {
			this.objectMapper = new ObjectMapper();
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		return this.objectMapper;
	}
	
	protected abstract Class<O> getClassObject();

}
