package com.alxmcr.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.alxmcr.dao.SchoolDAOImpl;
import com.alxmcr.entities.School;

class SchoolDAOTest {

	private static SchoolDAOImpl schoolDAO;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		schoolDAO = new SchoolDAOImpl();
		schoolDAO.setUp();
	}

	@Test
	void testSetUp() {
		SessionFactory factory = schoolDAO.getFactory();
		assertNotNull(factory);
	}

	@Test
	void testFindSchoolById() {
		// School List
		List<School> schools = schoolDAO.readAllSchools();

		if (schools != null) {
			if (!schools.isEmpty()) {
				// ID School
				int idSchool = schools.get(schools.size() - 1).getId();

				School school = schoolDAO.findSchoolById(idSchool);

				assertNotNull(school);
				assertEquals(idSchool, school.getId());
				assertEquals("Mike", school.getName());
			} else {
				System.err.println("List is empty...");
			}
		} else {
			assertFalse(false, "List is empty...");
		}

	}

	@Test
	void testCreateSchool() {
		// New Person
		String name = "San Andrew";

		School school = new School();
		school.setName(name);

		int id = schoolDAO.createSchool(school);
		assertTrue(id > 0);
		assertNotNull(id);
		assertEquals(name, school.getName());

		boolean isRollback = true;
		boolean isDeleted = schoolDAO.deleteSchool(id, isRollback);
		assertTrue(isDeleted);
	}

	@Test
	void testReadAllSchools() {
		List<School> schools = schoolDAO.readAllSchools();
		assertNotNull(schools);
	}

	@Test
	void updateSchoolName() {

		// School List
		List<School> schools = schoolDAO.readAllSchools();

		if (schools != null) {
			if (!schools.isEmpty()) {
				// ID School
				int idSchool = schools.get(schools.size() - 1).getId();

				String name = "San Antonio";
				boolean isUpdated = schoolDAO.updateSchoolName(idSchool, name);
				assertTrue(isUpdated);

				School school = schoolDAO.findSchoolById(idSchool);

				assertNotNull(school);
				assertEquals(idSchool, school.getId());
				assertEquals(name, school.getName());
			} else {
				System.err.println("List is empty...");
			}
		} else {
			assertFalse(false, "List is empty...");
		}

	}

	@Test
	void testDeleteSchool() {
		// School List
		List<School> schools = schoolDAO.readAllSchools();

		if (schools != null) {
			if (!schools.isEmpty()) {
				// ID School
				int idSchool = schools.get(0).getId();

				// Delete
				boolean isRollback = false;
				boolean isDeleted = schoolDAO.deleteSchool(idSchool, isRollback);
				assertTrue(isDeleted);

				// Check
				School school = schoolDAO.findSchoolById(idSchool);
				assertNull(school);
			} else {
				System.err.println("List is empty...");
			}
		} else {
			assertFalse(false, "List is empty...");
		}

	}
}
