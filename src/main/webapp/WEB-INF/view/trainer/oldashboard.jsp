<!-- Author: Geon Gayles -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Trainer Dashboard</title>
    <meta name="viewport" content="w1th=device-w1th, initial-scale=1">
    
    <link rel="stylesheet" href="../../../../webapp/static/css/landing.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway%7CUbuntu" rel="stylesheet">
    

</head>


<body>
        <div class="wrapper">
<%@include file="../common/header-default.jsp"%>
		
			<%@include file="sidebar-trainer.jsp"%>
            <section class="content">
                <h1 class="content_header"></h1>
                <div class="content_container">
                        <div class="trainerDashboard">
                            <form class="trainerDashboard_form">
                                
                                <button class="trainerDashboard_section trainerDashboard_traineesBox" formaction="#">
                                    <div class="trainerDashboard_traineesBox__trainee">
                                        <h2>Trainees</h2>
                                    </div>
                                    <div class="trainerDashboard_boxDiv trainerDashboard_traineesBox__traineeNumber">
                                        <p>27</p>
                                    </div>
                                </button>
                            
                                <button class="trainerDashboard_section trainerDashboard_reportsBox" formaction="#">
                                    <div class="trainerDashboard_reportsBox__reports">
                                        <h2>Reports</h2>
                                    </div>
                                    <div class="trainerDashboard_reportsBox__reportsNumber">
                                        <p>3 updated</p>
                                    </div>
                                </button>
                            
                                <button class="trainerDashboard_section trainerDashboard_questionsBox" formaction="#">
                                    <div class="trainerDashboard_questionsBox__questions">
                                        <h2>Questions</h2>
                                    </div>
                                    <div class="trainerDashboard_questionsBox__questionsNumber">
                                        <p>100</p>
                                    </div>
                                </button>
                                
                                <button class="trainerDashboard_section trainerDashboard_batchesBox" formaction="#">
                                        <div class="trainerDashboard_batchesBox__batches">
                                            <h2>Batches</h2>
                                        </div>
                                        <div class="trainerDashboard_batchesBox__batchesNumber">
                                            <p>6</p>
                                        </div>
                                        <div class="trainerDashboard_batchesBox__currentBatch">
                                            <h2>Current Batch</h2>
                                        </div>
                                        <div class="trainerDashboard_batchesBox__currentBatchText">
                                            <p>Moline 2018</p>
                                        </div>
                                    </button>
                              
                            
                                <button class="trainerDashboard_section trainerDashboard_testsBox" formaction="#">
                                    <div class="trainerDashboard_testsBox__tests">
                                        <h2>Tests</h2>
                                    </div>
                                    <div class="trainerDashboard_testsBox__testsNumber">
                                        <p>30</p>
                                    </div>
                                </button>
                            </form>
                                 
                            </div>
                </div>
                </div>  
            </section>
        </div>
       <%@include file="../common/footer.jsp"%>
    
        
        <script src="../../../../webapp/static/js/jquery.js"></script>
        
        <script src="../../../../webapp/static/js/main.js"></script>
    
    <script>
        $(function () {
            $('#header').load("../common/header.html");
            $('#sidebar').load("trainer-sidebar.html");
            $("#footer").load("../common/footer.html");
        });
    </script>
    </body>

</html>