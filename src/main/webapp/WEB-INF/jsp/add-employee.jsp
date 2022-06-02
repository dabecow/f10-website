<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">Add employee</jsp:attribute>

    <jsp:attribute name="content">
        <link rel="stylesheet" href="css/forms.css">

        <div class="container">

                    <form:form method="post" action="/employees/add/process" class = "service">
                        <select name="userSelect">
                                <c:forEach items="${users}" var="user">
                                    <option value="${user.id}">${user.username}</option>
                                </c:forEach>
                        </select>
                        <select name="occupationSelect">
                                <c:forEach items="${occupations}" var="occupation">
                                    <option value="${occupation.id}">${occupation.name}</option>
                                </c:forEach>
                        </select>

                        <input type="submit" class="submit" value="Add"/>
                    </form:form>
        </div>
    </jsp:attribute>
</t:wrapper>