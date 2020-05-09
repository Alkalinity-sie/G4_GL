
var mymap;
var geocodeService;
var searchControl;
var myLocation;
var address;
var latlng;
var Routing;
function onMapClick(e) {
    		//alert("You clicked the map at " + e.latlng);
    		//var marker = L.marker(e.latlng).addTo(mymap);
    		latlng = e.latlng;
    		geocodeService.reverse().latlng(e.latlng).run(function (error,result) {
    			if(error){
    				return;
    			}
    			address = result.address.Match_addr; // result address en string
      			console.log(address);
      			L.marker(result.latlng).addTo(mymap).bindPopup(result.address.Match_addr).openPopup();
    		});
    		//return marker;
}

function removemarker(e){
	mymap.removeLayer(e);
}
function getServerData(type,url,success){
	$.ajax({
		type:type,
		dataType:'json',
		url:url
	}).done(success);

}

function yaller(e){
	mymap.removeControl(Routing);
	Routing = L.Routing.control({
	  waypoints: [
	    L.latLng(e.latlng),
	    L.latLng(latlng)
	  ],
	  language : 'fr'
	}).addTo(mymap);
}

$(function (){

		mymap = L.map('mapleflet1').setView([48.866667, 2.333333], 11);
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
	


		Routing = L.Routing.control({
			geocoder: L.Control.Geocoder.nominatim(),
			routeWhileDragging: true,
			reverseWaypoints: true
		}).addTo(mymap);
    // permet d'utiliser la position actuelle
		$('#yaller').click(function(){
			mymap.locate({setView: true, maxZoom: 11});
			mymap.on('locationfound', yaller);
		});



});