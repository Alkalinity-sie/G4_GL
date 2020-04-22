var map;
var markerGroup;
var geocodeService;
var searchControl;
var numberOfMarker = 0;
var tab = []
var nom = ['m', 'h'];
var adresse;//adresse de là où on a cliqué pour la dernière fois
//CMD+ALT+J : POUR OUVRIR LA CONSOLE

function partagerLaCarte (){
	/* Partage la carte à l'utilisateur quand on cliquer sur partager*/
}

function enregistrerDataCarte (){
	/* Enregistre les infos (de la map) entrés dans les champs du popup du bouton info */ 

}

function supprimerLaCarte () {
	/* Supprime la carte depuis le popup du bouton info */
}

function afficheFenetreMapInfo (){
	document.querySelector('.bg-modal').style.display = "flex";
}

function fermerFenetreMapInfo () {
	document.querySelector('.bg-modal').style.display = "none"; //le rend invisible
}

function supprimerLocation (markerID){
	markerGroup.removeLayer(tab[markerID])
}

function enregistrerLocation (markerID, lat, lng, nom, adresse, description, labels){
	//récupérer nom, adresse, labels, photos, date de debut, date de fin des inputs
	//appeler les requetes SET sur ce marqueur (pas besoin de map id)
	var contenu = 'markerID : ' + markerID + '\nlat/lng : ' + lat + "/" + lng + '\nnom : '+ nom +'\nadresse : ' + adresse + '\ndescription : '+ description + '\nlabels : '+ labels;
	console.log(contenu)
}

function ouvreNouvelleCarte() {
	//ajouter une nouvelle map dans la BDD
	//enlever tous les marqueurs existants sur la map

	//ajout du marqueur

	markerGroup.clearLayers(); //removeLayer(219) envleve le marqueur d'id 219
	//L.marker([48.854300179622555, 2.3098754882812504]).addTo(markerGroup); //ajoute un marqueur

}

function click1(){
	$("#liste-des-cartes").html("");
	style = "font-size: 12px"
	var id = 0
	var name = "Restaurants"
	var description = "Mes resto's favoris!"
	var labels = ['Chinois','Japonais','Illimité']
	var fonction = "mapclick('"+id+"')"
	//<li><a onclick="click2("++")" href="#">Cartes partagées</a></li>
	contenu = name + " : " + description + "<br/>" +"Labels : "+labels.join(", ")
	$("#liste-des-cartes").append("<li "+style+"><a onclick=\""+fonction+"\" href=\"#\">"+contenu+"</a></li>");

	id = 1
	name = "Zoo"
	description = "Mes zoos favoris!"
	labels = ['Zebre','Grenouille','Giraffe']
	fonction = "mapclick('"+id+"')"
	//<li><a onclick="click2("++")" href="#">Cartes partagées</a></li>
	contenu = name + " : " + description + "<br/>" +"Labels : "+labels.join(", ")
	$("#liste-des-cartes").append("<li "+style+"><a onclick=\""+fonction+"\" href=\"#\">"+contenu+"</a></li>");

}

function click2(){
	$("#liste-des-cartes").html("");
}

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

function trouveAdresse(e){
	return new Promise(function(resolve, reject){
		L.esri.Geocoding.reverseGeocode().latlng(e.latlng).run(function (error, result, response) {
		    	// callback is called with error, result, and raw response
		    	// result.latlng contains the coordinates of the located address
		   		// result.address contains information about the match
		    	adresse = result.address.LongLabel;
		    	console.log('1 : '+adresse);
		    	resolve(response)
  		});
	});
}

async function onMapClick(e) {
			var markerID = numberOfMarker;
			numberOfMarker = numberOfMarker + 1;
    		console.log("You clicked the map at : " + e.latlng.lat + " " + e.latlng.lng);
			await trouveAdresse(e);
			console.log('2 : '+adresse);
			var marker = L.marker(e.latlng).addTo(markerGroup);
    		var fun =`var nom = document.getElementById('nom').value;
				      var adresse = document.getElementById('adresse').value;
					  var description = document.getElementById('description').value;
				      var labels = document.getElementById('labels').value;
				      enregistrerLocation(`+markerID+`,`+e.latlng.lat+`,`+e.latlng.lng+`, nom, adresse, description, labels); `;
    		var popup = `
	    		<div id="popup">
	    			<div style="text-align:center; font-weight: bold;"> 
	    			Lieu / Évènement
	    			</div>
	    			<br/>
	    			<button id="saveButton" onclick="`+fun+`">Enregistrer</button>
	    			<button id="supprimerButton" onclick="supprimerLocation(`+markerID+`)">Supprimer</button><br/>
	    			</br>
	    			Nom <br/>      
	    			<input id="nom" style="width: 171px;"><br/>
	    			Adresse <br/>      
	    			<input id="adresse" style="width: 171px;" value='`+adresse+`'><br/>
	    			Description <br/>
	    			<textarea id="description" style="resize: none; width: 173px; height: 60px; border:1px solid #C2C6C9;"></textarea><br/>
	    			Labels <br/>      
	    			<input id="labels" style="width: 171px;"><br/>
	    			Photos <br/>    
	    			<input id="photos" style="width: 171px;"><br/>

	    			<!-- SWITCH -->
	    			<br/>
	    			<b>Evenement &emsp;</b> 
					<label class="switch">
					  <input type="checkbox" onclick="EnableDisableEvent()">
					  <span class="slider round"></span>
					</label>
					<br/> 
					<!-- FIN SWITCH -->
					<div style="visibility:hidden;" id="Evenement-infos">
						<div id="debutText" type = "hidden"> Debut </div>
						<input id="debutInput" type="datetime-local" ><br/> 
						<div id="finText"> Fin </div>
						<input id="finInput" type="datetime-local" ><br/> 
					</div>

	    		</div>
	    		`;
    		marker.bindPopup(popup).openPopup();
    		//ne pas mettre le code suivant dans une fonction a part sinon fonctionne pas
    		marker.on('click', function(){ //click d'ouverture et fermeture
    			//RECUPERER DONNEES SUR LE MARQUEUR
    			var text = nom[markerID]
    			nom[markerID] += nom[markerID].charAt(0);
				console.log(e.latlng + "| ID : "+markerID);
				var fun =`var nom = document.getElementById('nom').value;
					      var adresse = document.getElementById('adresse').value;
						  var description = document.getElementById('description').value;
					      var labels = document.getElementById('labels').value;
					      enregistrerLocation(`+markerID+`,`+e.latlng.lat+`,`+e.latlng.lng+`, nom, adresse, description, labels); `;

    			var popup = `
	    		<div id="popup">
	    			<div style="text-align:center; font-weight: bold;"> 
	    			Lieu / Évènement
	    			</div>
	    			<br/>
	    			<button id="saveButton" onclick="`+fun+`">Enregistrer</button>
	    			<button id="supprimerButton" onclick="supprimerLocation(`+markerID+`)">Supprimer</button><br/>
	    			</br>
	    			Nom <br/>      
	    			<input id="nom" style="width: 171px;" value='`+text+`'><br/>
	    			Adresse <br/>      
	    			<input id="adresse" style="width: 171px;" value='`+adresse+`'><br/>
	    			Description <br/>
	    			<textarea id="description" style="resize: none; width: 173px; height: 60px; border:1px solid #C2C6C9;"></textarea><br/>
	    			Labels <br/>      
	    			<input id="labels" style="width: 171px;"><br/>
	    			Photos <br/>    
	    			<input id="photos" style="width: 171px;"><br/>

	    			<!-- SWITCH -->
	    			<br/>
	    			<b>Evenement &emsp;</b> 
					<label class="switch">
					  <input type="checkbox" onclick="EnableDisableEvent()">
					  <span class="slider round"></span>
					</label>
					<br/> 
					<!-- FIN SWITCH -->
					<div style="visibility:hidden;" id="Evenement-infos">
						<div id="debutText" type = "hidden"> Debut </div>
						<input id="debutInput" type="datetime-local" ><br/> 
						<div id="finText"> Fin </div>
						<input id="finInput" type="datetime-local" ><br/> 
					</div>

	    		</div>
	    		`;
   				marker._popup.setContent(popup)
			});
    		tab.push(marker);
}

function mapclick(nom){
	console.log(nom);
}

function displayLocation(position) {
	var lat = position.coords.latitude;
    var lng = position.coords.longitude;
	L.marker([lat, lng]).addTo(markerGroup);
		    //map.setView([lat, lng], 16);
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
		//ajout du marqueur
		markerGroup = L.layerGroup().addTo(map);
		//navigator.geolocation.getCurrentPosition(displayLocation); //récupère ma position

});

$('#remonter').click(function(){
	$("html, body").animate({scrollTop : 0}, 550);
});