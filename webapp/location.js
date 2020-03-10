$(function (){
	/*	$.ajax({
			type: 'GET',
			dataType: 'text',
			url:'/ws/User/1/Username'
		}).done(function(username){
				$('#name').append('<p>'+username+'</p>');
				console.log(username)
		});

		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: '/ws/User/1/PersonnalMaps'
		}).done(function(Maps){
			var m= '';
			for (var i = 0; i<Maps.length; i++) {
				m += '<p> MapId : '+Maps[i].id+' MapName: '+Maps[i].name+'</p>';
			}
			$('#PersonnalMaps').append(m);
		});

		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: '/ws/User/1/MapsSharedToHim'
		}).done(function(Maps){
			var m= '';
			for (var i = 0; i<Maps.length; i++) {
				m += '<p> MapId : '+Maps[i].id+' MapName: '+Maps[i].name+'</p>';
			}
			$('#MapSharedToHim').append(m);
		});
		$('#ChooseMaps').click(function(){
			var id = $('input[name="MapID"]').val();
			console.log(id);
			$.ajax({
				type:'GET',
				dataType: 'json',
				data: id,
				url: '/ws/User/1/Map/'+$('input[name="MapID"]').val()
			}).done(function(Map){
				$('#PersonnalMaps').html('<p> MapId: '+Map.id+' MapName: '+Map.name+'</p>');
			});
		});*/

		/*$('#AddMap').click(function(){
			$.ajax({
				type:'POST',
				dataType: 'json',
				url: 'ws/User/1/'+$('input[name="MapID"]').val()+'/PersonnalMap'
			}).done(function(Map){
				$('#log').html('<p> Map with id: '+$('input[name="MapID"]').val()+' is added</p>');
			});
		});

		$('#SetUsername').click(function(){
			$.ajax({
				type: 'POST',
				dataType: 'json',
				url:'/ws/User/1/Fayas/Username'
			}).done(function(username){
					var name = $('input[name="setName"]').val();
					$('#name').html('<p>'+name+'</p>');
			});
		});*/
		$.ajax({
			type: 'GET',
			dataType: 'text',
			url:'/ws/User/1/Map/1/Location/1/getName'
		}).done(function(Lname){
				$('#Locationname').append('<p>'+Lname+'</p>');
				console.log(Lname)
		});

		$('#SetLocationname').click(function(){
			$.ajax({
				type: 'POST',
				dataType: 'json',
				url:'/ws/User/1/Map/1/Location/1/setName/Sushi'
			}).done(function(Lname){
					var name = $('input[name="setLocationName"]').val();
					$('#Locationname').html('<p>'+name+'</p>');
			});
		});
		$.ajax({
			type: 'GET',
			dataType: 'text',
			url:'/ws/User/1/Map/1/Location/1/getDescription'
		}).done(function(LDesc){
				$('#Locationdesc').append('<p>'+LDesc+'</p>');
				console.log(LDesc)
		});

		$('#SetDesc').click(function(){
			$.ajax({
				type: 'POST',
				dataType: 'json',
				url:'/ws/User/1/Map/1/Location/1/setDescription/Descriptions'
			}).done(function(LDesc){
					var name = $('input[name="setLocationdesc"]').val();
					$('#Locationdesc').html('<p>'+name+'</p>');
			});
		});
		$.ajax({
			type: 'GET',
			dataType: 'text',
			url:'/ws/User/1/Map/1/Location/1/getAddress'
		}).done(function(LAdrr){
				$('#Locationadrr').append('<p>'+LAdrr+'</p>');
				console.log(LAdrr)
		});

		$('#SetAdrr').click(function(){
			$.ajax({
				type: 'POST',
				dataType: 'json',
				url:'/ws/User/1/Map/1/Location/1/setAddress/myAddress'
			}).done(function(LAdrr){
					var name = $('input[name="setLocationadrr"]').val();
					$('#Locationadrr').html('<p>'+name+'</p>');
			});
		});






});