<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
    <html>
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Test Default</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
    href="<c:url value = "/static/css/landing.css"/>">
     <link rel="stylesheet"
    href="<c:url value = "/static/css/table/table_style.css"/>">
    
</head>
<body>
<div class="container">
		<%@include file="../../common/header-default.jsp"%>
		<div class="content">
			
     	<%@include file="../sidebar-trainee.jsp" %>
   
			<div class="ota-main_container">
        <p class="content__header">Available Tests</p>
        <div class="content__container">
           <table class="table">
                <thead class="table__head">
                    <tr class="table__rowheader">
                        <td class="table__header">Test Name</td>
                        <td class="table__header">Number of Questions</td>
                        <td class="table__header">Duration(min)</td>
                    </tr>
                </thead>
                <tbody class="table__body">
                <c:choose>
						<c:when test="${availableTests == null || availableTests.isEmpty()}">
                    <tr class="table__row">
								<td class="table__data" colspan="5">No available tests.</td>
							</tr>
						</c:when>
						<c:otherwise>
						<c:forEach items="${availableTests}" var="test">
						<tr class="table__row">
                        <td class="table__data"><a href="./test/${test.id}/instruction.htm" class="table__anchor">${test.testName}</a></td>
                        <td class="table__data">${test.num_questions}</td>
                        <td class="table__data">${test.timeAllotted}</td>
                        </tr>
                    </c:forEach>
                    </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div><br>

            <p class="content__header">Previous Tests</p>
        <div class="content__container">
            <table class="table">
                <thead class="table__head">
                    <tr class="table__rowheader">
                        <td class="table__header">Test Name</td>
                        <td class="table__header">Score</td>
                        <td class="table__header">Total Correct</td>
                        <td class="table__header">Number of Questions</td>
                        <td class="table__header">Time Taken</td>
                    </tr>
                </thead>
                <tbody class="table__body">
                <c:choose>
						<c:when test="${previousTests == null || previousTests.isEmpty()}">
                    <tr class="table_row">
								<td class="table__data" colspan="5">No previous tests.</td>
							</tr>
						</c:when>
					<c:otherwise>
					<c:forEach items="${previousTests}" var="test">
                    <tr class="table__row">
                        <td class="table__data"><a href="#" class="table_anchor">${test.testName}</a></td>
                        <td class="table__data">${test.percent}%</td>
                        <td class="table__data">${test.correctAnswers}</td>
                        <td class="table__data">${test.totalAnswers}</td>
                        <td class="table__data">${test.timeTaken}</td>
                    </tr>
                  	</c:forEach>
                  	</c:otherwise>
                  	</c:choose>
                </tbody>
            </table>
        </div>
        </div>
</div>
    <%@include file="../../common/footer.jsp" %>

</body>

</html>