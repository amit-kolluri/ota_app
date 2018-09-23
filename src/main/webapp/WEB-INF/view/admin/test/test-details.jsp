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
    <title>Test Details</title>
    <%-- Spring link preparation --%>
    <s:url var="url_stylesheet" value="/static/css/style.css"/>
    <s:url var="url_font" value="https://fonts.googleapis.com/css?family=Raleway%7CUbuntu"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/landing.css"/>">


</head>
<body>
<div class="wrapper">
        <jsp:include page="../../common/header-default.jsp"/>
        <jsp:include page="../sidebar-admin.jsp"/>
    <section class="content">
        <div class="content_container content--align">
            <h1 class="content_header">Tests</h1>
            <c:choose>
                <c:when test="${error!=null}">
                    <p>${error}</p>
                    <br />
                </c:when>
                <c:otherwise>
                    <table role="table" class="table table_style table_data--padding">
                        <thead role="rowgroup" class="table_head">
                        <tr role="row" class="table_rowheader">
                            <th role="columnheader" class="table_header">Test Name</th>
                            <th role="columnheader" class="table_header">Number of Questions</th>
                            <th role="columnheader" class="table_header">Duration</th>
                        </tr>
                        <tbody role="rowgroup" class="table_body">
                        <tr role="row" class="table_row table_row--border">
                            <td role="cell" class="table_data table_data--padding"><a href="#">${test.testName}</a></td>
                            <td role="cell" class="table_data table_data--padding_">${test.num_questions}</td>
                            <td role="cell" class="table_data ">${test.timeAllotted}</td>
                            <td class="display_while_large">
                                <button class="button button_export" type="button" onclick="location.href='/admin/export/${test.id}.htm'">Export</button>
                            </td>

                        </tr>
                        <tr role="row" class="table_row--align display_while_small">
                            <td>
                                <button class="button button_export" type="button">Export</button>
                            </td>
                        </tr>
                    </table>
                    <table role="table" class="table table_style">
                        <thead role="rowgroup" class="table_head">
                        <tr role="row" class="table_rowheader">
                            <th role="columnheader" class="table_header">Id</th>
                            <th role="columnheader" class="table_header">Question</th>
                            <th role="columnheader" class="table_header">Option 1</th>
                            <th role="columnheader" class="table_header">Option 2</th>
                            <th role="columnheader" class="table_header">Option 3</th>
                            <th role="columnheader" class="table_header">Option 4</th>
                            <th role="columnheader" class="table_header">Correct Answer</th>
                        </tr>
                        <tbody role="rowgroup" class="table_body">
                        <c:forEach var="question" items="${questions}" varStatus="st">
                            <tr role = "row" class="table_row table_row--border">
                                <td role="cell" class="table_data table_data--padding table_data--font">${question.id}</td>
                                <td role="cell" class="table_data table_data--padding table_data--font">${question.question}</td>
                                <td role="cell" class="table_data table_data--padding table_data--font">${question.option1}</td>
                                <td role="cell" class="table_data table_data--padding table_data--font">${question.option2}</td>
                                <td role="cell" class="table_data table_data--padding table_data--font">${question.option3}</td>
                                <td role="cell" class="table_data table_data--padding table_data--font">${question.option4}</td>
                                <td role="cell" class="table_data table_data--padding table_data--font">${question.correctAnswer}</td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>

        </div>
    </section>
</div>
<div id="footer">
    <jsp:include page="../../common/footer.jsp"/>
</div>
<s:url var="url_jquery" value="/static/js/jquery.js"/>
<script src="${url_jquery}"></script>

</body>
</html>