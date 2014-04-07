<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:errorPage>
    <div class="container">
        <div class="col-md-offset-4 col-md-4"></div>
        <h2 class="centered-alert">
            <span class="glyphicon glyphicon-warning-sign"></span>
            The requested resource is not available
            <br> <br> <br>
            <img src="${pageContext.request.contextPath}/resources/images/404_girl.gif">
        </h2>
    </div>
</t:errorPage>
