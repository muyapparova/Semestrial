<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
  Creating own tags and simple templating system : http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
--%>
<t:mainLayout title="Checkout">


    <div class="well">
    <style>h3 {
        color: black;
    }</style>
    <h3>Please, fill up and check again your shipping info</h3><hr>


            <form:form class="form-horizontal" method="POST" modelAttribute="billing">

                    <form:hidden path="id" />

                <p id="billing_country_field" class="form-row form-row-wide address-field update_totals_on_change validate-required woocommerce-validated">
                     <t:input path="country" required="true"/>
                </p>
                <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                   <t:input path="firstName" required="true"/>
                </p>
                <p id="billing_last_name_field" class="form-row form-row-last validate-required">
                    <t:input path="lastName" required="true"/>
                </p>
                <p id="billing_company_field" class="form-row form-row-wide">
                   <t:input path="companyName"/>
                </p>
                <p id="billing_address_1_field" class="form-row form-row-wide address-field validate-required">
                    <t:input path="street" required="true"/>
                </p>
                <p id="billing_city_field" class="form-row form-row-wide address-field validate-required" data-o_class="form-row form-row-wide address-field validate-required">
                    <t:input path="city" required="true"/>
                </p>
                <p id="billing_state_field" class="form-row form-row-first address-field validate-state" data-o_class="form-row form-row-first address-field validate-state">
                    <t:input path="republic"/>
                </p>
                <p id="billing_postcode_field" class="form-row form-row-last address-field validate-required validate-postcode" data-o_class="form-row form-row-last address-field validate-required validate-postcode">
                    <t:input path="postcode" required="true"/>
                </p>
                <p id="billing_email_field" class="form-row form-row-first validate-required validate-email">
                    <t:input path="email" required="true"/>
                </p>
                <p id="billing_phone_field" class="form-row form-row-last validate-required validate-phone">
                    <t:input path="phone" required="true"/>
                </p>

        <div class="form-row place-order">
           <button type="submit" class="btn btn-info">Save billing info</button>
        </div>
    </div>
    </form:form>
<br>

</t:mainLayout>