<%@tag description="ErrorPage Wrapper" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <style>
        .centered-alert {
            width: 100%;
            text-align: center;
            color: #a94442;
            height: 600px;
            position: fixed;
            top: 15%;
            margin-left: -50px;
            margin-top: -50px;
        }
        body {
            padding-top: 60px;
            padding-bottom: 60px;
        }
    </style>
</head>
<body>
<jsp:doBody/>
<div class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
        <p class="navbar-text">&copy; East Payment Service, 2014</p>
    </div>
</div>
</body></html>