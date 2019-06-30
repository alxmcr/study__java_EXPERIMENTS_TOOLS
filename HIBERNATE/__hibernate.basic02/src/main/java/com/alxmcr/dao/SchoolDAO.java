package com.alxmcr.dao;

import com.alxmcr.entities.School;

import java.util.List;

public interface SchoolDAO {
    void setUp();

    School findSchoolById(int id);

    int createSchool(School School);

    List<School> readAllSchools();

    boolean updateSchoolName(int idSchool, String name);

    boolean deleteSchool(int id, boolean flagRollback);
}
