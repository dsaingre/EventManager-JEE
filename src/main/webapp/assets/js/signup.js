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
var isValidEmail = false; 
var password = "";
var passwordConf = "";

fieldCallback();
activeOrDesactiveLoginButton();

emailField.addEventListener("keyup", function(e) {
	fieldCallback();
});
emailField.addEventListener("focusout", function(e) {
	fieldCallback();
});
passwordField.addEventListener("keyup", function(e) {
	fieldCallback();
});
passwordConfField.addEventListener("keyup", function(e) {
	fieldCallback();
});
passwordConfField.addEventListener("focusout", function(e) {
	fieldCallback();
});

function fieldCallback() {
	email = emailField.value;
	isValidEmail = emailField.classList.contains("valid");
	password = passwordField.value;
	passwordConf = passwordConfField.value;
	activeOrDesactiveLoginButton();
}


function activeOrDesactiveLoginButton() {
	if (password == passwordConf) {
		if (password.length != 0 && passwordConf.length != 0) {
			removeClass(passwordField, "invalid")
			removeClass(passwordConfField, "invalid")
			addClass(passwordField, "valid")
			addClass(passwordConfField, "valid")
			if (email.length != 0 && isValidEmail) {
				removeClass(connectButton, "disabled")
				return
			}
		}
	} else {
		addClass(passwordField, "invalid")
		addClass(passwordConfField, "invalid")
	}
		addClass(connectButton, "disabled")
}
