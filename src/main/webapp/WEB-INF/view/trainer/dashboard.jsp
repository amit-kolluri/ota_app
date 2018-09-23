<!-- Author: Geon Gayles -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Trainer Dashboard</title>
<meta name="viewport" content="w1th=device-w1th, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Raleway%7CUbuntu"
	rel="stylesheet">
<link rel="stylesheet" href="/static/css/landing.css">


</head>

<body>
	<div class="container">
		<%@include file="../common/header-default.jsp"%>
		<div class="content">
			<%@include file="sidebar-trainer.jsp"%>
			<div class="ota-main_container-dashboard">

				<h1>TRAINER DASHBOARD</h1>

				<div class="ota-main_dashboard_feature-boxes">
					<a href="/trainer/trainees.htm">
					<div class="feature-box_dashboard">
						
						<h3 class="heading-tertiary u-margin-bottom-small">TRAINEES</h3>
						
						<p>
							<c:out value="${totalTrainees}" />Total
						</p>
					</div>
					</a>
					<a href="/trainer/report/overall/chart.htm">
					<div class="feature-box_dashboard">
						
						<h3 class="heading-tertiary u-margin-bottom-small">REPORTS</h3>
						
						<p>${totalReports} updated</p>
					</div>
					</a>

					<a href="/trainer/edit.htm">
					<div class="feature-box_dashboard">
						
						<h3 class="heading-tertiary u-margin-bottom-small">Questions</h3>

						<p>${totalQuestions} Questions</p>
					</div>
					</a>
					
					<a href="/trainer/tests.htm">
					<div class="feature-box_dashboard">
						
						<h3 class="heading-tertiary u-margin-bottom-small">Tests</h3>

						<p>${totalTests} Tests</p>
					</div>
					</a>
					
					<a href="/trainer/batches.htm">
					<div class="feature-box_dashboard">
						
						<h3 class="heading-tertiary u-margin-bottom-small">BATCHES</h3>

						<p>${totalBatches}</p>
						
						<h3 class="heading-tertiary u-margin-bottom-small">CURRENT
							BATCH</h3>

						<p>${currentBatch}</p>

					</div>
					</a>

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