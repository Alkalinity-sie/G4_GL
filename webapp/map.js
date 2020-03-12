function getServerData(type,url,success){
	$.ajax({
		type:type,
		dataType:'json',
		url:url
	}).done(success);

}
function getServerDataText(type,url,success){
	$.ajax({
		type:type,
		dataType:'text',
		url:url
	}).done(success);
}


function callMapname(mapname){
	var templateExample = _.template($('#templateName').html());
	var html = templateExample({
		"attribute":JSON.stringify(mapname)
	});

	$("#Mapname").append(html);
}

function callMapDescription(mapdescription){
	var templateExample = _.template($('#templateDescription').html());
	var html = templateExample({
		"description":JSON.stringify(mapdescription)
	});

	$("#Description").html(html);
}

function callMapStatus(mapstatus){
	var templateExample = _.template($('#templateName').html());
	var html = templateExample({
		"attribute":JSON.stringify(mapstatus)
	});

	$("#isPublic").append(html);
}

function callLocations(Locationlist){
	for (var i = 0; i<Locationlist.length; i++) {
		var templateExample = _.template($('#templateMap').html());
		var html = templateExample({
			"Location":JSON.stringify(Locationlist[i].myLocations)
		});

		$("#myLocations").append(html);
	}
}


function callSharedWith(SharedUserlist){
	for (var i = 0; i<SharedUserlist.length; i++) {
		var templateExample = _.template($('#templateMap').html());
		var html = templateExample({
			"Shared_user":JSON.stringify(SharedUserlist[i].sharedWith)
		});

		$("#sharedWith").append(html);
	}
}

function callSetMapname(mapname){
	var name = $('input[name="setName"]').val();
	$('#Mapname').html('<p> '+name+' </p>');
}	

function callSetMapdescription(mapdescription){
	var description = $('input[description="setDescription"]').val();
	$('#Description').html('<p> '+description+' </p>');
}	

function callSetMapstatus(mapstatus){
	var status = $('input[status="setStatus"]').val();
	$('#isPublic').html('<p> '+status+' </p>');
}	

function callSetUserSharedWith(idshared){
	var id = $('input[id="setUserSharedWith"]').val();
	$('#sharedWith').html('<p> '+sharedWith+' </p>');
}	



function callLogLocation(Location){
	var templateExample = _.template($('#templateLog').html());
	var html = templateExample({
		"Locationid":JSON.stringify($('input[name="LocationID"]').val())
	});

	$("#logLocation").html(html);
	
}

function callLogEvent(Event){
	var templateExample = _.template($('#templateLog').html());
	var html = templateExample({
		"Eventid":JSON.stringify($('input[name="EventID"]').val())
	});

	$("#logEvent").html(html);
	
}

function callDeleteLocation(Location){
	var templateExample = _.template($('#templateDelete').html());
	var html = templateExample({
		"Locationid":JSON.stringify($('input[name="LocationID"]').val())
	});

	$("#logLocation").html(html);
	
}

function callDeleteEvent(Event){
	var templateExample = _.template($('#templateDelete').html());
	var html = templateExample({
		"Eventid":JSON.stringify($('input[name="EventID"]').val())
	});

	$("#logEvent").html(html);
	
}

$(function (){
		
		
		getServerDataText('GET','/ws/User/1/Map/1/getName',callMapname);
		getServerDataText('GET','/ws/User/1/Map/1/getDescription',callMapDescription);
		getServerDataText('GET','/ws/User/1/Map/1/getStatus',callMapStatus);
		getServerData('GET','/ws/User/1/Map/1/getLocations',callLocations);
		getServerData('GET','/ws/User/1/Map/1/getSharedWith',callSharedWith);
				
		$('#SetMapname').click(function(){
			getServerData('POST','/ws/User/1/Map/1/setName/Remy',callSetMapname);
		});
		
		
		$('#SetMapdescription').click(function(){
			getServerData('POST','/ws/User/1/Map/1/setDescription/Bio',callSetMapdescription);
		});
		
		
		$('#SetMapstatus').click(function(){
			getServerData('POST','/ws/User/1/Map/1/setStatus/true',callSetMapstatus);
		});
		
		$('#SetUserSharedWith').click(function(){
			getServerData('POST','/ws/User/1/Map/1/addUserToSharedWith/Alko',callSetUserSharedWith);
		});
		
						
		
		$('#AddLocation').click(function(){
			getServerData('PUT','ws/User/1/Map/1/addLocation',callLogLocation);
		});
		
		$('#AddEvent').click(function(){
			getServerData('PUT','ws/User/1/Map/1/addEvent',callLogEvent);
		});
		
		
		$('#DeleteLocation').click(function(){
			getServerData('DELETE','ws/User/1/Map/1/removeLocation',callDeleteLocation);
		});
		
		$('#DeleteEvent').click(function(){
			getServerData('DELETE','ws/User/1/Map/1/removeEvent',callDeleteEvent);
		});
		
		
		
});

	
