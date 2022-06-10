<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:wrapper>
    <jsp:attribute name="title">About</jsp:attribute>
    <jsp:attribute name="content">

    <link rel="stylesheet" href="/css/customButtons.css">
    <link rel="stylesheet" href="/css/articles.css">

        <div class="container">
                <div class="text">
                    <div class="heading">${article.title}</div>


                    <div class="grayedOut">
                        <p>Created: ${article.creationDate} by ${article.author.username}</p>
                        <c:if test="${Objects.nonNull(article.lastEditor)}">
                            <p>Edited: ${article.editDate} by ${article.lastEditor.username}</p>
                        </c:if>
                    </div>
                    <img src="data:${article.mainImage.dataType};base64,${article.mainImage.base64String}" alt="${article.title}"/>

                    <p>${article.content}</p>

                </div>
        </div>

        <sec:authorize access="hasAuthority('ADMIN')">

            <div class="service">
                      <a href="/articles/${article.id}/edit">
                        <div class = "submit">
                            Edit
                        </div>
                      </a>

                      <br/>

                      <a href="/articles/${article.id}/remove">
                          <div class = "submit">
                              Remove
                          </div>
                        </a>
                  </div>
        </sec:authorize>

    </jsp:attribute>
</t:wrapper>