<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.Student, com.example.model.Course, com.example.model.StudentCourse" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    List<Student> students = (List<Student>) request.getAttribute("students");
    List<Course> courses = (List<Course>) request.getAttribute("courses");
%>

<html>
<head>
    <title>Вся база данных</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin-bottom: 30px; }
        th, td { border: 1px solid black; padding: 5px; text-align: left; }
        th { background-color: #ddd; }
    </style>
</head>
<body>

<h1>Студенты</h1>
<c:if test="${empty students}">
    <p>Нет данных в базе!</p>
</c:if>
<c:if test="${not empty students}">
    <table>
        <tr>
            <th>ID</th>
            <th>ФИО</th>
            <th>Группа</th>
            <th>Дата рождения</th>
        </tr>
        <c:forEach var="s" items="${students}">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.groupName}</td>
                <td>${s.birthDate}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Курсы</h1>
<c:if test="${empty courses}">
    <p>Нет курсов!</p>
</c:if>
<c:if test="${not empty courses}">
    <table>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Преподаватель</th>
        </tr>
        <c:forEach var="c" items="${courses}">
            <tr>
                <td>${c.id}</td>
                <td>${c.title}</td>
                <td>${c.professor}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Оценки студентов</h1>
<c:forEach var="s" items="${students}">
    <h3>${s.name} (${s.groupName})</h3>
    <c:if test="${empty s.studentCourses}">
        <p>Нет оценок!</p>
    </c:if>
    <c:if test="${not empty s.studentCourses}">
        <table>
            <tr>
                <th>Курс</th>
                <th>Преподаватель</th>
                <th>Оценка</th>
            </tr>
            <c:forEach var="sc" items="${s.studentCourses}">
                <tr>
                    <td>${sc.course.title}</td>
                    <td>${sc.course.professor}</td>
                    <td>${sc.mark}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</c:forEach>

</body>
</html>