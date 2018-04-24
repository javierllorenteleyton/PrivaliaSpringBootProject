package com.privalia.services;

import java.util.List;

public interface CRUService<T> {

	List<?> listall();
	
	T getById(Integer id);
	
	T saveOrUpdate(T domainObject);
	
	void delete(Integer id);
}
