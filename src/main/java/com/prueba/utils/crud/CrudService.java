package com.prueba.utils.crud;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.prueba.utils.resolver.ResolverError;
import com.prueba.utils.resolver.ResolverSuccess;
import com.prueba.utils.response.ResponseData;
import com.prueba.utils.response.ResponseList;
import com.prueba.utils.response.ResponsePage;

public abstract class CrudService<O extends Serializable, ID extends Serializable> {
	
	protected abstract MongoRepository<O, ID> getRepository();
	
	public ResponseData<O> createObject(O object) {
		ResponseData<O> response = new ResponseData<O>(
				ResolverSuccess.get(ResolverSuccess.OBJECT_CREATED, getClassName()));
		if (object == null) {
			response.addError(ResolverError.BAD_REQUEST);
			return response;
		}
		
		if (!response.isSuccess()) {
			return response;
		}
		
		try {
			object = this.getRepository().save(object);
		} catch (Exception e) {
			response.addError(ResolverError.UNKNOW_ERROR);
			return response;
		}
		if (object == null) {
			response.addError(ResolverError.UNKNOW_ERROR);
			return response;
		}
		response.setData(object);
		return response;
	}
	
	public ResponseData<O> updateObject(O object) {
		ResponseData<O> response = new ResponseData<O>(
				ResolverSuccess.get(ResolverSuccess.OBJECT_UPDATE, getClassName()));
		if (object == null) {
			response.addError(ResolverError.BAD_REQUEST);
			return response;
		}
		
		if (!response.isSuccess()) {
			return response;
		}
		
		try {
			object = this.getRepository().save(object);
		} catch (Exception e) {
			response.addError(ResolverError.UNKNOW_ERROR);
			return response;
		}
		if (object == null) {
			response.addError(ResolverError.NO_FOUND);
			return response;
		}
		
		response.setData(object);
		return response;
	}
	
	public ResponseData<O> getOne(ID id) {
		ResponseData<O> response = new ResponseData<O>(
				ResolverSuccess.get(ResolverSuccess.OBJECT_GET, getClassName()));
		if (id == null) {
			response.addError(ResolverError.BAD_REQUEST);
			return response;
		}
		O object = null;
		try {
			object = this.getRepository().findAllById(Arrays.asList(id)).stream().findAny().orElse(null);
		} catch (Exception e) {
			response.addError(ResolverError.UNKNOW_ERROR);
			return response;
		}
		if (object == null) {
			response.addError(ResolverError.NO_FOUND);
			return response;
		}
		
		response.setData(object);
		return response;
	}
	
	public ResponseList<O> getList() {
		ResponseList<O> response = new ResponseList<O>(
				ResolverSuccess.get(ResolverSuccess.OBJECTS_GET, getClassName()));
		List<O> listObject = null;
		try {
			listObject = this.getRepository().findAll();
		} catch (Exception e) {
			response.addError(ResolverError.UNKNOW_ERROR);
			return response;
		}
		if (listObject == null || listObject.size() == 0) {
			response.addError(ResolverError.NO_FOUND);
			return response;
		}
		response.setData(listObject);
		return response;
	}
	
	public ResponsePage<O> getForPage(Long page, Long recordPerPage) {
		ResponsePage<O> response = new ResponsePage<O>(
				ResolverSuccess.get(ResolverSuccess.OBJECT_PAGE_GET, getClassName()));
		if (page == null) {
			page = 0l;
		}
		if (page.intValue() > 0) {
			page = (long) (page.intValue() - 1);
		}
		if (recordPerPage == null) {
			recordPerPage = (long) 10;
		}
		PageRequest pageRequest = PageRequest.of(page.intValue(), recordPerPage.intValue(),
				Sort.by(Direction.DESC, "creationDate"));
		Page<O> pageObject = null;
		try {
			pageObject = this.getRepository().findAll(pageRequest);
		} catch (Exception e) {
			response.addError(ResolverError.UNKNOW_ERROR);
			return response;
		}
		if (pageObject == null || pageObject.getContent().size() == 0) {
			response.addError(ResolverError.NO_FOUND);
			return response;
		}
		return response.ToResponsePage(pageObject);
	}
	
	protected String getClassName() {
		return null;
	}

}
