package com.example.service;

import com.example.model.Course;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CourseService implements CourseServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Course create(Course c) {
        em.persist(c);
        return c;
    }

    @Override
    public Course update(Course c) {
        return em.merge(c);
    }

    @Override
    public void remove(Integer id) {
        Course c = em.find(Course.class, id);
        if (c != null) em.remove(c);
    }

    @Override
    public Course findById(Integer id) {
        return em.find(Course.class, id);
    }

    @Override
    public List<Course> findAll() {
        return em.createQuery(
            "SELECT c FROM Course c ORDER BY c.title", Course.class
        ).getResultList();
    }
}
