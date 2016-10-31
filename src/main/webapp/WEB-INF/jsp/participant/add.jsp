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
    <link href="<app:uri src="/assets/css/materialize.clockpicker.css"/>"
          rel="stylesheet">
    <!-- Compiled and minified Materialize JavaScript -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dino Planner</title>
</head>
<body>
<%@ include file="../header.jsp"%>


<main>

    <div class="container">
        <h3>S'inscrire à "${event.name}"</h3>

        <h5>Si vous avez déjà un compte, nous vous invitons à vous <a href="<app:uri src="/login"/>?event=${event.id}">identifier</a>.</h5>

        <p>Pour que vous inscrire à cet événement, nous avons besoin des informations suivantes : </p>

        <form action="<app:uri src="/events/"/>${event.id}/participants/add" method="POST">


            <div class="row">
                <div class="input-field col s12">
                    <input id="first_name" type="text" name="first_name" class="validate"> <label
                        for="first_name">Prénom </label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <input id="last_name" type="text" name="last_name" class="validate"> <label
                        for="last_name">Nom </label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <input id="email" name="email" type="email" class="validate">
                    <label for="email" data-error="Veuillez entrer une adresse mail valide">Email (exemple : john.doe@exemple.com)</label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <input id="company" type="text" name="company" class="validate"> <label
                        for="company">Entreprise </label>
                </div>
            </div>
            <div class="row" style="margin-top:60px;">
                <div class="center">
                    <button class="btn waves-effect waves-light" type="submit"
                            name="action" id="connect">
                        <i class="material-icons left">done</i>
                        Valider l'inscription
                    </button>
                </div>
            </div>
        </form>


    </div>

</main>

<%@ include file="../footer.jsp"%>

<script type="text/javascript"
        src="<app:uri src="/assets/js/lib/jquery-2.1.1.min.js"/>"></script>
<script type="text/javascript"
        src="<app:uri src="/assets/js/lib/materialize.min.js"/>"></script>
<script type="text/javascript"
        src="<app:uri src="/assets/js/lib/materialize.clockpicker.js"/>"></script>
<script type="text/javascript"
        src="<app:uri src="/assets/js/addEvent.js"/>"></script>

</body>
</html>