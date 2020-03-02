package com.groupe4.server_bouchon3;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bouchons")
public class Bouchons {

		
	public Maps map = new Maps();
	public User user = new User();
	public Location location = new Location();
	public Event event = new Event();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/MapID")
	public String getMapId() {
		int id = map.getId();
		return ""+id;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Map/{ID}")
	public void SetMapId(@PathParam("ID") String newId) {
		map.setId(Integer.parseInt(newId));
		System.out.println(map.getId());
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/MapName")
	public String getMapname() {
		return map.getName();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/MapDescrition")
	public String getMapDescription() {
		return map.getDescription();
	}
		
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/UserID")
	public String getUserId() {
		int id = user.getId();
		return ""+id;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/UserName")
	public String getUserName() {
		return user.getUsername();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/UserPassword")
	public String getUserPassword() {
		return user.getPassword();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/LocationID")
	public String getLocationId() {
		int id = location.getId();
		return ""+id;
	}



	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/EventID")
	public String getEventId() {
		int id = event.getId();
		return ""+id;
	}

	
	
	
}