package com.alxmcr.dao;

import com.alxmcr.entities.Course;

import java.util.List;

public interface CourseDAO {
    void setUp();

    Course findCourseById(int id);

    int createCourse(Course Course);

    List<Course> readAllCourses();

    boolean updateCourseName(int idCourse, String name);

    boolean deleteCourse(int id, boolean flagRollback);
}
