<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  Creating own tags and simple templating system : http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
--%>
<t:mainLayout title="Good">
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
  <%--
    Creating own form tags as spring form tags wrappers: http://duckranger.com/2012/07/spring-mvc-and-twitter-bootstrap-customizing-the-input-fields/
  --%>
  <form:form class="form-horizontal" method="POST" commandName="good">
    <form:errors path="" />
    <t:input label="Name" required="true" path="name"/>
    <t:select label="Publishing House" required="true" path="publishingHouse" options="${brand}"/>
    <t:input label="Published" required="true" path="year"/>
    <t:input label="Pages" required="true" path="pages"/>
    <t:input label="ISBN" required="true" path="isbn"/>
    <t:input label="Link" required="false" path="url"/>
    <t:textarea label="Description" required="false" path="description"/>

    <button type="submit" class="btn btn-success">Save</button>
  </form:form>
</t:mainLayout>