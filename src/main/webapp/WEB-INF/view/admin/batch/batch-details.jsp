<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
    <!--<![endif]-->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Batch Details</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../../../../static/css/landing.css">
    </head>

    <body>
        <c:set var = "batchDetails" value ="${batchDetails}"/>
        <c:set var = "traineeList" value="${traineeList}"/>

        <div class="wrapper">
            <%@include file="../../common/header-default.jsp"%>
            <%@include file="../sidebar-admin.jsp"%>
            <section class="content trainer_batch_content">
                <h1 class="content_header"></h1>
                <div class="content_container batch_details_container">

                    <fieldset class="content_fieldset">
                        <table class="batch_summary">
                            <tr>
                                <td>Batch Name</td>
                                <td class="content_td_data"><c:out value="${batchDetails.name}"/></td>
                            </tr>
                            <tr>
                                <td>Number of Trainees</td>
                                <td class="content_td_data"><c:out value="${fn:length(traineeList)}"/></td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td class="content_td_data"><c:out value="${batchDetails.statusId}"/></td>
                            </tr>
                        </table>
                    </fieldset>
                    <div class="content_scrollable_list">
                        <table class="table_static_head">
                            <tr>
                                <th>Trainee Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Status</th>
                            </tr>
                        </table>
                        <div class="inner_trainee_details">
                            <table>
                                <c:forEach items="${traineeList}" var = "trainee">
                                    <c:choose>
                                        <c:when test="${trainee.statusId == 1}">
                                            <c:set var = "statusName" value = "Active"/>
                                        </c:when>
                                        <c:when test="${trainee.statusId == 2}">
                                            <c:set var = "statusName" value = "Inactive"/>
                                        </c:when>
                                    </c:choose>
                                    <tr>
                                        <td class="table__data table__data--padding--trainee-default">
                                            <a class="link--trainee-default" href="/admin/trainee/${trainee.id}/details.htm">${trainee.firstName} ${trainee.lastName}</a>
                                        </td>
                                        <td class="table__data table__data--padding--trainee-default">
                                            <a class="link--trainee-default" href="mailto:${trainee.email}">${trainee.email}</a>
                                        </td>
                                        <td class="table__data table__data--padding--trainee-default">
                                            <a class="link--trainee-default" href="tel:+1${trainee.phoneNumber}">${trainee.phoneNumber}</a>
                                        </td>
                                        <td class="table__data table__data--padding--trainee-default">
                                                ${statusName}
                                        </td>
                                    </tr>
                                </c:forEach>

                            </table>
                        </div>
                    </div>
                </div>
            </section>
        </div>

    <%@include file="../../common/footer.jsp"%>
    </body>

</html>
