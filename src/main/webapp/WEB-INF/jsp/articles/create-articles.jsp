<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">Create article</jsp:attribute>

    <jsp:attribute name="content">
        <link rel="stylesheet" href="/css/forms.css">

        <div class="container">
                    <c:url var="processCreatingArticle" value="/articles/create/process"/>

                    <form:form autocomplete="off" method="post" action="${processCreatingArticle}" modelAttribute="article" class = "service" enctype="multipart/form-data">

                        <form:input type="text" placeholder="Title" path="title"/>
                        <form:textarea placeholder="Type your text here..." path="content"/>

                        <input type="file" name="image"/>

                        <input type="submit" class="submit" value="Create"/>
                    </form:form>
                </div>
    </jsp:attribute>
</t:wrapper>