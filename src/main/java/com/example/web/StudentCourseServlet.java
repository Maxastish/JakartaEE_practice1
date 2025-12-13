package com.example.web;

import com.example.service.StudentCourseServiceLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/studentcourses")
public class StudentCourseServlet extends HttpServlet {

    @EJB
    private StudentCourseServiceLocal studentCourseService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");
        Integer studentId = Integer.valueOf(req.getParameter("studentId"));
        Integer courseId = Integer.valueOf(req.getParameter("courseId"));

        if ("assign".equals(action)) {
            studentCourseService.assignStudentToCourse(studentId, courseId);
        } else if ("remove".equals(action)) {
            studentCourseService.removeAssignment(studentId, courseId);
        }

        resp.sendRedirect("students");
    }
}