<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="/WEB-INF/app.tld"%>
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
<title>Dino Planner - Aide</title>
</head>
<body>

	<%@ include file="header.jsp"%>

	<main>

	<div class="container">
		<div class="hspace-30"></div>
		<div class="row">
			<a href="<app:uri src="/login"/>">← Retour a la page
				d'identification</a>
		</div>
		<div class="row">
			<h3 class="center-align">Besoin d'aide pour vous connecter ?</h3>
		</div>
		<div class="row">
			<form class="col s6">
				<div class="row">
					<p>Vous pouvez renseigner votre adresse email dans le champ
						ci-dessous. Nous enverrons alors un nouveau mot de passe pour que
						vous puisiez de nouveau vous connecter.</p>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<i class="material-icons prefix">perm_identity</i> <input
							id="email" type="email" class="validate" value="${email}">
						<label for="email"
							data-error="Veuillez entrer une adresse mail valide">Email
							(exemple : john.doe@exemple.com)</label>
					</div>
				</div>
				<div class="row">
					<div class="center">
						<button class="btn waves-effect waves-light disabled"
							type="submit" name="action" id="connect">
							Me renvoyer un mot de passe <i class="material-icons right">send</i>
						</button>
					</div>
				</div>
			</form>
			<div class="col s6">
				<div class="hspace-50"></div>
				<div class="hspace-50"></div>
				<div class="hspace-50"></div>
				<h5 class="center">
					Vous pouvez aussi nous contacter à l'adresse mail : <a
						href="mailto:fake@eventmanager.fr">fake@eventmanager.fr</a>
				</h5>
			</div>
		</div>
	</main>


	<%@ include file="footer.jsp"%>


	<script type="text/javascript"
		src="<app:uri src="/assets/js/lib/jquery-2.1.1.min.js"/>"></script>
	<script type="text/javascript"
		src="<app:uri src="/assets/js/lib/materialize.min.js"/>"></script>
	<script type="text/javascript"
		src="<app:uri src="/assets/js/help.js"/>"></script>
</body>
</html>