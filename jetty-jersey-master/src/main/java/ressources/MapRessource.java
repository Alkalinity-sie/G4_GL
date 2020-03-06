package ressources;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import couchedepersistance.Location;
import couchedepersistance.User;
import couchedepersistance.MapDao;

@Path("/Map")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MapRessource implements MapDao {
	
	@GET
	@Path("/{MapID}/Name")
	//retrieval of a map's name
    public String getName (@PathParam("MapID") int map_id) {
		return "random_map_name";
    }
	
	@GET
	@Path("/{MapID}/Description")
    //retrieval of a map's description
    public String getDescription (@PathParam("MapID") int map_id) {
		return "random_map_description";
    }
	
	@GET
	@Path("/{MapID}/Status")
    //retrieval of a map's status. Return true if it's public
    public boolean isPublic (@PathParam("MapID") int map_id) {
    	return true;
    }
	
	@GET
	@Path("/{MapID}/Locations")
    //retrieval of map's list of locations
    public List<Location> getLocations (@PathParam("MapID") int map_id){
		List<Location> locations = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            locations.add(new Location());
        }
        return locations;
    }
	
	@GET
	@Path("/{MapID}/SharedWith")
    //retrieval of the list of users to whom this map has been shared
    public List<User> getSharedWith (@PathParam("MapID") int map_id){
		List<User> sharedWith = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            sharedWith.add(new User());
        }
        return sharedWith;
    }
	
	@POST
	@Path("/{MapID}/{Name}/Name")
	//set a map's name
    public void setName (@PathParam("MapID") int map_id, @PathParam("Name") String name) {}
	
	@POST
	@Path("/{MapID}/{Description}/Description")
    //retrieval of a map's description
	public void setDescription (@PathParam("MapID") int map_id, @PathParam("Description") String description) {}
	
	@POST
	@Path("/{MapID}/{Status}/Status")
    //retrieval of a map's status. Return true if it's public
	public void setStatus (@PathParam("MapID") int map_id, @PathParam("Status") boolean status) {} //true = public, false = private
	
	@POST
	@Path("/{MapID}/{LocationID}/Location")
    //retrieval of map's list of locations
	public void addLocation (@PathParam("MapID") int map_id, @PathParam("LocationID") int location_id) {}
	
	@POST
	@Path("/{MapID}/{UserID}/User")
    //retrieval of the list of users to whom this map has been shared
	public void addUserToSharedWith (@PathParam("MapID") int map_id, @PathParam("UserID") int user_id) {}
	
}
