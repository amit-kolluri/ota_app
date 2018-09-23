<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Test ${testName}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/static/css/landing.css"/>" />
<script src="main.js"></script>
</head>
<body>
	
	<script>
		var minutes = ${timeAllotted.minutes};
		var seconds = ${timeAllotted.seconds};
		function timer() {
			if (--seconds == -1) {
				seconds = 59;
				--minutes;
			}

			document.getElementById("minutes").value = minutes;
			document.getElementById("seconds").value = seconds;

			if (seconds == "0" && minutes == "0") {
				//end of test, disable stuff???
				//document.getElementById('CAPITAL OF USA').style.backgroundColor = "#000000";
				document.getElementById('test-submit-button').click();
			} else {
				window.setTimeout("timer()", 1000);
			}
		}
	</script>

	<div class="wrapper">
		<div class="container">
        <%@include file="../../common/header-default.jsp"%>
        <div class="content">
         <%@include file="../../trainer/sidebar-trainer.jsp"%>
        <div class="ota-main_container">
		<h1 class="content_header"></h1>
		<!-- <div class="content_container"> -->

		<div class="mainContent-test-v3">
			<div class="mainContent_header-test-v3">
				<c:choose>
					<c:when test="${testName != null}">
						<h2 class="mainContent_header--testName-test-v3">${testName}</h2>
					</c:when>
					<c:otherwise>
						<h2 class="mainContent_header--testName-test-v3">Test Name
							Default</h2>
					</c:otherwise>
				</c:choose>
				<h2 class="mainContent_header--timeRemaining-test-v3">
					Time Remaining: <input type="text" id="minutes" name="minutes"
						value="0" size="1"
						style="border: 0; text-align: center; font-weight: bold">:
					<input type="text" id="seconds" name="seconds" value="0" size="1"
						style="border: 0; font-weight: bold">
				</h2>
			</div>
			<form:form class="mainContent_test-test-v3"
				action="./processTest.htm" method="post"
				modelAttribute="UserAnswerCommand"
				id="actualTestForm">
				<c:forEach varStatus="vs" items="${questionList}" var="question">
					<div class="mainContent_test--questions-test-v3"
						id="${question.question}">
						<h2>${question.question}</h2>
						<div class="mainContent_test--questions--question-test-v3">
							<form:radiobutton path="userAnswer[${vs.index}]"
								value="${question.option1}"
								onclick="changeColor('${question.question}')" />${question.option1}
							<br>
						</div>
						<div class="mainContent_test--questions--question-test-v3">
							<form:radiobutton path="userAnswer[${vs.index}]"
								value="${question.option2}"
								onclick="changeColor('${question.question}')" />${question.option2}
							<br>
						</div>
						<div class="mainContent_test--questions--question-test-v3">
							<form:radiobutton path="userAnswer[${vs.index}]"
								value="${question.option3}"
								onclick="changeColor('${question.question}')" />${question.option3}
							<br>
						</div>
						<div class="mainContent_test--questions--question-test-v3">
							<form:radiobutton path="userAnswer[${vs.index}]"
								value="${question.option4}"
								onclick="changeColor('${question.question}')" />${question.option4}
							<br>
						</div>
					</div>
				</c:forEach>
			</form:form>

				<p id="confirmClickPopup">Confirm Submission</p>
				<input id="test-submit-button"
					class="mainContent_test--submit-test-v3" type="submit"
					value="Submit" onclick="popup()">
		</div>
		</div>
		</div>
		</div>
		<section class="icons">
            <div class="icon-box"></div>
        </section>
	</div>

	<!-- 
	<div class="footer-test-v3">
		<h3>Copyright OTA_Yash</h3>
	</div>
	-->
	<script type="text/javascript">
		function changeColor(id) {
			document.getElementById(id).style.backgroundColor = "rgb(74, 143, 161, 0.65)";
		}
	</script>
	<script type="text/javascript">
		var clicked = false;
		function popup() {
			if(clicked) {
				document.getElementById("actualTestForm").submit();
			} else {
				clicked = true;
				document.getElementById("confirmClickPopup").style.display = "block";
			}
		}
	</script>

	<script>
		timer();
	</script>
</body>
</html>