<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>

<title>Add account</title>

<t:mainPage>
    <div class="container">
        <div class="col-md-offset-3 col-md-6">
            <form:form class="form-horizontal"
                       name="addAccount"
                       onsubmit="return checkiban(addAccount.iban.value);"
                       id="addAccount"
                       action="/bank/addNewAccount"
                       method="post"
                       modelAttribute="account">

                <c:if test="${error != null}">
                    <div class="alert alert-danger">
                            ${error}
                    </div>
                </c:if>

                <div class="form-group">
                    <label class="control-label col-md-4" for="selectProposal">Proposals</label>

                    <div class="col-md-8">
                        <select class="form-control input-md" name="bankProposalId" id="selectProposal">
                            <c:forEach var="proposal" items="${proposals}">
                                <option value="${proposal.id}">
                                        ${proposal.currencyName} ---- ${proposal.commission}% commission
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-4" for="iban">
                        <b class="text-danger">*</b>
                        IBAN
                    </label>

                    <div class="col-md-8">
                        <input class="form-control input-md" name="iban" type="text" id="iban">
                    </div>
                </div>

                <input name="bankId" value="${bankId}" hidden="hidden"/>

                <div class="form-group">
                    <label class="control-label col-md-4" for="submit"></label>

                    <div class="col-md-8">
                        <input class="btn btn-info" type="submit" value="Add account" id="submit">
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</t:mainPage>

<script>
    $('#addAccount').validate({
        rules: {
            iban: {
                required: true,
                minlength: 15,
                maxlength: 34
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

