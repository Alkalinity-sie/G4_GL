var map;
var markerGroup; //contient les marqueurs de la map dans la BDD avec l'id : currentMapID
var geocodeService;
var searchControl;
var last_adresse;//adresse de là où on a cliqué pour la dernière fois
var currentUserID = 0;
var currentMapID = 0;
var currentMarkerID = 0;
var markersMAP = [];
var sidebar_last_clicked = "";
var photo_a_envoyer;
function ouvreNouvelleCarte() {
	//ajouter une nouvelle map dans la BDD
	$.ajax({
		async: false,
		type:'PUT',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/addPersonnalMap'
	}).done(function(id){
		console.log('Nouvelle map ajoutée!')
		currentMapID = id
	});
	//enlever tous les marqueurs existants sur la map
	markerGroup.clearLayers(); //removeLayer(219) envleve le marqueur d'id 219

}

$(function (){
		// Let’s create a map of the center of London with pretty Mapbox Streets tiles.
		// First we’ll initialize the map and set its view to our chosen geographical coordinates and a zoom level
		map = L.map('mapleflet1').setView([48.866667, 2.333333], 11);
		L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    	maxZoom: 18,
    	id: 'mapbox/streets-v11',
    	tileSize: 512,
    	zoomOffset: -1,
    	accessToken: 'pk.eyJ1IjoibGVkb2RvaW5jb25udSIsImEiOiJjazg3OXNkZWUwZDdiM2VwcmQxbjI0cWczIn0.gsqi-Jp2FZ066BoQ_0IEJQ'
		}).addTo(map);

		map.on('click', onMapClick);
		L.control.scale().addTo(map);
		searchControl = L.esri.Geocoding.geosearch().addTo(map);
		geocodeService = L.esri.Geocoding.geocodeService();
		//ajout du groupe de marqueur
		markerGroup = L.layerGroup().addTo(map);

		// RECUPERE MA POS ET PLACE UN MARQUEUR
		//navigator.geolocation.getCurrentPosition(displayLocation);
		//var lat = position.coords.latitude;
    	//var lng = position.coords.longitude;
		//L.marker([lat, lng]).addTo(markerGroup);

});

$('#remonter').click(function(){
	$("html, body").animate({scrollTop : 0}, 550);
});

function EnableDisableEvent(marker) {
	var node = document.getElementById('Evenement-infos');
	if (node.style.visibility=="hidden"){
		node.style.visibility = "visible";
		document.getElementById('popup').style.height = '400px';
	} else {
		node.style.visibility = "hidden";
		document.getElementById('popup').style.height = '325px';
	}
}

function getCartesPerso(){
	sidebar_last_clicked = "perso";
	$.ajax({
		async: false,
		type:'GET',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/getPersonnalMaps'
	}).done(function(maps){
		$("#liste-des-cartes").html("");
		for(var i = 0; i < maps.length; i++){
			var style = "font-size: 12px"
			var id = maps[i].id
			var name = maps[i].name
			var description = maps[i].description
			var fonction = "mapclick('"+id+"')"
			var contenu = name + " : " + description + "<br/>"
			$("#liste-des-cartes").append("<li "+style+"><a onclick=\""+fonction+"\" href=\"#\">"+contenu+"</a></li>");
		}
	});
}

function getCartesParta(){
	sidebar_last_clicked = "shared";
	$.ajax({
		async: false,
		type:'GET',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/getMapsSharedToMe'
	}).done(function(maps){
			console.log(maps)
			$("#liste-des-cartes").html("");
			for(var i = 0; i < maps.length; i++){
				var style = "font-size: 12px"
				var id = maps[i].id
				var name = maps[i].name
				var description = maps[i].description
				var fonction = "mapclick('"+id+"')"
				var contenu = name + " : " + description + "<br/>"
				$("#liste-des-cartes").append("<li "+style+"><a onclick=\""+fonction+"\" href=\"#\">"+contenu+"</a></li>");
			}
	});
}

function changeStatut(){
	var element = document.querySelector('.statut');
	if(element.textContent == "Private") {
		element.textContent = "Public";
		return;
	}
	element.textContent = "Private";
	
}

function trouveAdresse(e){
	return new Promise(function(resolve, reject){
		L.esri.Geocoding.reverseGeocode().latlng(e.latlng).run(function (error, result, response) {
		    	// callback is called with error, result, and raw response
		    	// result.latlng contains the coordinates of the located address
		   		// result.address contains information about the match
		   	resolve(result.address.LongLabel)
  		});
	});
}

function fermerFenetreMapInfo () {
	document.querySelector('.bg-modal').style.display = "none"; //le rend invisible
}

/* Enregistre les infos (de la map) entrés dans les champs du popup du bouton info lors du clique sur le bouton enregistrer*/ 
function enregistrerDataCarte (){
	var nom = document.getElementById('nomMap').value;
	var description = document.getElementById('descriptionMap').value;
	var statut = (document.querySelector('.statut').textContent == "Public") ? true:false;
	console.log("statut enregistré :"+statut)
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/setName/'+nom
	}).done(function(){
			console.log('Nom de la map sauvegardé!')
	});
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/setDescription/'+description
	}).done(function(){
			console.log('Description de la map sauvegardé!')
	});
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/setStatus/'+statut
	}).done(function(){
			console.log('Statut de la map sauvegardé!')
	});
}

/* Affiche la fenêtre avec les infos sur la map */
function afficheFenetreMapInfo (){
	document.querySelector('.bg-modal').style.display = "flex"; //rend visible la fenêtre
	//on doit récupérer les infos déjà entrés dans la base de données et les afficher
	$.ajax({
		type:'GET',
		dataType:'text',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/getName/'
	}).done(function(nom){
		document.getElementById('nomMap').value = nom;
	});
	$.ajax({
		type:'GET',
		dataType:'text',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/getDescription/'
	}).done(function(description){
		document.getElementById('descriptionMap').value = description;
	});
	$.ajax({
		type:'GET',
		dataType:'text',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/getStatus/'
	}).done(function(statut){
		if(statut == "true"){
			document.querySelector('.statut').textContent = "Public"
			document.getElementById('statutMap').checked = true; //public
		} else {
			document.querySelector('.statut').textContent = "Private"
			document.getElementById('statutMap').checked = false; //public
		}
	});
}

function supprimerLaCarte () {
	/* Supprime la carte depuis le popup du bouton info */
	markerGroup.clearLayers();
	$.ajax({
		type:'DELETE',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/removeMap/'+currentMapID
	}).done(function(carte_perso){
			if(carte_perso){
				console.log('Map supprimée des cartes personnelles!')
			} else {
				console.log('Map supprimée des cartes partagées!')	
			}
	});
	if(sidebar_last_clicked=="perso"){
		getCartesPerso();
	} else if (sidebar_last_clicked=="shared"){
		getCartesParta();
	}
}


function getMarkerOfID(markerID){
	for(var i = 0; i < markersMAP.length; i++){
		if(markersMAP[i][0] == markerID){
			return markersMAP[i][1];
		}
	}
	console.log("ce marqueur n'existe pas")
}

function corrigeToutSaufAnnee (nb){
	if(nb.length < 2){
		return "0"+nb;
	}
	return nb;
}

function corrigeAnnee (nb){
	if(nb.length < 4){
		var difference = nb.length - 4;
		var zeros = "";
		for(var i = 0; i < difference; i++){
			zeros.add("0");
		}
		return zeros + nb;
	}
	return nb;
}

function JSONDateTimeToString (dt){
	minute = corrigeToutSaufAnnee(dt.minute+"")
	heure  = corrigeToutSaufAnnee(dt.hour+"")
	mois = corrigeToutSaufAnnee(dt.monthValue+"")
	jour = corrigeToutSaufAnnee(dt.dayOfMonth+"")
	annee = corrigeAnnee(dt.year+"")
	//1999-12-01T12:40
	return annee+"-"+mois+"-"+jour+"T"+heure+":"+minute
}


/* Fonction qui se déclenche quand on clique sur une carte dans Cartes Perso ou Partagées */
function mapclick(mapID){
	console.log("Fonction mapclick : Vous avez sélectionné la map d'ID : "+mapID);
	currentMapID = mapID;
	//enlever tous les marqueurs existants sur la map
	markerGroup.clearLayers();
	markersMAP = [] //on vide les marqueurs
	$.ajax({
	    async: false,
		type:'GET',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/getEvents/'
	}).done(function(events){
		console.log('Récupération des évènements de la map d\'ID '+currentMapID+' a réussie!')
		for(var i = 0; i < events.length; i++){
			var e = events[i]
			var id = e.id
			var nom = e.name
			var description = e.description
			var a = e.address.split("*")
			var adresse = a[0]
			var lat = a[1]
			var lng = a[2]
			var debut = e.beginning
			var end = e.end
			var labels = e.labels
			var photos = e.photos
			ajoutMarqueur(id, nom, adresse, description, labels, debut.substring(0,16), end.substring(0,16),lat, lng, true) //on place des sauvegardes
		}
	});
}

async function onMapClick(e) {
	var adresse = await trouveAdresse(e);
	var last_adresse = adresse;
	var markerID;
	//création d'un Event dans la BDD
	$.ajax({
		async: false, //pour que on attend la fin de la requete d'abord
		type:'PUT',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/addEvent'
	}).done(function(id){
		console.log('Nouveau Event ajoutée à la position : '+e.latlng.lat + " / " + e.latlng.lng)
		markerID = id
	});
	ajoutMarqueur(markerID, "", adresse, "", "", "", "", e.latlng.lat, e.latlng.lng, false) 
}





/* Partage la carte à l'utilisateur quand on cliquer sur partager*/
function partagerLaCarte (){
	user_name = document.getElementById('shareWith').value;
	console.log("Vous voulez partager la carte d'ID "+currentMapID+" à "+user_name);
	var TO_id;
	//on cherche l'id de l'utilisateur
	$.ajax({
		async: false,
		type:'GET',
		dataType:'json',
		url:'ws/User/'+user_name+'/getUserID'
	}).done(function(user_id){
		console.log('Map partagée!')
		console.log("destinataire a l'ID "+user_id)
		TO_id = user_id
	});
	if(TO_id == -1) {
		console.log('Cet utilisateur n\'existe pas')
		return
	}
	$.ajax({
		type:'PUT',
		dataType:'json',
		url:'ws/User/'+TO_id+'/addMapToSharedToMe/'+currentUserID+'/'+currentMapID
	}).done(function(id){
		console.log('Map partagée!')
	});
}

/* ajouter la map parmis ses cartes partagées */
function ajouterLaMap(){
	console.log("ajouterLaMap() est appelée!");

	$.ajax({
		async: false,
		type:'PUT',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/addMapToSharedToMe/0/'+currentMapID
	}).done(function(id){
		console.log('Map ajoutée parmis mes cartes (partagées)!')
	});


}

$('#buttonRechercher').click(function(){
	var entry = document.getElementById('mapSearchBar').value;
	if(entry == ''){
		entry = 'all'
	}
	$.ajax({
		type:'GET',
		dataType:'json', //text si fonctionne pas
		url:'ws/BarreDeRecherche/'+entry+'/'+currentUserID+'/true/getMapResults'
	}).done(function(maps){

		$(".center-liste").html("");
		var map_elem = _.template($('#templateMapElement').html()); 
		var label = _.template($('#templateLabel').html());
		for(var i = 0; i < maps.length; i++){
			(function (i){
				var resultat = map_elem({ //ce qu'on affiche dans le code template
					"indice":maps[i].id+"",//une map n'a pas d'attribut proprietaire, mieux vaut l'enlever et PLACER LE BOUTON 'AJOUTER PARMIS MES CARTES' A LA PLACE
					"nom":maps[i].name,
					"description":maps[i].description
				});
				//ajout de l'element a la fin
				$(".center-liste").append(resultat);
				document.querySelector('.indice'+maps[i].id).addEventListener("click", function(e) {
					mapclick(maps[i].id)
				}, false);

				//ajout des labels
				$.ajax({
					async: false,
					type:'GET',
					dataType:'json',
					url:'ws/User/0/Map/'+maps[i].id+'/getAllLabels'
				}).done(function(labels){
					var label = _.template($('#templateLabel').html());
					for(var j = 0; j < labels.length; j++){
						(function (lab){
							var l = label({"contenu":lab});
							$('.indice'+maps[i].id).find('.map-labels').append(l);
						})(labels[j]);
					}
				});
				
				
			})(i); //immediate invocation 
		}
		console.log("Résultats récupérées et placés")
	});
});



