package com.example.service;

import com.example.model.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentService implements StudentServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Student create(Student s) {
        em.persist(s);
        return s;
    }

    @Override
    public Student update(Student s) {
        return em.merge(s);
    }

    @Override
    public void remove(Integer id) {
        Student s = em.find(Student.class, id);
        if (s != null) em.remove(s);
    }

    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        return em.createQuery(
            "SELECT s FROM Student s ORDER BY s.name", Student.class
        ).getResultList();
    }
}
