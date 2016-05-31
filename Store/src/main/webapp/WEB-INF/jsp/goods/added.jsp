<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  Creating own tags and simple templating system : http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
--%>
<t:mainLayout title="Success">
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

    <link href='<c:url value="/css/nprogress.css" />' rel='stylesheet' />
    <script src="<c:url value="/js/nprogress.js"/>"></script>

    <script type="text/javascript">
        setTimeout(function(){ window.location.replace("/cart"); }, 4000);
    </script>

    <script type="text/javascript">
        NProgress.configure({ easing: 'ease', speed: 4000 });
        NProgress.start();
        NProgress.done();
    </script>


<br>

    <div class="container">
        <div class="alert alert-success" role="alert" style="text-align: center"><b>This product was successfully added to cart!</b> Redirecting to view cart..</div>
    </div>

</t:mainLayout>