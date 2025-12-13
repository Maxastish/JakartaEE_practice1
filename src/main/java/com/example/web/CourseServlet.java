package com.example.web;

import com.example.model.Course;
import com.example.service.CourseServiceLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    @EJB
    private CourseServiceLocal courseService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("courses", courseService.findAll());
        req.getRequestDispatcher("/courses.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        if ("create".equals(action)) {
            Course c = new Course();
            c.setTitle(req.getParameter("title"));
            c.setProfessor(req.getParameter("professor"));
            courseService.create(c);
        }

        if ("update".equals(action)) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            Course c = courseService.findById(id);
            c.setTitle(req.getParameter("title"));
            c.setProfessor(req.getParameter("professor"));
            courseService.update(c);
        }

        if ("delete".equals(action)) {
            courseService.remove(Integer.valueOf(req.getParameter("id")));
        }

        resp.sendRedirect("courses");
    }
}
