<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	<link rel="stylesheet" href="${url_style}">
	<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="ota-aside_no-picture">

		<h2 class="ota-aside_h2">Trainer</h2>

		<div class="dropdown">
			<button class="dropbtn">Batches</button>
			<div class="dropdown-content">
				<a href="/trainer/batches.htm">Batch List</a>
				<a href="/trainer/batch/create.htm">Create Batch</a>
			</div>
		</div>

		<div class="dropdown">
			<button class="dropbtn">Trainees</button>
			<div class="dropdown-content">
				<a href="/trainer/trainees.htm">Trainee List</a>
				<a href="/trainer/trainee/approve-awaiting-requests.htm">Approve Requests</a>
			</div>
		</div>

		<div class="dropdown">
			<button class="dropbtn">Tests</button>
			<div class="dropdown-content">
				<a href="/trainer/tests.htm">Test List</a>
				<a href="/trainer/test/create.htm">Create Test</a>
			</div>
		</div>

		<div class="dropdown">
			<button class="dropbtn">Test Questions</button>
			<div class="dropdown-content">
				<a href="/trainer/question/create.htm">Create Question</a>
				<a href="/trainer/questions/import.htm">Import Questions</a>

			</div>
		</div>


		<div class="dropdown">
			<button class="dropbtn">Reports</button>
			<div class="dropdown-content">
				<a href="/trainer/report/overall/chart.htm">Overall Consolidated Report</a>
				<a href="/trainer/report/tech/chart.htm">Technology Specific Report</a>
				<a href="/trainer/report/batch.htm">Individual Report</a>
			</div>
		</div>

		<div class="dropdown">
			<button class="dropbtn">Settings</button>
			<div class="dropdown-content">
				<a href="/users/account.htm">Account</a>
				<a href="/users/profile.htm">Profile</a>
			</div>
		</div>


	</div>
</body>
</html>
