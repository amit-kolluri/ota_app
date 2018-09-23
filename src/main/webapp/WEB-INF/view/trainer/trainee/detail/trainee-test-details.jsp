<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sjay9
  Date: 8/22/2018
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Test Details for Trainee</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/static/css/landing.css">
        <script src="//www.amcharts.com/lib/4/core.js"></script>
        <script src="//www.amcharts.com/lib/4/charts.js"></script>
        <script src="//www.amcharts.com/lib/4/themes/animated.js"></script>
        <link rel="stylesheet" href="/static/css/trainer/trainee/detail/trainee-details.css">
    </head>
    <body>
        <div id="container">
            <%@include file="/WEB-INF/view/common/header-default.jsp" %>
            <h1 class="content_header"></h1>
            <div class="content">
                <%@include file="/WEB-INF/view/trainer/sidebar-trainer.jsp" %>
                <div class="content_container trainee_details_container" id="trainee_container-5percentpadding">
                    <div class="row">
                        <div class="column" id="dashboard_dropdown-25percent">
                            <select name="technologies" id="technologies">
                                <option value="initial">Select A Test</option>
                                <c:forEach items="${topics}" var="topic">
                                    <option value="/trainer/trainee/${traineeId}/${topic.id}/tech.htm">${topic.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <script type="text/javascript">
                            var url = document.getElementById('technologies');
                            url.onchange = function () {
                                var optionSelected = this.options[this.selectedIndex];
                                if (optionSelected.value !== "initial") {
                                    window.open(optionSelected.value, "Test Details")
                                }
                            }
                        </script>
                        <div class="column" id="dashboard_chart-75percent">
                            <div class="dashboard_chart">
                                <div id="chartdiv"
                                     style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            am4core.useTheme(am4themes_animated);
                            var chart = am4core.create("chartdiv", am4charts.XYChart);
                            chart.dataSource.url = "testwise/data.json";
                            chart.padding(40, 40, 0, 0);
                            chart.maskBullets = false; // allow bullets to go out of plot area
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
                        </script>
                    </div>
                </div>
            </div>
            <div id="footer">
                <%@include file="/WEB-INF/view/common/footer.jsp" %>
            </div>
        </div>
    </body>
</html>
