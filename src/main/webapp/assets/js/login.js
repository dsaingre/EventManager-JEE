function addClass(element, clazz) {
	element.className += " " + clazz;
}

function removeClass(element, clazz) {
	element.className = element.className.replace(new RegExp(clazz, 'g'), "");
}

var emailField = document.getElementById("email");
var helpLink = document.getElementById("helplink");
var passwordField = document.getElementById("password");
var connectButton = document.getElementById("connect");

var email = "";
var password = "";
var baseHelpLink = helpLink.getAttribute("href");

fieldCallback();
activeOrDesactiveLoginButton();

emailField.addEventListener("keyup", function(e) {
	fieldCallback();
});
passwordField.addEventListener("keyup", function(e) {
	fieldCallback();
});

helpLink.addEventListener("click", function(e) {
	email = emailField.value;
	helpLink.setAttribute("href", baseHelpLink + "?email=" + email);
});

function fieldCallback() {
	email = emailField.value;
	password = passwordField.value;
	activeOrDesactiveLoginButton();
}

function activeOrDesactiveLoginButton() {
	if (email.length != 0 && password.length != 0) {
		removeClass(connectButton, "disabled")
	} else {
		addClass(connectButton, "disabled")
	}
}
