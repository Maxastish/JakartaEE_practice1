package com.example.web;

import com.example.model.Course;
import com.example.model.Student;
import com.example.service.CourseServiceLocal;
import com.example.service.StudentCourseServiceLocal;
import com.example.service.StudentServiceLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentServiceLocal studentService;
    
    @EJB
    private CourseServiceLocal courseService;
    
    @EJB
    private StudentCourseServiceLocal scService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Student> students = studentService.findAll();
        List<Course> allCourses = courseService.findAll();

        // Заполняем studentCourses у каждого студента
        for (Student s : students) {
            s.setStudentCourses(scService.findByStudent(s.getId()));
        }

        // Формируем доступные курсы для каждого студента
        Map<Integer, List<Course>> availableCoursesMap = new java.util.HashMap<>();
        for (Student s : students) {
            List<Course> studentCourses = s.getStudentCourses().stream()
                                          .map(sc -> sc.getCourse())
                                          .toList();

            // Убедимся, что contains() работает — Course должен переопределять equals/hashCode
            List<Course> availableCourses = allCourses.stream()
                    .filter(c -> !studentCourses.contains(c))
                    .toList();

            availableCoursesMap.put(s.getId(), availableCourses);
        }

        req.setAttribute("students", students);
        req.setAttribute("availableCoursesMap", availableCoursesMap);

        req.getRequestDispatcher("/students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equals(action)) {
            Student s = new Student();
            s.setName(req.getParameter("name"));
            s.setGroupName(req.getParameter("groupName"));
            s.setBirthDate(LocalDate.parse(req.getParameter("birthDate")));
            studentService.create(s);
        }

        if ("update".equals(action)) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            Student s = studentService.findById(id);
            if (s != null) {
                s.setName(req.getParameter("name"));
                s.setGroupName(req.getParameter("groupName"));
                s.setBirthDate(LocalDate.parse(req.getParameter("birthDate")));
                studentService.update(s);
            }
        }

        if ("delete".equals(action)) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            studentService.remove(id);
        }

        resp.sendRedirect("students");
    }
}