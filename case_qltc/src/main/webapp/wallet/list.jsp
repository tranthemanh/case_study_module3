<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 8/2/2024
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quan Ly Tai Chinh</title>
</head>
<body>
<div align="center">
    <h2>Quan Ly Vi Tien</h2>
    <table border="1" cellpadding="10">
        <caption>
            <h3>Danh sach vi tien</h3>
        </caption>
        <tr>
            <th>Wallet Name</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${listWallet}" var="wallet">
            <tr>
                <td><c:out value="${wallet.name}"></c:out></td>
                <td><c:out value="${wallet.amount}"></c:out></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
