<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${sessionScope.image.base64Image != null}">
		<img class="profile_image" src="data:image/jpeg;base64,${image.base64Image}"/>
	</c:when>
</c:choose>