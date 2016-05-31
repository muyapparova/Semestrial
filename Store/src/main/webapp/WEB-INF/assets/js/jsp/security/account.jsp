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
    <div class="inner cover">

                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>Account Info</h2>
                    </div>
                </div>



                <c:choose>
                    <c:when test="${not empty billing}">

                     <div class="bs-callout bs-callout-warning">
                         <h3>Please, fill up your billing details correctly</h3>
                         <p>This information will be used for the delivery</p>
                     </div>
                    <table class="shop_table" align="center">


                <tfoot>

                <tr class="cart-subtotal">
                    <th>First Name</th>
                    <td><span class="amount">${billing.firstName}</span>
                    </td>
                </tr>
                <tr class="cart-subtotal">
                    <th>Last Name</th>
                    <td><span class="amount">${billing.lastName}</span>
                    </td>
                </tr>
                <tr class="cart-subtotal">
                    <th>Company Name</th>
                    <td><span class="amount">${billing.companyName}</span>
                    </td>
                </tr>
                <tr class="cart-subtotal">
                    <th>Address</th>
                    <td><span class="amount">${billing.street} ${billing.apartment}</span>
                    </td>
                </tr>
                <tr class="cart-subtotal">
                    <th>Town / City</th>
                    <td><span class="amount">${billing.city}</span>
                    </td>
                </tr>
                <tr class="cart-subtotal">
                    <th>State</th>
                    <td><span class="amount">${billing.republic}</span>
                    </td>
                </tr>
                <tr class="cart-subtotal">
                    <th>Postcode</th>
                    <td><span class="amount">${billing.postcode}</span>
                    </td>
                </tr>
                <tr class="cart-subtotal">
                    <th>E-mail</th>
                    <td><span class="amount">${billing.email}</span>
                    </td>
                </tr>
                <tr class="cart-subtotal">
                    <th>Phone</th>
                    <td><span class="amount">${billing.phone}</span>
                    </td>
                </tr>



                </tfoot>
            </table><br>

                        <a href="<spring:url value="/checkout"/>" class="btn btn-sm btn-default">Edit Info</a>

                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-info" role="alert" style="text-align: center">
                            <b>There is still nothing to show</b>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

</t:mainLayout>