<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title></title>
<t:mainPage>
    <div class="container">
        <div class="col-md-offset-1 col-md-10">

            <c:if test="${message != null}">
                <div class="alert alert-success">
                        ${message}
                </div>
            </c:if>

            <table class="table">
                <thead>
                <tr>

                    <th>Bank name</th>
                    <th>Location</th>
                    <th>Country code</th>
                    <th>Active</th>
                </tr>
                </thead>

                <tbody>
                <tr>

                    <td>${bank.name}</td>
                    <td>${bank.country.name}</td>
                    <td>${bank.country.code}</td>
                    <td>${bank.active}</td>
                </tr>
                </tbody>
            </table>

            <table class="table">
                <thead>
                <tr>

                    <th>Currency</th>
                    <th>Commission</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="proposal" items="${bank.proposals}">
                    <tr>

                        <td>${proposal.currency.currencyName}</td>
                        <td>${proposal.commission}%</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <table class="table">
                <thead>
                <tr>

                    <th>IBAN</th>
                    <th>Active</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="account" items="${bank.accounts}">
                    <tr>

                        <td>${account.iban}</td>
                        <td>${account.active}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="col-md-offset-2 col-md-8">
                <div class="col-md-6">
                    <form:form class="form-horizontal" id="addProposal" action="/bank/addProposal" method="get">
                        <div class="form-group">
                            <input name="bankId" value="${bank.id}" hidden="hidden"/>
                            <input class="btn btn-info pull-left" type="submit" value="Add proposal">
                        </div>
                    </form:form>
                </div>

                <div class="col-md-6">
                    <form:form class="form-horizontal" id="addAccount" action="/bank/addAccount" method="get">
                        <div class="form-group">
                            <input name="bankId" value="${bank.id}" hidden="hidden">
                            <input class="btn btn-info pull-right" type="submit" value="Add account">
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</t:mainPage>