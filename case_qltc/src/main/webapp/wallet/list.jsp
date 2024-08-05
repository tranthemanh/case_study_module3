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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div align="center">
    <h2>Quan Ly Vi Tien</h2>
    <h2>
        <a href="/wallets?action=create">Them vi tien moi</a>
    </h2>
    <table cellpadding="10">
        <caption>
            <h3>Danh sach vi tien</h3>
        </caption>
        <tr>
            <th></th>
            <th>Ten Vi</th>
            <th>So Tien</th>
            <th>Lua Chon</th>
        </tr>
        <c:forEach items="${listWallet}" var="wallet">
            <tr>
                <td><i class="fas fa-wallet"></i></td>
                <td><c:out value="${wallet.name}"></c:out></td>
                <td><c:out value="${wallet.amount}"></c:out></td>
                <td>
                    <a href="/wallets?action=update&id=${wallet.id}">Sua</a>
                    <a href="/wallets?action=delete&id=${wallet.id}">Xoa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
