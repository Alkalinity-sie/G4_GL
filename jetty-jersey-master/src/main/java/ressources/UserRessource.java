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
import couchedepersistance.Map;
import couchedepersistance.User;
import couchedepersistance.UserDao;

@Path("/User")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRessource implements UserDao {
	
	@GET
	@Path("/{UserID}/Username")
	//retrieval of a username
    public String getUsername (@PathParam("UserID") int user_id) {
		User utilisateur = new User("Jean","123456789");
    	return utilisateur.getUsername();
    }
	
	@GET
	@Path("/{UserID}/Password")
    //retrieval of a user's password 
    public String getPassword (@PathParam("UserID") int user_id) {
    	return "random_password";
    }
	
	@GET
	@Path("/{UserID}/PersonnelMaps")
    //retrieval of user's list of maps that he created
    public List<Map> getPersonnalMaps (@PathParam("UserID") int user_id){
		List<Map> personnelMaps = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            personnelMaps.add(new Map());
        }
        return personnelMaps;
    }
	
	@GET
	@Path("/{UserID}/MapsSharedToHim")
    //retrieval of a user's list of map that other users shared with him
    public List<Map> getMapsSharedToHim (@PathParam("UserID") int user_id){
		List<Map> sharedToHim = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            sharedToHim.add(new Map());
        }
        return sharedToHim;
    }
	
	@POST
	@Path("{UserID}/{Username}/Username")
	//set of a username
    public void setUsername (@PathParam("UserID") int user_id, @PathParam("Username") String username) {}
	
	@POST
	@Path("{UserID}/{Password}/Password")
    //set of a user's password 
	public void setPassword (@PathParam("UserID") int user_id, @PathParam("Password") String password) {}
	
	@POST
	@Path("{UserID}/{PersonnalMapID}/PersonnalMap")
    //add a new personnal map
	public void addPersonnalMap (@PathParam("UserID") int user_id, @PathParam("PersonnalMapID") int map_id) {}
	
	@POST
	@Path("{UserID}/{SharedMapID}/SharedMap")
    //add a new shared map 
	public void addMapToSharedToHim (@PathParam("UserID") int user_id, @PathParam("SharedMapID") int map_id) {}
	
}
