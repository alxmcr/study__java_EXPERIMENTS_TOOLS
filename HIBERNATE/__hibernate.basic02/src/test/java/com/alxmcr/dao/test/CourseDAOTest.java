package com.alxmcr.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.alxmcr.dao.CourseDAOImpl;
import com.alxmcr.entities.Course;

public class CourseDAOTest {

	private static CourseDAOImpl courseDAO;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		courseDAO = new CourseDAOImpl();
		courseDAO.setUp();
	}

	@Test
	void testSetup() {
		SessionFactory factory = courseDAO.getFactory();
		assertNotNull(factory);
	}

	@Test
	void testFindCourseById() {

		int idCourse = 2;
		Course course = courseDAO.findCourseById(idCourse);
		System.out.println("Course=" + course);
		assertNotNull(course);
		assertEquals(idCourse, course.getId());
	}

	@Test
	void testCreateCourse() {

		// New Person
		String name = "Test Course - " + new Date().toString();

		Course course = new Course();
		course.setName(name);

		int id = courseDAO.createCourse(course);
		assertTrue(id > 0);
		assertNotNull(id);
		assertEquals(name, course.getName());

		boolean isRollback = true;
		boolean isDeleted = courseDAO.deleteCourse(id, isRollback);
		assertTrue(isDeleted);
	}
	
	@Test
	void testReadAllCourses() {
		List<Course> courses = courseDAO.readAllCourses();
		assertNotNull(courses);
	}

	@Test
	void updateCourseName() {

		// Course List
		List<Course> courses = courseDAO.readAllCourses();

		if (courses != null) {
			if (!courses.isEmpty()) {
				// ID Course
				int idCourse = courses.get(courses.size() - 1).getId();

				String name = "Mike";
				boolean isUpdated = courseDAO.updateCourseName(idCourse, name);
				assertTrue(isUpdated);

				Course course = courseDAO.findCourseById(idCourse);

				assertNotNull(course);
				assertEquals(idCourse, course.getId());
				assertEquals(name, course.getName());
			}
		} else {
			assertFalse(false, "List is empty...");
		}

	}



	@Test
	void testDeleteCourse() {
		// Course List
		List<Course> courses = courseDAO.readAllCourses();

		if (courses != null) {
			if (!courses.isEmpty()) {
				// ID Course
				int idCourse = courses.get(0).getId();

				// Delete
				boolean isRollback = false;
				boolean isDeleted = courseDAO.deleteCourse(idCourse, isRollback);
				assertTrue(isDeleted);

				// Check
				Course course = courseDAO.findCourseById(idCourse);
				assertNull(course);
			}else {
				System.err.println("List is empty...");
			}
		} else {
			assertFalse(false, "List is empty...");
		}

	}

}
