$(function (){
		$.ajax({
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
		});

		$('#AddMap').click(function(){
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
		});



});

	

