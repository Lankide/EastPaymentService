<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>

<title>Bank enrollment</title>

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

            <form:form class="form-horizontal" id="addBank" action="/bank/new" name="addBank" method="POST" modelAttribute="bank">
                <div class="form-group">
                    <label class="control-label col-md-4" for="name">
                        <b class="text-danger">*</b>
                        Bank name
                    </label>
                    <div class="col-md-8">
                        <input class="form-control input-md" name="name" type="text" id="name">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-4" for="country">Country</label>
                    <div class="col-md-8">
                        <form:select class="form-control input-md" path="country" name="country" id="country" items="${countries}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-4" for="submit"></label>
                    <div class="col-md-8">
                        <input class="btn btn-info" id="submit" type="submit" value="Add bank">
                    </div>
                </div>
            </form:form>
        </div>
    </div>

</t:mainPage>

<script>
    $('#addBank').validate({
        rules: {
            name: {
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
</script>