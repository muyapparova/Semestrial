<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  Creating own tags and simple templating system : http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
--%>
<t:mainLayout title="Cart">
    <style>
        .masthead {
            position: fixed;
            top: 0;
        }
        .mastfoot {
            position: fixed;
            bottom: 0;
        }
    </style>
    <div class="col-md-12">
        <div class="product-bit-title text-center">
            <h2>Shopping Cart</h2>
        </div>
    </div>
    <br>
    <style>
        .colortext {
            color: #000000;
        }
    </style>

    <div class="row">

        <c:choose>
                <c:when test="${not empty cartListWrapper.cartList}">
                    <c:url var="cartUrl" value="/cart"/>
                    <style>
                        td {
                            padding: 5px; /* Поля вокруг текста */
                        }
                    </style>
                    <form:form id="frmFoo" action="${cartUrl}" method="POST" modelAttribute="cartListWrapper">

            <table cellspacing="5" cellpadding="10" border="0" width="100%" align="center">
                            <thead>
                            <tr>
                                <th class="product-remove" style="text-align: center;">&nbsp;</th>
                                <th class="product-name" style="text-align: center;">Product</th>
                                <th class="product-price" style="text-align: center;">Price</th>
                                <th class="product-quantity" style="text-align: center;">Quantity</th>
                                <th class="product-subtotal" style="text-align: center;">Total</th>
                            </tr>
                            </thead>
            <tbody>

                <c:forEach var="cart" items="${cartListWrapper.cartList}" varStatus="loop">
                <tr class="cart_item">
                    <td class="product-remove">
                        <a title="Remove this item" href="${spring:mvcUrl("GC#delete").arg(0, cart.id).build()}" class="btn btn-sm btn-default">Delete</a>
                    </td>

                    <td class="product-name">
                        <form:input type="hidden" path="cartList[${loop.index}].id" id="id" name="id" value="${cart.id}"/>
                        <a href="single-product.html">${cart.good.name}</a>
                    </td>

                    <td class="product-price">
                        <span class="amount">$${cart.good.price}</span>
                    </td>

                    <td class="product-quantity">
                        <div class="quantity buttons_added">
                            <form:input cssStyle="width: 40px;" class="colortext" type="number" min="1" step="1" max="99" path="cartList[${loop.index}].goodAmount" name="goodAmount" id="goodAmount" value="${cart.goodAmount}" />

                            <!--<input type="number" size="4" class="input-text qty text" title="Qty" value="" >-->
                        </div>
                    </td>

                    <td class="product-subtotal">
                        <span class="amount">$${cart.goodAmount * cart.good.price}</span>
                    </td>
                </tr>

                </c:forEach>

                </tr>

                <tr>
                    <td class="actions" colspan="6">
                        <input type="submit" name="action" value="Update" class="btn btn-sm btn-default"/>
                        <input type="submit" name="action" value="Checkout" class="btn btn-sm btn-default"/>
                    </td>
                </tr>
                            </tbody>
                        </table>
                        <hr>
                        <h4>Order Total: $${total}</h4>
                    </form>

                    </form:form>
                </c:when>
                    <c:otherwise>
                    <div class="alert alert-info" role="alert" style="text-align: center">
                        <b>There is still nothing to show</b>
                    </div>
                    </c:otherwise>
                </c:choose>

    </div>

</t:mainLayout>