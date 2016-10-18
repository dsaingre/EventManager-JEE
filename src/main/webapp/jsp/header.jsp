<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="/WEB-INF/app.tld"%>

<nav>

    <div class="nav-wrapper purple lighten-2" style="padding-left: 20px">
        <a href="<app:uri src="/"/>" class="brand-logo">Dino Planner</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li class="${isLoginPage}"><a href="<app:uri src="/login"/>">S'identifier</a></li>
            <li class="${isSignupPage}"><a href="<app:uri src="/signup"/>">Créer un compte</a></li>
        </ul>
    </div>
</nav>


<div class="hspace-50"></div>