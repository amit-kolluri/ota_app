<%--
  Created by IntelliJ IDEA.
  User: judil
  Date: 8/22/2018
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Previous Tests</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value ="/static/css/landing.css"/>">
</head>

<body>
<div class="wrapper">
    <div id="header">
        <%@include file="../../common/header-default.jsp"%>
    </div>
    <div id="sidebar">
        <%@include file="../sidebar-trainee.jsp"%>
    </div>

    <section class="content">
        <h1 class="content_header">Previous Tests</h1>
        <div class="content_container">
            <c:choose>
                <c:when test="${not empty PreviousTests}">
                    <table class="table">
                        <thead class="table_head">
                        <tr class="table_rowheader">
                            <td class="table_header--uniform-width">Test Name</td>
                            <td class="table_header">Technology</td>
                            <td class="table_header">Score</td>
                            <td class="table_header">Total Correct</td>
                            <td class="table_header">Total Questions</td>
                            <td class="table_header">Time Taken</td>
                        </tr>
                        </thead>
                        <tbody class="table_body">
                        <c:forEach items="${PreviousTests}" var="test">
                            <tr class="table_row">
                                <td class="table_data"><a href="#" class="table_anchor">${test.testName}</a></td>
                                <td class="table_data">${test.technologyName}%</td>
                                <td class="table_data">${test.percent}%</td>
                                <td class="table_data">${test.correctAnswers}</td>
                                <td class="table_data">${test.totalAnswers}</td>
                                <td class="table_data">${test.timeTaken}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <h2>You have not taken any tests</h2>
                </c:otherwise>
            </c:choose>
        </div><br>
    </section>
</div>
<%@include file="../../common/footer.jsp"%>
<script src="../../../../static/js/main.js"></script>
</body>

</html>
