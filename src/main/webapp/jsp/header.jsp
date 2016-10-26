<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav>

    <div class="nav-wrapper purple lighten-2" style="padding-left: 20px">
        <a href="<app:uri src="/"/>" class="brand-logo">Dino Planner</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="<app:uri src="/events"/>">Parcourir les évènements</a></li>
            <li><a href="<app:uri src="/myevents"/>">Les évènements que j'ai créé</a></li>
            <li><a href="<app:uri src="/addevent"/>">Ajouter un évènement ${sessionScope.containsKey("user")}</a></li>
            <c:if test="${! sessionScope.containsKey(\"user\")}">
                <li class="${isLoginPage}"><a href="<app:uri src="/login"/>">S'identifier</a></li>
                <li class="${isSignupPage}"><a href="<app:uri src="/signup"/>">Créer un compte</a></li>
            </c:if>
            <c:if test="${sessionScope.containsKey(\"user\")}">
                <li><a href="<app:uri src="/logout"/>">Se déconnecter</a></li>
            </c:if>
        </ul>
    </div>
</nav>


<div class="hspace-50"></div>


<c:if test="${app:flashExist(sessionScope, \"error\")}">
    <div class="alert alert-error">${app:consumeFlash(sessionScope, "error")}</div>
</c:if>

<c:if test="${app:flashExist(sessionScope, \"info\")}">
    <div class="alert alert-info">${app:consumeFlash(sessionScope, "info")}</div>
</c:if>