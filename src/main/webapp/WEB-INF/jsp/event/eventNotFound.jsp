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
<%@ include file="../header.jsp" %>


<main>

    <div class="container">
        <h3>Impossible de trouver l'événement.</h3>
        <p>Nous vous prions de nous excuser pour la gène occasionnée.</p>
        <a href="<app:uri src="/events"/>">← Retour à la page des événements</a>
    </div>

</main>

<%@ include file="../footer.jsp" %>

<script type="text/javascript" src="<app:uri src="/assets/js/lib/jquery-2.1.1.min.js"/>"></script>
<script type="text/javascript" src="<app:uri src="/assets/js/lib/materialize.min.js"/>"></script>
</body>
</html>