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
import couchedepersistance.Map;
import couchedepersistance.User;
import couchedepersistance.UserDao;

@Path("/User/{UserID}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRessource implements UserDao {
	
	/* GET */
	
	@GET
	@Path("/getUser")
	//get a user
    public User getUser (@PathParam("UserID") int user_id) {
    	return new User();
    }
	
	@GET
	@Path("/getUsername")
	//retrieval of a username
    public String getUsername (@PathParam("UserID") int user_id) {
		User utilisateur = new User("Jean","123456789");
    	return utilisateur.getUsername();
    }
	
	@GET
	@Path("/getPassword")
    //retrieval of a user's password 
    public String getPassword (@PathParam("UserID") int user_id) {
    	return "random_password";
    }
	
	@GET
	@Path("/getPersonnalMaps")
    //retrieval of user's list of maps that he created
    public List<Map> getPersonnalMaps (@PathParam("UserID") int user_id){
		List<Map> personnelMaps = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            personnelMaps.add(new Map());
        }
        return personnelMaps;
    }
	
	@GET
	@Path("/getMapsSharedToMe")
    //retrieval of a user's list of map that other users shared with him
    public List<Map> getMapsSharedToMe (@PathParam("UserID") int user_id){
		List<Map> sharedToHim = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            sharedToHim.add(new Map());
        }
        return sharedToHim;
    }
	
	/* POST */
	
	@POST
	@Path("/setUsername/{Username}")
	//set of a username
    public void setUsername (@PathParam("UserID") int user_id, @PathParam("Username") String username) {}
	
	@POST
	@Path("/setPassword/{Password}")
    //set of a user's password 
	public void setPassword (@PathParam("UserID") int user_id, @PathParam("Password") String password) {}
	
	/* PUT */
	
	@PUT
	@Path("/addPersonnalMap")
    //add a new (empty) personnal map
	public int addPersonnalMap (@PathParam("UserID") int user_id) {
		return 0;
	}
	
	@PUT
	@Path("/addMapToSharedToMe/{FromUserID}/{SharedMapID}")
    //add a new shared map 
	public void addMapToSharedToMe (
			@PathParam("FromUserID")  int FROM_user_id, 
			@PathParam("UserID")      int user_id,
			@PathParam("SharedMapID") int map_id) {}
	
	/* DELETE */
	
	@DELETE
	@Path("/removePersonnalMap/{PersonnalMapID}")
	//remove a personnal map
    public void removePersonnalMap(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {}
	
	@DELETE
	@Path("/removeSharedMap/{SharedMapID}")
	//remove a shared map
    public void removeSharedMap(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {}
	
}
