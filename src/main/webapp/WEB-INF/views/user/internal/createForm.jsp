<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>Create user</title>

<t:mainPage>
<div class="container">
<div class="col-md-offset-3 col-md-6">

    <div class="alert" id="alertMessage" style="display: none;"></div>

    <form:form class="form-horizontal" id="createUserForm" name="createUserForm" modelAttribute="userForm">

        <fieldset>
            <legend>Credentials</legend>
            <div class="form-group">
                <label class="control-label col-md-4" for="email">
                    <b class="text-danger">*</b>
                    Email
                </label>

                <div class="col-md-8">
                    <form:input class="form-control input-md" type="email" path="email" name="email"
                                id="email"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-4" for="password">
                    <b class="text-danger">*</b>
                    Password
                </label>

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
            <legend>Personal Information</legend>
            <div class="form-group">
                <label class="control-label col-md-4" for="name">
                    <b class="text-danger">*</b>
                    Name
                </label>

                <div class="col-md-8">
                    <form:input class="form-control input-md" type="text" path="name" name="name" id="name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-4" for="surname">
                    <b class="text-danger">*</b>
                    Surname
                </label>

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
            <legend>Permissions</legend>
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
            <input class="btn btn-info pull-right" type="button" value="Create user" name="createUser"
                   id="createUser" onclick="ajaxSubmit()"/>
        </fieldset>

    </form:form>
</div>

<script>
    var createUserForm = $("#createUserForm");

    createUserForm.formToWizard({ submitButton: 'createUser' });

    var validator = createUserForm.validate({
        rules: {
            email: {
                required: true
            },
            password: {
                required: true,
                minlength: 8
            },
            name: {
                required: true
            },
            surname: {
                required: true
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            if (element.parent('.form-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });

    function showAlert(alertElement, alertClass, alertMessage) {
        alertElement.toggleClass(alertClass, true);
        alertElement.show().html(alertMessage);
        setTimeout(function () {
            alertElement.toggleClass(alertClass, false);
            alertElement.hide().html('');
        }, 5000);
    }

    function ajaxSubmit() {
        var $alertMessage = $('#alertMessage');
//        alert("Form valid: " + createUserForm.valid());
        if (!createUserForm.valid()) {
            showAlert($alertMessage, "alert-danger", "Form is not valid");
            return;
        }
        var permissions = [];
        $('.permission:checked').map(function () {
            permissions.push($(this).attr('name'));
            $(this).remove();
        });
        var userForm = createUserForm.serialize();
        userForm += '&permissions=' + permissions;
        $.ajax({
            url: '/user',
            type: 'POST',
            contentType: "application/x-www-form-urlencoded",
            dataType: 'json',
            data: userForm,
            success: function (data) {
                showAlert($alertMessage, "alert-success", data.message);
            },
            error: function (data) {
                showAlert($alertMessage, "alert-danger", "User has not been created");
            }
        });
    }
</script>

</div>
</t:mainPage>
