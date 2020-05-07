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
	@Path("/getAllLabels")
    public List<String> getAllLabels (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) { //OK
		List<String> copy = new ArrayList<String>();
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Map m = pm.getObjectById(Map.class, map_id);
			for(Long lid : m.getMyLocations()) {
				Location l = pm.getObjectById(Event.class, lid);
				for(String label : l.getLabels()) {
					copy.add(new String(label));
				}
			}
			for(Long eid : m.getMyEvents()) {
				Event e = pm.getObjectById(Event.class, eid);
				for(String label : e.getLabels()) {
					copy.add(new String(label));
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
	
	@GET
	@Path("/getMap")
	//get a map. Can either be a shared one or a personnal one depending on user_id
    public Map getMap (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) { //OK
    	/*
		Map m = Database.getPersonnalMap(user_id, map_id);
    	if(m==null) {
    		return Database.getSharedMap(user_id, map_id);
    	}
    	return m;
    	*/
		return Database.getMap(user_id, map_id);
    }
	
	@GET
	@Path("/getName")
	//retrieval of a map's name
    public String getName (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) { //OK
		/*
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return "la map n'existe pas";
    	}
    	return m.getName();
    	*/
		User u = Database.getUser(user_id);
		if(u == null) {
			System.out.println("Map ressource - getName : null");
			return null;
		}
		
		if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
			String name = Database.getMap(user_id, map_id).getName();
			System.out.println("Map ressource - getName : "+name);
			return name;
		}
		System.out.println("Map ressource - getName : null");
		return "null";
    }
	
	@GET
	@Path("/getDescription")
    //retrieval of a map's description
    public String getDescription (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) { //OK
		/*
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return "la map n'existe pas";
    	}
    	return m.getDescription();
    	*/
		User u = Database.getUser(user_id);
		if(u == null) {
			System.out.println("Map ressource - getName : null");
			return null;
		}
		
		if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
			String description = Database.getMap(user_id, map_id).getDescription();
			System.out.println("Map ressource - getName : "+description);
			return description;
		}
		System.out.println("Map ressource - getName : null");
		return "null";
    }
	
	@GET
	@Path("/getStatus")
    //retrieval of a map's status. Return true if it's public
    public boolean getStatus (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) { //OK
		/*
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return false;
    	}
    	return m.getStatus();
    	*/
		User u = Database.getUser(user_id);
		if(u == null) {
			System.out.println("getStatus dans MapRessource est null (1)");
			return false;
		}
		
		if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) 
			return Database.getMap(user_id, map_id).getStatus();
	
		System.out.println("getStatus dans MapRessource est null (2)");
		return false;
    }
	
	@GET
	@Path("/getLocations")
    //retrieval of map's list of locations
    public List<Location> getLocations (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id){ //OK
		User u = Database.getUser(user_id);
		if(u == null) return null;
		
		if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
			Map m = Database.getMap(user_id, map_id);
			List<Location> copy = new ArrayList<Location>();
			for(Long lid : m.getMyLocations()) {
				Location loc = Database.getLocation(user_id, map_id, lid.intValue());
				copy.add(loc);
			}
			return copy;
		}
			
		return null;
    }
	
	@GET
	@Path("/getEvents")
    //retrieval of map's list of locations
    public List<Event> getEvents (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id){ //OK
		System.out.println("Appel à getEvents avec user_id="+user_id+" et map_id="+map_id);
		List<Event> copy = new ArrayList<Event>();
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Map m = pm.getObjectById(Map.class, map_id);
			for(Long eid : m.getMyEvents()) {
				Event e = pm.getObjectById(Event.class, eid);
				copy.add(Copy.copyEvent(e));
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		
		return copy;
    }
	
	@GET
	@Path("/getSharedWith")
    //retrieval of the list of users to whom this map has been shared
    public List<User> getSharedWith (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id){ //OK
		/*
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return null;
    	}
    	return m.getSharedWith();
    	*/
		User u = Database.getUser(user_id);
		if(u == null) return null;
		
		if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
			Map m = Database.getMap(user_id, map_id);
			List<User> copy = new ArrayList<User>();
			for(Long uid : m.getSharedWith()) {
				User user = Database.getUser(uid.intValue());
				copy.add(user);
			}
			return copy;
		}
			
		return null;
    }
	
	/* POST */ 
	
	@POST
	@Path("/setName/{Name}")
	//set a map's name
    public void setName (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("Name") String name) { //OK
		/*
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return;
    	}
    	m.setName(name);
    	*/
		User u = Database.getUser(user_id);
		if(u == null) return;
		
		if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				
				Map m = pm.getObjectById(Map.class, map_id);
				m.setName(name);
				
				tx.commit();
			} finally {
				if (tx.isActive()) tx.rollback();
				pm.close();
				pmf.close();
			}
		}
	}
	
	@POST
	@Path("/setDescription/{Description}")
    //set a map's description
	public void setDescription (@PathParam("UserID")      int user_id, 
								@PathParam("MapID")       int map_id, 
								@PathParam("Description") String description) { //OK
		/*
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return;
    	}
    	m.setDescription(description);
    	*/
		
		User u = Database.getUser(user_id);
		if(u == null) return;
		
		if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				
				Map m = pm.getObjectById(Map.class, map_id);
				m.setDescription(description);
				
				tx.commit();
			} finally {
				if (tx.isActive()) tx.rollback();
				pm.close();
				pmf.close();
			}
		}
	}
	
	@POST
	@Path("/setStatus/{Status}")
    //set a map's status
	public void setStatus (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id, @PathParam("Status") boolean status) { //OK
		/*
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return;
    	}
    	m.setStatus(status);
    	*/
		
		User u = Database.getUser(user_id);
		if(u == null) return;
		
		if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				
				Map m = pm.getObjectById(Map.class, map_id);
				m.setStatus(status);
				
				tx.commit();
			} finally {
				if (tx.isActive()) tx.rollback();
				pm.close();
				pmf.close();
			}
		}
	}
	
	@POST
	@Path("/addUserToSharedWith/{UserIDToAdd}")
    //add a user to the list getSharedWith of a map. And also add the map to the list sharedToMe of the user whose id is user_id_to_add
	public void addUserToSharedWith (@PathParam("UserID")      int user_id, 
									 @PathParam("MapID")       int map_id, 
									 @PathParam("UserIDToAdd") int user_id_to_add) { //OK
		/*
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
    	m.getSharedWith().add(new Integer (TO.getId()));
    	//ajoute de la map à la liste sharedToMe de l'utilisateur avec l'id "user_id_to_add"
    	TO.getSharedToMe().add(m);
    	*/
		
		User u1 = Database.getUser(user_id);
		User u2 = Database.getUser(user_id_to_add);
		if(u1 == null || u2 == null) return;
		
		if(u1.getMyMaps().contains(new Long(map_id)) || u1.getSharedToMe().contains(new Long(map_id))) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				
				Map m = pm.getObjectById(Map.class, map_id);
				m.getSharedWith().add(new Long(user_id_to_add));
				
				tx.commit();
			} finally {
				if (tx.isActive()) tx.rollback();
				pm.close();
				pmf.close();
			}
		}
	}
	
	/* PUT */
    
	@PUT
	@Path("/addLocation")
    //add a new empty location to the map and returns his id
    public int addLocation (@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) { //OK
		/*
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return -1;
    	}
    	Location l = new Location();
    	m.getMyLocations().add(l);
    	return l.getId();
    	*/
		User u = Database.getUser(user_id);
		if(u == null) return -1;
		
		Long id = null;
		if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				
				Map m = pm.getObjectById(Map.class, map_id);
				Location l = new Location();
				Location loc = pm.makePersistent(l);
				id = loc.getId();
				m.getMyLocations().add(id);
				
				tx.commit();
			} finally {
				if (tx.isActive()) tx.rollback();
				pm.close();
				pmf.close();
			}
		}
		return id.intValue();
	}
	
	@PUT
	@Path("/addEvent")
    //add a new empty event to the map and returns the new event's id
    public int addEvent(@PathParam("UserID") int user_id, @PathParam("MapID") int map_id) { //OK
		/*
		Map m = getMap(user_id, map_id);
    	if(m==null) {//si map n'existe pas du tout, ni en tant que partagé ni en tant que map perso
    		return -1;
    	}
    	Location e = new Event();
    	m.getMyLocations().add(e);
    	return e.getId();
    	*/
		int id = -1;
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
				Event e = pm.makePersistent(new Event());
				id = e.getId().intValue();
				
				Map m = pm.getObjectById(Map.class, map_id);
				m.getMyEvents().add(new Long(id));
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		
		return id;
	}
    
    /* DELETE */
    
	@DELETE
	@Path("/removeLocation/{LocationID}")
	//remove a location (i.e. could be an event)
    public void removeLocation(@PathParam("UserID")     int user_id, 
    						   @PathParam("MapID")      int map_id, 
    						   @PathParam("LocationID") int location_id) { //OK
		/*
		Map m = getMap(user_id, map_id);
		if(m==null) return;
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l==null) return;
		m.getMyLocations().remove(l);
		*/
		System.out.println("removeLocation");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
				Map m = pm.getObjectById(Map.class, map_id);
				m.getMyLocations().remove(new Long(location_id));
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		
	}
	
	@DELETE
	@Path("/removeEvent/{EventID}")
    //remove an event
    public void removeEvent(@PathParam("UserID")  int user_id,
    					    @PathParam("MapID")   int map_id, 
    					    @PathParam("EventID") int event_id) { //OK
		/*
		Map m = getMap(user_id, map_id);
		if(m==null) return;
		Location e = Database.getEvent(user_id, map_id, event_id);
		if(e==null) return;
		m.getMyLocations().remove(e);
		*/
		System.out.println("removeEvent");
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			if(u.getMyMaps().contains(new Long(map_id)) || u.getSharedToMe().contains(new Long(map_id))) {
				Map m = pm.getObjectById(Map.class, map_id);
				m.getMyEvents().remove(new Long(event_id));
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
	}
	
}
