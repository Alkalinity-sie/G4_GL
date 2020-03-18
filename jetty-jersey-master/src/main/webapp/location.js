function getServerData(url, success){
    $.ajax({
    	type: 'GET',
        dataType: "text",
        url: url
    }).done(success);
}
function getServerDataJ(url, success){
    $.ajax({
    	type: 'GET',
        dataType: "json",
        url: url
    }).done(success);
}
function postServerData(url, success){
    $.ajax({
    	type: 'POST',
        dataType: "json",
        url: url
    }).done(success);
}

function callLocationName(result){
	var templateExample = _.template($('#templateLocation').html());

	var html = templateExample({
		"attribut":"name: "+ result
	});
	$("#LocationName").append(html);
}
$(function(){
		getServerData('/ws/User/1/Map/1/Location/1/getName',callEventName);
});

function setLocationName(result){
	var templateExample = _.template($('#templateLocation').html());

	var html = templateExample({
		"attribut":"name : "+ $('input[name="setLocationName"]').val()
	});
	$("#LocationName").html(html);
}

$('#SetLocationname').click(function(){
		postServerData('/ws/User/1/Map/1/Location/1/setName/Youssef',setLocationName);
});

function callLocationDes(result){
	var templateExample = _.template($('#templateLocation').html());

	var html = templateExample({
		"attribut":"description: "+ result
	});
	$("#EventLocation").append(html);
}
$(function(){
	getServerData('/ws/User/1/Map/1/Location/1/getDescription',callLocationDes);
});

function setLocationDes(result){
	var templateExample = _.template($('#templateLocation').html());

	var html = templateExample({
		"attribut":"description : "+ $('input[name="setLocationDescription"]').val()
	});
	$("#LocationDescription").html(html);
}
$('#SetLocationdescription').click(function(){
		postServerData('/ws/User/1/Map/1/Location/1/setDescription/Miam',setLocationName);
});

function callLocationAddress(result){
	var templateExample = _.template($('#templateLocation').html());

	var html = templateExample({
		"attribut":"address: "+ result
	});
	$("#LocationAddress").append(html);
}
$(function(){
	getServerData('/ws/User/1/Map/1/Location/1/getAddress',callLocationAddress);
});

function callLocationLabels(result){
	var templateExample = _.template($('#templateLocation').html());

	var html = templateExample({
		"attribut":"labels: "+ result
	});
	$("#LocationLabels").append(html);
}
$(function(){
		getServerData('/ws/User/1/Map/1/Location/1/getLabels',callEventLabels);
});
