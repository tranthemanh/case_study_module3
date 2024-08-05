
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>hien thi danh sach danh muc</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></head>
<body>
<div class="container">
    <h1>Quan ly danh muc</h1>
    <h2><a href="categories_spend?action=create" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Them danh muc chi</a></h2>
    <h2><a href="categories_spend?action=edit" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Sua danh muc</a></h2>
    <table class="table table-bordered">
        <tr>
            <th>Name</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${categories_spend}" var="category_spend">
            <tr>
                <td><c:out value="${category_spend.name}"></c:out></td>
                <td><a href="/categories_spend?action=delete&id=${category_spend.id}"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
                <td><a href="/categories_spend?action=edit&id=${category_spend.id}"><i class="fa fa-trash" update="true"></i></a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
