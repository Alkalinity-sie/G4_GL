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

function callEventName(result){
	var templateExample = _.template($('#templateEvent').html());

	var html = templateExample({
		"attribut":"name: "+ result
	});
	$("#EventName").append(html);
}
$(function(){
		getServerData('/ws/User/1/Map/1/Event/1/getName',callEventName);
});

function setEventName(result){
	var templateExample = _.template($('#templateEvent').html());

	var html = templateExample({
		"attribut":"name : "+ $('input[name="setEventName"]').val()
	});
	$("#EventName").html(html);
}
$('#SetEventname').click(function(){
		postServerData('/ws/User/1/Map/1/Event/1/setName/sarah',setEventName);
});

function callEventDes(result){
	var templateExample = _.template($('#templateEvent').html());

	var html = templateExample({
		"attribut":"description: "+ result
	});
	$("#EventDescription").append(html);
}
$(function(){
	getServerData('/ws/User/1/Map/1/Event/1/getDescription',callEventDes);
});

function setEventDes(result){
	var templateExample = _.template($('#templateEvent').html());

	var html = templateExample({
		"attribut":"description : "+ $('input[name="setEventDescription"]').val()
	});
	$("#EventDescription").html(html);
}
$('#SetEventdescription').click(function(){
		postServerData('/ws/User/1/Map/1/Event/1/setDescription/sarah',setEventName);
});

function callEventAddress(result){
	var templateExample = _.template($('#templateEvent').html());

	var html = templateExample({
		"attribut":"address: "+ result
	});
	$("#EventAddress").append(html);
}
$(function(){
	getServerData('/ws/User/1/Map/1/Event/1/getAddress',callEventAddress);
});

function callEventLabels(result){
	var templateExample = _.template($('#templateEvent').html());

	var html = templateExample({
		"attribut":"labels: "+ result
	});
	$("#EventLabels").append(html);
}
$(function(){
		getServerData('/ws/User/1/Map/1/Event/1/getLabels',callEventLabels);
});

function callEventBeginning(result){
	var templateExample = _.template($('#templateDateTime').html());

	var html = templateExample({
		"attribut":"beginning: ",
		"dayOfMonth":result.dayOfMonth,
		"month":result.month,
		"year":result.year,
		"hour": result.hour,
		"minute":result.minute
	});
	$("#EventBeginning").append(html);
}
$(function(){
		getServerDataJ('/ws/User/1/Map/1/Event/1/getBeginning',callEventBeginning);
});

function callEventEnd(result){
	var templateExample = _.template($('#templateDateTime').html());

	var html = templateExample({
		"attribut":"end: ",
		"dayOfMonth":result.dayOfMonth,
		"month":result.month,
		"year":result.year,
		"hour": result.hour,
		"minute":result.minute		
	});
	$("#EventEnd").append(html);
}
$(function(){
		getServerDataJ('/ws/User/1/Map/1/Event/1/getEnd',callEventEnd);
});




/*

$(function (){
	$.ajax({
		type: 'GET',
		dataType: 'text',
		url:'/ws/User/1/Map/1/Event/1/getBeginning'
	}).done(function(datetime){
			$('#EventBeginning').append('<p>'+"begin: "+datetime+'</p>');
			console.log(datetime)
	});
	$('#SetEventbeginning').click(function(){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url:'/ws/User/1/Map/1/Event/1/setBeginning/1/1/1/12/30'
		}).done(function(Eventbeginning){
				var name = $('input[name="setEventBeginning"]').val();
				$('#EventBeginning').html('<p>'+"begin: "+name+'</p>');
		});
	});

	$.ajax({
		type: 'GET',
		dataType: 'text',
		url:'/ws/User/1/Map/1/Event/1/getEnd'
	}).done(function(datetime){
			$('#EventEnd').append('<p>'+"end: "+datetime+'</p>');
			console.log(datetime)
	});
	$('#SetEventend').click(function(){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url:'/ws/User/1/Map/1/Event/1/setEnd/1/1/1/12/30'
		}).done(function(Eventend){
				var name = $('input[name="setEventEnd"]').val();
				$('#EventEnd').html('<p>'+"end: "+name+'</p>');
		});
	});
	
	$.ajax({
		type: 'GET',
		dataType: 'text',
		url:'/ws/User/1/Map/1/Event/1/getName'
	}).done(function(Eventname){
			$('#EventName').append('<p>'+"name: "+Eventname+'</p>');
			console.log(Eventname)
	});
	$('#SetEventname').click(function(){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url:'/ws/User/1/Map/1/Event/1/setName/sarah'
		}).done(function(Eventname){
				var name = $('input[name="setEventName"]').val();
				$('#EventName').html('<p>'+"name: "+name+'</p>');
		});
	});

	$.ajax({
		type: 'GET',
		dataType: 'text',
		url:'/ws/User/1/Map/1/Event/1/getDescription'
	}).done(function(Eventdescription){
			$('#EventDescription').append('<p>'+"description: "+Eventdescription+'</p>');
			console.log(Eventdescription)
	});
	$('#SetEventdescription').click(function(){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url:'/ws/User/1/Map/1/Event/1/setDescription/sarah'
		}).done(function(Eventdescription){
				var name = $('input[name="setEventDescription"]').val();
				$('#EventDescription').html('<p>'+"description: "+name+'</p>');
		});
	});
	
	$.ajax({
		type: 'GET',
		dataType: 'text',
		url:'/ws/User/1/Map/1/Event/1/getAddress'
	}).done(function(Eventaddress){
			$('#EventAddress').append('<p>'+"address: "+Eventaddress+'</p>');
			console.log(Eventaddress)
	});
	$('#SetEventaddress').click(function(){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url:'/ws/User/1/Map/1/Event/1/setAddress/sarah'
		}).done(function(Eventadress){
				var name = $('input[name="setEventAddress"]').val();
				$('#EventAddress').html('<p>'+"address: "+name+'</p>');
		});
	});
	
	$.ajax({
		type: 'GET',
		dataType: 'text',
		url:'/ws/User/1/Map/1/Event/1/getLabels'
	}).done(function(Eventlabels){
			$('#EventLabels').append('<p>'+"labels: "+Eventlabels+'</p>');
			console.log(Eventlabels)
	});
	$('#SetEventlabels').click(function(){
		$.ajax({
			type: 'POST',
			dataType: 'json',
			url:'/ws/User/1/Map/1/Event/1/setName/sarah'
		}).done(function(Eventlabels){
				var name = $('input[name="setEventLabels"]').val();
				$('#EventLabels').html('<p>'+"labels: "+name+'</p>');
		});
	});
	
	$.ajax({
		type: 'GET',
		dataType: 'ImageIcon',
		url:'/ws/User/1/Map/1/Event/1/getPhotos'
	}).done(function(Eventphotos){
			$('#EventPhotos').append('<p>'+"photos: "+Eventphotos+'</p>');
			console.log(Eventphotos)
	});
});
		<script id="templateDateTime" type="text/template">
			<p>
				beginning: <%= dayOfMonth %>  <%= month %>    <%= hour %>  <%= minute %>
			</p>
		</script>
*
*/
