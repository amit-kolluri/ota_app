<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	

<c:choose>
    <c:when test="${sessionScope.user.roleId == '3'}">
        <%@include file="../admin/sidebar-admin.jsp" %>
    </c:when>
    <c:when test="${sessionScope.user.roleId == '2'}">
        <%@include file="../trainer/sidebar-trainer.jsp" %>
    </c:when>    
    <c:otherwise>
        <%@include file="../trainee/sidebar-trainee.jsp" %>
    </c:otherwise>
</c:choose>