$('#buttonRechercher').click(function(){
	var temp = _.template($('#templateMapElement').html()); //récupération du template dans le fichier html
	var resultat = temp({ //ce qu'on affiche dans le code template
		"indice":"0",
		"proprietaire":"Murat", //JSON.stringify(User.username) //stringify prend en paramètre un objet JSON et retourne son équivalent sous forme de chaîne de caractères.
		"nom":"Restaurant",
		"description":"Mes restaurant favoris"
	});
	var resultat2 = temp({ //ce qu'on affiche dans le code template
		"indice":"1",
		"proprietaire":"Jean", //JSON.stringify(User.username) //stringify prend en paramètre un objet JSON et retourne son équivalent sous forme de chaîne de caractères.
		"nom":"Restaurant",
		"description":"Mes restaurant favoris"
	});
	$(".center-liste").append(resultat); //on ajoute le resultat à la suite du div
	$(".center-liste").append(resultat2); //on ajoute le resultat à la suite du div

	document.querySelector('.indice0').addEventListener("click", function(e) {
    	console.log("indice 0");
	}, false);
	document.querySelector('.indice1').addEventListener("click", function(e) {
    	console.log("indice 1");
	}, false);
});