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
<title>Reset Password - ${sessionScope.user.firstName}</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value = "../static/css/landing.css"/>">
<link rel="stylesheet"
	href="<c:url value = "../static/css/table/table.css"/>">
<link rel="stylesheet"
	href="<c:url value = "../static/css/util/register.css"/>">
<link rel="stylesheet"
	href="<c:url value = "../static/css/user/profile.css"/>">
</head>

<body>
	<div class="container">
		<%@include file="../common/header-default.jsp"%>
		<div class="content">
			<%@include file="../common/sidebar.jsp"%>
			<div class="ota-main_container">
				<section class="registration_wrapper">
					<div class="registration">
						<form:form action="./processResetPassword.htm" method="post"
							modelAttribute="command">
							<div class="registration_table" style="border-style: none">
								<table class="table_content">
									<tr>
										<td>
											<h1 class="table_title">Reset Password</h1>
										</td>
									</tr>
									<tr>
										<td class="errorMessage">${errorMessage}</td>
									</tr>
									<tr>
										<td><label class="table_label">Password</label> <form:password
												class="table_input" path="password" id="password" /> <form:errors
												element="div" class="registereduser_error" path="password" />
										</td>
									</tr>
									<tr>
										<td><label class="table_label">Confirm Password</label> <form:password
												class="table_input" path="confirmPassword"
												id="confirmPassword" /> <form:errors element="div"
												class="registereduser_error" path="confirmPassword" /></td>
									</tr>
									<tr>
										<td><input class="table_button" type="submit"
											value="Submit"></td>
									</tr>
								</table>
							</div>
						</form:form>
					</div>
				</section>
			</div>
		</div>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>
