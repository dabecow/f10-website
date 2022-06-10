<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<head>

    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/navStyle.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Main</title>

</head>

<body class="mainbody">
    <img class="logo" src="img/f10logo.png" alt="f10logo">

    <nav>

        <ul class="nav">

            <li class="nav-item">
                <a href="about">About us</a>
            </li>

            <li class="nav-item">
                <a href="employees">Employees</a>
            </li>

            <li class="nav-item">
                <a href="articles">Articles</a>
            </li>

            <li class="nav-item">
                <a href="contact">Contact us</a>
            </li>

        </ul>
    </nav>

    <footer>
        <hr class="redline">
        <p>
            © F10crew.ru • ОРЕЛ • 1566 - 2022
        </p>
        <sec:authorize access="!isAuthenticated()">
            <a href="/login"> Login </a> | <a href="/register"> Register </a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="/logout"> Log out </a>
        </sec:authorize>
    </footer>

  </body>
</html>