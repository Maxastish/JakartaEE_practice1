package com.example.service;

import com.example.model.StudentCourse;

import jakarta.ejb.Local;
import java.util.List;

@Local
public interface StudentCourseServiceLocal {
    StudentCourse assignStudentToCourse(Integer studentId, Integer courseId);
    StudentCourse setMark(Integer studentId, Integer courseId, Integer mark);
    void removeAssignment(Integer studentId, Integer courseId);
    List<StudentCourse> findByStudent(Integer studentId);
    List<StudentCourse> findByCourse(Integer courseId);

    List<StudentCourse> findAll();
}
