$(function (){
		$.ajax({
			type: 'GET',
			dataType: 'text',
			url:'/ws/User/1/getUsername'
		}).done(function(username){
				$('#name').append('<p>'+username+'</p>');
				console.log(username)
		});

		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: '/ws/User/1/getPersonnalMaps'
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
			url: '/ws/User/1/getMapsSharedToMe'
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
				dataType: 'text',
				data: id,
				url: '/ws/User/1/Map/1/getName'
			}).done(function(Map){
				$('#MapName').html('Name: '+Map);
			});

			$.ajax({
				type:'GET',
				dataType: 'text',
				data: id,
				url: '/ws/User/1/Map/1/getDescription'
			}).done(function(Map){
				$('#Description').html('Description: '+Map);
			});
		});

		$('#AddMap').click(function(){
			$.ajax({
				type:'PUT',
				dataType: 'json',
				url: 'ws/User/1/addPersonnalMap'
			}).done(function(Map){
				$('#log').html('<p> Map with id: '+$('input[name="MapID"]').val()+' is added</p>');
			});
		});

		$('#SetUsername').click(function(){
			$.ajax({
				type: 'POST',
				dataType: 'json',
				url:'/ws/User/1/setUsername/Fayas'
			}).done(function(username){
					var name = $('input[name="setName"]').val();
					$('#name').html('<p>'+name+'</p>');
			});
		});

		$('#MyMaps').click(function(){
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url: '/ws/User/1/getPersonnalMaps'
			}).done(function(Maps){
				var m= '';
				for (var i = 0; i<Maps.length; i++) {
				m += '<p> MapId : '+Maps[i].id+' MapName: '+Maps[i].name+'</p>';
				}
				$('#PersonnalMaps').html(m);
			});
		});

		$('#SharedMaps').click(function(){
			$.ajax({
			type: 'GET',
			dataType: 'json',
			url: '/ws/User/1/getMapsSharedToMe'
		}).done(function(Maps){
			var m= '';
			for (var i = 0; i<Maps.length; i++) {
				m += '<p> MapId : '+Maps[i].id+' MapName: '+Maps[i].name+'</p>';
			}
			$('#MapSharedToHim').html(m);
		});
		});

		$('#ChooseSharedMaps').click(function(){
			var id = $('input[name="SharedMapID"]').val();
			console.log(id);
			$.ajax({
				type:'GET',
				dataType: 'text',
				data: id,
				url: '/ws/User/1/Map/1/getName'
			}).done(function(Map){
				$('#MapSharedToHim').html('<p> MapId: '+id+' MapName: '+Map.name+'</p>');
			});
		});

		$('#AddSharedMap').click(function(){
			$.ajax({
				type:'PUT',
				dataType: 'json',
				url: 'ws/User/1/addMapToSharedToMe/2/2'
			}).done(function(Map){
				$('#logShared').html('<p> Map with id: '+$('input[name="SharedMapID"]').val()+' is added</p>');
			});
		});

		$('#DeleteMap').click(function(){
			$.ajax({
				type: 'DELETE',
				dataType: 'json',
				url: 'ws/User/1/removePersonnalMap/1'
			}).done(function(){
				$('#log').html('<p> Map deleted</p>')
			});
		});

		$('#DeleteSharedMap').click(function(){
			$.ajax({
				type: 'DELETE',
				dataType: 'json',
				url: 'ws/User/1/removeSharedMap/1'
			}).done(function(){
				$('#logShared').html('<p> Map deleted</p>')
			});
		});



});

	

