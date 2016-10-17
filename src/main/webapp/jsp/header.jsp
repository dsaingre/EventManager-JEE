<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/a.tld"%>

<nav>

    <div class="nav-wrapper purple lighten-2" style="padding-left: 20px">
        <c:a href="/" clas="brand-logo">Dino Planner</c:a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li class="${isLoginPage}"><c:a href="/login">S'identifier</c:a></li>
            <li class="${isSignupPage}"><c:a href="/signup">Créer un compte</c:a></li>
        </ul>
    </div>
</nav>


<div class="hspace-50"></div>