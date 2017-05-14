function validateEmail(message) {
	var email = document.form.email.value;
	atpos = email.indexOf("@");
	dotpos = email.lastIndexOf(".");
	if(atpos < 1 || (dotpos - atpos) < 2){
		alert(message);
		document.form.email.focus();
		return false;
	}
	return true;
}
