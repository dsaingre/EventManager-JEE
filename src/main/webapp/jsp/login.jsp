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
      <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dino Planner - Login</title>
</head>
<body>
	<nav>
      <div class="nav-wrapper purple lighten-2">
        <a href="#" class="brand-logo">Dino Planner</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li><a href="#">Identification</a></li>
        </ul>
      </div>
    </nav>

    <div class="container">
     <form class="col s12">
       <div class="row">
         <h1 class="center-align">
           Identification
         </h1>
       </div>
       <div class="row">
         <div class="input-field col s12">
           <i class="material-icons prefix">perm_identity</i>
           <input id="email" type="email" class="validate">
           <label for="email" data-error="Veuillez entrer une adresse mail valide">Email</label>
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
         <button class="btn waves-effect waves-light right" type="submit" name="action">Se connecter
           <i class="material-icons right">send</i>
         </button>
       </div>
       <div class="row">
        <div class="col s4">
          <p class="center-align">Pas encore inscrit ?</p>
        </div>
        <div class="col s4">
          <p class="center-align">Mot de passe oublié ?</p>
        </div>
        <div class="col s4">
          <p class="center-align">Besoin d'aide ?</p>
        </div>
       </div>
     </form>
    </div>
</body>
</html>