package com.alxmcr.dao;

import com.alxmcr.entities.School;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class SchoolDAOImpl implements Dao<School> {

	private EntityManager entityManager;

	public SchoolDAOImpl(EntityManager em) {
		this.entityManager = em;
	}


	@Override
	public School findById(Long id) {
		return null;
	}

	@Override
	public School findByName(String name) {
		return null;
	}

	@Override
	public List<School> getAll() {
		return null;
	}

	@Override
	public School save(School school) throws Exception {
		return null;
	}

	@Override
	public School save(School school, boolean isRollback) throws Exception {
		return null;
	}

	@Override
	public School update(School school, String[] params) {
		return null;
	}

	@Override
	public School delete(School school) {
		return null;
	}
}
