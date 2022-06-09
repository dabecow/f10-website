<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">Articles</jsp:attribute>
    <jsp:attribute name="content">

    <link rel="stylesheet" href="/css/articleCards.css">
    <link rel="stylesheet" href="/css/customButtons.css">
    <link rel="stylesheet" href="/css/pagination.css">

    <div class="container">
                <div class="text">
                    <div class="heading">Articles</div>
                </div>
            </div>

    <c:forEach items="${articles}" var="article">
        <a href="/articles/${article.id}">
            <div class="card">
                <div class="card_image">
                    <img src="data:${article.mainImage.dataType};base64,${article.mainImage.base64String}" alt="${article.title}"/>
                </div>
                <div class="card_title title-white">
                    <p> ${article.title} </p>
                </div>
            </div>
        </a>
    </c:forEach>

    <div class="pagination">
            <ul>
                <c:if test="${0 != currentPage}">
                    <li><a href="?page=0">First</a></li>
                    <li><a href="?page=${currentPage - 1}">Prev</a></li>
                </c:if>

                <c:if test="${startPage != currentPage}">
                    <li><a href="?page=${startPage}">${startPage}</a></li>
                </c:if>

                <c:if test="${maxPage != 0}">
                    <li><p class="currentPage">${currentPage}</p></li>
                </c:if>

                <c:if test="${endPage != currentPage}">
                    <li><a href="?page=${endPage}">${endPage}</a></li>
                </c:if>

                <c:if test="${maxPage != currentPage}">
                    <li><a href="?page=${currentPage + 1}">Next</a></li>
                    <li><a href="?page=${maxPage}">Last</a></li>
                </c:if>

            </ul>
    </div>

    <div class="service">
          <a href="/articles/create">
            <div class = "submit">
                Create article
            </div>
          </a>
      </div>

    </jsp:attribute>
</t:wrapper>