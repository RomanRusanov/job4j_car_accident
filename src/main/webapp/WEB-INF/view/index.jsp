<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .table,
        .buttonAddAccident
        {
            width: 55%;
            margin-left: 20%;
        }
    </style>
</head>
<body>
<a href="<c:url value='/create'/>" class="buttonAddAccident">Добавить инцидент</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col"></th>
        <th scope="col">id</th>
        <th scope="col">Имя</th>
        <th scope="col">Текст</th>
        <th scope="col">Адрес</th>
        <th scope="col">Тип</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${all_accidents}" var="accident">
        <tr>
            <td scope="row">
                <a href='<c:url value="/update?id=${accident.id}"/>'>
                    <i class="fa fa-edit mr-3"></i>
                </a>
            </td>
            <td scope="row">${accident.id}</td>
            <td scope="row">${accident.name}</td>
            <td scope="row">${accident.text}</td>
            <td scope="row">${accident.address}</td>
            <td scope="row">${accident.type.name}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>