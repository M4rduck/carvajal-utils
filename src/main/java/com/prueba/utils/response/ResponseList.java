package com.prueba.utils.response;

import java.util.List;

public class ResponseList<T> extends ResponseData<List<T>> {
	
	public ResponseList() {
		super(null);
	}

	public ResponseList(ResponseSuccess responseSuccess) {
		super(responseSuccess);
	}

}
