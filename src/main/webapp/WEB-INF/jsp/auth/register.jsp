<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">Register</jsp:attribute>

    <jsp:attribute name="content">
        <link rel="stylesheet" href="css/forms.css">

        <div class="container">
                    <c:url var="processRegisterUrl" value="/register"/>

                    <form:form autocomplete="off" method="post" action="${processRegisterUrl}" modelAttribute="user" class = "service" enctype="multipart/form-data">

                        <form:input type="text" placeholder="Username" path="username"/>
                        <form:input type="text" placeholder="Email" path="email"/>
                        <form:input type="password" placeholder="Password" path="password"/>
                        <form:input type="text" placeholder="First name" path="firstName"/>
                        <form:input type="text" placeholder="Last name" path="lastName"/>
                        <form:input type="text" placeholder="Patronymic" path="patronymic"/>
                        <form:input type="text" placeholder="Occupation" path="occupation"/>
                        <input type="file" name="image"/>

                        <input type="submit" class="submit" value="Register"/>
                    </form:form>
                </div>
    </jsp:attribute>
</t:wrapper>