<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->

<head>
<s:url var="url_style" value="/static/css/landing.css" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="${url_style}">


<script>
	function getOption1() {
		document.getElementById("radio1").value = document
				.getElementById("option1id").value;
	}

	function getOption2() {
		document.getElementById("radio2").value = document
				.getElementById("option2id").value;
	}

	function getOption3() {
		document.getElementById("radio3").value = document
				.getElementById("option3id").value;
	}

	function getOption4() {
		document.getElementById("radio4").value = document
				.getElementById("option4id").value;
	}
	function radioError() {
		if (!document.getElementById("radio1").checked
				&& !document.getElementById("radio2").checked
				&& !document.getElementById("radio1").checked
				&& !document.getElementById("radio1").checked) {
			alert("Please select the correct answer");
		}
	}
</script>
</head>

<body>
	<div class="wrapper">
		<div id="header">
			<%@include file="../../common/header-default.jsp"%>
		</div>
		<div id="sidebar">
			<%@include file="../sidebar-trainer.jsp"%>
		</div>
		<section class="content">
			<h1 class="content_header"></h1>
			<div class="content_container">
				<div class="questioncreatetrainer">

					<div class="questiontrainer_header">
						<h2>Questions</h2>
					</div>

					<div class="questiontrainer_box">
						<div class="questiontrainer_box_top">
							<div class="questiontrainer_box_testname">
								<p>Select Test Name</p>
							</div>

							<div class="questiontrainer_box_dropdown">
								<form:form action="/trainer/question/insert.htm"
									modelAttribute="command" method="POST">
									<form:errors path="*" id="error" cssClass="error" element="div" />
									<select class="questiontrainer_box_select" name="topicId">
										<option value="0" disabled selected>Test Name</option>
										<c:forEach items="${topicList}" var="topic">
											<option value="${topic.id}">"${topic.name}"</option>
										</c:forEach>

									</select>
									<form:errors path="topicId" />
									<br>
									<br>
							</div>
						</div>
						<hr class="questiontrainer_box_hr">
						<div class="questiontrainer_box_bottom">
							<div class="questiontrainer_box_question">
								<textarea class="questiontrainer_createquestion" name="question"
									placeholder="Question"></textarea>
							</div>

							<div class="questiontrainer_box_answers">


								<div class="questiontrainer__radiobuttons">
									<input class="inputButton" type="radio" value="radioerror"
										name="radioanswer" id="radio1" onclick="getOption1()" required>
									<textarea class="questiontrainer_box_answers_1" id="option1id"
										name="option1" placeholder="Option 1"></textarea>
									<form:errors path="option1" />
									</input> <input class="inputButton" type="radio" value="radioerror"
										name="radioanswer" id="radio2" onclick="getOption2()">
									<textarea class="questiontrainer_box_answers_1" id="option2id"
										name="option2" placeholder="Option 2"></textarea>
									<form:errors path="option2" />
									</input> <input class="inputButton" type="radio" value="radioerror"
										name="radioanswer" id="radio3" onclick="getOption3()">
									<textarea class="questiontrainer_box_answers_1" id="option3id"
										name="option3" placeholder="Option 3"></textarea>
									<form:errors path="option3" />
									</input> <input class="inputButton" type="radio" value="radioerror"
										name="radioanswer" id="radio4" onclick="getOption4()">
									<textarea class="questiontrainer_box_answers_1" id="option4id"
										name="option4" placeholder="Option 4"></textarea>
									<form:errors path="option4" />
									</input>
								</div>
								<div class="questiontrainer_box_answers_buttonDiv">
									<button type="submit" name="submit" onclick="radioError()">Submit</button>
								</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
	</section>
	</div>
	<div id="footer">
		<%@include file="../../common/footer.jsp"%>
	</div>
	<script src="../static/js/jquery.js"></script>

	<script src="../static/js/main.js"></script>


</body>

</html>