<div>
	<a href="#close" title="Close" class="close">x</a>
	<h2 id="modalTitle" style="text-align: center">OTA Creators</h2>
	<div class="creator-div">
		<c:forEach var="creator" items="${creators}">
			<div class="creator-badge">
				<img class="creator-image" src="./getProfileImage/${creator.id}.jpg" alt="No Image"/>
				<p>${creator.firstName} ${creator.lastName}</p>
			</div>
		</c:forEach>
	</div>
</div>
