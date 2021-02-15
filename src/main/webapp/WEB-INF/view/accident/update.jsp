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
    <style type="text/css">
        .editForm {
            margin-left: 5%;
            margin-top: 5%;
            border: 1px dotted gray;
            width: 20%;
        }
    </style>
    <script>
        function validate() {
            var id = checkUserInput($('#id'));
            var name = checkUserInput($('#name'));
            var text = checkUserInput($('#text'));
            var address = checkUserInput($('#address'));
            if (id !== "" && name !== "" && text !== "" && address !== "") {
                return true;
            }
            return false;
        }
        function checkUserInput(input) {
            var currentInput = $(input).val();
            if (currentInput === '' || currentInput === undefined) {
                alert("Поле: "+ $(input).attr('title') + " не заполнено")
                return "";
            }
            return currentInput;
        }
    </script>
    <title>Редактирование инцидента</title>
</head>
<body>
<a href="/accident">Перейти на главную страницу</a>
<form  class="editForm" action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <td colspan='2'><h5>Редактирование инцидента</h5>
        </tr>
        <tr>
            <td>id:</td>
            <td><input type='number' name='id' title="id" id="id" value="${accident.id}"></td>
        </tr>
        <tr>
            <td>Имя:</td>
            <td><input type='text' name='name' title="Имя" id="name" value="${accident.name}"></td>
        </tr>
        <tr>
            <td>Текст:</td>
            <td><input type='text' name='text' title="Текст" id="text" value="${accident.text}"></td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type='text' name='address' title="Адрес" id="address" value="${accident.address}"></td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" style="margin-left: 33%" onclick="return validate()"/></td>
        </tr>
    </table>
</form>
</body>
</html>