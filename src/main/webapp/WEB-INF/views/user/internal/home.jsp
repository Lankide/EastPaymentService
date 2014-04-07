<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title>User home</title>

<input type="hidden" id="status" value="${user.active}">
<t:mainPage>
    <div class="container">
        <div class="col-md-offset-2"></div>
        <form:form action="/user/search" method="get">
            <div class="form-group">

                <div class="col-md-4">
                    <input class="form-control input-md" type="text" name="name" placeholder="Username">
                </div>
                <div class="col-md-2">
                    <input class="btn btn-info" type="submit" value="Find"/>
                </div>
            </div>
        </form:form>
        <form:form action="/user/advancedSearch" method="get">
            <div class="form-group">
                <div class="col-md-2">
                    <input class="btn btn-info" type="submit" value="Advanced Search"/>
                </div>
            </div>
        </form:form>
        <div class="col-md-offset-2"></div>

        <div>
            <br>
            <br>
            <br>
            <br>

            <div>
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
            </div>

            <c:if test="${users != null}">
                <table class="table table-striped">
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Country</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <input type="hidden" id="status" value="${user.active}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>${user.country}</td>

                            <td class="col-sm-1 col-md-1">
                                <a href="/user/${user.id}" style="text-decoration: none">
                                    <button type="button" class="btn btn-primary btn-block"><i
                                            class="glyphicon glyphicon-pencil"></i> Edit
                                    </button>
                                </a>
                            </td>

                            <c:choose>
                                <c:when test="${user.active == true}">
                                    <td class="col-sm-1 col-md-1">
                                        <button type="button" class="btn btn-danger btn-block"><i
                                                class="glyphicon glyphicon-remove"></i> Suspend
                                        </button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td class="col-sm-1 col-md-1">
                                        <button type="button" class="btn btn-success" onclick="callOnClick(${user.id})">
                                            <i class="glyphicon glyphicon-ok"></i> Activate
                                        </button>
                                    </td>
                                </c:otherwise>
                            </c:choose>

                            <script>
                                function callOnClick(id) {
                                    var postRequest = new ajaxRequest()
                                    postRequest.onreadystatechange = function () {
                                        if (postRequest.readyState == 4) {
                                            if (postRequest.status == 200 || window.location.href.indexOf("http") == -1) {
                                                document.getElementById("result").innerHTML = postRequest.responseText
                                            }
                                            else {
                                                alert("An error has occured making the request")
                                            }
                                        }
                                    }
                                    var status = encodeURIComponent(document.getElementById("status").value)
                                    var userId = id
                                    var parameters = "status=" + status + "&id=" + userId
                                    postRequest.open("POST", "/user/status", true)
                                    postRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
                                    postRequest.send(parameters)
                                }
                                function ajaxRequest(){
                                    var activexmodes=["Msxml2.XMLHTTP", "Microsoft.XMLHTTP"]
                                    if (window.ActiveXObject){
                                        for (var i=0; i<activexmodes.length; i++){
                                            try{
                                                return new ActiveXObject(activexmodes[i])
                                            }
                                            catch(e){
                                                //suppress error
                                            }
                                        }
                                    }
                                    else if (window.XMLHttpRequest)
                                        return new XMLHttpRequest()
                                    else
                                        return false
                                }
                            </script>

                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
</t:mainPage>

