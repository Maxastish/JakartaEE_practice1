package com.example.service;

import com.example.model.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentCourseService implements StudentCourseServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public StudentCourse assignStudentToCourse(Integer studentId, Integer courseId) {
        Student s = em.find(Student.class, studentId);
        Course c = em.find(Course.class, courseId);

        if (s == null || c == null) return null;

        StudentCourse sc = new StudentCourse();
        sc.setStudent(s);
        sc.setCourse(c);
        sc.setMark(null);

        em.persist(sc);
        return sc;
    }

    @Override
    public StudentCourse setMark(Integer studentId, Integer courseId, Integer mark) {
        StudentCourseId id = new StudentCourseId(studentId, courseId);
        StudentCourse sc = em.find(StudentCourse.class, id);

        if (sc != null) {
            sc.setMark(mark);
        }
        return sc;
    }

    @Override
    public void removeAssignment(Integer studentId, Integer courseId) {
        StudentCourseId id = new StudentCourseId(studentId, courseId);
        StudentCourse sc = em.find(StudentCourse.class, id);
        if (sc != null) em.remove(sc);
    }

    @Override
    public List<StudentCourse> findByStudent(Integer studentId) {
        return em.createQuery(
            "SELECT sc FROM StudentCourse sc WHERE sc.student.id = :id",
            StudentCourse.class
        ).setParameter("id", studentId)
         .getResultList();
    }

    @Override
    public List<StudentCourse> findByCourse(Integer courseId) {
        return em.createQuery(
            "SELECT sc FROM StudentCourse sc WHERE sc.course.id = :id",
            StudentCourse.class
        ).setParameter("id", courseId)
         .getResultList();
    }

    @Override
    public List<StudentCourse> findAll() {
        return em.createQuery(
            "SELECT sc FROM StudentCourse sc", StudentCourse.class
        ).getResultList();
    }
}
