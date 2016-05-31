<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="title" %>
  
<%--
  Creating own tags and simple templating system : http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
--%>

<c:if test="${not empty messageType}" >
  <c:choose>
    <c:when test="${messageType == 'success'}">
      <c:set var="messageClass" value="text-success"/>
    </c:when>
    <c:when test="${messageType == 'fail'}">
      <c:set var="messageClass" value="text-danger"/>
    </c:when>
  </c:choose>
</c:if>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
      Shop<c:if test="${not empty title}" > :: ${title}</c:if>
      </title>


    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css" />">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<c:url value="/css/owl.carousel.css" />">
    <link rel="stylesheet" href="<c:url value="/css/style.css" />">
    <link rel="stylesheet" href="<c:url value="/css/responsive.css" />">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <jsp:include page="/WEB-INF/jsp/gwt/admin.jsp"/>
    <div class="container">
      <c:if test="${not empty message}">
        <div class="alert alert-info" role="alert" ${empty messageClass ? '' :  messageClass}">${message}</div>
      </c:if>
    </div>
      <jsp:doBody/>


    <!-- Latest jQuery form server -->
    <script src="https://code.jquery.com/jquery.min.js"></script>

    <!-- Bootstrap JS form CDN -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

    <!-- jQuery sticky menu -->
    <script src="<c:url value="/js/owl.carousel.min.js"/>"></script>
    <script src="<c:url value="/js/jquery.sticky.js"/>"></script>

    <!-- jQuery easing -->
    <script src="<c:url value="/js/jquery.easing.1.3.min.js"/>"></script>

    <!-- Main Script -->
    <script src="<c:url value="/js/main.js"/>"></script>

    <!-- Slider -->
    <script type="text/javascript" src="<c:url value="js/bxslider.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="js/script.slider.js"/>"></script>
  </body>
</html>