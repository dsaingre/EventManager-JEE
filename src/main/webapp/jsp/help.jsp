<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/a.tld"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Compiled and minified Materialize CSS -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
      <!-- icons -->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <link href="assets/css/global.css" rel="stylesheet">
    <!-- Compiled and minified Materialize JavaScript -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dino Planner - Aide</title>
</head>
<body>
	<nav>
      <div class="nav-wrapper purple lighten-2" style="padding-left: 20px">
        <a href="" class="brand-logo">Dino Planner</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li class="active"><c:a href="/login">S'identifier</c:a></li>
            <li><c:a href="/signup">Créer un compte</c:a></li>
        </ul>
      </div>
    </nav>

    <div class="container">
        <div class="hspace-30"></div>
        <div class="row">
            <c:a href="/login">← Retour a la page d'identification</c:a>
        </div>
        <div class="row">
            <h3 class="center-align">
                Besoin d'aide pour vous connecter ?
            </h3>
        </div>
        <div class="row">
            <form class="col s6">
                <div class="row">
                    <p>Vous pouvez renseigner votre adresse email dans le champ ci-dessous. Nous envoyerons alors un nouveau mot de passe pour que vous puissez de nouveau vous connecter.</p>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">perm_identity</i>
                        <input id="email" type="email" class="validate">
                        <label for="email" data-error="Veuillez entrer une adresse mail valide">Email (exemple : john.doe@exemple.com)</label>
                    </div>
                </div>
                <div class="row">
                    <div class="center">
                        <button class="btn waves-effect waves-light disabled" type="submit" name="action" id="connect">Me renvoyer un mot de passe
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
            <div class="col s6">
                <div class="hspace-50"></div>
                <div class="hspace-50"></div>
                <div class="hspace-50"></div>
                <h5 class="center">Vous pouvez aussi nous contacter à l'adresse mail : <a href="mailto:fake@eventmanager.fr">fake@eventmanager.fr</a></h5>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
    <script type="text/javascript" src="assets/js/help.js"></script>
</body>
</html>