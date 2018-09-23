
<!-- Author: Geon Gayles -->
<%@page import="com.yash.ota.serviceimpl.QuestionServiceImpl"%>
<%@page import="com.yash.ota.service.QuestionService"%>
<%@page import="com.yash.ota.serviceimpl.BatchServiceImpl"%>
<%@page import="com.yash.ota.service.BatchService"%>
<%@page import="com.yash.ota.domain.User"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Trainer Dashboard</title>
<meta name="viewport" content="w1th=device-w1th, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Raleway%7CUbuntu"
    rel="stylesheet">
<link rel="stylesheet" href="static/css/landing.css">
</head>
<body>
    <div class="container">
        <%@include file="../../common/header-default.jsp"%>
        <div class="content">
            <%@include file="../sidebar-trainer.jsp"%>
            <div class="ota-main_container-dashboard">
                <div class="questionedit">
                                    <div class="questionedit_header">
                                        <h2>Questions</h2>
                                    </div>
                            
                                    <div class="questionedit_box">
                                        <div class="questionedit_box_top">
                                            <div class="questionedit_box_testname">
                                                <p>Select Test Name</p>
                                            </div>
                            
                                            <div class="questionedit_box_dropdown">
                                                <form:form action= "./processChangeQuestionsbyTests.htm" modelAttribute="topic" method="POST">
                                                    <form:select path="id" class="questionedit_box_select" name="tests">
                                                        <option disabled selected>Test Name</option>
                                                        <c:forEach items="${topics}" var="topic">
                                                        <form:option value="${topic.id}" id="${topic.id}"><c:out value= "${topic.name}"/> </form:option>
                                                        </c:forEach>
                                                    </form:select>
                                                    
                                                    <input type="submit" value="Submit" ></input>
                                                 </form:form>
                                                    <p id="demo"></p>
                                                    <br>
                                                    <br>
                                            </div>
                                        </div>
                                    
                                    <hr class="questionedit_box_hr">
                                    <c:forEach items="${questions}" var="item">
                            <form action="#" method="post">
                                    <div class="questionedit_box_bottom">
                                        <input type="hidden" value='<c:out value="${item.id}"></c:out>' name="qid">
                                    
                                <div class="questionedit_box_question"><h2>Question</h2>
                                    <textarea class="questionedit_box_textarea" name="question" ><c:out
                                            value="${item.question}"/></textarea>
                                </div>
                                <div class="questionedit_box_answers">
                                    
                                        <div class="questionedit_box_answers_1"><h2>Option 1</h2>
                                            <textarea class="questionedit_box_textarea" rows="" cols="" name="option1"><c:out
                                                    value="${item.option1 }"></c:out></textarea>
                                        </div>
                                        
                                        <div class="questionedit_box_answers_1"><h2>Option 2</h2>
                                            <textarea class="questionedit_box_textarea" rows="" cols="" name="option2"><c:out
                                                    value="${item.option2 }"></c:out></textarea>
                                        </div>
                                         
                                        <div class="questionedit_box_answers_1"><h2>Option 3</h2>
                                            <textarea class="questionedit_box_textarea" rows="" cols="" name="option3"><c:out
                                                    value="${item.option3 }"></c:out></textarea>
                                        </div>
                                        
                                        <div class="questionedit_box_answers_1"><h2>Option 4</h2>
                                            <textarea class="questionedit_box_textarea" rows="" cols="" name="option4"><c:out
                                                    value="${item.option4 }"></c:out></textarea>
                                        </div>
                                        
                                        <div class="questionedit_box_answers_1"><h2>Correct Answer</h2>
                                            <textarea class="questionedit_box_textarea" rows="" cols="" name="correctAnswer"><c:out
                                                    value="${item.correctAnswer}"></c:out></textarea>
                                        </div>
                                        
                                        <div class="questionedit_box_answers_buttonDiv">
                                            <button type="submit" name="delete" formaction="./processdeletequestion.htm">Delete</button>
                                            <button type="submit" name="delete" formaction="./processeditquestion.htm">Edit</button>
                                        </div>
                                    
                                </div>
                            
                                    </div>
                                    </form>
                                    </c:forEach>
                                </div>
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