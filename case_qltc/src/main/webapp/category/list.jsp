<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 8/2/2024
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hien thi danh sach danh muc</title>
</head>
<body>
<h1>Quan ly danh muc</h1>
<table>
    <tr>
        <th> Name <th>
    </tr>
    <c:forEach items="${categorys}" var="cate">
        <tr>
            <td> <c:out value="${cate.name}"></c:out></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
