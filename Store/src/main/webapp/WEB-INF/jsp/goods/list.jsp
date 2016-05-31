<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  Creating own tags and simple templating system : http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
--%>
<t:mainLayout title="All goods">
    <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
            <li><a href="<spring:url value="/"/>">Home</a></li>
            <li class="active"><a href="<spring:url value="/shop"/>">Shop page</a></li>
            <li><a href="<spring:url value="/cart"/>">Cart</a></li>
            <li><a href="<spring:url value="/checkout"/>">Checkout</a></li>
            <li><a href="<spring:url value="/account"/>">My Account</a></li>
            <li><a href="<spring:url value="/contact"/>">Contact</a></li>
        </ul>
    </div>
    </div>
    </div>
    </div>
    <div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>Shop</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <c:choose>
                <c:when test="${not empty goods}">
                <c:forEach var="good" items="${goods.getContent()}" varStatus="loop">

                <div class="col-md-3 col-sm-6">
                    <div class="single-shop-product">
                        <div class="product-upper">
                            <a href="${spring:mvcUrl("GC#show").arg(0, good.id).build()}"><img src="<c:url value="${good.img}"/>" alt=""></a>
                        </div>
                        <h2><a href="${spring:mvcUrl("GC#show").arg(0, good.id).build()}">${good.name}</a></h2>
                        <div class="product-carousel-price">
                            <ins>$${good.price}</ins>
                        </div>

                        <div class="product-option-shop">
                            <a class="add_to_cart_button" data-quantity="1" href="${spring:mvcUrl('GC#addGood').arg(0, good.id).build()}">Add to cart</a>
                        </div>
                    </div>
                </div>
                </c:forEach>

                </c:when>
                    <c:otherwise>
                        <h3>No goods to show</h3>
                    </c:otherwise>
                </c:choose>

            </div>

            <c:if test="${not empty goods}">
                <div class="row">
                    <div class="col-md-6 col-lg-offset-3">
                        <t:pagination thispage="${goods}"></t:pagination>
                    </div>
                </div>
            </c:if>

        </div>
    </div>

</t:mainLayout>