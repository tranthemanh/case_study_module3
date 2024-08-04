<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cập nhật danh mục</title>
</head>
<body>
<h2>Cập nhật danh mục</h2>

<!-- Hiển thị thông báo nếu có -->
<c:if test="${not empty message}">
    <p style="color: green;">${message}</p>
</c:if>

<!-- Form cập nhật danh mục -->
<form method="post">
    <input type="hidden" name="id" value="${categories_earn.id}"/>

    <div>
        <label for="name">Tên danh mục:</label>
        <input type="text" id="name" name="name" value="${category_earn.name}" required/>
    </div>

    <div>
        <label for="note">Ghi chú:</label>
        <textarea id="note" name="note" required>${category_earn.note}</textarea>
    </div>

    <div>
        <button type="submit">Cập nhật</button>
    </div>
</form>

<!-- Nút quay lại -->
<div>
    <a href="categories_earn">Quay lại danh sách danh mục</a>
</div>
</body>
</html>
