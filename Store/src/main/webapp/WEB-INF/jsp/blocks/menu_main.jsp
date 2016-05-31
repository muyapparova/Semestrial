<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!--<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="{spring:mvcUrl('GC#list').build()}">Home</a></li>
        <li><a href="{spring:mvcUrl('GC#add').build()}">New Good</a></li>
        <li><a href="{spring:mvcUrl('UC#list').build()}">Users list</a></li>
        <security:authorize access="isAuthenticated()">
          <li><a href="{spring:mvcUrl('SC#profile').build()}">Profile</a></li>
          <li><a href="<spring:url value="/logout" />">Log out</a></li>
        </security:authorize>
        <security:authorize access="isAnonymous()">
          <li><a href="{spring:mvcUrl('SC#register').build()}">Register</a></li>
          <li><a href="<spring:url value="/login"/>">Log in</a></li>
        </security:authorize>
        <li><a href="<c:url value='/about' />">About</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="header-area">
  <div class="container">
    <div class="row">
      <div class="col-md-8">
        <div class="user-menu">
          <ul>
            <security:authorize access="isAuthenticated()">
              <li><i class="fa fa-user"></i> Hello, <security:authentication property="principal.username" /></li>
              <li><a href="<spring:url value="/account"/>"><i class="fa fa-user"></i> My Account</a></li>
              <li><a href="<spring:url value="/cart"/>"><i class="fa fa-user"></i> My Cart</a></li>
            </security:authorize>
<security:authorize access="isAnonymous()">
            <li><a href="${spring:mvcUrl('SC#register').build()}"><i class="fa fa-arrow-right"></i> Registration</a></li>
            <li><a href="<spring:url value="/login"/>"><i class="fa fa-user"></i> Login</a></li>
</security:authorize>
          </ul>
        </div>
      </div>

      <div class="col-md-4">
        <div class="header-right">
          <ul class="list-unstyled list-inline">
<security:authorize access="isAuthenticated()">
            <li><a href="<spring:url value="/logout"/>"><i class="fa fa-lock"></i> Logout</a></li>
</security:authorize>
            <li class="dropdown dropdown-small">
              <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span class="key">language: </span><span class="value">English </span><b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">English</a></li>
                <li><a href="#">Russian</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div> <!-- End header area


<div class="mainmenu-area">
  <div class="container">
    <div class="row">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>
      -->
<div class="site-wrapper">

  <div class="site-wrapper-inner">

    <div class="cover-container">
      <security:authorize access="isAnonymous()">
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
        </security:authorize>


        <style>
          hr {
            border: none; /* Убираем границу для браузера Firefox */
            color: white; /* Цвет линии для остальных браузеров */
            background-color: white; /* Цвет линии для браузера Firefox и Opera */
            height: 1px; /* Толщина линии */
          }
        </style>
      <div class="masthead clearfix">
        <div class="inner">
          <h3 class="masthead-brand"><a href="/"> Bookstore</a></h3>
          <nav>
            <ul class="nav masthead-nav">
              <security:authorize access="isAnonymous()">
              <li class="active"><a href="/">Home</a></li>

                <li><a href="${spring:mvcUrl('SC#register').build()}">Registration</a></li>
                <li><a href="<spring:url value="/login"/>">Sign In</a></li>
              </security:authorize>
              <security:authorize access="isAuthenticated()">
                <li>Hi, <security:authentication property="principal.fullName" />!</li>
                <li><a href="<spring:url value="/account"/>">Account</a></li>
                <li><a href="<spring:url value="/cart"/>">Cart</a></li>
                <li><a href="<spring:url value="/checkout"/>">Checkout</a></li>
                <li><a href="<spring:url value="/logout"/>">Exit</a></li>
              </security:authorize>

            </ul>
          </nav>
        </div>
      </div>
