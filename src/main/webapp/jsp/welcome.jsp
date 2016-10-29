<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="/WEB-INF/app.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>Dino Planner</title>
</head>
<body>
    <%@ include file="header.jsp" %>

    <main>
          <div class="row ">
          	<div class="col s11 push-s1">
          		<div class="row">
          			<h4>Rechercher un événement</h4>
          		</div>
          		<div class="row">
          			<div class="input-field col s2">
			          <input id="last_name" type="text" class="validate">
			          <label for="last_name">Lieu </label>
			        </div>
			        <div class="input-field col s2">
  						<input id="date_event" type="date" class="datepicker">
  						<label for="date_event">Date événement</label>
			        </div>
			        <div class="input-field col s2">
			        	<button class="btn waves-effect waves-light" type="submit" name="action" id="connect">Rechercher
	                        <i class="material-icons right">send</i>
	                    </button>
			        </div>
          		</div>
          	</div>
          </div>
          
          <div class="row">
			<c:choose>
				<c:when test="${events.size() == 0}">
					<h3 class="center-align">Aucun événement trouvé</h3>
				</c:when>
				<c:otherwise>
					<c:forEach items="${events}" var="event">
						<div class="col s6 m6">
				          <div class="card blue-grey darken-1">
				            <div class="card-content white-text">
				              <span class="card-title"><c:out value="${event.name}"/></span>
				              <p><c:out value="${event.description}"/></p>
				              <p>Début : <c:out value="${event.startDate}"/></p>
				            </div>
				            <div class="card-action">
				              <a href="/event/${event.slugs.get(0).getSlug()}">Détail de l'événement</a>
				            </div>
				          </div>
				        </div>
					</c:forEach>
				</c:otherwise>
			</c:choose> 
          </div>
          
    </main>

    <%@ include file="footer.jsp" %>

    <script type="text/javascript" src="<app:uri src="/assets/js/lib/jquery-2.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<app:uri src="/assets/js/lib/materialize.min.js"/>"></script>
<script>
    $(document).ready(function() {
        $('.datepicker').pickadate({
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 15 // Creates a dropdown of 15 years to control year
        });
    });
</script>
</body>
</html>