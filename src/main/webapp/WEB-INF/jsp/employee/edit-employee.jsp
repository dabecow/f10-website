<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">Add employee</jsp:attribute>

    <jsp:attribute name="content">
        <link rel="stylesheet" href="/css/forms.css">

        <div class="container">

                    <form:form method="POST" class="service" modelAttribute="employee">
                        <form:select path="employeeId">
                                <c:forEach items="${employees}" var="e">
                                    <option value="${e.id}">${e.firstName} (${e.username})</option>
                                </c:forEach>
                        </form:select>
                        <form:select path="occupationId">
                                <c:forEach items="${occupations}" var="occupation">
                                    <option value="${occupation.id}">${occupation.name}</option>
                                </c:forEach>
                        </form:select>

                        <input type="submit" class="submit" value="Edit" formaction="edit/process"/>
                        <input type="submit" class="submit" value="Remove" formaction="remove"/>
                    </form:form>
        </div>
    </jsp:attribute>
</t:wrapper>