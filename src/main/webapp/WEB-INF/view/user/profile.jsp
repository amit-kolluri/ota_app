<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<title>Profile - ${sessionScope.user.firstName}</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value = "../static/css/landing.css"/>">
<link rel="stylesheet"
	href="<c:url value = "../static/css/user/profile.css"/>">
</head>

<body>
	<div class="container">
		<%@include file="../common/header-default.jsp"%>
		<div class="content">
			<%@include file="../common/sidebar.jsp"%>
			<div class="ota-main_container">
				<h1 class="content_header header_container">Profile</h1>
				<div class="content_container">
					<div class="bio">
						<p>${sessionScope.user.firstName}
							${sessionScope.user.lastName}</p>
						<p>${sessionScope.user.email}</p>
						<p>Group: ${sessionScope.user.batchId}</p>
						<p>Phone Number: ${sessionScope.user.phoneNumber}</p>
						<c:choose>
							<c:when test="${sessionScope.user.roleId == '3'}">
								<p>Role: Admin</p>
							</c:when>
							<c:when test="${sessionScope.user.roleId == '2'}">
								<p>Role: Trainer</p>
							</c:when>
							<c:otherwise>
								<p>Role: Trainee</p>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.user.statusId == '1'}">
								<p>Status: Active</p>
							</c:when>
							<c:otherwise>
								<p>Status: Inactive</p>
							</c:otherwise>
						</c:choose>
						<p>Created on: ${sessionScope.user.createdDate}</p>
					</div>
					<div class="prof_img_container">
				<span class="profile_picture">
					<%@include file="../common/profile-picture.jsp"%>
				</span>
				</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>