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
	<title>Dino Planner - Login</title>
</head>
<body>
	<nav>
      <div class="nav-wrapper purple lighten-2" style="padding-left: 20px">
        <c:a href="/" clas="brand-logo">Dino Planner</c:a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><c:a href="/login">S'identifier</c:a></li>
            <li class="active"><c:a href="/signup">Créer un compte</c:a></li>
        </ul>
      </div>
    </nav>

    <div class="container">
        <div class="row">
        </div>
    </div>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
    <script type="text/javascript" src="assets/js/login.js"></script>
</body>
</html>