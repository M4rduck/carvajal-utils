package com.prueba.utils.resolver;

import org.springframework.http.HttpStatus;

import com.prueba.utils.response.ResponseError;

public class ResolverError {
	
	public final static String ORIGIN = "co.com.ed.gmaes.utils.service";

	public final static ResponseError BAD_REQUEST = build("BAD_REQUEST", "Solicitud incorrecta", HttpStatus.BAD_REQUEST);

	public final static ResponseError NO_FOUND = build("NO_FOUND", "No encontrado", HttpStatus.BAD_REQUEST);

	public final static ResponseError UNKNOW_ERROR = build("UNKNOW_ERROR", "Error desconocido",
			HttpStatus.INTERNAL_SERVER_ERROR);

	public final static ResponseError BAD_OPERATION_AUDIT = build("BAD_OPERATION_AUDIT", "No se puede actualizar una auditoria",
			HttpStatus.INTERNAL_SERVER_ERROR);

	
	public static ResponseError build(String title, String description, HttpStatus status) {
		if (description != null && status != null) {
			ResponseError err = new ResponseError();
			err.setTitle(title);
			err.setDescription(description);
			err.setOrigin(ORIGIN);
			err.setTypeError(status);
			return err;
		}
		return null;
	}

}
