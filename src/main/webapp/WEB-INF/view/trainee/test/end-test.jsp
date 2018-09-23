<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Report on ${EndOfTestResult.testName}</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/static/css/landing.css"/>">
</head>

<body>
	<div class="wrapper">
		<div class="container">
			<%@include file="../../common/header-default.jsp"%>
			<div class="content">
				<%@include file="../sidebar-trainee.jsp"%>
				<div class="ota-main_container">

					<h1>TEST REPORT</h1>

					&nbsp;

					<div class="chart">
						<div class="chart-block">
							<p class="chart-block_title">Test Name:</p>
							<p class="chart-block_value">${EndOfTestResult.testName}</p>
						</div>

						<div class="chart-block">
							<p class="chart-block_title">Number of Tests Taken:</p>
							<p class="chart-block_value">${fn:length(PreviousTests)}</p>
						</div>

						<div class="chart-block">
							<p class="chart-block_title">Status:</p>
							<p class="chart-block_value">Active</p>
						</div>

						<div class="chart-block filterBar">
							<input type=text id=filterBox placeholder="Search"
								onkeyup="filterTable(this)" />
						</div>
					</div>

					<div class="wrap" style="font-size: 1.5rem;">
						<table id="example" class="head">
							<tr>
								<td class="wrap-td">Username</td>
								<td class="wrap-td">Date Taken</td>
								<td class="wrap-td">Technology</td>
								<td class="wrap-td">Topic Name</td>
								<td class="wrap-td">Time Taken</td>
								<td class="wrap-td">Score</td>
								<td class="wrap-td">Questions</td>
							</tr>
						</table>

					<div class="inner_table">
						<table id="test_table">
							<c:choose>
							<c:when test="${EndOfTestResult != null}">
								<tr
									style="background: rgba(74, 143, 161, 0.65)">
									<td>${EndOfTestResult.username}</td>
									<td>${EndOfTestResult.date}</td>
									<td>${EndOfTestResult.technologyName}</td>
									<td>${EndOfTestResult.testName}</td>
									<td><fmt:formatDate pattern="mm:ss"
											value="${EndOfTestResult.timeTaken}" /></td>
									<td>${EndOfTestResult.correctAnswers}</td>
									<td>${EndOfTestResult.totalAnswers}</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>SOMETHING MESSED UP! ABORT!!!</td>
								</tr>
							</c:otherwise>
						</c:choose>
						<c:forEach items="${PreviousTests}" var="test">
							<c:if
								test="${!test.date.equals(EndOfTestResult.date) || !test.testName.equals(EndOfTestResult.testName) || !test.timeTaken.equals(EndOfTestResult.timeTaken) 
								|| test.correctAnswers != EndOfTestResult.correctAnswers || test.totalAnswers != EndOfTestResult.totalAnswers}">
								<tr class="table_row--testcompletionandreport">
									<td class="table_data">${test.username}</td>
									<td class="table_data">${test.date}</td>
									<td class="table_data">${test.technologyName}</td>
									<td class="table_data">${test.testName}</td>
									<td class="table_data"><fmt:formatDate pattern="mm:ss"
											value="${test.timeTaken}" /></td>
									<td class="table_data">${test.correctAnswers}</td>
									<td class="table_data">${test.totalAnswers}</td>
								</tr>
							</c:if>
						</c:forEach>
						</table>
					</div>
				</div>
				</div>
			</div>
		</div>
		<section class="icons">
            <div class="icon-box"></div>
        </section>
	</div>
	<%@include file="../../common/footer.jsp"%>
	<script src="/static/js/jquery.js"></script>

	<script src="/static/js/main.js"></script>
	<script type=text/javascript>
		function filterTable(filterBox) {
			var filterValue = filterBox.value;
			var rows = document.getElementById('test_table')
					.getElementsByTagName("tr");

			for (i = 0; i < rows.length; i++) {
				var cols = rows[i].getElementsByTagName("td");
				var rowContains = false;
				for (j = 0; j < cols.length; j++) {
					if (cols[j].innerHTML.toUpperCase().indexOf(
							filterValue.toUpperCase()) >= 0) {
						rowContains = true;
					}
				}
				if (rowContains == false) {
					rows[i].style.display = "none";
				} else {
					rows[i].style.display = "";
				}
			}
		}
		// get the table element
		var $table = document.getElementById("test_table"),
		// number of rows per page
		$n = 5,
		// number of rows of the table
		$rowCount = $table.rows.length,
		// get the first cell's tag name (in the first row)
		$firstRow = $table.rows[0].firstElementChild.tagName,
		// boolean var to check if table has a head row
		$hasHead = ($firstRow === "TH"),
		// an array to hold each row
		$tr = [],
		// loop counters, to start count from rows[1] (2nd row) if the first row has a head tag
		$i, $ii, $j = ($hasHead) ? 1 : 0,
		// holds the first row if it has a (<TH>) & nothing if (<TD>)
		$th = ($hasHead ? $table.rows[(0)].outerHTML : "");
		// count the number of pages
		var $pageCount = Math.ceil($rowCount / $n);
		// if we had one page only, then we have nothing to do ..
		if ($pageCount > 1) {
			// assign each row outHTML (tag name & innerHTML) to the array
			for ($i = $j, $ii = 0; $i < $rowCount; $i++, $ii++)
				$tr[$ii] = $table.rows[$i].outerHTML;
			// create a div block to hold the buttons
			$table.insertAdjacentHTML("afterend", "<div id='buttons'></div");
			// the first sort, default page is the first one
			sort(1);
		}
		// ($p) is the selected page number. it will be generated when a user clicks a button
		function sort($p) {
			/* create ($rows) a variable to hold the group of rows
			 ** to be displayed on the selected page,
			 ** ($s) the start point .. the first row in each page, Do The Math
			 */
			var $rows = $th, $s = (($n * $p) - $n);
			for ($i = $s; $i < ($s + $n) && $i < $tr.length; $i++)
				$rows += $tr[$i];

			// now the table has a processed group of rows ..
			$table.innerHTML = $rows;
			// create the pagination buttons
			document.getElementById("buttons").innerHTML = pageButtons(
					$pageCount, $p);
			// CSS Stuff
			document.getElementById("id" + $p).setAttribute("class", "active");
		}
		// ($pCount) : number of pages,($cur) : current page, the selected one ..
		function pageButtons($pCount, $cur) {
			/* this variables will disable the "Prev" button on 1st page
			   and "next" button on the last one */
			var $prevDis = ($cur == 1) ? "disabled" : "", $nextDis = ($cur == $pCount) ? "disabled"
					: "",
			/* this ($buttons) will hold every single button needed
			 ** it will creates each button and sets the onclick attribute
			 ** to the "sort" function with a special ($p) number..
			 */
			$buttons = "<input type='button' value='&lt;&lt; Prev' onclick='sort("
					+ ($cur - 1) + ")' " + $prevDis + ">";
			for ($i = 1; $i <= $pCount; $i++)
				$buttons += "<input type='button' id='id" + $i + "'value='"
						+ $i + "' onclick='sort(" + $i + ")'>";
			$buttons += "<input type='button' value='Next &gt;&gt;' onclick='sort("
					+ ($cur + 1) + ")' " + $nextDis + ">";
			return $buttons;
		}
	</script>

</body>

</html>
