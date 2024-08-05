<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 8/5/2024
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quan Ly Chi Tieu</title>
</head>
<body>
<div align="center">
    <form method="post">
        <table>
            <caption>Them Khoan Chi</caption>
            <tr>
                <td>Ngay: </td>
                <td><input type="date" name="date"></td>
            </tr>
            <tr>
                <td>So Tien: </td>
                <td><input type="text" name="amount"></td>
            </tr>
            <tr>
                <td>Ghi Chu: </td>
                <td><input type="text" name="note"></td>
            </tr>
            <tr>
                <td>Danh Muc: </td>
                <td><select name="categoryId" id="categoryId">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}"><p>${category.name}</p></option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td>Chon Vi: </td>
                <td><select name="walletId" id="walletId">
                    <c:forEach items="${wallets}" var="wallet">
                        <option value="${wallet.id}"><p>${wallet.name}</p></option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Them</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
