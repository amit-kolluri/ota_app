<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>Edit Trainee Detail</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/static/css/landing.css"/>">
</head>
<body>
	<div class="wrapper">
		<%@include file="../../common/header-default.jsp"%>
		<%@include file="../sidebar-trainer.jsp"%>
		<section class="content trainer_batch_indv_trainee_edit_content">
			<h1 class="content_header"></h1>
			
			<div class="content_container_edit_trainee">
				<h1 class="edit_trainee_deatil_header_h1" id="edit_trainee_detail_h1">Edit Trainee Details</h1>
				<form:form
					action="/ota/trainer/batch/editTraineeDetail.htm" method="post"
					class="registration_form" modelAttribute="command">
					
					<div class="editTraineeDetail_table">
						<table class="table_content">


							<tr>
								<td class="table_input" ><label class="table_label">Current Batch</label></td>
								<td class="table_input" id="table_input_fontSize">${traineeCurrentBatch}</td>
							</tr>
 
							<tr>
								<td class="table_input" ><label class="table_label">Select a new Batch</label> </td>
								<td>
								<form:select
										path="batchId" name="batchId" class="table_input">
										<form:option value="${traineeCurrentBatchId}" selected="selected" path="batchId" >${traineeCurrentBatch}</form:option> 
										<c:forEach items="${batchList}" var="batch">
										
											<form:option path="batchId" value="${batch.id}">${batch.name}</form:option>
										</c:forEach>
									</form:select>

									</td>
							</tr> 
							
							<tr>
								<td class="table_input" ><label class="table_label">First Name</label> </td>
								<td>
								<form:input class="table_input" 
								path="firstName"
								name="firstName"
								value="${firstName}"/>
								<form:errors element="div" path="firstName" class="editTraineeDetail_errors" />
								</td>
							</tr>

							<tr>
								<td class="table_input" ><label class="table_label">Last Name</label>  </td>
								<td>
								<form:input
								path="lastName"
										class="table_input" name="lastName"
										value="${lastName}" />
										<form:errors element="div" path="lastName" class="editTraineeDetail_errors"/>
										</td>
							</tr>


							<tr>
								<td class="table_input" ><label class="table_label">Phone Number</label> </td>
								<td>
								<form:input
								path="phoneNumber"
										class="table_input" name="phoneNumber"
										value="${phoneNumber}" />
										<form:errors element="div" path="phoneNumber" class="editTraineeDetail_errors"/>
										</td>
							</tr>


							<tr>
								<td></td>
								<td><input class="table_button" type="submit" value="Save">
								</td>
							</tr>
						</table>
					</div>
				</form:form>



			</div>
		</section>
	</div>
	<%@include file="../../common/footer.jsp"%>
	<script src="../../../../static/js/jquery.js"></script>

	<script src="../../../../static/js/main.js"></script>

	<script>
		$(function() {
			$('#header').load("../../common/header.html");
			$('#sidebar').load("../trainer-sidebar.html");
			$("#footer").load("../../common/footer.html");
		});
	</script>
</body>
</html>