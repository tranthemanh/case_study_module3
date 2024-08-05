<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 8/2/2024
  Time: 4:34 PM
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
    <form method="post">
        <table cellpadding="10">
            <caption>Sua vi tien</caption>
            <c:if test="${wallet != null}">
                <input type="hidden" name="id" value="<c:out value="${wallet.id}"/>"/>
            </c:if>
            <tr>
                <th>Wallet Name:</th>
                <td>
                    <input type="text" name="name" size="30" value="<c:out value="${wallet.name}"/>">
                </td>
            </tr>
            <tr>
                <th>Amount:</th>
                <td>
                    <input type="text" name="amount" size="30" value="<c:out value="${wallet.amount}"/>">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Sua</button>
                    <button type="submit"><a href="/wallets">Quay lại</a></button>
                </td>
        </table>
    </form>
</div>
</body>
</html>
