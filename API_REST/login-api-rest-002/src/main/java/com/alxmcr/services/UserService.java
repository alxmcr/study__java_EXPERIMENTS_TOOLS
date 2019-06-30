package com.alxmcr.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.alxmcr.dao.UserDao;
import com.alxmcr.entities.User;

public class UserService implements Service<User> {

	private UserDao dao;

	private EntityManagerFactory emf;
	private EntityManager em;

	public UserService() {

		this.emf = Persistence.createEntityManagerFactory("my-persistence-unit");

		this.em = this.emf.createEntityManager();

		// DAO
		this.dao = new UserDao(this.em);
	}

	@Override
	public User login(String username, String password) throws NoResultException, Exception {
		User user = null;

		try {
			user = this.dao.login(username, password);
		} catch (Exception e) {
			throw e;
		} finally {
			this.em.close();
		}
		return user;
	}

}
