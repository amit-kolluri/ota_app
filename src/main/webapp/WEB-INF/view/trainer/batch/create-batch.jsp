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


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Create A Batch</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/static/css/landing.css">
    <%--<link rel="stylesheet" href="${url_style}">--%>
    <link rel="stylesheet" type="text/css" href="${url_style}">
    <%--<script src="../../../../static/js/create-test.js"></script>--%>
    <%--<style>--%>

    <%--/*</style>*/--%>
</head>

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
            <div >
            <form:form id="createbatch_container" action="./createbatch.htm" method="post"
                       modelAttribute="command">

                <form:errors path="*" cssClass="errorblock" element="div"></form:errors>
                <div>
                    <div class="content_container--input">
                        <label class="content_text">Enter Batch Name</label>
                        <input class="content_batch--textbox" type="text" name="batchName">
                        <form:errors path="batchName"></form:errors>
                    </div>
                    <div class="content_container--textarea">
                        <label class="content_text--description" id="">Enter Discription</label>
                        <textarea id="content_batch--textarea"  type="textarea" name="batchDescription"></textarea>
                    </div>
                    <div class="content_button--submit">
                        <input class="button" type="submit" value="Submit">
                    </div>
                </div>
            </form:form>
            </div>


        </div>
    </div>
    <section class="icons">
        <div class="icon-box"></div>
    </section>
</div>

<%@include file="../../common/footer.jsp"%>

</body>

</html>