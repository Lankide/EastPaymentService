<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loginPage>
    <div class="container">

        <div class="col-md-offset-3 col-md-6">
            <form:form class="form-horizontal" id="loginForm" action="/j_spring_security_check" method="POST">
                <fieldset>
                    <legend>Please sign in</legend>
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            The email or password is incorrect.
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            You have been logged out.
                        </div>
                    </c:if>

                    <div class="form-group">
                        <label class="col-md-4 control-label" for="login">Email</label>

                        <div class="col-md-6">
                                <input id="login" name="login" type="email" value="tor@gmail.com" class="form-control input-md">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="password">Password</label>
                        <div class="col-md-6">
                            <input id="password" name="password" type="password" value="12345678" class="form-control input-md">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="submit"></label>
                        <div class="col-md-4">
                            <button id="submit" name="submit" class="btn btn-info">Sign in</button>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>
    </div>
</t:loginPage>

<script>
    $('#loginForm').validate({
        rules: {
           login: {
                required: true
            },
            password: {
                required: true,
                minlength: 8
            }
        },
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.parent('.form-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });
</script>
