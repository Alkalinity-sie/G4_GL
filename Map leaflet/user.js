var mymap;
var geocodeService;
var searchControl;
var address;
var userid ='1';
var MyMapId = '1';
var SharedMapID = '1';

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

function onMapClick(e) {
	var res = geocodeService.reverse().latlng(e.latlng).run(function (error,result) {
		if(error){
			return;
		}
		address = result.address.Match_addr; // result address en string
		L.marker(result.latlng).addTo(mymap).bindPopup(result.address.Match_addr).openPopup();
	});


}
function callLocation(Locations){
	for(var i = 0; i<Locations.length;i++){
		var templateExample = _.template($('#templateName').html());
		var html = templateExample({
			"attribute":JSON.stringify(Locations[i].address),
		});

		$("#afficheLocation").append(html);
	}
}

function callAddLocation(ID){
	console.log(ID);
	getServerData('POST', '/ws/User/'+userid+'/Map/'+MyMapId+'/Location/'+ID+'/setAddress/'+address, callback);
}

function callback(){
	console.log("callback");
	console.log(address);
}

$(function (){
		
		

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
			MyMapId= $('input[name="MapID"]').val();
			getServerDataText('GET','/ws/User/'+userid+'/Map/'+MyMapId+'/getName',callChooseMaps);			
			getServerDataText('GET','/ws/User/'+userid+'/Map/'+MyMapId+'/getDescription',callDescription);
			$('#afficheLocation').empty();		
			getServerData('GET', '/ws/User/'+userid+'/Map/'+MyMapId+'/getLocations',callLocation)
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
			SharedMapID = $('input[name="SharedMapID"]').val();
			getServerDataText('GET','/ws/User/'+userid+'/Map/'+SharedMapID+'/getName',callChooseMaps);			
			getServerDataText('GET','/ws/User/'+userid+'/Map/'+SharedMapID+'/getDescription',callDescription);
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


		
		mymap = L.map('mapleflet').setView([48.866667, 2.333333], 11);
		L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    	maxZoom: 18,
    	id: 'mapbox/streets-v11',
    	tileSize: 512,
    	zoomOffset: -1,
    	accessToken: 'pk.eyJ1IjoibGVkb2RvaW5jb25udSIsImEiOiJjazg3OXNkZWUwZDdiM2VwcmQxbjI0cWczIn0.gsqi-Jp2FZ066BoQ_0IEJQ'
		}).addTo(mymap);


		L.control.scale().addTo(mymap);
		searchControl = L.esri.Geocoding.geosearch().addTo(mymap);
		geocodeService = L.esri.Geocoding.geocodeService();
		mymap.on('click', onMapClick);
		
		$('#addLocation').click(function(){

			getServerData('PUT', '/ws/User/'+userid+'/Map/'+MyMapId+'/addLocation', callAddLocation);
	
		});

});

	

