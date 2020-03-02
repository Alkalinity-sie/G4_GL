package com.groupe4.server_bouchon3.ressource;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import com.groupe4.server_bouchon3.Maps;
import com.groupe4.server_bouchon3.service.MapService;


@Path("/Map")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MapRessource {
	
	MapService mapService = new MapService();
	
	@GET
	public List<Maps> getMaps(){
		return mapService.getAllMaps();
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	public Maps addMaps(Maps m) {
		mapService.addMap(m);
		return m;
	}
	
	@GET
	@Path("/{MapID}")
	public Maps getEvent(@PathParam("MapID") int id) {
		return mapService.getMap(id);
	}

	


}
