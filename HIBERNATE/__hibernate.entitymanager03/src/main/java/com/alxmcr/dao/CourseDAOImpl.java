package com.alxmcr.dao;

import com.alxmcr.entities.Course;
import com.alxmcr.entities.Person;
import com.alxmcr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class CourseDAOImpl implements Dao<Course> {

	private EntityManager entityManager;

	public CourseDAOImpl(EntityManager em) {
		this.entityManager = em;
	}


	@Override
	public Course findById(Long id) {
		return null;
	}

	@Override
	public Course findByName(String name) {
		return null;
	}

	@Override
	public List<Course> getAll() {
		return null;
	}

	@Override
	public Course save(Course course) throws Exception {
		return null;
	}

	@Override
	public Course save(Course course, boolean isRollback) throws Exception {
		return null;
	}

	@Override
	public Course update(Course course, String[] params) {
		return null;
	}

	@Override
	public Course delete(Course course) {
		return null;
	}
}
