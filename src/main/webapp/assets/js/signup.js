function addClass(element, clazz) {
	element.className += " " + clazz;
}

function removeClass(element, clazz) {
	element.className = element.className.replace(new RegExp(clazz, 'g'), "");
}

var emailField = document.getElementById("email");
var passwordField = document.getElementById("password");
var passwordConfField = document.getElementById("password_conf");
var connectButton = document.getElementById("connect");

var email = "";
var password = "";
var passwordConf = "";

emailFieldCallback();
passwordFieldCallback();
passwordConfFieldCallback();
activeOrDesactiveLoginButton();

emailField.addEventListener("keyup", function(e) {
	emailFieldCallback();
});
passwordField.addEventListener("keyup", function(e) {
	passwordFieldCallback();
});
passwordConfField.addEventListener("keyup", function(e) {
	passwordConfFieldCallback();
});
passwordConfField.addEventListener("focusout", function(e) {
	passwordConfFieldCallback();
});

function emailFieldCallback() {
	email = emailField.value;
	activeOrDesactiveLoginButton();
}

function passwordFieldCallback() {
	password = passwordField.value;
	activeOrDesactiveLoginButton();
	passwordMatching();
}

function passwordConfFieldCallback() {
	passwordConf = passwordConfField.value;
	activeOrDesactiveLoginButton();
	passwordMatching(passwordConf);
}

function activeOrDesactiveLoginButton() {
	if (email.length != 0 && password.length != 0 && passwordConf.length != 0) {
		removeClass(connectButton, "disabled")
	} else {
		addClass(connectButton, "disabled")
	}
}

function passwordMatching(pwdConf) {
	if (password == pwdConf) {
		removeClass(connectButton, "disabled")
		removeClass(passwordField, "invalid")
		removeClass(passwordConfField, "invalid")
	} else if (pwdConf != undefined && pwdConf.length != 0) {
		addClass(connectButton, "disabled")
		addClass(passwordField, "invalid")
		addClass(passwordConfField, "invalid")
	}
}
