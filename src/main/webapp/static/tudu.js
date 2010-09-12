//Messages and errors
var isMessageShowed = false;
function showMessage(text) {
    isMessageShowed = true;
    $("#message-text").html("<strong>Information:</strong> " + text);
    $("#message").effect("bounce", {}, 250);
    setTimeout("hideMessage()", 5000);
}
function hideMessage() {
    isMessageShowed = false;
    $("#message").effect("blind", {}, 500);
    $("#message-text").html("");
}
var isErrorShowed = false;
function showError(text) {
    isErrorShowed = true;
    $("#error-text").html("<strong>Erreur:</strong> " + text);
    $("#error").effect("bounce", {}, 250);
}
$("#error").ajaxError(function(event, request, settings){
   showError("Une erreur technique s'est produite, votre action a &eacute;t&eacute; annul&eacute;e.")
 });
function hideError() {
    isErrorShowed = false;
    $("#error").effect("blind", {}, 500);
    $("#error-text").html("");
}

//Form
function submitForm() {
    document.forms[0].submit();
}
function resetForm() {
    document.forms[0].reset();
}