
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

function callUsername(username){
	var templateExample = _.template($('#templateName').html());
	var html = templateExample({
		"attribute":JSON.stringify(username)
	});

	$("#name").append(html);
}

function callPersonnalMap(Maps){
	for (var i = 0; i<Maps.length; i++) {
		var templateExample = _.template($('#templateMap').html());
		var html = templateExample({
			"id":JSON.stringify(Maps[i].id),
			"name":JSON.stringify(Maps[i].name)
		});

		$("#PersonnalMaps").append(html);
	}
}

function callSharedMaps(Maps){
	for (var i = 0; i<Maps.length; i++) {
		var templateExample = _.template($('#templateMap').html());
		var html = templateExample({
			"id":JSON.stringify(Maps[i].id),
			"name":JSON.stringify(Maps[i].name)
		});

		$("#MapSharedToHim").append(html);
	}
}

function callChooseMaps(Maps){
	var templateExample = _.template($('#templateName').html());
	var html = templateExample({
		"attribute":JSON.stringify(Maps)
	});

	$("#MapName").html(html);
}

function callDescription(Description){
	var templateExample = _.template($('#templateDescription').html());
	var html = templateExample({
		"description":JSON.stringify(Description)
	});

	$("#Description").html(html);
}

function callLogPersonnalMap(Map){
	var templateExample = _.template($('#templateLog').html());
	var html = templateExample({
		"Mapid":JSON.stringify($('input[name="MapID"]').val())
	});

	$("#log").html(html);
	
}

function callLogSharedMap(Map){
	var templateExample = _.template($('#templateLog').html());
	var html = templateExample({
		"Mapid":JSON.stringify($('input[name="SharedMapID"]').val())
	});

	$("#logShared").html(html);
}

function callSetUsername(username){
	var name = $('input[name="setName"]').val();
	$('#name').html('<p>'+name+'</p>');
}	

function callDeletePersonnalMap(Map){
	var templateExample = _.template($('#templateDelete').html());
	var html = templateExample({
		"Mapid":JSON.stringify($('input[name="MapID"]').val())
	});

	$("#log").html(html);
}

function callDeleteSharedMap(Map){
	var templateExample = _.template($('#templateDelete').html());
	var html = templateExample({
		"Mapid":JSON.stringify($('input[name="SharedMapID"]').val())
	});

	$("#logShared").html(html);
}

$(function (){

		getServerDataText('GET','/ws/User/1/getUsername',callUsername);
		getServerData('GET','/ws/User/1/getPersonnalMaps',callPersonnalMap);		
		getServerData('GET','/ws/User/1/getMapsSharedToMe',callSharedMaps);
		
		$('#ChooseMaps').click(function(){
			getServerDataText('GET','/ws/User/1/Map/1/getName',callChooseMaps);			
			getServerDataText('GET','/ws/User/1/Map/1/getDescription',callDescription);			
		});

		$('#AddMap').click(function(){
			getServerData('PUT','ws/User/1/addPersonnalMap',callLogPersonnalMap);
		});
		
		$('#SetUsername').click(function(){
			getServerData('POST','/ws/User/1/setUsername/Fayas',callSetUsername);
		});

		$('#MyMaps').click(function(){
			$("#PersonnalMaps").empty();
			getServerData('GET','/ws/User/1/getPersonnalMaps',callPersonnalMap);			
		});

		$('#SharedMaps').click(function(){
			$("#MapSharedToHim").empty();
			getServerData('GET','/ws/User/1/getMapsSharedToMe',callSharedMaps);			
		});

		$('#ChooseSharedMaps').click(function(){
			getServerDataText('GET','/ws/User/1/Map/1/getName',callChooseMaps);			
			getServerDataText('GET','/ws/User/1/Map/1/getDescription',callDescription);	
		});

		$('#AddSharedMap').click(function(){
			getServerData('PUT', 'ws/User/1/addMapToSharedToMe/2/2', callLogSharedMap);			
		});

		$('#DeleteMap').click(function(){
			getServerData('DELETE', 'ws/User/1/removePersonnalMap/1', callDeletePersonnalMap);
		});

		$('#DeleteSharedMap').click(function(){
			getServerData('DELETE', 'ws/User/1/removeSharedMap/1', callDeleteSharedMap);			
		});

});

	

