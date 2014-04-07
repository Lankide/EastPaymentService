<%@tag description="Main Wrapper Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <%--<link href="${pageContext.request.contextPath}/resources/css/ui.multiselect.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/resources/css/ui.jqgrid.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-switch.css" rel="stylesheet">

    <style>
        <%-- Main theme style --%>

        .btn {
            max-width: 150px;
            min-width: 150px;
        }
        body {
            padding-top: 60px;
            padding-bottom: 60px;
        }

        <%-- Bootstrap switch style (create user form) --%>

        .bootstrap-switch {
            max-height: 30px;
            min-height: 30px;
        }

        <%-- jqGrid style --%>

        .table tbody tr:hover td {
            background-color: #39b3d7;
            border-color: #269abc;
            color: #ffffff;
        }
        input.ui-pg-input {
            width: auto;
            padding: 0px;
            margin: 0px;
            line-height: normal
        }
        select.ui-pg-selbox {
            width: auto;
            padding: 0px;
            margin: 0px;
            line-height: normal
        }
        .jqgfirstrow {
            display: none;
        }
        :focus {
            outline: none;
        }
    </style>

    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/additional-methods.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/form-to-wizard.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-switch.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/checkiban.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/grid.locale-en.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.jqGrid.src.js"></script>

</head>
<body>

<c:set value="${sessionScope.authorizedUser.role}" var="role"/>

<c:choose>
    <c:when test="${role == 'ROLE_EXTERNAL_USER'}">
        <c:set value="/external" var="homePage"/>
    </c:when>
    <c:when test="${role == 'ROLE_INTERNAL_USER'}">
        <c:set value="/internal" var="homePage"/>
    </c:when>
</c:choose>

<c:set value="${sessionScope.authorizedUser.permissions}" var="permissions"/>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="${homePage}">Home</a></li>
                <li>
                    <c:if test="${role == 'ROLE_INTERNAL_USER'}">
                        <a href="/company">Company</a>
                    </c:if>
                </li>
                <c:if test='${fn:contains(permissions, "create_user") or
                              fn:contains(permissions, "view_all_users")}'>
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown">User<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <c:if test='${fn:contains(permissions, "create_user")}'>
                                <li><a href="/user">Create</a></li>
                            </c:if>
                            <c:if test='${fn:contains(permissions, "search_user")}'>
                                <li class="divider"></li>
                                <li><a href="/user/advancedSearch">Find</a></li>
                            </c:if>
                            <c:if test='${fn:contains(permissions, "view_all_users")}'>
                                <li class="divider"></li>
                                <li><a href="#">View all</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test='${fn:contains(permissions, "add_bank") or
                              fn:contains(permissions, "view_bank_info")}'>
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown">Bank<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <c:if test='${fn:contains(permissions, "add_bank")}'>
                                <li><a href="/bank/new">Add</a></li>
                            </c:if>
                            <c:if test='${fn:contains(permissions, "view_bank_info")}'>
                                <li class="divider"></li>
                                <li><a href="/bank">View all</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user"></span>
                        ${sessionScope.authorizedUser.name}
                        ${sessionScope.authorizedUser.surname}
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/profile/edit">Edit profile</a></li>
                        <li class="divider"></li>
                        <li><a href="/logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<jsp:doBody/>

<div class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
        <p class="navbar-text">&copy; East Payment Service, 2014</p>
    </div>
</div>
</body>
</html>