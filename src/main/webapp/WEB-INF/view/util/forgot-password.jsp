<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<s:url var="url_style" value="../static/css/style.css" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Forgot Password</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${url_style}">

</head>

<body>
   <%@ include file="../common/header-default.jsp" %> 
        <section class="content">
                <div class="Forgot-Password__Container">
                   
                    <fieldset>
                    <div class="exception_format">
                        <h1 class="EmailNotFound_Warning"> ${param.err}</h1>
                        </div>
                        <div class="Container_Padding">
                            <h1 class = "title_size">Forgot Password</h1>
                              <div class="Email-Form__Container">
                     <form:form action="processForgotPassword.htm" method="post" modelAttribute="command">
                                    Email: <input type="text" name="Email">
                                    <br>
                                    <div id="Forgot-Password__Submit-Button">							
                                       <input id="Submit__Hover-Effect" type="Submit"  value="Submit"> 
                                    </div>
                                 </form:form>
                                </div>
                        </div>
                    </fieldset>
                </div>           
        </section>
    
    <%@include file="../common/footer.jsp"%>
    

    
</body>

</html>
