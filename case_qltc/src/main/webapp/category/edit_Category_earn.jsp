<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 8/3/2024
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
<center>
    <h1>User Management</h1>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit User
                </h2>
            </caption>
            <tr>
                <th>ID</th>
                <td>
                    <input type="text" name="id" size="45"
                    />
                </td>
            </tr>
            <tr>
                <th>Name</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${category.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Note</th>
                <td>
                    <input type="text" name="note" size="15"
                           value="<c:out value='${category.note}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>