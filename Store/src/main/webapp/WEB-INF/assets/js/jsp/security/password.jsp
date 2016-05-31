<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  Creating own tags and simple templating system : http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
--%>
<t:mainLayout title="Password retrieval">
    <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="<spring:url value="/"/>">Home</a></li>
            <li><a href="<spring:url value="/shop"/>">Shop page</a></li>
            <li><a href="<spring:url value="/cart"/>">Cart</a></li>
            <li><a href="<spring:url value="/checkout"/>">Checkout</a></li>
            <li><a href="<spring:url value="/account"/>">My Account</a></li>
            <li><a href="<spring:url value="/contact"/>">Contact</a></li>
        </ul>
    </div>
    </div>
    </div>
    </div>
  <%--
    Creating own form tags as spring form tags wrappers: http://duckranger.com/2012/07/spring-mvc-and-twitter-bootstrap-customizing-the-input-fields/
  --%>
    <br>
  <div class="container">
    <div class="row">
      <div class="col-md-6 col-lg-offset-3">
  <c:if test="${error != null}">
    <div class="alert alert-info" role="alert">
      Wrong email-password.
    </div>
  </c:if>
        <div class="panel panel-success">
          <div class="panel-heading">Password retrieval</div>
          <div class="panel-body">
  <h5>Please enter the email that you used to log on to the site.</h5><hr>
      <form:form class="form-horizontal" method="POST" commandName="user">
      <t:input label="E-mail" path="username" required="true"/>
        <div class="col-md-offset-6">
        <button type="submit" class="btn btn-success">Send</button>
        </div>
      </form:form>

          </div>
        </div>
      </div>
      </div>
    </div>
</t:mainLayout>