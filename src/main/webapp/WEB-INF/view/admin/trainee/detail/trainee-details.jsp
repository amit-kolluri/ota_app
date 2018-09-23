<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>
<!--<![endif]-->

<head>
<spring:url value="/static/css/landing.css" var="css" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Trainee Details</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/static/css/landing.css"/>">
<link rel="stylesheet" href="${css}">

<!-- amCharts javascript sources -->
<script src="//www.amcharts.com/lib/4/core.js"></script>
<script src="//www.amcharts.com/lib/4/charts.js"></script>
<script src="//www.amcharts.com/lib/4/themes/animated.js"></script>

<!-- amCharts javascript code -->
<style>
@import url("https://fonts.googleapis.com/css?family=Archivo+Narrow");

body {
	font-family: "Roboto";
	font-size: 1.6rem;
}

.trainee-dropdown_25percent {
	width: 25%;
}

.trainee-graph_75percent {
	width: 75%;
}

#admin_container-5percentpadding {
	padding: 5%;
}
</style>

		 
<body>
	<div class="container">
		<%@include file="../../../common/header-default.jsp"%>
		<div class="content">
			<%@include file="../../sidebar-admin.jsp" %>
			<div class="ota-main_container">
				<div class="content_container" id="admin_container-5percentpadding">

					<h1>${trainee.firstName} ${trainee.lastName}</h1>
					<form:form modelAttribute="reportItem" action="./tech/details.htm"
						method="POST">
						<tr>
							<td><form:select path="id">
									<c:forEach items="${technologies}" var="report">
										<form:option path="id" value="${report.id}">${report.name}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
						<tr>
							<td><input type="submit" value="Submit"></td>
						</tr>
					</form:form>
					<div id="chartdiv"
						style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
					<script type="text/javascript">
						am4core.useTheme(am4themes_animated);

						var chart = am4core.create("chartdiv",
								am4charts.XYChart);

						chart.dataSource.url = "techwise/data.json";

						chart.padding(40, 40, 0, 0);
						chart.maskBullets = false; // allow bullets to go out of plot area

						// category axis
						var categoryAxis = chart.xAxes
								.push(new am4charts.CategoryAxis());
						categoryAxis.dataFields.category = "name";
						categoryAxis.renderer.grid.template.disabled = true;
						categoryAxis.renderer.minGridDistance = 50;
						categoryAxis.autoWrap = true;

						// value axis
						var valueAxis = chart.yAxes
								.push(new am4charts.ValueAxis());
						// we set fixed min/max and strictMinMax to true, as otherwise value axis will adjust min/max while dragging and it won't look smooth
						valueAxis.strictMinMax = true;
						valueAxis.min = 0;
						valueAxis.max = 100;
						valueAxis.renderer.minWidth = 60;

						// series
						var series = chart.series
								.push(new am4charts.ColumnSeries());
						series.dataFields.categoryX = "name";
						series.dataFields.valueY = "marks";
						series.tooltip.pointerOrientation = "vertical";
						series.tooltip.dy = -8;
						series.sequencedInterpolation = true;
						series.defaultState.interpolationDuration = 1500;
						series.columns.template.strokeOpacity = 0;
						series.fill = am4core.color("#0055A3");

						//Line series
						var series2 = chart.series
								.push(new am4charts.LineSeries());
						series2.dataFields.categoryX = "name";
						series2.dataFields.valueY = "marks";
						series2.tooltip.pointerOrientation = "vertical";
						series2.tooltip.dy = -8;
						series2.sequencedInterpolation = true;
						series2.defaultState.interpolationDuration = 1500;
						series2.columns.template.strokeOpacity = 0;
						series2.fill = am4core.color("");

						// label bullet
						var labelBullet = new am4charts.LabelBullet();
						series2.bullets.push(labelBullet);
						labelBullet.label.text = "{valueY.value.formatNumber('#.')}";
						labelBullet.strokeOpacity = 0;
						labelBullet.stroke = am4core.color("#dadada");
						labelBullet.dy = -20;

						// series bullet
						var bullet = series.bullets.create();
						bullet.stroke = am4core.color("#ffffff");
						bullet.strokeWidth = 3;
						bullet.opacity = 0; // initially invisible
						bullet.defaultState.properties.opacity = 0;
						// resize cursor when over
						bullet.cursorOverStyle = am4core.MouseCursorStyle.verticalResize;
						bullet.draggable = true;

						// create hover state
						var hoverState = bullet.states.create("hover");
						hoverState.properties.opacity = 1; // visible when hovered

						// add circle sprite to bullet
						var circle = bullet.createChild(am4core.Circle);
						circle.radius = 8;
					</script>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../../../common/footer.jsp"%>
	<script src="main.js"></script>

</body>

</html>
