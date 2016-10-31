<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<!-- Compiled and minified Materialize CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
<!-- icons -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="<app:uri src="/assets/css/global.css"/>" rel="stylesheet">
<!-- Compiled and minified Materialize JavaScript -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dino Planner - Login</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>


	<main>

	<div class="container">
		<a href="<app:uri src="/myevents"/>">← Retour</a>
		<div class="blue-grey lighten-1 event-box white-text">
			<div class="row event-name valign-wrapper">
				<h4 class="valign col s10">${event.name}</h4>
				<c:choose>
					<c:when test="${event.owners.contains(user)}">
						<div class="chip center col s5 right">Vous êtes le créateur
							de cet événement</div>
					</c:when>
					<c:when test="${event.registeredPersons.contains(user)}">
						<div class="chip center col s3 right">Vous êtes déjà inscrit</div>
					</c:when>
					<c:when test="${event.participants.contains(user)}">
						<div class="chip center col s3 right">Vous êtes déjà inscrit</div>
					</c:when>
					<c:otherwise>
						<a class="waves-effect waves-light btn right"
							href="<app:uri src="/events/"/>${event.id}/apply">S'inscrire</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<p>
					<i class="material-icons">room</i> ${event.location}
				</p>
				<p>
					<i class="material-icons">today</i> Du <span
						style="font-weight: bold;"> <fmt:formatDate
							value="${event.startDate}" pattern="dd MMMM yyyy, HH:mm" /></span> au <span
						style="font-weight: bold;"><fmt:formatDate
							value="${event.endDate}" pattern="dd MMMM yyyy, HH:mm" /></span>
				</p>
				<div class="event-desc blue-grey lighten-3">
					<p>${event.description}</p>
				</div>

				<c:if test="${event.owners.contains(user)}">
					<c:if
						test="${!event.participants.isEmpty() || !event.registeredPersons.isEmpty()}">
						<table
							class="highlight bordered grey-text blue-grey lighten-3 text-darken-2 event-user-list">
							<thead>
								<tr>
									<th data-field="id">Participants</th>
								</tr>
							</thead>


							<tbody>
								<c:forEach items="${event.participants}" var="user">
									<tr>
										<td>${user.firstName} ${user.lastName}</td>
									</tr>
								</c:forEach>
								<c:forEach items="${event.registeredPersons}" var="user">
									<tr>
										<td>${user.firstName} ${user.lastName}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</c:if>









				Créé par :
				<c:forEach items="${event.owners}" var="owner">
					<div class="chip class teal lighten-3">${owner.firstName}
						${owner.lastName}</div>
				</c:forEach>
			</div>

		</div>
	</div>

	</main>

	<%@ include file="/jsp/footer.jsp"%>

	<script type="text/javascript"
		src="<app:uri src="/assets/js/lib/jquery-2.1.1.min.js"/>"></script>
	<script type="text/javascript"
		src="<app:uri src="/assets/js/lib/materialize.min.js"/>"></script>
</body>
</html>