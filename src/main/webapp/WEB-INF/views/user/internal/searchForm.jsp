<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title>User Search</title>
<t:mainPage>

    <div class="container">
        <div class="col-md-offset-3 col-md-6">

            <form:form class="form-horizontal" action="/user/advancedSearch" method="post" modelAttribute="criteria">

                <%--<form:input getPath="name" placeholder="Name"/><br>--%>
                <%--<form:input getPath="email"/><br>--%>
                <%--<form:select getPath="country" items="${countries}"/><br>--%>
                <%--<form:input getPath="phoneNumber"/><br>--%>

                    <legend>Advanced Search</legend>

                    <div class="form-group">
                        <label class="control-label col-md-4" for="name">Username</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="name" path="name" name="name" id="name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="email">Email</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" path="email" name="email" id="email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="country">Country</label>
                        <div class="col-md-8">
                            <form:select class="form-control input-md" path="country" name="country" id="country"
                                         items="${countries}" selected=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="phoneNumber">Phone Number</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" path="phoneNumber" name="phoneNumber" id="phoneNumber"/>
                        </div>
                    </div>

                <div class="form-group">
                    <label class="control-label col-md-4"></label>

                    <div class="col-md-8">
                        <input class="btn btn-info pull-right" type="submit" value="Find" />
                    </div>
                </div>

                <%--<input type="submit" value="Find"/>--%>
            </form:form>
        </div>
    </div>
</t:mainPage>

