function supprimerLocation (markerID){
	console.log("markerID qu'on veut supprimer : "+markerID)
	var marker = getMarkerOfID(markerID)
	console.log("supprimerLocation : marker : "+marker)
	console.log(markerGroup.removeLayer(marker))
	/*
	$.ajax({
		type:'DELETE',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/removeLocation/'+markerID
	}).done(function(){
			console.log('Location supprimée!')
	});
	*/
	$.ajax({
		type:'DELETE',
		dataType:'json',
		url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/removeEvent/'+markerID
	}).done(function(){
			console.log('Marqueur supprimée!')
	});
}

function enregistrerLocation (boolEvent, markerID, lat, lng, nom, adresse, description, labels, debut, fin){
	if(boolEvent == false){
		if(nom.length > 0) {
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Location/'+markerID+'/setName/'+nom
			}).done(function(){
				console.log('Nom du Location sauvegardé')
			});
		}
		if(adresse.length > 0) {
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Location/'+markerID+'/setAddress/'+adresse+'*'+lat+'*'+lng
			}).done(function(){
				console.log('Adresse du Location sauvegardé')
			});
		}
		if(description.length > 0) {
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Location/'+markerID+'/setDescription/'+description
			}).done(function(){
				console.log('Description du Location sauvegardé')
			});
		}
		if(labels.length > 0) {
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Location/'+markerID+'/setLabels/'+labels
			}).done(function(){
				console.log('Labels du Location sauvegardé')
			});
		}
		return;
	}

	//event
	if(nom.length > 0) {
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setName/'+nom
		}).done(function(){
			console.log('Nom du Event sauvegardé')
		});
	}
	if(adresse.length > 0) {
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setAddress/'+adresse+'*'+lat+'*'+lng
		}).done(function(){
			console.log('Adresse du Event sauvegardé')
		});
	}
	if(description.length > 0) {
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setDescription/'+description
		}).done(function(){
			console.log('Description du Event sauvegardé')
		});
	}
	if(labels.length > 0) {
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setLabels/'+labels
		}).done(function(){
			console.log('Labels du Event sauvegardé')
		});
	}
	console.log("debut.length : "+debut.length)
	if(debut.length == 16) {
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
		}).done(function(){
			console.log('Début du Event sauvegardé')
		});
	}
	console.log("fin.length : "+fin.length)
	if(fin.length == 16) {
		var E_Year = fin.substring(0,4);
		var E_Month = fin.substring(5,7);
		var E_DayOfMonth = fin.substring(8,10);
		var E_Hour = fin.substring(11,13);
		var E_Minute = fin.substring(14,16); //peut peut etre planter si minute = 07 et si la BDD attend 7
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setEnd/'+E_Year+'/'+E_Month+'/'+E_DayOfMonth+'/'+E_Hour+'/'+E_Minute
		}).done(function(){
			console.log('Fin du Event sauvegardé')
		});
	}
}


function ajoutMarqueur(markerID, nom_initiale, adresse_initiale, description_initiale, labels_initiale, debut_initiale, end_initiale, lat, lng, isBackup){
	//ajout du marqueur
	var marker = L.marker([lat, lng]).addTo(markerGroup);
    var code =`var nom = document.getElementById('nom`+markerID+`').value;
		       var adresse = document.getElementById('adresse`+markerID+`').value;
			   var description = document.getElementById('description`+markerID+`').value;
		       var labels = document.getElementById('labels`+markerID+`').value;
		       var debut = document.getElementById('debutInput`+markerID+`').value;
			   var fin = document.getElementById('finInput`+markerID+`').value;
			   var boolEvent = document.getElementById('popupIsEvent`+markerID+`').checked;
			   enregistrerLocation(true,`+markerID+`,`+lat+`,`+lng+`, nom, adresse, description, labels, debut, fin); `;
    var temp = _.template($('#templatePopup').html());
   
	//création du popup
	var popup = temp({ 
		"mID":markerID,
		"code":code,
		"markerID":markerID,
		"nom":nom_initiale,
		"adresse":adresse_initiale,
		"description":description_initiale,
		"labels":labels_initiale,
		"beginning":debut_initiale,
		"end":end_initiale
	});

    marker.bindPopup(popup)
    //on sauvegarde son adresse dès la création du marqueur si ce n'est pas une sauvegarde que l'on place
    if(isBackup == false){ //si c'est pas une sauvegarde 
    	console.log("is backup - adresse ")
		console.log(adresse_initiale)
		console.log("is backup - lat ")
		console.log(lat)
		console.log("is backup - lng ")
		console.log(lng)
	    $.ajax({
	    	async: false,
			type:'POST',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+markerID+'/setAddress/'+adresse_initiale+'*'+lat+'*'+lng
		}).done(function(){
			console.log('Adresse du Event sauvegardé')
		});
	}

	//ne pas mettre le code suivant dans une fonction a part sinon fonctionne pas
    marker.on('click', function(e){ //click d'ouverture et fermeture
   		var content = e.target.getPopup().getContent();
   		var doc = new DOMParser().parseFromString(content, "text/html")
		var ID = doc.getElementById("mID").getAttribute("data-value");
		console.log("ID du marqueur sur lequel j'ai cliqué : "+ID);
		currentMarkerID = ID; //on met à jour le dernier marqueur sur laquelle on a cliqué
		var marker_nom = "";
		var marker_description = "";
		var marker_adresse = "";
		var marker_labels = "";
		var marker_beginning = "";
		var marker_end = "";

		 $.ajax({
	    	async: false,
			type:'GET',
			dataType:'json',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+ID+'/getEvent'
		}).done(function(event){
			console.log('Récupération du marqueur!')
			marker_nom = event.name;
			marker_description = event.description;
			var a = event.address.split("*")
			marker_adresse = a[0]
			for(var i = 0; i < event.labels.length; i++){
				marker_labels += event.labels[i] + " ";
			}
			marker_beginning = JSON.stringify(event.beginning);
			marker_end = JSON.stringify(event.end);
			console.log(marker_beginning)

		});

		$.ajax({
			async: false,
			type:'GET',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+ID+'/getBeginning'
		}).done(function(beginning){
			console.log('Récupération de beginning du marqueur!')
			console.log(beginning)//2020-05-02T03:45
			marker_beginning = beginning

			if(beginning.length > 16){
				marker_beginning = beginning.substring(0, 16)
			}

		});

		$.ajax({
			async: false,
			type:'GET',
			dataType:'text',
			url:'ws/User/'+currentUserID+'/Map/'+currentMapID+'/Event/'+ID+'/getEnd'
		}).done(function(end){
			console.log('Récupération de end du marqueur!')
			console.log(end)
			marker_end = end
			if(end.length > 16){
				marker_end = end.substring(0, 16)
			}
		});

		var code =`var nom = document.getElementById('nom`+markerID+`').value;
			       var adresse = document.getElementById('adresse`+markerID+`').value;
		 		   var description = document.getElementById('description`+markerID+`').value;
			       var labels = document.getElementById('labels`+markerID+`').value;
			       var debut = document.getElementById('debutInput`+markerID+`').value;
			       var fin = document.getElementById('finInput`+markerID+`').value;
				   var boolEvent = document.getElementById('popupIsEvent`+markerID+`').checked;
			       enregistrerLocation(true,`+ID+`,`+e.latlng.lat+`,`+e.latlng.lng+`, nom, adresse, description, labels, debut, fin); `;

		var temp = _.template($('#templatePopup').html()); 
		var popup = temp({ 
			"mID":markerID,
			"code":code,
			"markerID":markerID,
			"nom":marker_nom,
			"adresse":marker_adresse,
			"description":marker_description,
			"labels":marker_labels,
			"beginning":marker_beginning,
			"end":marker_end
		});

	   	marker._popup.setContent(popup)

	});
    markersMAP.push([markerID, marker]);
}