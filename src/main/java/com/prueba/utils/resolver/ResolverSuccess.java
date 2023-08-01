package com.prueba.utils.resolver;

import com.prueba.utils.response.ResponseSuccess;

public class ResolverSuccess {
	
	public final static String ORIGIN = "Carvajal Utils";

	public final static ResponseSuccess OBJECT_CREATED = build("%s creado");

	public final static ResponseSuccess OBJECTS_CREATED = build("Lista de %s creados");

	public final static ResponseSuccess OBJECT_GET = build("%s obtenido");

	public final static ResponseSuccess OBJECTS_GET = build("Lista de %s obtenida");

	public final static ResponseSuccess OBJECT_PAGE_GET = build("Pagina de %s obtenida");

	public final static ResponseSuccess OBJECT_UPDATE = build("%s Actualizado");

	public static ResponseSuccess build(String message) {
		if (!(message == null ? true : message.trim().isEmpty())) {
			return new ResponseSuccess(message);
		}
		return null;
	}

	public static ResponseSuccess get(ResponseSuccess responseSuccess, String className) {
		if (responseSuccess != null && !(className == null ? true : className.trim().isEmpty())) {
			responseSuccess.setMessage(String.format(responseSuccess.getMessage(), className));
			return responseSuccess;
		}
		return null;
	}

}
