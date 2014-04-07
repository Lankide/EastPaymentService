<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>Create user</title>

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
            <form:form class="form-horizontal" action="/user" method="POST" id="newUser" name="newUser" modelAttribute="user">
                <input type="hidden" name="_method" value="PUT"/>
                <fieldset>
                    <legend>Edit User Credentials</legend>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="email">Email</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" path="email" name="email" id="email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="password">Password</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="password" path="password" name="password"
                                        id="password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="role">Role</label>

                        <div class="col-md-8">
                            <form:select class="form-control input-md" path="role" name="role" id="role"
                                         items="${roles}"/>
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Edit User Personal Information</legend>
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
                            <form:select class="form-control input-md" path="country" name="country" id="country"
                                         items="${countries}"/>
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
                            <form:input class="form-control input-md" type="text" path="address" name="address"
                                        id="address"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="zipCode">Zip code</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="text" path="zipCode" name="zipCode"
                                        id="zipCode"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="phoneNumber">Phone number</label>

                        <div class="col-md-8">
                            <form:input class="form-control input-md" type="text" path="phoneNumber" name="phoneNumber"
                                        id="phoneNumber"/>
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Edit User Permissions</legend>
                    <div class="form-group">
                        <c:forEach var="permission" items="${permissions}">
                            <div class="form-group">
                                <label class="control-label col-md-offset-2 col-md-4" id="${permission}"
                                       for="${permission}"> </label>
                                <script>
                                    function textToSentence(text) {
                                        text = text.toLowerCase().replace(/_/g, ' ');
                                        return text.charAt(0).toUpperCase() + text.slice(1);
                                    }
                                    document.getElementById('${permission}').innerHTML = textToSentence('${permission}');
                                </script>
                                <div class="col-md-6">
                                    <label>
                                        <input type="checkbox" class="checkbox permission" name="${permission}"
                                               data-on-color="success" data-off-color="danger" data-animate="false"/>
                                    </label>
                                </div>
                                <script>
                                    $('[name=${permission}]').bootstrapSwitch();
                                </script>
                            </div>
                        </c:forEach>
                    </div>
                </fieldset>

                <fieldset>
                    <legend>Edit User Status</legend>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="active">Active</label>
                        <div class="col-md-8">
                            <form:checkbox path="active" class="checkbox" id="active" name="active" data-size="large"
                                           value="User status"/>
                                <%--<input type="checkbox" class="checkbox permission" id="active" name="my-checkbox" checked--%>
                                <%--data-on-color="success" data-off-color="danger" data-animate="false" data-on-text="Activated" data-off-text="Deactivated"/>--%>
                                <%--<script>--%>
                                <%--$("[name='my-checkbox']").bootstrapSwitch();--%>
                                <%--</script>--%>
                        </div>
                    </div>
                    <input class="btn btn-info pull-right" type="submit" value="Save changes" name="createUser" id="createUser"/>
                        <%--onclick="ajaxSubmit()"--%>
                </fieldset>

            </form:form>
        </div>
        <script>
            $("#newUser").formToWizard({submitButton: 'createUser'});
        </script>
        <script>
            function ajaxSubmit() {
                $("#newUser").submit(function (evt) {
                    evt.preventDefault();
                    var permissions = [];
                    $('.permission:checked').map(function () {
                        permissions.push($(this).attr('name'));
                        $(this).remove();
                    });
                    var user = $("#newUser").serialize();
                    user += '&permissions=' + permissions;
                    $.ajax({
                        url: '/user',
                        type: 'PUT',
                        data: user,
                        dataType: 'json'
                    });
                });
            }
        </script>
        <script>
            $('#dimension-switch').bootstrapSwitch('size', 'large');
        </script>
    </div>
</t:mainPage>