package com.alxmcr.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.alxmcr.entities.User;

public class UserDao implements Dao<User> {

	private EntityManager entityManager;

	public UserDao(EntityManager em) {
		this.entityManager = em;
	}

	@Override
	public User login(String username, String password) throws NoResultException, Exception {
		System.out.println("DAO: login()");

		User User = null;

		try {
			TypedQuery<User> UserByName = entityManager
					.createQuery("FROM User WHERE username= :username AND password= :password", User.class);
			UserByName.setParameter("username", username);
			UserByName.setParameter("password", password);
			User = UserByName.getSingleResult();
		} catch (NoResultException nre) {
			throw nre;
		} catch (Exception e) {
			throw e;
		}
		return User;
	}

}
