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
    <title>Trainee Dashboard</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
    href="<c:url value = "../static/css/landing.css"/>">
     <link rel="stylesheet"
    href="<c:url value = "../static/css/trainee/trainee-dashboard.css"/>">
</head>
<body>
 	
  <div class="container">
		<%@include file="../common/header-default.jsp"%>
		<div class="content">
     	<%@include file="sidebar-trainee.jsp" %>
			<div class="ota-main_container">
       <div class="trainee_main">
        <div class="my_reports_content">
               <a href="./report.htm"><button class="my_report_button">My Reports</button></a>
        </div>

        <table class="my_tests_table">
                <thead class="my_tests_table__head">
                    <tr class="my_tests_table__rowheader">
                        <td class="my_tests_table__header">My Tests</td>
                    </tr>
                </thead>
                <tbody class="my_tests_table__body">
                <c:choose>
						<c:when test="${assignedTests == null || assignedTests.isEmpty()}">
                    <tr class="table_row">
								<td class="table_data" colspan="5">No assigned tests.</td>
							</tr>
						</c:when>
						<c:otherwise>
						<c:forEach items="${assignedTests}" var="test">
                    <tr class="my_tests_table__row">
                        <td class="my_tests_table__data"><a href="./test/${test.id}/instruction.htm" class="table_anchor">${test.testName}</a></td>
                    </tr>
                   </c:forEach>
                   </c:otherwise>
                   </c:choose>
                </tbody>
            </table>
            <div class="performance_content">
                    <script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
                    <script src="https://www.amcharts.com/lib/3/serial.js"></script>
                     <script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
                    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
                    <script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
                     <script src="//www.amcharts.com/lib/4/core.js"></script>
        <script src="//www.amcharts.com/lib/4/charts.js"></script>
        <script src="//www.amcharts.com/lib/4/themes/animated.js"></script>
<%--                 <script src="../static/js/testsdefault-performance.js"></script> --%>
                <div class="my_performance_header">My Performance</div>
                  <div id="chartdiv"></div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            am4core.useTheme(am4themes_animated);
                            var chart = am4core.create("chartdiv", am4charts.XYChart);
                            chart.dataSource.url = "trainee/data.json";
                            chart.padding(40, 40, 0, 0);
                            chart.maskBullets = false;
                            // allow bullets to go out of plot area
                            // category axis
                            var categoryAxis = chart.xAxes
                                .push(new am4charts.CategoryAxis());
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
                            // label bullet
                            var labelBullet = new am4charts.LabelBullet();
                            series.bullets.push(labelBullet);
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
                            // legend
                          
                        </script>

					<div id="test"></div> 

            </div>
</div>
</div>
    <%@include file="../common/footer.jsp" %>

</body>

</html>