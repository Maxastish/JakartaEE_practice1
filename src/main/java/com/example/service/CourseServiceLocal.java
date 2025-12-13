package com.example.service;

import com.example.model.Course;

import jakarta.ejb.Local;
import java.util.List;

@Local
public interface CourseServiceLocal {
    Course create(Course c);
    Course update(Course c);
    void remove(Integer id);
    Course findById(Integer id);
    List<Course> findAll();
}