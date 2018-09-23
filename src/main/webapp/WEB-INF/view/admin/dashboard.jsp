
<!-- Author: Geon Gayles -->
<%@page import="com.yash.ota.serviceimpl.QuestionServiceImpl"%>
<%@page import="com.yash.ota.service.QuestionService"%>
<%@page import="com.yash.ota.serviceimpl.BatchServiceImpl"%>
<%@page import="com.yash.ota.service.BatchService"%>
<%@page import="com.yash.ota.domain.User"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Trainer Dashboard</title>
<meta name="viewport" content="w1th=device-w1th, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Raleway%7CUbuntu"
	rel="stylesheet">
<link rel="stylesheet" href="static/css/landing.css">


</head>

<body>
	<div class="container">
		<%@include file="../common/header-default.jsp"%>
		<div class="content">
			<%@include file="sidebar-admin.jsp"%>
			<div class="ota-main_container-dashboard">

				<h1>ADMIN DASHBOARD</h1>

				<div class="ota-main_dashboard_feature-boxes">

					<div class="feature-box_dashboard">
						<a href="/admin/trainees.htm"></a>
						<h3 class="heading-tertiary u-margin-bottom-small">TRAINEES</h3>
						
						<p>
							<c:out value="${numberOfTrainees} Trainees Available" />
						</p>
					</div>

					<div class="feature-box_dashboard">
						<a href="/admin/report/overall/chart.htm"></a>
						<h3 class="heading-tertiary u-margin-bottom-small">REPORTS</h3>
						
					</div>

					<div class="feature-box_dashboard">
						<a href="/admin/batches.htm"></a>
						<h3 class="heading-tertiary u-margin-bottom-small">BATCHES</h3>

						<p>  ${numberOfBatches} Batches Available</p>


					</div>
					
					<div class="feature-box_dashboard">
						<a href="/admin/tests.htm"></a>
						<h3 class="heading-tertiary u-margin-bottom-small">TESTS</h3>
						<p>${numberOfTests} Tests Available</p>

					</div>

				</div>
			</div>
			</div>
			<section class="icons">
				<div class="icon-box"></div>
			</section>

		</div>
		
		<%@include file="../common/footer.jsp"%>
</body>
</html>