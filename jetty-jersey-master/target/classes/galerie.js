
function encodeImageFileAsURL(element) {
	var file = element.files[0];
	var reader = new FileReader();
	reader.onloadend = function() { photo_a_envoyer = reader.result }
	reader.readAsDataURL(file);
}

document.getElementById('valider').addEventListener("click", function() {
	console.log(photo_a_envoyer.length)
	console.log(photo_a_envoyer.substring(photo_a_envoyer.length-10,photo_a_envoyer.length))
	$.ajax({
		type:'POST',
		data: JSON.stringify(photo_a_envoyer),
		contentType: 'application/json',
		dataType:'json',
	  	url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+currentMarkerID+'/addPhoto/'
	}).done(function(ID){
		console.log("photo ajouté!");
		afficheGalerie(currentMarkerID)
	});
})

function afficheGalerie(markerID){
	document.querySelector('.frame').innerHTML = "";
	document.querySelector('.galerie-modal').style.display = "flex"; //rend visible la fenêtre
	//on doit récupérer les infos déjà entrés dans la base de données et les afficher
	console.log("GALERIE, markerID : "+markerID)

	$.ajax({
		type:'GET',
		dataType:'json',
	  	url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/getPhotos/'
	}).done(function(photos){
		console.log("photos récupérés!")
		console.log(photos)
		for(var i = 0; i < photos.length; i++){
			recupereEtAffichePhoto(markerID, photos[i])
		}
	});

}
function recupereEtAffichePhoto(markerID, photoID){
	console.log("photoID : "+photoID)
	var contenu = recupere_contenu_photo(markerID, photoID)
	var temp = _.template($('#templateButton').html());
	var image = new Image(390, 390); 
	image.src = contenu.substring(1, contenu.length-1);
	document.getElementsByClassName('frame')[0].appendChild(image);
	document.getElementsByClassName('frame')[0].insertAdjacentHTML('beforeend', temp({ "photoID":photoID, "markerID":currentMarkerID })+'<br/><br/><br/>');
}

function recupere_contenu_photo(markerID, photoID){
	console.log("currentMapID : " + currentMapID)
	console.log("markerID : " + markerID)
	console.log("photoID : " + photoID)
	var src;
	$.ajax({
		async: false,
		type:'GET',
		dataType:'text',
	  	url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/getPhoto/'+photoID+'/-1/-1'
	}).done(function(contenu){
		src = contenu
	});
	return src
}

function fermerGalerie () {
	document.querySelector('.galerie-modal').style.display = "none"; //le rend invisible
}

function supprimerPhoto(markerID, photoID){
	console.log("supprimerPhoto -> markerID : "+markerID+" photoID : "+photoID)
	$.ajax({
		type:'POST',
		data: JSON.stringify(photo_a_envoyer),
		contentType: 'application/json',
		dataType:'json',
	  	url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/removePhoto/'+photoID
	}).done(function(photos){
		console.log("photo supprimé!")
		afficheGalerie(markerID)
	});
}
