<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Course" %>

<html>
<head>
    <title>Курсы</title>
</head>
<body>

<h2>Список курсов</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Преподаватель</th>
        <th>Действия</th>
    </tr>
    <%
        List<Course> courses = (List<Course>) request.getAttribute("courses");
        if (courses != null) {
            for (Course c : courses) {
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getTitle() %></td>
        <td><%= c.getProfessor() %></td>
        <td>
            <form method="post" action="courses" style="display:inline">
                <input type="hidden" name="id" value="<%= c.getId() %>">
                <input type="hidden" name="action" value="delete">
                <button type="submit">Удалить</button>
            </form>
            <!-- Можно добавить форму для обновления на месте, если нужно -->
        </td>
    </tr>
    <%      }
        }
    %>
</table>

<h2>Добавить новый курс</h2>
<form method="post" action="courses" accept-charset="UTF-8">
    <input type="hidden" name="action" value="create">
    Название курса: <input type="text" name="title">
    Преподаватель: <input type="text" name="professor">
    <button type="submit">Добавить</button>
</form>

</body>
</html>