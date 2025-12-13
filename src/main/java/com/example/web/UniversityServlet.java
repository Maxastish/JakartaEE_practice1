package com.example.web;

import com.example.model.Course;
import com.example.model.Student;
import com.example.service.CourseServiceLocal;
import com.example.service.StudentCourseServiceLocal;
import com.example.service.StudentServiceLocal;
import jakarta.ejb.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/university")
public class UniversityServlet extends HttpServlet {
    @EJB private StudentServiceLocal studentService;
    @EJB private CourseServiceLocal courseService;
    @EJB private StudentCourseServiceLocal scService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Student> students = studentService.findAll();
        List<Course> courses = courseService.findAll();

        // Устанавливаем studentCourses в каждом студенте
        for (Student s : students) {
            s.setStudentCourses(scService.findByStudent(s.getId()));
        }

        req.setAttribute("students", students);
        req.setAttribute("courses", courses);

        req.getRequestDispatcher("/university.jsp").forward(req, resp);
    }
}
