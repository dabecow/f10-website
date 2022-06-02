<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">Login</jsp:attribute>

    <jsp:attribute name="content">
        <link rel="stylesheet" href="css/forms.css">

        <div class="container">
                    <c:url var="processLoginUrl" value="/login"/>

                    <form:form method="post" action="${processLoginUrl}" modelAttribute="user" class = "service">

                        <form:input type="text" placeholder="Username" path="username"/>
                        <form:input type="password" placeholder="Password" path="password"/>

                        <input type="submit" class="submit" value="Login"/>
                    </form:form>
        </div>
    </jsp:attribute>
</t:wrapper>