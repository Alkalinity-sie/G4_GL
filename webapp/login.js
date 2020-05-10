var username;
var password;
function Authentification(type,url,username,password,callback,error){
	$.ajax({
		type:type,
		dataType:'text',
		url:url,
		headers:{
    		'Authorization': 'Basic ' + btoa(username + ':' + password)
  		},
  		success: callback,
  		error : error
  	});
}

function getServerData(type,url,success){
	$.ajax({
		type:type,
		dataType:'json',
		url:url
	}).done(success);

}

function Success(e){
	window.location = 'http://localhost:8081/'
}

function failed(){
	var templateExample = _.template($('#templateError').html());
	var html = templateExample({});

	$("#error").html(html);
}

function NewUser(ID){
	if(ID ==-1){
		var templateExample = _.template($('#templateErrorSignup').html());
		var html = templateExample({});
		$("#error").html(html);
	}
	else{
		getServerData('POST', 'http://localhost:8081/ws/User/'+ID+'/setUsername/'+username,(function(){}));
		getServerData('POST', 'http://localhost:8081/ws/User/'+ID+'/setPassword/'+password, Success);

	}
}

$(function (){
	$('#login').click(function(){
		username = $('input[name="Username"]').val();
		password = $('input[name="Password"]').val();
		Authentification('GET', 'http://localhost:8081/ws/secured/message', username,password,Success, failed);
	});
	$('#signUp').click(function(){
		username = $('input[name="Username"]').val();
		password = $('input[name="Password"]').val();
		getServerData('PUT','http://localhost:8081/ws/User/addUser',NewUser);

	});
});
