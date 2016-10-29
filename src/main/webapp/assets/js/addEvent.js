function addClass(element, clazz){
    element.className += " " + clazz;
}

function removeClass(element, clazz){
    element.className =
        element.className.replace(new RegExp(clazz, 'g'), "");
}


var startDateField = document.getElementById("start_date");
var startTimeField = document.getElementById("start_time");
var endDateField = document.getElementById("end_date");
var endTimeField = document.getElementById("end_time");

// Initialisation config
$(document).ready(function() {
    $('.datepicker').pickadate({
        selectMonths: true, // month dropdown
        selectYears: 5, // 5 years dropdown
        weekdaysFull: ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'],
        weekdaysShort: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'],
        monthsFull: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        
     // First day of the week
        firstDay: 1,
        
     // Minimum pickable
        min: new Date(),
        
     // Buttons
//        today: 'Aujourd\'hui',
//        clear: 'Effacer',
//        close: 'Fermer',
        
     // Accessibility labels
        labelMonthNext: 'Mois suivant',
        labelMonthPrev: 'Mois précédent',
        labelMonthSelect: 'Sélectionner un mois',
        labelYearSelect: 'Sélectionner une année',

        // Formats
        format: 'd mmmm yyyy',
        formatSubmit: 'd/mm/yyyy',
        
        onSet: function(e) {
        	dateFieldCallBack();
        }
    });
});

$('.timepicker').pickatime({
    autoclose: false,
    twelvehour: false
  });

//startDateField.addEventListener("onSelect", function(e) {
//	fieldCallBack(startDateField);
//});
startTimeField.addEventListener("onSelect", function(e) {
	fieldCallBack(startTimeField);
});
//endDateField.addEventListener("onSelect", function(e) {
//	fieldCallBack(endDateField);
//});
endTimeField.addEventListener("onSelect", function(e) {
	fieldCallBack(endTimeField);
});

function dateFieldCallBack() {
	var start = startDateField.value
	var end = endTimeField.value
	if (start.length != 0 && end.length != 0 && start < end) {
		addClass(startDateField, "valid");
		removeClass(startDateField, "invalid");
		addClass(endTimeField, "valid");
		removeClass(endTimeField, "invalid");
	} else {
		addClass(startDateField, "invalid");
		removeClass(startDateField, "valid");
		addClass(endTimeField, "invalid");
		removeClass(endTimeField, "valid");
	}
}

function timeFieldCallBack() {
	if (field.value.length > 0) {
		addClass(field, "valid");
		removeClass(field, "invalid");
	} else {
		addClass(field, "invalid");
		removeClass(field, "valid");
	}
}







