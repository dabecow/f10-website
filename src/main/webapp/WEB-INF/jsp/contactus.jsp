<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">Contact</jsp:attribute>

    <jsp:attribute name="content">
        <link rel="stylesheet" href="css/forms.css">

        <div class="container">
            <form:form method="post" action="/contact" modelAttribute="message" class="service">
                <form:input type="text" placeholder="Email" path="email"/>
                <form:input type="text" placeholder="Name" path="name"/>
                <form:textarea placeholder="Type your text here..." path="text"/>
                <input type="submit" class="submit" value="Send"/>
            </form:form>
        </div>
    </jsp:attribute>
</t:wrapper>