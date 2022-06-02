<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">Error</jsp:attribute>
    <jsp:attribute name="content">

        <div class="container">
            <div class="text">
            <div class="heading">${message}</div>
                <p>${quoteText}</p>
                <p>${quoteAuthor}</p>
            </div>
        </div>
    </jsp:attribute>
</t:wrapper>