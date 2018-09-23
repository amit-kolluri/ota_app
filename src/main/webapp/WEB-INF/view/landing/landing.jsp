<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<header class="header">
			<img src="${logo}" alt="company logo" class="logo">
			<nav class="header-signin">
				<ul class="header-signin_ul">
					
					<a href="./userRegistration.htm"><li class="header-signin_li" id="sign-up">Register</li></a>
				</ul>
			</nav>
		</header>
		<main class="content">
		<div class="ota-aside"></div>
		<div class="ota-main">
			<h1 class="ota-main_h1">Yash Online Test Application</h1>
			<h2>OTA is an initiative to automate training at YASH</h2>
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
			<form>
				<p>Email:</p>
				<input type="text" name="firstname" value=""
					id="ota-main_login-input"> <br>
				<p>Password:</p>
				<input type="text" name="lastname" value=""
					id="ota-main_login-input"> <br> <br> <input
					class="ota-main_login-btn" type="submit" value="Submit"
					id="ota-main_login-input"> <a><h3>Forgot Password?</h3></a>
			</form>
		</div>
		</main>
		<section class="icons">
			<div class="icon-box">

				<img src="${java}" class="icons-box_icon"> <img src="${leaf}"
					class="icons-box_icon"> <img src="${cloud}"
					class="icons-box_icon"> <img src="${server}"
					class="icons-box_icon"> <img src="${html}"
					class="icons-box_icon"> <img src="${css}"
					class="icons-box_icon"> <img src="${js}"
					class="icons-box_icon"> <img src="${github}"
					class="icons-box_icon">
					
				<a id="ota-creators-modal-link" href="#ota-creators-modal"><button id="ota-creators-button">OTA Creators</button></a>
				<div id="ota-creators-modal" class="modalDialog">
					<%@include file="/WEB-INF/view/util/ota-creators.jsp"%>
				</div>

			</div>
		</section>
	</div>
	<footer>
		<div class="footer-nav">
			<p class="copyright">Copyright © 2018. YASH Technologies. All
				Rights Reserved.</p>
		</div>
	</footer>
</body>

</html>