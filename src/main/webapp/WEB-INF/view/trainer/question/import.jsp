<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--TRAINEE REPORTS DEFAULT-->

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Trainer Import Questions</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Resources -->
<link rel="stylesheet" href="<c:url value = "/static/css/landing.css"/>">
<script>
var technologies = [
	<c:forEach var="tech" items="${technologies}" varStatus="techStatus">
		{
			id : '${tech.getId()}',
			name :	'${tech.getName()}'
		}
		 <c:if test="${!techStatus.last}">    
         ,    
       </c:if>
	</c:forEach>	
];

var topics = [
	<c:forEach var="topic" items="${topics}" varStatus="topicStatus">
	{
		id : '${topic.getId()}',
		techId: '${topic.getTechnologyId()}',
		name :	'${topic.getName()}'
	}
	 <c:if test="${!topicStatus.last}">    
     ,    
   </c:if>
</c:forEach>	
];

var tests = [
	<c:forEach var="test" items="${tests}" varStatus="testStatus">
	{
		id : '${test.getId()}',
		topicId: '${test.getTopicId()}',
		name :	'${test.getTestName()}'
	}
	 <c:if test="${!testStatus.last}">    
     ,    
   </c:if>
</c:forEach>	
];

</script>
<script type="text/javascript"
	src="<c:url value="/static/js/trainer-question-import.js"/>">
</script>

</head>

<body>
	<div class="container">
		<%@include file="/WEB-INF/view/common/header-default.jsp"%>
		<div class="content">
			<%@include file="/WEB-INF/view/trainer/sidebar-trainer.jsp"%>
			<div class="ota-main_container-dashboard">

				<div>
					<h1>Import Questions</h1>
					<!-- ALL OTHER CONTENT HERE -->
					<c:set var="technologies" value="${technologies}"/>
					<c:set var="topics" value="${topics}"/>
					<c:set var="tests" value="${tests}"/>
					<label>Technology: </label>
					<select id="tech-select" onChange="changeTopicDropdown(this)">
						<c:forEach var="tech" items="${technologies}">
							<option value="${tech.getId()}">${tech.getName()}</option>
						</c:forEach>
					</select>
					<br><br>
					
					<form:form modelAttribute="command" action="./processImport.htm"
						method="POST" enctype="multipart/form-data">
						
						<label>Topic: </label>
						<span>
						<form:select path="topicId" name="topicId" id="topic-select" onChange="changeTestDropdown(this)">
							<option disabled=true>Please select a technology!</option>
						</form:select>
						<form:errors element="div" style="color:red" path="topicId"></form:errors>
						</span>
						<br><br>
						
						<label>Test: </label>
						<span>
						<form:select path="testId" name="testId" id="test-select">
							<option disabled=true>Please select a topic!</option>
						</form:select>
						<form:errors element="div" style="color:red" path="testId"></form:errors>
						</span>
						<br><br>
						
						<form:input path="file" name="file" id="import-file" type="file" />
						<div style="color:red">${fileErrorMsg}</div>
						<br><br>

						<input type="submit" value="import" />
					</form:form>
				</div>
			</div>
		</div>
					<div id="test"></div>

		<section class="icons">
		<div class="icon-box"></div>
		</section>

	</div>
	<%@include file="/WEB-INF/view/common/footer.jsp"%>

</body>
</html>
