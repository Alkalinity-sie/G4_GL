$(function (){
		
		$.ajax({
			type: 'GET',
			dataType: 'text',
			data: id,
			url:'/ws/User/1/Map/1/getName'
		}).done(function(Map){
				$('#MapName').html('MapName : '+Map);
		});
		
		$.ajax({
			type: 'GET',
			dataType: 'text',
			data: id,
			url:'/ws/User/1/Map/1/getDescription'
		}).done(function(Map){
				$('#Description').html('Description : '+Map);
		});
		
		$.ajax({
			type: 'GET',
			dataType: 'boolean',
			data: id,
			url:'/ws/User/1/Map/1/getStatus'
		}).done(function(Map){
				$('#isPublic').html('isPublic : '+Map);
		});
		
		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: '/ws/User/1/Map/1/getLocations'
		}).done(function(Locationlist){
			var m= '';
			for (var i = 0; i<Maps.length; i++) {
				m += '<p> MapLocation : '+Locationlist[i].myLocations '</p>';
			}
			$('#myLocations').append(m);
		});
		
		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: '/ws/User/1/Map/1/getSharedWith'
		}).done(function(SharedUserlist){
			var m= '';
			for (var i = 0; i<Maps.length; i++) {
				m += '<p> SharedWith : '+SharedUserlist[i].sharedWith '</p>';
			}
			$('#sharedWith').append(m);
		});
		
		
		
		
		
		$('#setName').click(function(){
			$.ajax({
				type: 'POST',
				dataType: 'text',
				url:'/ws/User/1/Map/1/setName/Remy'
			}).done(function(mapname){
					var name = $('input[name="setName"]').val();
					$('#name').html('<p> '+ name +' </p>');
			});
		});
		
		$('#setDescription').click(function(){
			$.ajax({
				type: 'POST',
				dataType: 'text',
				url:'/ws/User/1/Map/1/setDescription/Bio'
			}).done(function(mapdescription){
					var description = $('input[description="setDescription"]').val();
					$('#description').html('<p> '+ description +' </p>');
			});
		});
		
		
		$('#setStatus').click(function(){
			$.ajax({
				type: 'POST',
				dataType: 'boolean',
				url:'/ws/User/1/Map/1/setStatus/true'
			}).done(function(mapstatus){
					var status = $('input[status="setStatus"]').val();
					$('#isPublic').html('<p> '+ isPublic +' </p>');
			});
		});
		
		$('#addUserToSharedWith').click(function(){
			$.ajax({
				type: 'POST',
				dataType: 'json',
				url:'/ws/User/1/Map/1/addUserToSharedWith/Alko'
			}).done(function(idshared){
					var idshared = $('input[idshared="addUserToSharedWith"]').val();
					$('#sharedWith').html('<p> '+ sharedWith +' </p>');
			});
		});
		
		
		
		
		$('#addLocation').click(function(){
			$.ajax({
				type:'PUT',
				dataType: 'json',
				url: 'ws/User/1/Map/1/addLocation'
			}).done(function(maplocation){
				$('#log').html('<p> Location with id: '+$('input[name="LocationID"]').val()+' is added</p>');
			});
		});
		
		
		$('#addEvent').click(function(){
			$.ajax({
				type:'PUT',
				dataType: 'json',
				url: 'ws/User/1/Map/1/addEvent'
			}).done(function(mapevent){
				$('#log').html('<p> Event with id: '+$('input[name="EventID"]').val()+' is added</p>');
			});
		});
		
		
		
		
		$('#DeleteLocation').click(function(){
			$.ajax({
				type: 'DELETE',
				dataType: 'json',
				url: 'ws/User/1/Map/1/removeLocation/1'
			}).done(function(){
				$('#log').html('<p> Location deleted</p>')
			});
		});
		
		
		$('#DeleteEvent').click(function(){
			$.ajax({
				type: 'DELETE',
				dataType: 'json',
				url: 'ws/User/1/Map/1/removeEvent/1'
			}).done(function(){
				$('#log').html('<p> Event deleted</p>')
			});
		});

});

	

