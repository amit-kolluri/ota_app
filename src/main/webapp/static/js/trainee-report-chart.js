/**
 * This script is for the trainee charts. It receives the data for the chart using the select value sent. The values of select are never
 * actually seen by the user.
 * @author phoutsaksit.keomala
 */

	var chart = AmCharts
			.makeChart(
					"trainee-chart-div",
					{
						"type" : "serial",
						"addClassNames" : true,
						"autoMargins" : true,
						"plotAreaBorderAlpha" : 1,
						"plotAreaBorderColor" : "#c00",
						"balloon" : {
							"adjustBorderColor" : false,
							"horizontalPadding" : 10,
							"verticalPadding" : 8,
						},
						"legend": {
					        "valueWidth":0,
					        "verticalGap":5,
					        	"data": [{
					        	       "title": "Score",
					        	       "color": "#0055A3",
								        "markerSize":20
					        	     }, {
					        	       "title": "Batch Average",
					        	       "color": "#ffd200",
					        	       "markerType" : "circle",
								        "markerSize":12
					        	     }]
					    },
						"valueAxes" : [ {
					        "stackType": "regular",
							"axisAlpha" : 0,
							"position" : "left",
							 "title": "Score(%)"
						} ],
						"startDuration" : 1,
						"graphs" : [
								{
									"id" : "userScores",
									"alphaField" : "alpha",
									"balloonText" : "<span style='font-size:12px;'>[[title]] in [[category]]:<br><span style='font-size:20px;'>[[value]]</span> [[additional]]</span>",
									"fillAlphas" : 1,
									"title" : "Score",
									"fillAlphas" : 1,
									"type" : "column",
									"colorField" : "color",
									"lineColorField" : "lineColor",
									"classNameField" : "dataClass",
									"valueField" : "score",
								},
								{
									"id" : "classAverages",
									"balloonText" : "<span style='font-size:12px;'>[[title]] in [[category]]:<br><span style='font-size:20px;'>[[value]]</span> [[additional]]</span>",
									"bullet" : "round",
									"lineThickness" : 3,
									"bulletColor" : "#ffd200",
									"lineColor" : "#f70000",
									"bulletSize" : 7,
									"useLineColorForBulletBorder" : true,
									"bulletBorderThickness" : 3,
									"fillAlphas" : 0,
									"lineAlpha" : 1,
									"title" : "Batch Average",
									"valueField" : "average",
								} ],
						"categoryField" : "test",
						"categoryAxis" : {
							"gridPosition" : "start",
							"axisAlpha" : 0,
							"tickLength" : 0,
							"title" : "Test"
						},
						"export" : {
							"enabled" : true
						}
					});

	window.onload = function () {
		var defaultSelect = document.getElementById("selectData");
		var selectData = defaultSelect.value;
		var techArray = selectData.split("|");
		var chartData = JSON.parse(techArray[1].replace("whitespace"," "));
		chart.dataProvider = chartData;
		chart.validateData();
		chart.animateAgain();
	}
	
	function chartChange(c) {
		var techArray = c.value.split("|");
		var techId = techArray[0];
		var chartData = JSON.parse(techArray[1].replace("whitespace", " "));
		chart.dataProvider = chartData;
		chart.validateData();
		chart.animateAgain();
	}