<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@attribute name="content" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<head>

    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/navStyle.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>
        <jsp:invoke fragment="title"/>
    </title>

</head>

<body class="mainbody">

    <nav>
        <a href="/"> __main__ </a>

        <hr class="redline">

        <ul class="nav">

            <li class="nav-item">
                <a href="/about">About us</a>
            </li>

            <li class="nav-item">
                <a href="/employees">Employees</a>
            </li>

            <li class="nav-item">
                <a href="/articles">Articles</a>
            </li>

            <li class="nav-item">
                <a href="/contact">Contact us</a>
            </li>

        </ul>
    </nav>

    <jsp:invoke fragment="content"/>

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