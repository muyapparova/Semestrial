<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  Creating own tags and simple templating system : http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
--%>
<t:mainLayout title="Registration">

  <br>
  <div class="inner cover">
    <div class="col-md-10 col-lg-offset-1">
  <div class="well">
    <style>h3 {
      color: black;
    }</style>
    <h3>Please, fill up registration form</h3><hr>
  <form:form class="form-horizontal" method="POST" commandName="user">
    <t:input label="E-mail" path="username" required="true"/>
    <t:input label="Name" path="fullName" required="true"/>
    <t:password label="Password" path="password" required="true"/>
    <t:password label="Password Repeat" path="passwordRepeat" required="true"/>
    <div class="class">
    <button type="submit" class="btn btn-info">Register</button>
    </div>
  </form:form>
          </div>
        </div>
      </div>
</t:mainLayout>