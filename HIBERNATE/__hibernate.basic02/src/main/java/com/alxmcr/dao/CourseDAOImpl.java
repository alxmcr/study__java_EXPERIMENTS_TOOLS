package com.alxmcr.dao;

import com.alxmcr.entities.Course;
import com.alxmcr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
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
	public Course findCourseById(int id) {
		Course course = null;

		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			course = session.get(Course.class, id);

			// Commit transaction
			tx.commit();

			// Close the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return course;
	}

	@Override
	public int createCourse(Course course) {

		int id = -1;

		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			session.save(course);

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

		if (course != null) {
			id = course.getId();
		}

		return id;
	}

	@Override
	public List<Course> readAllCourses() {
		List<Course> courses = null;

		try (Session session = factory.openSession()) {

			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			courses = session.createQuery("from Course", Course.class).list();

			// Commit transaction
			tx.commit();

			// Close the session
			session.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public boolean updateCourseName(int idCourse, String name) {
		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			Course course = this.findCourseById(idCourse);
			if (course != null) {
				course.setName(name);

				session.update(course);

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
	public boolean deleteCourse(int id, boolean flagRollback) {
		boolean result = false;

		try (Session session = factory.openSession()) {
			// Begin Transaction
			Transaction tx = session.beginTransaction();

			// Working here...
			Course courseToDelete = findCourseById(id);

			if (courseToDelete != null) {
				session.delete(courseToDelete);

				if (flagRollback) {
					tx.rollback();
				} else {
					// Commit transaction
					tx.commit();
				}
				result = true;
			} else {
				System.err.println("Course can not found...");
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
