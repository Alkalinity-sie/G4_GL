
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
		"logAttribute":'Map added'
	});

	$("#log").html(html);
	
}

function callLogSharedMap(Map){
	var templateExample = _.template($('#templateLog').html());
	var html = templateExample({
		"logAttribute":'Map added'
	});

	$("#logShared").html(html);
}

function callSetUsername(username){
	var name = $('input[name="setName"]').val();
	var templateExample = _.template($('#templateName').html());
	var html = templateExample({
		"attribute":JSON.stringify(name)
	});
	$('#name').html(html);
}	

function callDeletePersonnalMap(Map){
	var templateExample = _.template($('#templateLog').html());
	var html = templateExample({
		"logAttribute":'Map deleted'
	});

	$("#log").html(html);
}

function callDeleteSharedMap(Map){
	var templateExample = _.template($('#templateLog').html());
	var html = templateExample({
		"logAttribute":'Map deleted'
	});

	$("#logShared").html(html);
}

function callGetUser(User){
	var templateExample = _.template($('#templateUser').html());
	var html = templateExample({
			"name":JSON.stringify(User.username),
			"mdp":JSON.stringify(User.password)
	});

	$("#UserInfo").html(html);
}
function callSetPassword(password){
	var templateExample = _.template($('#templateName').html());
	var html = templateExample({
		"attribute": 'password set'
	});
	$('#name').html(html);
}

$(function (){
		
		var userid ='1';

		getServerDataText('GET','/ws/User/1/getUsername',callUsername);
		
		$('#getUser').click(function(){
			userid = $('input[name="UserID"]').val();
			getServerData('GET', 'ws/User/'+userid+'/getUser', callGetUser);
			$("#PersonnalMaps").empty();
			getServerData('GET','/ws/User/'+userid+'/getPersonnalMaps',callPersonnalMap);
			$("#MapSharedToHim").empty();		
			getServerData('GET','/ws/User/'+userid+'/getMapsSharedToMe',callSharedMaps);		
		});

		$('#ChooseMaps').click(function(){
			var input = $('input[name="MapID"]').val();
			getServerDataText('GET','/ws/User/'+userid+'/Map/'+input+'/getName',callChooseMaps);			
			getServerDataText('GET','/ws/User/'+userid+'/Map/'+input+'/getDescription',callDescription);		
		});

		$('#AddMap').click(function(){
			getServerData('PUT','ws/User/'+userid+'/addPersonnalMap',callLogPersonnalMap);
		});
		
		$('#SetUsername').click(function(){
			var input = $('input[name="setName"]').val();
			getServerData('POST','/ws/User/'+userid+'/setUsername/'+input,callSetUsername);
		});

		$('#MyMaps').click(function(){
			$("#PersonnalMaps").empty();
			getServerData('GET','/ws/User/'+userid+'/getPersonnalMaps',callPersonnalMap);
		});

		$('#SharedMaps').click(function(){
			$("#MapSharedToHim").empty();
			getServerData('GET','/ws/User/'+userid+'/getMapsSharedToMe',callSharedMaps);
		});

		$('#ChooseSharedMaps').click(function(){
			var input = $('input[name="SharedMapID"]').val();
			getServerDataText('GET','/ws/User/'+userid+'/Map/'+input+'/getName',callChooseMaps);			
			getServerDataText('GET','/ws/User/'+userid+'/Map/'+input+'/getDescription',callDescription);
		});

		$('#AddSharedMap').click(function(){
			var from = $('input[name="SharedMapFrom"]').val();
			var mapid = $('input[name="SharedMapIDtoAdd"]').val();
			getServerData('PUT', 'ws/User/'+userid+'/addMapToSharedToMe/'+from+'/'+mapid, callLogSharedMap);
		});

		$('#DeleteMap').click(function(){
			var input = $('input[name="MapIDtoDelete"]').val();
			getServerData('DELETE', 'ws/User/'+userid+'/removePersonnalMap/'+input, callDeletePersonnalMap);
		});

		$('#DeleteSharedMap').click(function(){
			var input = $('input[name="SharedMapIDtoDelete"]').val();
			getServerData('DELETE', 'ws/User/'+userid+'/removeSharedMap/'+input, callDeleteSharedMap);
		});

		$('#SetPassword').click(function(){
			var input = $('input[name="Password"]').val();
			getServerData('POST','/ws/User/'+userid+'/setPassword/'+input,callSetPassword);
		});

});

	

