package com.alxmcr.services;

import javax.persistence.NoResultException;

public interface Service<T> {
	T login(String username, String password) throws NoResultException, Exception;
}
