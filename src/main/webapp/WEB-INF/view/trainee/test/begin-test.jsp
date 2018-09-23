<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${test.testName} Instructions</title>
<meta name="viewport" content="w1th=device-w1th, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Raleway%7CUbuntu"
	rel="stylesheet">
<link rel="stylesheet" href="static/css/landing.css">


</head>

<body>
	<div class="container">
		<%@include file="../../common/header-default.jsp"%>
		<div class="content">
			<%@include file="../sidebar-trainee.jsp"%>
			<div class="ota-main_container-dashboard">

				<h1>TEST INSTRUCTIONS</h1>

				<div class="ota-main-content">

					<div style="font-size: 1.5rem; color: white; text-align: left; width: 50%; margin: 0 auto;">
						<strong>Test: </strong>${test.testName}<br> <strong>Technology:
						</strong>${test.technology_name}<br> <strong>Duration: </strong>
						<fmt:formatDate pattern="mm" value="${test.timeAllotted}" />
						minutes.<br> <strong>Total Questions: </strong>${test.num_questions}<br>
						<strong>Instructions: </strong> Click the button below to begin
						this test.<br> All questions will be displayed on the same
						page, press the submit button at the bottom of the test to submit
						your answers.<br> If the test is not completed in the time
						allotted all answers will automatically be submitted.
					</div>
					<form action="./questions.htm" method="post">
						<input type="submit" value="Begin">
					</form>
				</div>
			</div>
		</div>
		<section class="icons">
			<div class="icon-box"></div>
		</section>

	</div>

	<%@include file="../../common/footer.jsp"%>
</body>
</html>