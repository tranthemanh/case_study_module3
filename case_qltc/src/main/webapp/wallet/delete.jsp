<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 8/2/2024
  Time: 9:01 PM
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
        <caption>Xoa Vi</caption>
        <table cellpadding="10">
            <tr>
                <td>ID:</td>
                <td>${walletDelete.id}</td>
            </tr>
            <tr>
                <td>Wallet Name:</td>
                <td>${walletDelete.name}</td>
            </tr>
            <tr>
                <td>Amount:</td>
                <td>${walletDelete.amount}</td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Xoa</button>
                    <button type="submit"><a href="/wallets">Quay lại</a></button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
