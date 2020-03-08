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