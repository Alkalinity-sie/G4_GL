package ressources;


import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.swing.ImageIcon;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import couchedepersistance.Location;
import couchedepersistance.LocationDao;
import couchedepersistance.Map;
import couchedepersistance.Photo;
import couchedepersistance.User;


@Path("/User/{UserID}/Map/{MapID}/Location/{LocationID}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationRessource implements LocationDao { 
	
	/* GET */
	
	@GET
	@Path("/getLocation")
	//get a location
	public Location getLocation (
			@PathParam("UserID")  int user_id, 
    		@PathParam("MapID")   int map_id, 
    		@PathParam("EventID") int location_id) { //OK
		return Database.getLocation(user_id, map_id, location_id);
	}
	
	@GET
	@Path("/getName")
	//retrieval of a location's name
    public String getName (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id) {
	
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return "null";
		return l.getName();
		
    }
	
	@GET
	@Path("/getDescription")
    //retrieval of a location's description
    public String getDescription (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id) {
		/*
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return "null";
		return l.getDescription();
		*/
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return "null";
		return l.getDescription();
		
    }
	
	@GET
	@Path("/getAddress")
    //retrieval of a location's address
    public String getAddress (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id) {
		/*
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return "null";
		return l.getAddress();
		*/
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return "null";
		return l.getAddress();
    }
	
	@GET
	@Path("/getLabels")
    //retrieval of a location's list of labels
    public List<String> getLabels (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id,
    		@PathParam("LocationID") int location_id){
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return null;
		return l.getLabels();
    }
	
	@GET
	@Path("/getPhotos")
    //retrieval of a location's list of photos
    public List<Photo> getPhotos (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id){
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return null;
		
		List<Photo> res = new ArrayList<>();
		for(Long pid : l.getPhotos()){
			Photo p = Database.getPhoto(user_id, map_id, location_id, pid.intValue());
			res.add(p);
		}
		return res;
		
    }
	
	/* POST */
	
    @POST
    @Path("/setName/{Name}")
    //set a location's name
    public void setName (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Name")       String name) {
    	/*
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.setName(name);
		*/
    	
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			
			//on regarde si map_id appartient à user_id
			boolean found = false;
			for(Long mid : u.getMyMaps()) {
				if(mid.intValue() == map_id) {
					found = true;
				}
			}
			
			if(found == true) {
				Map m = pm.getObjectById(Map.class, map_id);
				//on regarde si location_id appartient à map_id
				for(Long l : m.getMyLocations()) {
					if(l.intValue() == location_id) {
						Location loc = pm.getObjectById(Location.class, location_id);
						loc.setName(name);
					}
				}
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
    }
    
    @POST
    @Path("/setDescription/{Description}")
    //set a location's description
    public void setDescription (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Description") String description) {
    	/*
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.setDescription(description);
		*/
    	
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			
			//on regarde si map_id appartient à user_id
			boolean found = false;
			for(Long mid : u.getMyMaps()) {
				if(mid.intValue() == map_id) {
					found = true;
				}
			}
			
			if(found == true) {
				Map m = pm.getObjectById(Map.class, map_id);
				//on regarde si location_id appartient à map_id
				for(Long l : m.getMyLocations()) {
					if(l.intValue() == location_id) {
						Location loc = pm.getObjectById(Location.class, location_id);
						loc.setDescription(description);
					}
				}
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
    }
    
    @POST
    @Path("/setAddress/{Address}")
    //set an location's address
    public void setAddress (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Address") String address) {
    	/*
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.setaddress(address);
    	 */
    	
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			
			//on regarde si map_id appartient à user_id
			boolean found = false;
			for(Long mid : u.getMyMaps()) {
				if(mid.intValue() == map_id) {
					found = true;
				}
			}
			
			if(found == true) {
				Map m = pm.getObjectById(Map.class, map_id);
				//on regarde si location_id appartient à map_id
				for(Long l : m.getMyLocations()) {
					if(l.intValue() == location_id) {
						Location loc = pm.getObjectById(Location.class, location_id);
						loc.setAddress(address);
					}
				}
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
    }
    
    @POST
    @Path("/setLabels/{Text}")
    //add a label to an event
    public void setLabels (
    		@PathParam("UserID")  int user_id, 
    		@PathParam("MapID")   int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Text")   String text) {

    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			
			String labels[] = text.split(" ");
			
			//on regarde si map_id appartient à user_id
			boolean found = false;
			for(Long mid : u.getMyMaps()) {
				if(mid.intValue() == map_id) {
					found = true;
				}
			}
			
			if(found == true) {
				Map m = pm.getObjectById(Map.class, map_id);
				//on regarde si location_id appartient à map_id
				for(Long lid : m.getMyLocations()) {
					if(lid.intValue() == location_id) {
						Location loc = pm.getObjectById(Location.class, location_id);
						loc.setLabels(new ArrayList<String>());
						for(int i = 0; i < labels.length; i++) {
							loc.getLabels().add(labels[i]);
						}
					}
				}
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
    }
    
    
    @POST
    @Path("/addLabel/{Label}")
    //add a label to a location
    public void addLabel (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Label")      String label) {
    	/*
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.getLabels().add(label);
		*/
    	
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			
			//on regarde si map_id appartient à user_id
			boolean found = false;
			for(Long mid : u.getMyMaps()) {
				if(mid.intValue() == map_id) {
					found = true;
				}
			}
			
			if(found == true) {
				Map m = pm.getObjectById(Map.class, map_id);
				//on regarde si location_id appartient à map_id
				for(Long l : m.getMyLocations()) {
					if(l.intValue() == location_id) {
						Location loc = pm.getObjectById(Location.class, location_id);
						loc.getLabels().add(label);
					}
				}
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}

    }
    
    @POST
    @Path("addPhoto/{Photo}")
    //add a photo to a location
    public int addPhoto (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Photo")      ImageIcon photo) {
    	/*
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return -1;
		
		Photo p =new Photo(photo);
		l.getPhotos().add(p);
    	return p.getId();
    	*/
    	int id = -1;
    	
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			
			//on regarde si map_id appartient à user_id
			boolean found = false;
			for(Long mid : u.getMyMaps()) {
				if(mid.intValue() == map_id) {
					found = true;
				}
			}
			
			if(found == true) {
				Map m = pm.getObjectById(Map.class, map_id);
				//on regarde si location_id appartient à map_id
				for(Long l : m.getMyLocations()) {
					if(l.intValue() == location_id) {
						Location loc = pm.getObjectById(Location.class, location_id);
						Photo nouvelle = pm.makePersistent(new Photo());
						id = nouvelle.getId().intValue();
						loc.getPhotos().add(new Long(id));
					}
				}
			}
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
    	
    	return id;
    	
    }
    
    @POST
    @Path("/removeLabel/{Label}")
    //add a label to a location
    public void removeLabel (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Label")      String label) {
    	/*
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.getLabels().remove(label);
		*/
    	
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
User u = pm.getObjectById(User.class, user_id);
			
			//on regarde si map_id appartient à user_id
			boolean found = false;
			for(Long mid : u.getMyMaps()) {
				if(mid.intValue() == map_id) {
					found = true;
				}
			}
			
			if(found == true) {
				Map m = pm.getObjectById(Map.class, map_id);
				//on regarde si location_id appartient à map_id
				for(Long l : m.getMyLocations()) {
					if(l.intValue() == location_id) {
						Location loc = pm.getObjectById(Location.class, location_id);
						loc.getLabels().remove(label);
					}
				}
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
    }
    
    @POST
    @Path("removePhoto/{PhotoID}")
    //add a photo to a location
    public void removePhoto (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id,
    		@PathParam("PhotoID")    int photo_id) {
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			
			//on regarde si map_id appartient à user_id
			boolean found = false;
			for(Long mid : u.getMyMaps()) {
				if(mid.intValue() == map_id) {
					found = true;
				}
			}
			
			if(found == true) {
				Map m = pm.getObjectById(Map.class, map_id);
				//on regarde si location_id appartient à map_id
				for(Long l : m.getMyLocations()) {
					if(l.intValue() == location_id) {
						Location loc = pm.getObjectById(Location.class, location_id);
						for(Long pid : loc.getPhotos()) {
							if(pid.intValue() == photo_id) {
								loc.getPhotos().remove(pid);
								break;
							}
						}
					}
				}
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
    }
    
}
