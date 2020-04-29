var map;
var markerGroup; //contient les marqueurs de la map dans la BDD avec l'id : currentMapID
var geocodeService;
var searchControl;
var last_adresse;//adresse de là où on a cliqué pour la dernière fois
var currentUserID = 0;
var currentMapID = 0
var markersMAP = [];

function ouvreNouvelleCarte() {
	//ajouter une nouvelle map dans la BDD
	$.ajax({
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
	ajoutMarqueur(markerID, "", adresse, "", e.latlng.lat, e.latlng.lng) //nom = "" et description = ""
}


/* Fonction qui se déclenche quand on clique sur une carte dans Cartes Perso ou Partagées */
function mapclick(mapID){
	console.log("Vous avez sélectionné la map d'ID : "+mapID);
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
		console.log('Récupération des évènements de la map d\'ID '+currentMapID+'a réussie!')
		console.log(events)
		for(var i = 0; i < events.length; i++){
			var e = events[i]
			var id = e.id
			var nom = e.name
			var description = e.description
			var a = e.address.split("*")
			var adresse = a[0]
			var lat = a[1]
			var lng = a[2]
			var beginning = e.beginning
			var end = e.end
			var labels = e.labels
			var photos = e.photos
			ajoutMarqueur(id, nom, adresse, description, lat, lng)
		}
	});
}

function partagerLaCarte (){ /* Non terminé */
	/* Partage la carte à l'utilisateur quand on cliquer sur partager*/
	nom = document.querySelector('.shareWith').value;
	console.log(nom);
	/*-----
		TROUVER L'ID DE DESTINATAIRE A PARTIR DE SON NOM
	------*/
	var TO_id;
	$.ajax({
		type:'PUT',
		dataType:'json',
		url:'/User/'+TO_id+'/addMapToSharedToMe/'+currentUserID+'/'+currentMapID
	}).done(function(id){
		console.log('Map partagée!')
	});
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
	$.ajax({
		type:'GET',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/getPersonnalMaps'
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

function getCartesParta(){
	$.ajax({
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
		url:'ws/User/'+currentUserID+'/removePersonnalMap/'+currentMapID
	}).done(function(id){
			console.log('Map supprimée des cartes perso!')
	});
	$.ajax({
		type:'DELETE',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/removeSharedMap/'+currentMapID
	}).done(function(id){
			console.log('Map supprimée des cartes partagées!')
	});
}


function getMarkerOfID(markerID){
	for(var i = 0; i < markersMAP.length; i++){
		if(markersMAP[i][0] == markerID){
			return markersMAP[i][1];
		}
	}
	console.log("ce marqueur n'existe pas")
}

function supprimerLocation (markerID){
	console.log("markerID qu'on veut supprimer : "+markerID)
	var marker = getMarkerOfID(markerID)
	console.log("supprimerLocation : marker : "+marker)
	console.log(markerGroup.removeLayer(marker))
	$.ajax({
		type:'DELETE',
		dataType:'json',
		url:'/User/'+currentUserID+'/Map/'+currentMapID+'/removeLocation/'+markerID
	}).done(function(){
			console.log('Location supprimée!')
	});
	$.ajax({
		type:'DELETE',
		dataType:'json',
		url:'/User/'+currentUserID+'/Map/'+currentMapID+'/removeEvent/'+markerID
	}).done(function(){
			console.log('Event supprimée!')
	});
}

function enregistrerLocation (boolEvent, markerID, lat, lng, nom, adresse, description, labels, debut, fin){
	console.log("enregistrerLocation : marqueur ID:"+markerID)
	console.log("enregistrerLocation : nom "+nom)
	console.log("enregistrerLocation : description "+description)
	console.log("enregistrerLocation : adresse "+adresse)
	nom = (nom=="")?"x":nom;
	adresse = (adresse=="")?"x":adresse;
	description = (description=="")?"x":description;
	if(boolEvent == false){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Location/'+markerID+'/setName/'+nom
		}).done(function(){
			console.log('Nom du Location sauvegardé')
		});
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Location/'+markerID+'/setAddress/'+adresse+'?'+lat+'?'+lng
		}).done(function(){
			console.log('Adresse du Location sauvegardé')
		});
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Location/'+markerID+'/setDescription/'+description
		}).done(function(){
			console.log('Description du Location sauvegardé')
		});
		return;
	}
	//event
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setName/'+nom
	}).done(function(){
		console.log('Nom du Event sauvegardé')
	});
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setAddress/'+adresse+'*'+lat+'*'+lng
	}).done(function(){
		console.log('Adresse du Event sauvegardé')
	});
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setDescription/'+description
	}).done(function(){
		console.log('Description du Event sauvegardé')
	});

	if(debut.length > 0) {
		//1999-12-01T12:40
		var B_Year = debut.substring(0,4);
		var B_Month = debut.substring(5,7);
		var B_DayOfMonth = debut.substring(8,10);
		var B_Hour = debut.substring(11,13);
		var B_Minute = debut.substring(14,16); //peut peut etre planter si minute = 07 et si la BDD attend 7
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setBeginning/'+B_Year+'/'+B_Month+'/'+B_DayOfMonth+'/'+B_Hour+'/'+B_Minute
		}).done(function(id){
			console.log('Début du Event sauvegardé')
		});
	}
	if(debut.fin > 0) {
		var E_Year = debut.substring(0,4);
		var E_Month = debut.substring(5,7);
		var E_DayOfMonth = debut.substring(8,10);
		var E_Hour = debut.substring(11,13);
		var E_Minute = debut.substring(14,16); //peut peut etre planter si minute = 07 et si la BDD attend 7
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setEnd/'+E_Year+'/'+E_Month+'/'+E_DayOfMonth+'/'+E_Hour+'/'+E_Minute
		}).done(function(id){
			console.log('Fin du Event sauvegardé')
		});
	}
}

function ajoutMarqueur(markerID, nom_initiale, adresse_initiale, description_initiale, lat, lng){
	//ajout du marqueur
	var marker = L.marker([lat, lng]).addTo(markerGroup);
    var code =`var nom = document.getElementById('nom').value;
		       var adresse = document.getElementById('adresse').value;
			   var description = document.getElementById('description').value;
		       var labels = document.getElementById('labels').value;
		       var debut = document.getElementById('debutInput').value;
			   var fin = document.getElementById('finInput').value;
			   enregistrerLocation(true,`+markerID+`,`+lat+`,`+lng+`, nom, adresse, description, labels, debut, fin); `;
    var temp = _.template($('#templatePopup').html()); 
    //création du popup
	var popup = temp({ 
		"mID":markerID,
		"code":code,
		"markerID":markerID,
		"nom":nom_initiale,
		"adresse":adresse_initiale,
		"description":description_initiale
	});
    marker.bindPopup(popup).openPopup();
    console.log("markerId : "+markerID)
    //ne pas mettre le code suivant dans une fonction a part sinon fonctionne pas

    marker.on('click', function(e){ //click d'ouverture et fermeture
   		var content = e.target.getPopup().getContent();
   		var doc = new DOMParser().parseFromString(content, "text/html")
		var ID = doc.getElementById("mID").getAttribute("data-value");
		console.log("ID du marqueur sur lequel j'ai cliqué : "+ID);
		var marker_nom = "";
		var marker_description = "";
		var marker_adresse = "";
	    $.ajax({
	    	async: false,
			type:'GET',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+ID+'/getName'
		}).done(function(nom){
			console.log('Récupération du nom du marqueur!')
			marker_nom = nom;
		});

		$.ajax({
			async: false,
			type:'GET',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+ID+'/getAddress'
		}).done(function(adresse){
			console.log('Récupération de l\'adresse du marqueur!')
			marker_adresse = adresse;
		});

		$.ajax({
			async: false,
			type:'GET',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+ID+'/getDescription'
		}).done(function(description){
			console.log('Récupération de la description du marqueur!')
			marker_description = description;
		});

		var code =`var nom = document.getElementById('nom').value;
			       var adresse = document.getElementById('adresse').value;
		 		   var description = document.getElementById('description').value;
			       var labels = document.getElementById('labels').value;
			       var debut = document.getElementById('debutInput').value;
				   var fin = document.getElementById('finInput').value;
				   var boolEvent = document.getElementById('statutMap').checked;
			       enregistrerLocation(true,`+ID+`,`+e.latlng.lat+`,`+e.latlng.lng+`, nom, adresse, description, labels, debut, fin); `;

		var temp = _.template($('#templatePopup').html()); 
		var popup = temp({ 
			"mID":markerID,
			"code":code,
			"markerID":markerID,
			"nom":marker_nom,
			"adresse":marker_adresse,
			"description":marker_description
		});
	   	marker._popup.setContent(popup)
	});
    markersMAP.push([markerID, marker]);
    console.log("markersMAP : "+markersMAP)
    console.log("layerGroup : "+markerGroup)
}




