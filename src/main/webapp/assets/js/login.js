


function addClass(element, clazz){
    element.className += " " + clazz;
}

function removeClass(element, clazz){
    element.className =
        element.className.replace(new RegExp(clazz, 'g'), "");
}


var emailField = document.getElementById("email");
var passwordField = document.getElementById("password");
var connectButton = document.getElementById("connect");

var email = "";
var password = "";

emailFieldCallback();
passwordFieldCallback();
activeOrDesactiveLoginButton();





emailField.addEventListener("keyup", function(e){
    emailFieldCallback();
});
passwordField.addEventListener("keyup", function(e){
    passwordFieldCallback();
});


function emailFieldCallback(){
    email = emailField.value;
    activeOrDesactiveLoginButton();
}


function passwordFieldCallback(){
    password = passwordField.value;
    activeOrDesactiveLoginButton();
}

function activeOrDesactiveLoginButton(){
    if (email.length != 0 && password.length != 0){
        removeClass(connectButton, "disabled")
    }else{
        addClass(connectButton, "disabled")
    }
}