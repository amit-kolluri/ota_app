<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
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
<s:url var="logo" value="/static/images/yash-logo.svg" />
<s:url var="url_style" value="/static/css/landing.css" />
<link rel="stylesheet" href="${url_style}">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<header class="header">
			<img src="${logo}" alt="company logo" class="logo">
			<nav class="header-signin">
				<ul class="header-signin_ul">
					<a href="./userLogin.htm"><li class="header-signin_li" id="sign-up">Sign-In</li></a>
					
				</ul>
			</nav>
		</header>
</body>
</html>