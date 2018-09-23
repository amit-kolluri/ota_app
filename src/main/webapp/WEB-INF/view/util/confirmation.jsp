<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Confirmation</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"href="<c:url value = "/static/css/style.css"/>">
	<script type="text/javascript" src="<c:url value="/static/js/main.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/static/js/jquery.js"/>"></script>
</head>
<body>
	<div id="header">
		<%@include file="../common/header-default.jsp"%>
	</div>
	<section class="registration_wrapper">
			<div class="registration_confirmation">
				<h1 class="confirmation_message">${confirmationMessage}</h1>
				<c:choose>
				    <c:when test="${sessionScope.user.roleId == '3'}">
						<form action="/admin/dashboard.htm" method="get">
						    <input type="submit" value="Home" />
						</form>
				    </c:when>
				    <c:when test="${sessionScope.user.roleId == '2'}">
						<form action="/trainer/dashboard.htm" method="get">
						    <input type="submit" value="Home" />
						</form>
				    </c:when>    
				    <c:otherwise>
						<form action="/trainee/dashboard.htm" method="get">
						    <input type="submit" value="Home" />
						</form>
				    </c:otherwise>
				</c:choose>
			</div>
	</section>
	<div id="footer">
		<%@include file="../common/footer.jsp" %>
	</div>
</body>
</html>