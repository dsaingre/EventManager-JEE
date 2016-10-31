<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="/WEB-INF/app.tld"%>

<div class="hspace-50"></div>

<footer class="page-footer purple lighten-2">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Dino planner</h5>
                <p class="grey-text text-lighten-4">Dino planner est un projet que nous avons mené à l'École des Mines de Nantes. L'objectif est non seulement de mettre en application les cours de JEE mais aussi d'affiner notre sens critique vis-à-vis de l'ergonomie.</p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Liens</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="<app:uri src="/login"/>">S'identifier</a></li>
                    <li><a class="grey-text text-lighten-3" href="<app:uri src="/signup"/>">Créer un compte</a></li>
                    <%--<li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>--%>
                    <%--<li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>--%>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright purple lighten-1">
        <div class="container">
            Licence GPL-3.0
            <a class="grey-text text-lighten-4 right" href="https://github.com/Prygan/EventManager-JEE">Github</a>
        </div>
    </div>
</footer>