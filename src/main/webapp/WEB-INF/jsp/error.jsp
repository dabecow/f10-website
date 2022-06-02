<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">Error</jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">

            <h2>${message}</h2>
            <div class="text">
                <p>${quoteText}</p>
                <p>${quoteAuthor}</p>
            </div>
        </div>
    </jsp:attribute>
</t:wrapper>