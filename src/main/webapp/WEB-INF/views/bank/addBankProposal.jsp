<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>

<title>Add proposal</title>

<t:mainPage>

    <div class="container">

        <div class="col-md-offset-3 col-md-6">
            <form:form class="form-horizontal"
                       id="addProposal"
                       action="/bank/addNewProposal"
                       method="post"
                       modelAttribute="proposal">

                <div class="form-group">
                    <label class="control-label col-md-4" for="currency">Currency</label>

                    <div class="col-md-8">
                        <select class="form-control input-md" name="currencyCode" id="currency">
                            <c:forEach var="currency" items="${currencies}">
                                <option value="${currency.currencyCode}">${currency.currencyName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-4" for="commission">
                        <b class="text-danger">*</b>
                        Commission
                    </label>

                    <div class="col-md-8">
                        <input class="form-control input-md" id="commission" name="commission" type="text">
                    </div>
                </div>

                <input name="bankId" value="${bankId}" hidden="hidden"/>

                <div class="form-group">
                    <label class="control-label col-md-4" for="submit"></label>

                    <div class="col-md-8">
                        <input class="btn btn-info" type="submit" value="Add proposal" id="submit">
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</t:mainPage>

<script>
    $('#addProposal').validate({
        rules: {
            commission: {
                required: true,
                number: true,
                max: 100,
                min: 0
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