package com.alxmcr.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.alxmcr.entities.School;
import com.alxmcr.util.HibernateUtil;

public class SchoolDAOImpl implements SchoolDAO {

	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	@Override
	public void setUp() {
		// Hibernate configuration
		HibernateUtil hu = new HibernateUtil();
		hu.setup();

		// Session
		factory = hu.getFactory();
	}

	@Override
	public School findSchoolById(int id) {
		School school = null;

		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			school = session.get(School.class, id);

			// Commit transaction
			tx.commit();

			// Close the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return school;
	}

	@Override
	public int createSchool(School school) {
		
		int id = -1;
		
		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			session.save(school);

			// flush a batch of inserts and release memory:
			session.flush();
			session.clear();

			// Commit transaction
			tx.commit();

			// Close the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (school != null) {
			id = school.getId();
		}

		return id;
	}

	@Override
	public List<School> readAllSchools() {
		List<School> school = null;

		try (Session session = factory.openSession()) {

			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			school = session.createQuery("from School", School.class).list();

			// Commit transaction
			tx.commit();

			// Close the session
			session.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

		return school;
	}

	@Override
	public boolean updateSchoolName(int idSchool, String name) {
		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			School school = this.findSchoolById(idSchool);
			if (school != null) {
				school.setName(name);

				session.update(school);

				// Commit transaction
				tx.commit();
			}

			// Close the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSchool(int id, boolean flagRollback) {
		boolean result = false;

		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			School schoolToDelete = this.findSchoolById(id);

			if (schoolToDelete != null) {
				session.delete(schoolToDelete);

				if (flagRollback) {
					tx.rollback();
				} else {
					// Commit transaction
					tx.commit();
				}
				result = true;
			} else {
				System.err.println("School can not found...");
				result = false;
			}

			// Close the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

}
