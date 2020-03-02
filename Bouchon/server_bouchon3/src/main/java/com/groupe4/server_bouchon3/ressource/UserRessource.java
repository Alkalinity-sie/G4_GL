package com.groupe4.server_bouchon3.ressource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.groupe4.server_bouchon3.Maps;
import com.groupe4.server_bouchon3.User;
import com.groupe4.server_bouchon3.service.MapService;
import com.groupe4.server_bouchon3.service.UserService;

@Path("/User")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRessource {
	
	UserService userService = new UserService();
	MapService mapService = new MapService();
	
	@GET
	public List<User> getUser(){
		return userService.getAllUser();
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	public User addUser(User u) {
		userService.addUser(u);
		return u;
	}
	
	@GET
	@Path("/{Username}")
	public User getUser(@PathParam("Username") String name) {
		return userService.getUser(name);
	}
	@PUT
	@Path("/{Username}/Map/{MapID}")
	public User addMap(@PathParam("Username") String name, @PathParam("MapID") int MapID, User u) {
		if(u.getUsername().equals(name)) {
			Maps m = mapService.getMap(MapID);
			u.addMap(m);
			userService.updateUser(u);
		}
		return  u;
	}
	
	@GET
	@Path("/{Username}/Map")
	public List<Maps> getAllMyMaps(@PathParam("Username") String name){
		return userService.getAllMaps(userService.getUser(name));
	}
	
	@GET
	@Path("/{Username}/Map/{MapID}")
	public Maps getMyMaps(@PathParam("Username") String name, @PathParam("MapID") int MapID) {
		return userService.getMap(userService.getUser(name), MapID);
	}
	
	@GET
	@Path("/{Username}/Map/{MapID}/isPublic")
	public boolean isPublic(@PathParam("Username") String name, @PathParam("MapID") int MapID){
		Maps m = userService.getMap(userService.getUser(name), MapID);
		if(m == null) return false;
		return userService.isPublic(m);
	}

	
}
