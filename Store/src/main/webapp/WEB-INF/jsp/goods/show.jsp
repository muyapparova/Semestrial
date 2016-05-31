<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  Creating own tags and simple templating system : http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
--%>

<t:mainLayout title="Product \"${good.name}\"">
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
  <h2 class="product-name">${good.name}</h2>

    <div class="product-inner-price">
        <ins>Price: $${good.price}</ins>
    </div>
  <style>
    .colortext {
      color: #000000;
    }
  </style>

      <div role="tabpanel" class="tab-pane fade in active" id="home">
        <h2>Product Description</h2>
        <p>${good.description}</p>
      </div>
<br>
<div class="row">
    <form action="/addToCart" class="cart" method="post">
    <div class="col-md-6">
        <div class="quantity" style="float: right">
            <input type="hidden" name="id" value="${good.id}">
            Amount: <input type="number" style="width: 40px;" class="colortext" min="1" step="1" value="1" max="99" name="Amount">
        </div>
    </div>
    <div class="col-md-6">
        <div class="submitclass" style="float: left">
            <input type="submit" name="action" value="Add to cart" class="btn btn-sm btn-default"/>
        </div>
    </div>
    </form>
</div>

</t:mainLayout>