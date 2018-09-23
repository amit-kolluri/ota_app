<%--author Madhuri Vutukury--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>--%>
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
    <title>CreateBatch</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/static/css/landing.css">
    <link rel="stylesheet" type="text/css" href="${url_style}">
    <script src="../../../../static/js/create-test.js"></script>
    <%--<style>--%>

    <%--/*</style>*/--%>
</head>

<body>
<body>
<div class="container">
    <%@include file="../../common/header-default.jsp"%>
    <div class="content">
        <%@include file="../sidebar-trainer.jsp"%>
        <div class="ota-main_container-dashboard">
            <h1>TRAINER DASHBOARD</h1>
            <%--<div class="content_container">--%>
            <!-- ALL OTHER CONTENT HERE -->
            <%--<div class="content_batch-container">--%>
            <form:form action="./processCreateTest.htm" method="post"
                       modelAttribute="command">
                <form:errors path="*" cssClass="errorblock" element="div"/>
                <div class="content_technology">
                    <label class="content_technology--label">Select the technology</label>
                    <select class="content_technology--dropdown" id="technologies" name="technologies"
                            onchange="getTopics()">
                        <option value="">Select the technology</option>
                        <c:forEach var="technology" items="${technologyList}">
                            <option id=${technology.id} value="${technology.name}">${technology.name}</option>

                        </c:forEach>
                    </select>
                </div>

                <div class="content_topic">
                    <label class="content_topic--label">Select Topic</label>
                    <select class="content_topic--dropdown" name="topics">
                        <option value="">Select the topic</option>
                    </select>
                </div>

                <div class="content_text">
                    <label class="content_labelduration">Enter Test Duration</label>
                    <input class="content_textbox" type="text" name="testDuration">
                    <form:errors path="testDuration" />
                </div>
                <div class="content_button--submit">
                    <input class="button" type="submit" value="Submit">
                </div>
            </form:form>


        </div>
    </div>
    <section class="icons">
        <div class="icon-box"></div>
    </section>
</div>

<%@include file="../../common/footer.jsp"%>

</body>

</html>