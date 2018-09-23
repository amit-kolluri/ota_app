<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<s:url var="url_style" value="../static/css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Changed Successfully</title>
<link rel="stylesheet" href="${url_style}">
</head>
<body>
	<%@ include file="../common/header-default.jsp"%>
	<div class="wrapper">
		<div class="SuccessMessage_format">
			<h2>Password changed</h2>
		</div>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>