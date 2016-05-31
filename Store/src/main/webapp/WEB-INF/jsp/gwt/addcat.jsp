<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<m:mainLay title="Welcome">
<form class="form-horizontal" role="form" action="/admin/add-category" method="post">
    <h2 class="form-signin-heading">Добавить категорию</h2>
    <div class="form-group">
        <label >Имя</label>
        <div class="col-md-5">
            <input type="text" name="name" class="form-control" placeholder="Имя категории" required autofocus>
        </div>
    </div>
    <div class="col-md-5">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Добавить</button>
    </div>
</form>
</m:mainLay>