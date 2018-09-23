<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Admin Dashboard</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/static/css/landing.css"/>">
</head>

<body>
    <div class="wrapper">
        <%@include file="../common/header-default.jsp" %>
        <%@include file="sidebar-admin.jsp" %>
        <section class="content">
            <h1 class="content_header"></h1>
            <div class="content_container">
                <div class="dashboard">
                    <div class="dashboard_row">
                        <div class="dashboard_column">
                            <div class="dashboard_card">
                                <p class="dashboard_card_title">
                                    Trainees
                                </p>
                                <p class="dashboard_card_subtitle">
                                    ${numberOfTrainees} Trainees Available
                                </p>
                            </div>
                        </div>
                        <div class="dashboard_column">
                            <div class="dashboard_card">
                                <p class="dashboard_card_title">
                                    Reports
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="dashboard_row">
                        <div class="dashboard_column">
                            <div class="dashboard_card">
                                <p class="dashboard_card_title">
                                    Batches
                                </p>
                                <p class="dashboard_card_subtitle">
                                    ${numberOfBatches} Batches Available
                                </p>
                            </div>
                        </div>
                        <div class="dashboard_column">
                            <div class="dashboard_card">
                                <p class="dashboard_card_title">
                                    Tests
                                </p>
                                <p class="dashboard_card_subtitle">
                                   	${numberOfTests} Tests Available
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <%@include file="../common/footer.jsp" %>
</body>

</html>