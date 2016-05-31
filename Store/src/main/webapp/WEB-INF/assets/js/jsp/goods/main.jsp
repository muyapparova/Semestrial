<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<t:mainLayout title="Welcome">
    <security:authorize access="isAnonymous()">
    <div class="inner cover">
        <h1 class="cover-heading">Buy books online.</h1>
        <p class="lead">Online bookstore - the easiest way to find what you want. Buy the book - to buy a real, live emotions! Just reading the book is transformed into an extraordinary action. Books are always modern.</p>
        <p class="lead">
            <a href="<spring:url value="/login"/>" class="btn btn-lg btn-default">Sign In</a>
        </p>
    </div>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <div class="row">
            <c:choose>
                <c:when test="${not empty goods}">
                    <br><h2>Product List</h2>
                    <style>
                        .colortext {
                            color: #000000;
                        }
                    </style>
                    <form action="<spring:url value="/"/>" method="post">
                        <input type="text" class="colortext" name="searchTerm" placeholder="Search products...">
                        <input type="submit" value="Search">
                    </form>

                    <c:forEach var="good" items="${goods.getContent()}" varStatus="loop">


                            <div class="single-shop-product">
                                <hr>
                                <div class="book-name">
                                <h3><a href="${spring:mvcUrl("GC#show").arg(0, good.id).build()}">${good.name}</a></h3>
                                </div>
                                    <h4>${good.description}</h4>
                                <div class="col-md-6">
                                <div class="product-carousel-price">
                                    Price: $${good.price}
                                </div>
                                </div>
                                <div class="col-md-6">
                                <div class="product-option-shop">
                                    <a class="add_to_cart_button" data-quantity="1" href="${spring:mvcUrl('GC#addGood').arg(0, good.id).build()}"><ins>Add to cart</ins></a>
                                </div>
                                </div>
                            </div>
                        <hr>
                    </c:forEach>

                </c:when>
                <c:otherwise>
                    <h3>No goods to show</h3>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${not empty goods}">
            <div class="row">
                    <t:pagination thispage="${goods}"></t:pagination>
            </div>
        </c:if>
    </security:authorize>

</t:mainLayout>