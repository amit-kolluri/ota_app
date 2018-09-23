<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Test List</title>
    <%-- Spring link preparation --%>
    <s:url var="url_stylesheet" value="../../../../static/css/style.css"/>
    <s:url var="url_font" value="https://fonts.googleapis.com/css?family=Raleway%7CUbuntu"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/landing.css"/>">
    <link rel="stylesheet" type="text/css" href="${url_font}">


</head>
<body>
<div class="wrapper">
    <jsp:include page="../../common/header-default.jsp"/>
    <jsp:include page="../sidebar-admin.jsp"/>
    <section class="content">
        <div class="content_container content--align">
            <h2 class="content_header">Tests</h2>
            <table role="table" class="table ">
                <thead role="rowgroup" class="table_head">
                <tr role="row" class="table_rowheader">
                    <th role="columnheader" class="table_header">Test Name</th>
                    <th role="columnheader" class="table_header">No of Questions</th>
                    <th role="columnheader" class="table_header">Duration</th>
                </tr>
                </thead>
                <tbody role="rowgroup" class="table_body">
                <c:forEach var="testInfo" items="${testslistDurationQuestion}" varStatus="st">
                    <tr role="row" class="table_row table_row--border">
                        <td role="cell" class="table_data table_data--padding"><a href="./${testInfo.id}/details.htm">${testInfo.testName}</a></td>
                        <td role="cell" class="table_data table_data--padding">${testInfo.num_questions}</td>
                        <td role="cell" class="table_data table_data--padding">${testInfo.timeAllotted}</td>
                        <td class="display_while_large">
                            <button class="button button_export" type="button" onclick="location.href='/admin/export/${testInfo.id}.htm'">Export</button>
                        </td>
                    </tr>
                    <tr role="row" class="table_row--align display_while_small">
                        <td>
                            <button class="button button_export" type="button" onclick="location.href='/admin/export/${testInfo.id}.htm'">Export</button>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>

            </table>
        </div>

    </section>
</div>
<div id="footer">
    <jsp:include page="../../common/footer.jsp"/>
</div>
<s:url var="url_jquery" value="../../../../static/js/jquery.js"/>
<script src="${url_jquery}"></script>
</body>
</html>