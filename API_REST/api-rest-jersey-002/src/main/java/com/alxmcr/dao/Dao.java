package com.alxmcr.dao;

import java.util.List;

import com.alxmcr.entities.Person;

public interface Dao<T> {

	T findById(Long id) throws Exception;

	T findByName(String name) throws Exception;

	List<T> getAll() throws Exception;

	T save(T t) throws Exception;

	T save(T t, boolean isRollback) throws Exception;

	T update(Long id, T tUpdated) throws Exception;

	T delete(Long id) throws Exception;
}
