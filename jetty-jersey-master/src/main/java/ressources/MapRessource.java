package ressources;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import couchedepersistance.Event;
import couchedepersistance.Location;
import couchedepersistance.Map;
import couchedepersistance.User;
import couchedepersistance.MapDao;

@Path("/User/{UserID}/Map/{MapID}") //PRECISER USER_ID ICI
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MapRessource implements MapDao {
	
	/* GET */
	
	@GET
	@Path("/getMap")
	//get a map. Can either be a shared one or a personnal one depending on the user_id
    public Map getMap (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
    	Map m = Database.getPersonnalMap(user_id, map_id);
    	if(m==null) {
    		return Database.getSharedMap(user_id, map_id);
    	}
    	return m;
    	
    }
	
	@GET
	@Path("/getName")
	//retrieval of a map's name
    public String getName (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return "la map n'existe pas";
    	}
    	return m.getName();
    }
	
	@GET
	@Path("/getDescription")
    //retrieval of a map's description
    public String getDescription (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return "la map n'existe pas";
    	}
    	return m.getDescription();
    }
	
	@GET
	@Path("/getStatus")
    //retrieval of a map's status. Return true if it's public
    public boolean getStatus (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return false;
    	}
    	return m.getStatus();
    }
	
	@GET
	@Path("/getLocations")
    //retrieval of map's list of locations
    public List<Location> getLocations (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id){
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return null;
    	}
    	return m.getMyLocations();
    }
	
	@GET
	@Path("/getSharedWith")
    //retrieval of the list of users to whom this map has been shared
    public List<User> getSharedWith (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id){
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return null;
    	}
    	return m.getSharedWith();
    }
	
	/* POST */ 
	
	@POST
	@Path("/setName/{Name}")
	//set a map's name
    public void setName (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("Name") String name) {
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return;
    	}
    	m.setName(name);
	}
	
	@POST
	@Path("/setDescription/{Description}")
    //set a map's description
	public void setDescription (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("Description") String description) {
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return;
    	}
    	m.setDescription(description);
	}
	
	@POST
	@Path("/setStatus/{Status}")
    //set a map's status
	public void setStatus (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("Status") boolean status) {
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return;
    	}
    	m.setStatus(status);
	}
	
	@POST
	@Path("/addUserToSharedWith/{UserIDToAdd}")
    //add a user to the list getSharedWith of a map. And also add the map to the list sharedToMe of the user whose id is user_id_to_add
	public void addUserToSharedWith (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("UserIDToAdd") int user_id_to_add) {
		// récupération de la map
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return;
    	}
    	//récupération des 2 utilisateurs
    	User FROM = Database.getUser(user_id);
    	User TO = Database.getUser(user_id_to_add);
    	if(FROM==null || TO==null) { //si aucun utilisateur avec cet id
    		return;
    	}
    	//ajout
    	m.getSharedWith().add(TO);
    	//ajoute de la map à la liste sharedToMe de l'utilisateur avec l'id "user_id_to_add"
    	TO.getSharedToMe().add(m);
	}
	
	/* PUT */
    
	@PUT
	@Path("/addLocation")
    //add a new empty location to the map and returns his id
    public int addLocation (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return -1;
    	}
    	Location l = new Location();
    	m.getMyLocations().add(l);
    	return l.getId();
	}
	
	@PUT
	@Path("/addEvent")
    //add a new empty event to the map and returns the new event's id
    public int addEvent(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return -1;
    	}
    	Location e = new Event();
    	m.getMyLocations().add(e);
    	return e.getId();
	}
    
    /* DELETE */
    
	@DELETE
	@Path("/removeLocation/{LocationID}")
	//remove a location
    public void removeLocation(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("LocationID") int location_id) {
		Map m = getMap(user_id, map_id);
		if(m==null) return;
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l==null) return;
		m.getMyLocations().remove(l);
	}
	
	@DELETE
	@Path("/removeEvent/{EventID}")
    //remove an event
    public void removeEvent(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("EventID") int event_id) {
		Map m = getMap(user_id, map_id);
		if(m==null) return;
		Location e = Database.getEvent(user_id, map_id, event_id);
		if(e==null) return;
		m.getMyLocations().remove(e);
	}
	
}
