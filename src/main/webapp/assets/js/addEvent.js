function addClass(element, clazz){
    element.className += " " + clazz;
}

function removeClass(element, clazz){
    element.className =
        element.className.replace(new RegExp(clazz, 'g'), "");
}

$(document).ready(function() {
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
    });
});

$('.timepicker').pickatime({
    autoclose: false,
    twelvehour: false
  });