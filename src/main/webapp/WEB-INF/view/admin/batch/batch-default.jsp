<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Batch List</title>

<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/static/css/landing.css"/>">
</head>
<body>
	<div class="wrapper">
			<div class="container">
		<%@include file="../../common/header-default.jsp"%>
		<div class="content">
		<%@include file="../sidebar-admin.jsp"%>

		<section class="content admin_batch_content">
		<h1 class="content_header"></h1>
		<h1 class="batches__header">All Batches</h1>
		<div class="content__container">
			<p class="content__header">
				<br>
			</p>
			<table class="table">
				<thead class="table__header table__header">
					<tr class="table__rowheader">
						<td class="table__dataheader">Batch Name</td>
						<td class="table__dataheader">Number of Trainees</td>
						<td class="table__dataheader">Status</td>
					</tr>
				</thead>
				<tbody class="table__body">
					<c:forEach items="${batchList}" var="batch" varStatus="loop">
						<tr class="table__row--border">
							<td class="table__data"><a href="/admin/batch/${batch.id}/details.htm">${batch.name}</a></td>
							<td class="table__data">${numUsers[loop.index]}</td>
							<c:choose>
								<c:when test="${batch.statusId=='1'}">
									<td class="table__data">Active</td>
								</c:when>
								<c:otherwise>
									<td class="table__data">Inactive</td>
								</c:otherwise>
							</c:choose>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</section>
	</div>

</div>
	<%@include file="../../common/footer.jsp"%>
	
</div>
	
</body>
</html>