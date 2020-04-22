package ressources;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
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
		System.out.println("getUser");
		User u = Database.getUser(user_id);
		System.out.println("nom : "+u.getUsername());
    	return u;
    }
	
	@GET
	@Path("/getUsername")
	//retrieval of a username
    public String getUsername (@PathParam("UserID") int user_id) {
		System.out.println("getUsername");
		User u = Database.getUser(user_id);
		if(u == null) return null;
		return new String(u.getUsername());
    }
	
	@GET
	@Path("/getPassword")
    //retrieval of a user's password 
    public String getPassword (@PathParam("UserID") int user_id) { 
		System.out.println("getPassword");
		User u = Database.getUser(user_id);
		if(u == null) return null;
		return new String(u.getPassword());
    }
	
	@GET
	@Path("/getPersonnalMaps")
    //retrieval of user's list of maps that he created
    public List<Map> getPersonnalMaps (@PathParam("UserID") int user_id){ 
		System.out.println("getPersonnalMaps");
		User u = Database.getUser(user_id);
		if(u == null) return null;
		
		List<Map> copy = new ArrayList<>();
		for(Long mId : u.getMyMaps()) {
			Map m = Database.getMap(user_id, mId.intValue());
			if(m != null) copy.add(m);
		}	
		return copy;
    }
	
	@GET
	@Path("/getMapsSharedToMe")
    //retrieval of a user's list of map that other users shared with him
    public List<Map> getMapsSharedToMe (@PathParam("UserID") int user_id){
		System.out.println("getMapsSharedToMe");
		User u = Database.getUser(user_id);
		if(u == null) return null;
		
		List<Map> copy = new ArrayList<>();
		for(Long mId : u.getSharedToMe()) {
			Map m = Database.getMap(user_id, mId.intValue());
			if(m != null) copy.add(m);
		}	
		return copy;
    }
	
	/* POST */
	
	@POST
	@Path("/setUsername/{Username}")
	//set of a username
    public void setUsername (@PathParam("UserID")   int user_id, 
    						 @PathParam("Username") String username) {
		System.out.println("setUsername");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			u.setUsername(username);
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
	}
	
	@POST
	@Path("/setPassword/{Password}")
    //set of a user's password 
	public void setPassword (@PathParam("UserID") int user_id, @PathParam("Password") String password) { 
		System.out.println("setPassword");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			u.setPassword(password);
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
	}
	
	/* PUT */
	
	@PUT
	@Path("/addPersonnalMap")
    //add a new (empty) personnal map
	public int addPersonnalMap (@PathParam("UserID") int user_id) {
		System.out.println("addPersonnalMap");
		int id = -1;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			Map nouvelle = new Map();
			Map m = pm.makePersistent(nouvelle);
			id = m.getId().intValue();
			u.getMyMaps().add(new Long(id));
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		return id;
	}
	
	@PUT
	@Path("/addMapToSharedToMe/{FromUserID}/{SharedMapID}")
    //add a new shared map 
	public void addMapToSharedToMe (
			@PathParam("FromUserID")  int FROM_user_id, 
			@PathParam("UserID")      int user_id,
			@PathParam("SharedMapID") int map_id) { 
		System.out.println("addMapToSharedToMe");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User TO = pm.getObjectById(User.class, user_id);
			User FROM = pm.getObjectById(User.class, FROM_user_id);
			
			if(FROM.getMyMaps().contains(new Long(map_id))) {
				TO.getSharedToMe().add(new Long(map_id));
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
	}
	
	/* DELETE */
	
	@DELETE
	@Path("/removePersonnalMap/{PersonnalMapID}")
	//remove a personnal map
    public void removePersonnalMap(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		System.out.println("removePersonnalMap");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			
			u.getMyMaps().remove(new Long(map_id));
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
	}
	
	@DELETE
	@Path("/removeSharedMap/{SharedMapID}")
	//remove a shared map
    public void removeSharedMap(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		System.out.println("removeSharedMap");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			
			u.getSharedToMe().remove(new Long(map_id));
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
	}
	
}
