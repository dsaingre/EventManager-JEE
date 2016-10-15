<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Compiled and minified Materialize CSS -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
      <!-- icons -->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!-- Compiled and minified Materialize JavaScript -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dino Planner - Login</title>
</head>
<body>
	<nav>
      <div class="nav-wrapper purple lighten-2" style="padding-left: 20px">
        <a href="#" class="brand-logo">Dino Planner</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li><a href="#">Identification</a></li>
        </ul>
      </div>
    </nav>

    <div class="container">
        <div class="row">
            <form class="col s6 push-s3">
                <div class="row">
                    <h3 class="center-align">
                        Identification
                    </h3>
                    <h6 class="center-align">
                        Veuillez entrer votre email et votre mot de passe pour vous connecter.
                    </h6>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">perm_identity</i>
                        <input id="email" type="email" class="validate">
                        <label for="email" data-error="Veuillez entrer une adresse mail valide">Email (exemple : john.doe@exemple.com)</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">vpn_key</i>
                        <input id="password" type="password">
                        <label for="password">Mot de passe</label>
                    </div>
                </div>
                <div class="row">
                    <div class="center">
                        <button class="btn waves-effect waves-light disabled" type="submit" name="action" id="connect">Se connecter
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>

                <div class="center">
                    <a href="">Pas encore inscrit(e) ?</a> |
                    <a href="">Besoin d'aide ?</a>
                </div>
            </form>
        </div>
    </div>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
    <script type="text/javascript" src="assets/js/login.js"></script>
</body>
</html>