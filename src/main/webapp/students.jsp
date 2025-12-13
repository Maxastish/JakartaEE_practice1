<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.Student, com.example.model.Course, com.example.model.StudentCourse" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    List<Student> students = (List<Student>) request.getAttribute("students");
    Map<Integer, List<Course>> availableCoursesMap = (Map<Integer, List<Course>>) request.getAttribute("availableCoursesMap");
%>

<html>
<head>
    <title>Студенты</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin-bottom: 30px; }
        th, td { border: 1px solid black; padding: 5px; text-align: left; }
        th { background-color: #ddd; }
    </style>
</head>
<body>

<h1>Студенты</h1>
<c:if test="${empty students}">
    <p>Нет студентов!</p>
</c:if>
<c:if test="${not empty students}">
    <table>
        <tr>
            <th>ID</th>
            <th>ФИО</th>
            <th>Группа</th>
            <th>Дата рождения</th>
            <th>Действия</th>
        </tr>
        <c:forEach var="s" items="${students}">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.groupName}</td>
                <td>${s.birthDate}</td>
                <td>
                    <!-- Удалить студента -->
                    <form action="students" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${s.id}"/>
                        <input type="submit" value="Удалить"/>
                    </form>
                </td>
            </tr>

            <tr>
                <td colspan="5">
                    <strong>Курсы студента:</strong>
                    <c:if test="${empty s.studentCourses}">
                        Нет курсов
                    </c:if>
                    <c:if test="${not empty s.studentCourses}">
                        <ul>
                        <c:forEach var="sc" items="${s.studentCourses}">
                            <li>${sc.course.title} (${sc.course.professor})
                                <!-- Удалить курс студента -->
                                <form action="studentcourses" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="remove"/>
                                    <input type="hidden" name="studentId" value="${s.id}"/>
                                    <input type="hidden" name="courseId" value="${sc.course.id}"/>
                                    <input type="submit" value="Удалить"/>
                                </form>
                            </li>
                        </c:forEach>
                        </ul>
                    </c:if>

                    <!-- Добавить курс студенту -->
                    <form action="studentcourses" method="post">
                        <input type="hidden" name="action" value="assign"/>
                        <input type="hidden" name="studentId" value="${s.id}"/>
                        <select name="courseId">
                            <c:forEach var="c" items="${availableCoursesMap[s.id]}">
                                <option value="${c.id}">${c.title} (${c.professor})</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Добавить курс"/>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
</c:if>

<h2>Добавить нового студента</h2>
<form action="students" method="post">
    <input type="hidden" name="action" value="create"/>
    <label>ФИО: <input type="text" name="name"/></label><br/>
    <label>Группа: <input type="text" name="groupName"/></label><br/>
    <label>Дата рождения: <input type="date" name="birthDate"/></label><br/>
    <input type="submit" value="Добавить студента"/>
</form>

</body>
</html>