<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<m:mainLay title="Welcome">
<form class="form-horizontal" role="form" action="/admin/add-product" enctype="multipart/form-data" method="post">
    <h2 class="form-signin-heading">Добавить продукт</h2>
    <div class="form-group">
        <label >Описание</label>
        <div class="col-md-5">
            <input type="text" name="description" class="form-control" placeholder="Описание" required autofocus>
        </div>
    </div>
    <div class="form-group">
        <label>Имя</label>
        <div class="col-md-5">
            <input type="text" name="name" class="form-control" placeholder="Имя" required>
        </div>
    </div>
    <div class="form-group">
        <label>Стоимость</label>
        <div class="col-md-5">
            <input type="number" name="price" class="form-control" placeholder="Стоимость" required>
        </div>
    </div>
    <div class="form-group">
        <label>Картинка</label>
        <div class="col-md-5">
            <input type="file" name="img" class="form-control" placeholder="Картинка" required>
        </div>
    </div>
    <div class="form-group">
        <label>Категория</label>
        <div class="col-md-5">
            <select name="categoryId" class="form-control" >
                <#list categories as category>
                    <option value="${category.id}">${category.name}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="col-md-5">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Добавить</button>
    </div>
</form>
</m:mainLay>