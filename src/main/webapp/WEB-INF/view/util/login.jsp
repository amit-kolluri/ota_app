<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
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
<title>Login</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../static/css/landing.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Raleway%7CUbuntu"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">

<s:url var="url_style" value="/static/css/landing.css" />
<s:url var="logo" value="/static/images/yash-logo.svg" />
<s:url var="java" value="/static/images/png/java.png" />
<s:url var="leaf" value="/static/images/png/leaf.png" />
<s:url var="cloud" value="/static/images/png/cloud.png" />
<s:url var="server" value="/static/images/png/server.png" />
<s:url var="html" value="/static/images/png/html.png" />
<s:url var="css" value="/static/images/png/css.png" />
<s:url var="github" value="/static/images/png/github.png" />
<link rel="stylesheet" href="${url_style}">
<title>OTA</title>

</head>

<body>
	<div class="container">
	 <%@include file="../common/header-register.jsp" %>
		<main class="content">
		 <%@include file="../landing/sidebar-landing.jsp" %>
			<div class="ota-main">
			<h1 class="ota-main_h1">Yash Online Test Application</h1>
			<h2 class="ota-main_h2">OTA is an initiative to automate training at YASH</h2>
			<p class="ota-main_p">Yash OTA was designed and developed by the
				Moline-June-2018 batch under instructor Sharma Pankaj. The objective
				of this application is to automate the process of trainee's
				evaluation during their training period.</p>
			<div class="ota-main_feature-boxes">
				<div class="feature-box">
			
					<h3 class="heading-tertiary u-margin-bottom-small">JAVA</h3>
					<img src="${java}" class="icons-box_icon">
					<img src="${leaf}" class="icons-box_icon">
					 
				</div>
				<div class="feature-box">
					
					<h3 class="heading-tertiary u-margin-bottom-small">UI/UX</h3>
					
					<img src="${html}" class="icons-box_icon"> 
					 <img src="${css}" class="icons-box_icon">
					
				</div>
				<div class="feature-box">
					
					<h3 class="heading-tertiary u-margin-bottom-small">DATABASE</h3>
					<img src="${cloud}" class="icons-box_icon"> 
					<img src="${server}" class="icons-box_icon">
				</div>
			</div>
		</div>
		<div class="ota-main_login">
			<h1>Login</h1>
			<form:form action="./processLogin.htm" class="login_form"
					method="post" modelAttribute="command">
					<h3>${errMsg}</h3>
					<div class="login_table">
						<table class="table_content">
							<tr>
								<td><label class="table_label"><p>Email:</p></label> <form:input
										path="email" class="table_input" /></td>
							</tr>
							<tr>
								<td><label class="table_label"><p>Password:</p></label> <form:password
										path="password" class="table_input" />
								</td>
							</tr>
							<tr>
								<td><a class="table_button" href="./forgotpassword.htm"><p id="forgot-password">Forgot Password?</p></a></td>
							</tr>
							<tr>
								<td><input class="table_button" type="submit" value="Login">
								</td>
							</tr>
						</table>
					</div>
				</form:form>

		</div>
		</main>
		<section class="icons">
			<div class="icon-box">
				 
			</div>
		</section>
	</div>
	<%@include file="../common/footer.jsp" %>
</body>

</html>

