package com.alxmcr.dao;

import javax.persistence.NoResultException;

public interface Dao<T> {
	T login(String username, String password) throws NoResultException, Exception;
}
