package com.example.service;

import com.example.model.Student;

import jakarta.ejb.Local;
import java.util.List;

@Local
public interface StudentServiceLocal {
    Student create(Student s);
    Student update(Student s);
    void remove(Integer id);
    Student findById(Integer id);
    List<Student> findAll();
}