<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Confirm Registration</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/static/css/landing.css">
</head>

<body>

	<section class="registration_wrapper">

		<div class="ota-main">
			<h1 class="ota-main_h1">Thank You For Registering</h1>
			
			<br>
			<div class="registration_confirmation">
				<h2 class="confirmation_message">Once approved, confirmation email will be sent to ${command.email} </h2>
				<br> <br>
				<a href="/users/userLogin.htm"><button>
						<input type="submit" value="Home">
					</button></a>
					<br><br><br><br>
					<h1 class="ota-main_h1">STUDY STUDY STUDY!!!</h1>
			</div>
		</div>
	</section>
	<div id="footer"></div>
</body>

</html>