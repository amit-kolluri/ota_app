<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Select A Trainee</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/static/css/landing.css"/>">
</head>

<body>
    <div class="container">
        <%@include file="../../common/header-default.jsp" %>
        <section class="content">
        <%@include file="../sidebar-admin.jsp" %>
            <div class="ota-main_container-dashboard">
            	<h1>Select A Trainee to Display A Report</h1>
            	<form method="POST" action="/admin/report/processInput.htm">
	            	<select name="traineeId">
	            		<c:forEach items = "${listOfTrainees}" var="trainee">
	            			<option value="${trainee.getId()}">${trainee.getUserName()}</option>
	            		</c:forEach>
	            	</select>
	            	<input type="submit" value="Choose This Trainee"/>
            	</form>
            </div>
        </section>
    </div>
    <%@include file="../../common/footer.jsp" %>
</body>

</html>
