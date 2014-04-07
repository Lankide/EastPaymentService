<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>User profile</title>

<t:mainPage>
    <div class="container">
        <div class="col-md-offset-3 col-md-6">
            <c:if test="${globalMessage != null}">
                <c:if test="${alertCode == 'OK'}">
                    <div class="alert alert-success alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            ${globalMessage}
                    </div>
                    <br>
                </c:if>
                <c:if test="${alertCode == 'FAIL'}">
                    <div class="alert alert-danger alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            ${globalMessage}
                    </div>
                    <br>
                </c:if>
            </c:if>

            <form:form class="form-horizontal" action="/profile/edit" method="PUT" modelAttribute="user">
                <input type="hidden" name="_method" value="PUT"/>

                <fieldset>
                    <legend>Edit User Profile</legend>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="email">Email</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" path="email" name="email" id="email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="password">Password</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="password" path="password" name="password" id="password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="name">Name</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="text" path="name" name="name" id="name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="surname">Surname</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="text" path="surname" name="surname"
                                        id="surname"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="country">Country</label>

                        <div class="col-md-8">
                            <form:select class="form-control input-md" path="country" name="country" id="country" items="${countries}"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="city">City</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="text" path="city" name="city" id="city"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="address">Address</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="text" path="address" name="address" id="address"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="zipCode">Zip code</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="text" path="zipCode" name="zipCode" id="zipCode"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="phoneNumber">Phone number</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="text" path="phoneNumber" name="phoneNumber" id="phoneNumber"/>
                        </div>
                    </div>
                    <input class="btn btn-info pull-right" type="submit" value="Save changes" name="editUser" id="editUser"/>
                </fieldset>

                <form:hidden path="id"/>
                <form:hidden path="role"/>
                <form:hidden path="active"/>
                <form:hidden path="permissions"/>
            </form:form>
        </div>
    </div>
</t:mainPage>