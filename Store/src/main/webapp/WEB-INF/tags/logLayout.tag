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
    <jsp:include page="/WEB-INF/jsp/blocks/menu_main.jsp"/>
    <div class="container">
      <c:if test="${not empty message}">
      <br>
        <div class="alert alert-info" role="alert" ${empty messageClass ? '' :  messageClass}">${message}</div>
      </c:if>
    </div>
      <jsp:doBody/>

    <div class="footer-top-area">

      <div class="container">
        <div class="row">
          <div class="col-md-3 col-sm-6">
            <div class="footer-about-us">
              <h2>@<span>Time</span></h2>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis sunt id doloribus vero quam laborum quas alias dolores blanditiis iusto consequatur, modi aliquid eveniet eligendi iure eaque ipsam iste, pariatur omnis sint! Suscipit, debitis, quisquam. Laborum commodi veritatis magni at?</p>
              <div class="footer-social">
                <a href="#" target="_blank"><i class="fa fa-facebook"></i></a>
                <a href="#" target="_blank"><i class="fa fa-twitter"></i></a>
                <a href="#" target="_blank"><i class="fa fa-youtube"></i></a>
                <a href="#" target="_blank"><i class="fa fa-linkedin"></i></a>
              </div>
            </div>
          </div>

          <div class="col-md-3 col-sm-6">
            <div class="footer-menu">
              <h2 class="footer-wid-title">User Navigation </h2>
              <ul>
                <li><a href="#">My account</a></li>
                <li><a href="#">Order history</a></li>
                <li><a href="#">Wishlist</a></li>
                <li><a href="#">Vendor contact</a></li>
                <li><a href="#">Front page</a></li>
              </ul>
            </div>
          </div>

          <div class="col-md-3 col-sm-6">
            <div class="footer-menu">
              <h2 class="footer-wid-title">Categories</h2>
              <ul>
                <li><a href="#">Tissot</a></li>
                <li><a href="#">Casio</a></li>
                <li><a href="#">Seiko</a></li>
                <li><a href="#">Fossil</a></li>
                <li><a href="#">Citizen</a></li>
              </ul>
            </div>
          </div>

          <div class="col-md-3 col-sm-6">
            <div class="footer-newsletter">
              <h2 class="footer-wid-title">Newsletter</h2>
              <p>Sign up to our newsletter and get exclusive deals you wont find anywhere else straight to your inbox!</p>
              <div class="newsletter-form">
                <form action="#">
                  <input type="email" placeholder="Type your email">
                  <input type="submit" value="Subscribe">
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div> <!-- End footer top area -->

    <div class="footer-bottom-area">
      <div class="container">
        <div class="row">
          <div class="col-md-8">
            <div class="copyright">
              <p>&copy; 2016 kpfu.itis.shop All Rights Reserved. <a href="/" target="_blank">Home</a></p>
            </div>
          </div>

          <div class="col-md-4">
            <div class="footer-card-icon">
              <i class="fa fa-cc-discover"></i>
              <i class="fa fa-cc-mastercard"></i>
              <i class="fa fa-cc-paypal"></i>
              <i class="fa fa-cc-visa"></i>
            </div>
          </div>
        </div>
      </div>
    </div>

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