var current_page = 1;

$('#previous').click(function(){
	if(current_page == 1){
		console.log("1");
		return false;
	}
	document.getElementById(""+current_page).classList.remove("active"); /* on l'enlève de la classe "active" */
	current_page--;
	document.getElementById(""+current_page).classList.add("active"); /* on l'ajoute dans la classe "active" */
	return false; /* empêche le scroll automatique vers le haut lorsqu'on clique sur un des numéros de pages */
});

$('#1').click(function(){
	document.getElementById(""+current_page).classList.remove("active");
	current_page = 1;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});

$('#2').click(function(){
	document.getElementById(""+current_page).classList.remove("active");
	current_page = 2;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});

$('#3').click(function(){
	document.getElementById(""+current_page).classList.remove("active");
	current_page = 3;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});

$('#4').click(function(){
	document.getElementById(""+current_page).classList.remove("active");
	current_page = 4;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});

$('#5').click(function(){
	document.getElementById(""+current_page).classList.remove("active");
	current_page = 5;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});

$('#6').click(function(){
	document.getElementById(""+current_page).classList.remove("active");
	current_page = 6;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});

$('#7').click(function(){
	document.getElementById(""+current_page).classList.remove("active");
	current_page = 7;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});

$('#8').click(function(){
	document.getElementById(""+current_page).classList.remove("active");
	current_page = 8;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});

$('#9').click(function(){
	document.getElementById(""+current_page).classList.remove("active");
	current_page = 9;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});


$('#next').click(function(){
	if(current_page == 9){
		console.log("9");
		return false;
	}
	document.getElementById(""+current_page).classList.remove("active");
	current_page++;
	document.getElementById(""+current_page).classList.add("active");
	return false;
});