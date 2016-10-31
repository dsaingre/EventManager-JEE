<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- Compiled and minified Materialize CSS -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
      <!-- icons -->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <link href="<app:uri src="/assets/css/global.css"/>" rel="stylesheet">
      <!-- Compiled and minified Materialize JavaScript -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dino Planner - Login</title>
</head>
<body>
    <%@ include file="header.jsp" %>

    <main>
        <div class="container">
            <div class="row">
                <c:if test="${event == null}">
                    <form class="col s6 push-s3" method="POST" action="<app:uri src="/authentication"/>">
                </c:if>
                <c:if test="${event != null}">
                    <form class="col s6 push-s3" method="POST" action="<app:uri src="/authentication"/>?event=${event.id}">
                </c:if>
                    <div class="row">
                        <h3 class="center-align">
                            Identification
                        </h3>
                        <h6 class="center-align">
                            Veuillez renseigner votre adresse email et votre mot de passe pour vous connecter
                            <c:if test="${event == null}">
                                .
                            </c:if>
                            <c:if test="${event != null}">
                                et ainsi vous <span style="font-weight: bold;">inscrire à "${event.name}"</span>
                            </c:if>
                        </h6>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">perm_identity</i>
                            <input id="email" name="email" type="email" class="validate">
                            <label for="email" data-error="Veuillez entrer une adresse mail valide">Email (exemple : john.doe@exemple.com)</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">vpn_key</i>
                            <input id="password" name="password" type="password">
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
                        <a href="<app:uri src="/help"/>" id="helplink">Besoin d'aide ?</a>
                    </div>
                </form>
            </div>
        </div>
    </main>


    <%@ include file="footer.jsp" %>


    <script type="text/javascript" src="<app:uri src="/assets/js/lib/jquery-2.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<app:uri src="/assets/js/lib/materialize.min.js"/>"></script>
    <script type="text/javascript" src="<app:uri src="/assets/js/login.js"/>"></script>
</body>
</html>