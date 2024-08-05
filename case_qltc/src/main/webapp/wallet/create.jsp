<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 8/2/2024
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quan Ly Tai Chinh</title>
</head>
<body>
<div align="center">
    <form method="post">
        <table cellpadding="10">
            <caption>Them moi vi tien</caption>
            <tr>
                <td>Ten Vi:</td>
                <td><input type="text" name="name" size="30"/></td>
            </tr>
            <tr>
                <td>So Tien:</td>
                <td><input type="text" name="amount" size="30"/></td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Them</button>
                    <button type="submit"><a href="/wallets">Quay lại</a></button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
