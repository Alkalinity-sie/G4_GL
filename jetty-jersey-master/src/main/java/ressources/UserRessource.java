package ressources;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
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
@Path("/User")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRessource implements UserDao {
	
	/* GET */
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/{Username}/{Password}/getCorrespondantUser")
	//get a user
    public User getCorrespondantUser (@PathParam("Username") String username,
    								  @PathParam("Password") String password) {
		System.out.println("getCorrespondantUser");
		User copy = null;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			List<User> users = new ArrayList<User>();
			Query q = pm.newQuery(User.class);
			q.declareParameters("String user");
			q.setFilter("username == user");

			users = (List<User>) q.execute(username);
			for(User u : users) {
				if(u.getPassword().contentEquals(password)) {
					copy = Copy.copyUser(u);
					break;
				}
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
    	return copy;
    }
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/{Username}/getUserID")
	//get a user
    public int getUser (@PathParam("Username") String username) {
		System.out.println("getUserID");
		int user_id = -1;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			List<User> users = new ArrayList<User>();
			Query q = pm.newQuery(User.class);
			q.declareParameters("String user");
			q.setFilter("username == user");

			users = (List<User>) q.execute(username);
			if(users.size()>0) user_id = users.get(0).getId().intValue();
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
    	return user_id;
    }
	
	
	@GET
	@Path("/{UserID}/getUser")
	//get a user
    public User getUser (@PathParam("UserID") int user_id) {
		System.out.println("getUser");
		User u = Database.getUser(user_id);
		System.out.println("nom : "+u.getUsername());
    	return u;
    }
	
	@GET
	@Path("/{UserID}/getUsername")
	//retrieval of a username
    public String getUsername (@PathParam("UserID") int user_id) {
		System.out.println("getUsername");
		User u = Database.getUser(user_id);
		if(u == null) return null;
		return new String(u.getUsername());
    }
	
	@GET
	@Path("/{UserID}/getPassword")
    //retrieval of a user's password 
    public String getPassword (@PathParam("UserID") int user_id) { 
		System.out.println("getPassword");
		User u = Database.getUser(user_id);
		if(u == null) return null;
		return new String(u.getPassword());
    }
	
	@GET
	@Path("/{UserID}/getPersonnalMaps")
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
	@Path("/{UserID}/getMapsSharedToMe")
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
	@Path("/{UserID}/setUsername/{Username}")
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
	@Path("/{UserID}/setPassword/{Password}")
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
	@Path("/{UserID}/addPersonnalMap")
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
	@Path("/{UserID}/addMapToSharedToMe/{FromUserID}/{SharedMapID}")
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
			if(TO.getSharedToMe().contains(new Long(map_id))==false) TO.getSharedToMe().add(new Long(map_id));
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
	}
	
	/* DELETE */
	
	@DELETE
	@Path("/{UserID}/removeMap/{MapID}")
	//remove a personnal map
    public boolean removeMap(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		boolean carte_perso = true;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			Map m = pm.getObjectById(Map.class, map_id);
			if(u.getMyMaps().contains(new Long(map_id))) {
				pm.deletePersistent(m);
				u.getMyMaps().remove(new Long(map_id));
			} else {
				carte_perso = false;
				u.getSharedToMe().remove(new Long(map_id));
			}
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		return carte_perso;
	}
	
	
	@DELETE
	@Path("/{UserID}/removePersonnalMap/{PersonnalMapID}")
	//remove a personnal map
    public void removePersonnalMap(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) {
		System.out.println("removePersonnalMap");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			Map m = pm.getObjectById(Map.class, map_id);
			pm.deletePersistent(m);
			u.getMyMaps().remove(new Long(map_id));
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
	}
	
	@DELETE
	@Path("/{UserID}/removeSharedMap/{SharedMapID}")
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
