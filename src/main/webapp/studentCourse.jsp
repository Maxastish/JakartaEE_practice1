<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Student" %>
<%@ page import="com.example.model.Course" %>
<%@ page import="com.example.model.StudentCourse" %>

<html>
<head>
    <title>Назначение студентов на курсы</title>
</head>
<body>

<h2>Список назначений</h2>
<table border="1">
    <tr>
        <th>Студент</th>
        <th>Курс</th>
        <th>Оценка</th>
        <th>Действия</th>
    </tr>
    <%
        List<StudentCourse> assignments = (List<StudentCourse>) request.getAttribute("assignments");
        if (assignments != null) {
            for (StudentCourse sc : assignments) {
    %>
    <tr>
        <td><%= sc.getStudent().getName() %></td>
        <td><%= sc.getCourse().getTitle() %></td>
        <td><%= sc.getMark() != null ? sc.getMark() : "-" %></td>
        <td>
            <form method="post" action="assign" style="display:inline">
                <input type="hidden" name="studentId" value="<%= sc.getStudent().getId() %>">
                <input type="hidden" name="courseId" value="<%= sc.getCourse().getId() %>">
                <input type="hidden" name="action" value="delete">
                <button type="submit">Удалить</button>
            </form>
            <form method="post" action="assign" style="display:inline">
                <input type="hidden" name="studentId" value="<%= sc.getStudent().getId() %>">
                <input type="hidden" name="courseId" value="<%= sc.getCourse().getId() %>">
                <input type="hidden" name="action" value="mark">
                <input type="number" name="mark" min="0" max="100">
                <button type="submit">Установить оценку</button>
            </form>
        </td>
    </tr>
    <%      }
        }
    %>
</table>

<h2>Назначить студента на курс</h2>
<form method="post" action="assign">
    <input type="hidden" name="action" value="assign">
    Студент:
    <select name="studentId">
        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
            if (students != null) {
                for (Student s : students) {
        %>
            <option value="<%= s.getId() %>"><%= s.getName() %></option>
        <%
                }
            }
        %>
    </select>
    Курс:
    <select name="courseId">
        <%
            List<Course> courses = (List<Course>) request.getAttribute("courses");
            if (courses != null) {
                for (Course c : courses) {
        %>
            <option value="<%= c.getId() %>"><%= c.getTitle() %></option>
        <%
                }
            }
        %>
    </select>
    <button type="submit">Назначить</button>
</form>

</body>
</html>