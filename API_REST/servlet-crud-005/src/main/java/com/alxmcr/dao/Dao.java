package com.alxmcr.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	T findById(Long id) throws SQLException;

	T findByName(String name) throws SQLException;

	List<T> getAll() throws SQLException;

	T save(T t) throws SQLException;

	T save(T t, boolean isRollback) throws SQLException;

	T update(T t, String[] params);

	T delete(T t);
}
