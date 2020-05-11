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

function Success(ID){
	localStorage.setItem("ID",ID);
	window.location = 'http://localhost:8080/home.html';
	 
}

function MonPage(Res){
	window.location = 'http://localhost:8080/home.html';
}

function failed(){
	var templateExample = _.template($('#templateError').html());
	var html = templateExample({});

	$("#error").html(html);
}

function NewUser(ID){
	localStorage.setItem("ID",ID);
	getServerData('POST', '/ws/User/'+ID+'/setUsername/'+username,(function(){}));
	getServerData('POST', '/ws/User/'+ID+'/setPassword/'+password, MonPage);

}
function ifUser(Response){
	if(JSON.stringify(Response) == null){
		getServerData('PUT','/ws/User/addUser',NewUser);
	}
	else{
		var templateExample = _.template($('#templateErrorSignup').html());
		var html = templateExample({});

		$("#error").html(html);
	}
	
}

$(function (){
	$('#login').click(function(){
		username = $('input[name="Username"]').val();
		password = $('input[name="Password"]').val();
		Authentification('GET', '/ws/secured/message', username,password,window.Success, failed);
	});
	$('#signUp').click(function(){
		username = $('input[name="Username"]').val();
		password = $('input[name="Password"]').val();
		getServerData('GET', 'ws/User/'+username+'/'+password+'/getCorrespondantUser', ifUser);
		

	});
});
