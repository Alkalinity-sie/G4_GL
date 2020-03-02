package com.groupe4.server_bouchon3.ressource;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.groupe4.server_bouchon3.Location;
import com.groupe4.server_bouchon3.service.LocationService;


@Path("/Location")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationRessource {
	
	LocationService locationService = new LocationService();
	
	@GET
	public List<Location> getLocation(){
		return locationService.getAllLocation();
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	public Location addLocation(Location u) {
		locationService.addLocation(u);
		return u;
	}
	
	@GET
	@Path("/{LocationID}")
	public Location getEvent(@PathParam("LocationID") int id) {
		return locationService.getLocation(id);
	}

	


}
