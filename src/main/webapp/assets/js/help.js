


function addClass(element, clazz){
    element.className += " " + clazz;
}

function removeClass(element, clazz){
    element.className =
        element.className.replace(new RegExp(clazz, 'g'), "");
}


var emailField = document.getElementById("email");
var connectButton = document.getElementById("connect");

var email = "";

emailFieldCallback();
activeOrDesactiveLoginButton();





emailField.addEventListener("keyup", function(e){
    emailFieldCallback();
});


function emailFieldCallback(){
    email = emailField.value;
    activeOrDesactiveLoginButton();
}


function activeOrDesactiveLoginButton(){
    if (email.length != 0){
        removeClass(connectButton, "disabled")
    }else{
        addClass(connectButton, "disabled")
    }
}