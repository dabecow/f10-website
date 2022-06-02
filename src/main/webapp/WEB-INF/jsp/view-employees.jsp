<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="content">
        <link rel="stylesheet" href="css/employeeCards.css">
        <link rel="stylesheet" href="css/customButtons.css">

        <div class="container">
            <div class="text">
                <div class="heading"> Our crew </div>
            </div>
        </div>
                        <c:forEach items="${employees}" var="employee">

                            <div class="newBody">
                                <div class="box">
                                    <div class="cardEmployee">
                                        <div class="imgBx">
                                            <img src="data:${employee.photo.dataType};base64,${employee.photo.base64String}" alt="${employee.firstName}" alt="images">
                                        </div>
                                        <div class="details">
                                            <h2>
                                                ${employee.firstName}
                                                <br>
                                                <span>${employee.occupation}</span>
                                            </h2>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>

                        </div>

                          <div class="service">
                          <a href="employees/add">
                            <div class = "submit">
                                Add employee
                            </div>
                          </a>
                          </div>

    </jsp:attribute>
</t:wrapper>



