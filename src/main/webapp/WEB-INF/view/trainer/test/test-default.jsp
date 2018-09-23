<%@ page import="com.yash.ota.domain.User"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%--
  Created by IntelliJ IDEA.
  User: sjay9
  Date: 8/20/2018
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Batch Details</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="../static/css/landing.css">
<link rel="stylesheet" href="../static/css/trainer/test/test-default-new.css">
</head>
<body>
    <div class="container">
        <%@include file="../../common/header-default.jsp"%>
        <div class="content">
         <%@include file="../sidebar-trainer.jsp"%>
        <div class="ota-main_container">
                <h1>TESTS</h1>
                &nbsp;
                
                        <div class="chart">
                            
                            <div class="chart-block">
                                <p class="chart-block_title">Number of Tests: </p>
                                <p class="chart-block_value">${fn:length(tests)}</p>
                            </div>
                            
                            <div class="chart-block filterBar">
                            <input type=text id=filterBox placeholder="Search" onkeyup="filterTable(this)"/>
                            </div>
                        </div>
                        
                    
                    <div class="wrap">
                        <table id="example" class="head">
                            <tr>
                                <td class="wrap-td">Technology Name</td>
                                <td class="wrap-td">Topic Name</td>
                                <td class="wrap-td">Number of Questions</td>
                                <td class="wrap-td">Duration</td>
                                <td class="wrap-td">Show/Hide</td>
                                <td class="wrap-td"></td> 
                            </tr>
                      	</table>
                            
                        <div class="inner_table">
                            <table id="test-table">
                            <tbody>
                            <c:forEach items="${tests}" var="test">
                            <c:set var="testId" value="${test.get('id')}"/>
                                    <tr>
                                        <td><a href="/trainer/test/${testId}/details.htm"> ${test.get('topicName')} </a></td>
                                        <td>${test.get('techName')} </td>
                                        <td>${ test.get('count') }</td>
                                        <td>${ test.get('time_alloted') }</td>
                                        <td>
                                        	<label class="testdefaulttrainer-tests_toggle">
                                        	<c:if test="${test.get('status_id') == 1}">
                                        		<input type="checkbox" name="show" class="testdefaulttrainer-show--left" checked
												onclick='window.location.assign("/trainer/test/${testId}/status/2.htm")'>
                                    		</c:if> 
                                   	 		<c:if test="${test.get('status_id') == 2}">
                                        		<input type="checkbox" name="show" class="testdefaulttrainer-show--left"
												onclick='window.location.assign("/trainer/test/${testId}/status/1.htm")'>
                                    		</c:if>
                                    		<span class="testdefaulttrainer-tests_toggle--sliding"></span>
                                    		</label>
                                    	</td>
                                        <td><a class="testdefaulttrainer-buttons--padding" href="/trainer/export/${testId}.htm">Export</a></td>
                                    </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
        
        </div>
        </div>
        <section class="icons">
            <div class="icon-box"></div>
        </section>
    </div>
    <%@include file="../../common/footer.jsp"%>
    
    <%--Javascript functionality for Table Pagination and Table Search 
    Authors:  J.P./Nipu/Nick Stone
     --%>
    
    <script type=text/javascript>
        function filterTable(filterBox) {
            var filterValue = filterBox.value;
            var rows = document.getElementById('test-table').getElementsByTagName("tbody")[0].getElementsByTagName("tr");
          
            for (i = 0; i < rows.length; i++) {
                var cols = rows[i].getElementsByTagName("td");
                var rowContains = false;
                for (j = 0; j < cols.length; j++) {
                    if(cols[j].innerHTML.toUpperCase().indexOf(filterValue.toUpperCase()) >= 0) {
                        rowContains = true;
                    }
                }
                if(rowContains == false) {
                    rows[i].style.display = "none";
                } else {
                    rows[i].style.display = "";
                }
            }
       }
        // get the table element
        var $table = document.getElementById("test-table"),
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
        $i,$ii,$j = ($hasHead)?1:0,
        // holds the first row if it has a (<TH>) & nothing if (<TD>)
        $th = ($hasHead?$table.rows[(0)].outerHTML:"");
        // count the number of pages
        var $pageCount = Math.ceil($rowCount / $n);
        // if we had one page only, then we have nothing to do ..
        if ($pageCount > 1) {
            // assign each row outHTML (tag name & innerHTML) to the array
            for ($i = $j,$ii = 0; $i < $rowCount; $i++, $ii++)
                $tr[$ii] = $table.rows[$i].outerHTML;
            // create a div block to hold the buttons
            $table.insertAdjacentHTML("afterend","<div id='buttons'></div");
            // the first sort, default page is the first one
            sort(1);
        }
        // ($p) is the selected page number. it will be generated when a user clicks a button
        function sort($p) {
            /* create ($rows) a variable to hold the group of rows
            ** to be displayed on the selected page,
            ** ($s) the start point .. the first row in each page, Do The Math
            */
            var $rows = $th,$s = (($n * $p)-$n);
            for ($i = $s; $i < ($s+$n) && $i < $tr.length; $i++)
                $rows += $tr[$i];
            
            // now the table has a processed group of rows ..
            $table.innerHTML = $rows;
            // create the pagination buttons
            document.getElementById("buttons").innerHTML = pageButtons($pageCount,$p);
            // CSS Stuff
            document.getElementById("id"+$p).setAttribute("class","active");
        }
        // ($pCount) : number of pages,($cur) : current page, the selected one ..
        function pageButtons($pCount,$cur) {
            /* this variables will disable the "Prev" button on 1st page
               and "next" button on the last one */
            var $prevDis = ($cur == 1)?"disabled":"",
                $nextDis = ($cur == $pCount)?"disabled":"",
                /* this ($buttons) will hold every single button needed
                ** it will creates each button and sets the onclick attribute
                ** to the "sort" function with a special ($p) number..
                */
                $buttons = "<input type='button' value='&lt;&lt; Prev' onclick='sort("+($cur - 1)+")' "+$prevDis+">";
            for ($i=1; $i<=$pCount;$i++)
                $buttons += "<input type='button' id='id"+$i+"'value='"+$i+"' onclick='sort("+$i+")'>";
            $buttons += "<input type='button' value='Next &gt;&gt;' onclick='sort("+($cur + 1)+")' "+$nextDis+">";
            return $buttons;
        }
    </script>
</body>
</html>
