<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Overall Report</title>
    <!-- amCharts javascript sources -->
    <script src="//www.amcharts.com/lib/4/core.js"></script>
    <script src="//www.amcharts.com/lib/4/charts.js"></script>
    <script src="//www.amcharts.com/lib/4/themes/animated.js"></script>
	<link rel="stylesheet" href="<c:url value = "/static/css/landing.css"/>">
    <!-- amCharts javascript code -->
    <style>
        @import url("https://fonts.googleapis.com/css?family=Archivo+Narrow");

        body {
            font-family: "Archivo Narrow";
            font-size: 1.6REM;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <%@include file="/WEB-INF/view/common/header-default.jsp" %>
    <%@include file="/WEB-INF/view/trainer/sidebar-trainer.jsp" %>
    <section class="content">
        <div class="content_container">
            <form action="chart.htm">
                <h1>Batch: ${selectedBatch}</h1>
                <select name="batchId">
                    <c:forEach items="${batchList}" var="batch">
                        <option value="${batch.id}">${batch.name}</option>
                    </c:forEach>
                </select>
                <input type="submit">
            </form>
            <div id="chartdiv" style="width: 100%; height: 400px; background-color: #FFFFFF;"/>
        </div>
    </section>
</div>
<%@include file="/WEB-INF/view/common/footer.jsp" %>
<script type="text/javascript">
    am4core.useTheme(am4themes_animated);

    var chart = am4core.create("chartdiv", am4charts.XYChart);
    chart.dataSource.url = "${selectedBatch}/data.json";

    chart.padding(40, 40, 0, 0);
    chart.maskBullets = false; // allow bullets to go out of plot area


    // category axis
    var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
    categoryAxis.dataFields.category = "name";
    categoryAxis.renderer.grid.template.disabled = true;
    categoryAxis.renderer.minGridDistance = 50;


    // value axis
    var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
    // we set fixed min/max and strictMinMax to true, as otherwise value axis will adjust min/max while dragging and it won't look smooth
    valueAxis.strictMinMax = true;
    valueAxis.min = 0;
    valueAxis.renderer.minWidth = 60;

    // series
    var series = chart.series.push(new am4charts.ColumnSeries());
    series.dataFields.categoryX = "name";
    series.dataFields.valueY = "marks";
    series.tooltip.pointerOrientation = "vertical";
    series.tooltip.dy = -8;
    series.sequencedInterpolation = true;
    series.defaultState.interpolationDuration = 1500;
    series.columns.template.strokeOpacity = 0;
    series.fill = am4core.color("#0055A3");

    // create hover state
    var hoverState = bullet.states.create("hover");
    hoverState.properties.opacity = 1; // visible when hovered

    // add circle sprite to bullet
    var circle = bullet.createChild(am4core.Circle);
    circle.radius = 8;
</script>
</body>
</html>