package ressources;


import java.util.ArrayList;
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
	//get a map
    public Map getMap (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
    	return new Map();
    }
	
	@GET
	@Path("/getName")
	//retrieval of a map's name
    public String getName (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		return "random_map_name";
    }
	
	@GET
	@Path("/getDescription")
    //retrieval of a map's description
    public String getDescription (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		return "random_map_description";
    }
	
	@GET
	@Path("/getStatus")
    //retrieval of a map's status. Return true if it's public
    public boolean getStatus (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
    	return true;
    }
	
	@GET
	@Path("/getLocations")
    //retrieval of map's list of locations
    public List<Location> getLocations (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id){
		List<Location> locations = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            locations.add(new Location());
        }
        return locations;
    }
	
	@GET
	@Path("/getSharedWith")
    //retrieval of the list of users to whom this map has been shared
    public List<User> getSharedWith (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id){
		List<User> sharedWith = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            sharedWith.add(new User());
        }
        return sharedWith;
    }
	
	/* POST */
	
	@POST
	@Path("/setName/{Name}")
	//set a map's name
    public void setName (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("Name") String name) {}
	
	@POST
	@Path("/setDescription/{Description}")
    //retrieval of a map's description
	public void setDescription (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("Description") String description) {}
	
	@POST
	@Path("/setStatus/{Status}")
    //retrieval of a map's status. Return true if it's public
	public void setStatus (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("Status") boolean status) {} //true = public, false = private
	
	@POST
	@Path("/addUserToSharedWith/{UserIDToAdd}")
    //retrieval of the list of users to whom this map has been shared
	public void addUserToSharedWith (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("UserIDToAdd") int user_id_to_add) {}
	
	/* PUT */
    
	@PUT
	@Path("/addLocation")
    //add a new empty location to the map and returns his id
    public int addLocation (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		return 0;
	}
	
	@PUT
	@Path("/addEvent")
    //add a new empty event to the map and returns the new event's id
    public int addEvent(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		return 0;
	}
    
    
    /* DELETE */
    
	@DELETE
	@Path("/removeLocation/{LocationID}")
	//remove a location
    public void removeLocation(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("LocationID") int location_id) {}
	
	@DELETE
	@Path("/removeEvent/{EventID}")
    //remove an event
    public void removeEvent(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("EventID") int event_id) {}
	
}
