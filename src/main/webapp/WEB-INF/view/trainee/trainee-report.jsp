<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--TRAINEE REPORTS DEFAULT-->

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Trainee Report</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value = "/static/css/landing.css"/>">
<link rel="stylesheet"
	href="<c:url value = "/static/css/trainee/trainee-report.css"/>">


<!-- Resources -->

<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/serial.js"></script>
<script
	src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
<link rel="stylesheet"
	href="https://www.amcharts.com/lib/3/plugins/export/export.css"
	type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/trainee-report-chart.js"/>"></script>
</head>

<body>
	<div class="container">
		<%@include file="/WEB-INF/view/common/header-default.jsp"%>
		<div class="content">
			<%@include file="./sidebar-trainee.jsp"%>

			<div class="chart-container">
				<div>
					<!-- ALL OTHER CONTENT HERE -->
					<h1>My Reports</h1>
					<select id="selectData" onChange="chartChange(this);">
						<c:forEach var="tech" items="${techListForUser}">
							<c:set value="${tech.getId()}" var="techId"></c:set>
							<c:set value="${sortedResults.get(techId)}" var="userResults"></c:set>
							<option value=${tech.getId()}|${userResults}>
								${tech.getName()}</option>
						</c:forEach>
					</select>
				</div>

				<h2 id="chartTitle" style="text-align: center"></h2>
				<div id="trainee-chart-div"></div>

			</div>

		</div>
		<section class="icons">
		<div class="icon-box"></div>
		</section>

	</div>

	<%@include file="/WEB-INF/view/common/footer.jsp"%>
</body>
</html>
