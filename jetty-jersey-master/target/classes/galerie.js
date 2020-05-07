var array;
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

function check_array(){
	for(var i = 0; i < array.length; i++){
		if(array[i] == "null"){
			return -1
		}
	}
	return 0
}

function recupere_contenu_photo(photoID){
	var taille;

	$.ajax({
		async: false,
		type:'GET',
		dataType:'text',
	  	url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+currentMarkerID+'/getPhoto/'+photoID+'/-1/-1'
	}).done(function(length){
		console.log("taille : "+length)
		taille = parseInt(length) 
	});

	array = new Array(Math.ceil(taille/8000))
	console.log("taille du tableau:"+Math.ceil(taille/8000))
	//initialisation
	for(var i = 0; i < array.length; i++){
		array[i] = "null"
	}

	if(taille <= 8000){
		$.ajax({
			async: false,
			type:'GET',
			dataType:'text',
		  	url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+currentMarkerID+'/getPhoto/'+photoID+'/0/'+taille
		}).done(function(data){
			return data
		});
	}
	
	var indexDebut = 0;
		//var indexFin = i+8000;
		//console.log("indice debut:"+indexDebut)
		//console.log("indice fin:"+indexFin)
	var f = function(){
		$.ajax({
			async: true,
			type:'GET',
			dataType:'text',
			url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+currentMarkerID+'/getPhoto/'+photoID+'/'+indexDebut+'/'+(indexDebut+8000)
		}).done(function(data){
			indexDebut += 8000;
			var i = Math.floor(indexDebut/8000)
			console.log("i:"+i+"indexDebut:"+indexDebut)
			array[i] = data
			if(indexDebut < taille-8000) f();
		});
	}
	f();

	//console.log("indice debut:"+indexFin)
	//console.log("indice fin:"+taille)
	$.ajax({
		async:false,
		type:'GET',
		dataType:'text',
		url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+currentMarkerID+'/getPhoto/'+photoID+'/'+indexDebut+'/'+taille
	}).done(function(data){
		array[array.length-1] = data
	});
	/*
	while(check_array()==-1){

	}
	*/
	var contenu = "";
	for(var i = 0; i < array.length; i++){
		contenu += array[i]
	}
	console.log("taille u contenu : "+contenu.length)
	return contenu
}


function recupereEtAffichePhoto(photoID){
	console.log("photoID : "+photoID)
	var contenu = recupere_contenu_photo(photoID)
	var temp = _.template($('#templateButton').html());
	var image = new Image(390, 390); 
	image.src = contenu.substring(1, contenu.length-1);
	document.getElementsByClassName('frame')[0].appendChild(image);
	document.getElementsByClassName('frame')[0].insertAdjacentHTML('beforeend', temp({ "photoID":photoID, "markerID":currentMarkerID })+'<br/><br/><br/>');
}

function afficheGalerie(markerID){
	document.querySelector('.frame').innerHTML = "";
	document.querySelector('.galerie-modal').style.display = "flex"; //rend visible la fenêtre
	//on doit récupérer les infos déjà entrés dans la base de données et les afficher
	console.log("GALERIE, markerID : "+markerID)

	$.ajax({
		type:'GET',
		//contentType: 'application/json',
		dataType:'json',
	  	url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+currentMarkerID+'/getPhotos/'
	}).done(function(photos){
		console.log("photos récupérés!")
		console.log(photos)
		for(var i = 0; i < photos.length; i++){
			recupereEtAffichePhoto(photos[i])
		}
	});
	//document.getElementsByClassName('frame')[0].insertAdjacentHTML('beforeend', `<img src="coffee.png"  width="390" height="390">`);

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
	  	url: 'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+currentMarkerID+'/getPhotos/'
	}).done(function(photos){
		console.log("photo supprimé!")
		afficheGalerie(markerID)
	});
}
