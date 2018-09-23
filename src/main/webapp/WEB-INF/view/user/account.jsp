<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
<title>Account - ${sessionScope.user.firstName}</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value = "../static/css/landing.css"/>">
<link rel="stylesheet"
	href="<c:url value = "../static/css/user/profile.css"/>">
</head>
<body>
	<div class="container">
		<%@include file="../common/header-default.jsp"%>
		<div class="content">
			<%@include file="../common/sidebar.jsp"%>
			<div class="ota-main_container">
			<div class="account_container">
				<h1 class="content_header account_header">Account</h1>
				<div>
					<span class="errorMessage"> ${errorMessage} </span>
					<div class="list_container">
					<div>
						<ul class="ul_style">
							<li class="ul_li">${sessionScope.user.firstName}
								${sessionScope.user.lastName}</li>
							<li class="ul_li">${sessionScope.user.email}</li>
							<li class="ul_li">Group: ${sessionScope.user.batchId}</li>
							<li class="ul_li"><a href="./update-account.htm">Change Account Details</a></li>
							<li class="ul_li"><a href="./reset-password.htm">Reset Password</a></li>
						</ul>
					</div>
					</div>
					<form method="POST" action="./processProfilePicture.htm"
						enctype="multipart/form-data">
						<input type="file" name="file"><input
							type="submit" value="Upload">
					</form>
				</div>
			</div>
			</div>
		</div>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>