<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<%@ include file="../header.jsp"%>


	<main>

	<div class="container">

		<h3>Les événements que j'ai créé</h3>

		<c:if test="${events.isEmpty()}">
			<p class="align-center">
				Vous n'avez pas créé d'événement. <a href="<app:uri src="/addevent"/>">Créer un événement</a>.
			</p>
		</c:if>

		<c:forEach var="event" items="${events}">

			<div class="row">
				<div class="col s6 m12">
					<div class="card cyan darken-4">
						<div class="card-content white-text">
							<div class="valign-wrapper">
								<span class="card-title valign col s12">${event.name}</span>

								<c:choose>
									<c:when test="${event.publishingDate != null}">
										<div class="chip teal lighten-2 white-text right">Publié</div>
									</c:when>
									<c:otherwise>
										<a href="<app:uri src="/events/"/>${event.getId()}/publish"
											class="waves-effect waves-light btn teal lighten-2 right">Publier</a>
									</c:otherwise>
								</c:choose>
							</div>
							
							<p>${event.description}</p>
						</div>
						<div class="card-action white-text">
							<a href="<app:uri src="/events/" />${event.slug}"
								class="lime-text text-accent-3"> <i
								class="tiny material-icons">search</i> Voir les informations
							</a> <a href="<app:uri src="/update/event/"/>${event.getId()}"
								class="lime-text text-accent-3"> <i
								class="tiny material-icons">mode_edit</i> Modifier
							</a> <a href="<app:uri src="/events/"/>${event.getId()}/delete"
								onclick="confirm('Êtes-vous sûr(e) de vouloir supprimer cet événement ? Les données ne pourront pas être récupérées.')"
								class="lime-text text-accent-3"> <i
								class="tiny material-icons">delete</i> Supprimer
							</a>

						</div>
					</div>
				</div>
			</div>

		</c:forEach>
	</div>

	</main>

	<%@ include file="../footer.jsp"%>

	<script type="text/javascript"
		src="<app:uri src="/assets/js/lib/jquery-2.1.1.min.js"/>"></script>
	<script type="text/javascript"
		src="<app:uri src="/assets/js/lib/materialize.min.js"/>"></script>
</body>
</html>