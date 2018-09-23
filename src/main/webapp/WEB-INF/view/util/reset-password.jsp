<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <s:url var="url_style" value="/static/css/style.css" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Reset Password</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <link rel="stylesheet" href="${url_style}">
</head>

<body>
     <%@ include file="../common/header-default.jsp" %> 
        <div id="header"></div>
            <h1 class="content_header"></h1>
            <div class="Reset-Password__Container">
                <fieldset>
                <h1 class = "Notmatch_warning"> ${param.err}</h1>
                    <div class="Reset-Container_Padding">
                        <h1>Reset Password</h1>
                          <div class="Password-Form__Container">
                             <form:form action="processpasswordchanged.htm" method="post" modelAttribute="command">
                            
                               New Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="password">
                                <br>
                                
                                <br>
                                &nbsp;&nbsp;Confirm Password: <input type="text" name="confirmPassword">
                                <br>
                                 <form:errors  path="password" />
                                <div id="Reset-Password__Submit-Button">							
                                    <input id="Reset-Submit__Hover-Effect" type="Submit" value="Submit">
                                </div>
                            </form:form>
                        </div>
                    </div>
                </fieldset>
            </div>          
     <%@include file="../common/footer.jsp"%>
</body>

</html>
