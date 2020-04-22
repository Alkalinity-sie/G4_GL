package ressources;

import couchedepersistance.EventDao;
import couchedepersistance.Map;
import couchedepersistance.Photo;
import couchedepersistance.User;
import couchedepersistance.Event;
import java.time.LocalDateTime;
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

@Path("/User/{UserID}/Map/{MapID}/Event/{EventID}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventRessource implements EventDao {
	
		/* GET */
	
		@GET
		@Path("/getEvent")
		//get an event
		public Event getEvent (
				@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id) {
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			return e;
			*/
			return Database.getEvent(user_id, map_id, event_id);
			
		}
	
		@GET
		@Path("/getBeginning")
		//retrieval of the date and hour of begginning of an event
    	public LocalDateTime getBeginning (
        		@PathParam("UserID")  int user_id, 
        		@PathParam("MapID")   int map_id, 
        		@PathParam("EventID") int event_id) {
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getBeginning();
			*/
			System.out.println("getBeginning");
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) System.out.println("event est null");
			if(e == null) return null;
			
			LocalDateTime ldt = e.getBeginning();
			if(ldt == null) System.out.println("ldt est null");
			
			return e.getBeginning();
    	}
		@GET
		@Path("/getEnd")
    	//retrieval of the date and hour of ending of an event
        public LocalDateTime getEnd (
        		@PathParam("UserID")  int user_id, 
        		@PathParam("MapID")   int map_id, 
        		@PathParam("EventID") int event_id) {
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getEnd();
			*/
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getEnd();
        }
		
		@GET
		@Path("/getName")
		//retrieval of a location's name
	    public String getName (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id) {
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getName();
			*/
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return "null";
			return e.getName();
	    }
		
		@GET
		@Path("/getDescription")
	    //retrieval of a location's description
	    public String getDescription (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id) {
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getDescription();
			*/
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return "null";
			return e.getDescription();
	    }
		
		@GET
		@Path("/getAddress")
	    //retrieval of a location's address
	    public String getAddress (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id) {
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getAddress();
			*/
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return "null";
			return e.getAddress();
	    }
		
		@GET
		@Path("/getLabels")
	    //retrieval of a location's list of labels
	    public List<String> getLabels (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id){
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getLabels();
			*/
			
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getLabels();
	    }
		
		@GET
		@Path("/getPhotos")
	    //retrieval of a location's list of photos
	    public List<ImageIcon> getPhotos (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id){
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			List<Photo> photos = e.getPhotos();
			List<ImageIcon> res = new ArrayList<>();
			for(Photo p : photos) {
				res.add(p.getPhoto());
			}
			return res;
			*/
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			
			List<ImageIcon> res = new ArrayList<>();
			for(Long pid : e.getPhotos()){
				Photo p = Database.getPhoto(user_id, map_id, event_id, pid.intValue());
				res.add(p.getPhoto());
			}
			return res;
	    }
		
		/* POST */
		
		@POST
		@Path("/setBeginning/{Year}/{Month}/{DayOfMonth}/{Hour}/{Minute}")
		//set a beginning to an event
	    public void setBeginning (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id, 
	    		@PathParam("Year")       int year, 
	    		@PathParam("Month")      int month, 
	    		@PathParam("DayOfMonth") int dayOfMonth, 
	    		@PathParam("Hour")       int hour, 
	    		@PathParam("Minute")     int minute) {
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			String chaine = ""+year+"-"+month+"-"+dayOfMonth+"T"+hour+":"+minute+":"+"00.000"; 
			//exemple : "2020-10-06T17:00:00.000"
			LocalDateTime beginning = LocalDateTime.parse(chaine);
			e.setBeginning(beginning);
			*/
			
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				
				
				Event e = Database.getEvent(user_id, map_id, event_id);
				if(e == null) return;
				
				int Byear = e.getBeginning().getYear();
				int Bmonth = e.getBeginning().getMonthValue();
				int BdayOfMonth = e.getBeginning().getDayOfMonth();
				int Bhour = e.getBeginning().getHour();
				int Bminute = e.getBeginning().getMinute();
				String Bchaine = ""+Byear+"-"+Bmonth+"-"+BdayOfMonth+"T"+Bhour+":"+Bminute+":"+"00.000"; 
				//exemple : "2020-10-06T17:00:00.000"
				LocalDateTime beginning = LocalDateTime.parse(Bchaine);
				e.setBeginning(beginning);
				
				tx.commit();
			} finally {
				if (tx.isActive()) tx.rollback();
				pm.close();
				pmf.close();
			}
			
		}
		
		@POST
		@Path("/setEnd/{Year}/{Month}/{DayOfMonth}/{Hour}/{Minute}")
	    //set an end to an event
	    public void setEnd (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id, 
	    		@PathParam("Year")       int year, 
	    		@PathParam("Month")      int month, 
	    		@PathParam("DayOfMonth") int dayOfMonth, 
	    		@PathParam("Hour")       int hour, 
	    		@PathParam("Minute")     int minute) {
			/*
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			String chaine = ""+year+"-"+month+"-"+dayOfMonth+"T"+hour+":"+minute+":"+"00.000"; 
			//exemple : "2020-10-06T17:00:00.000"
			LocalDateTime ending = LocalDateTime.parse(chaine);
			e.setBeginning(ending);
			*/
			
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				
				
				Event e = Database.getEvent(user_id, map_id, event_id);
				if(e == null) return;
				
				int Eyear = e.getBeginning().getYear();
				int Emonth = e.getBeginning().getMonthValue();
				int EdayOfMonth = e.getBeginning().getDayOfMonth();
				int Ehour = e.getBeginning().getHour();
				int Eminute = e.getBeginning().getMinute();
				String Echaine = ""+Eyear+"-"+Emonth+"-"+EdayOfMonth+"T"+Ehour+":"+Eminute+":"+"00.000"; 
				//exemple : "2020-10-06T17:00:00.000"
				LocalDateTime end = LocalDateTime.parse(Echaine);
				e.setBeginning(end);
				
				tx.commit();
			} finally {
				if (tx.isActive()) tx.rollback();
				pm.close();
				pmf.close();
			}
			
		}
	    
	    @POST
	    @Path("/setName/{Name}")
	    //set an event's name
	    public void setName (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id, 
	    		@PathParam("Name")       String name) {
	    	/*
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.setName(name);
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
					//on regarde si event_id appartient à map_id
					for(Long eid : m.getMyEvents()) {
						if(eid.intValue() == event_id) {
							Event event = pm.getObjectById(Event.class, event_id);
							event.setName(name);
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
	    //set an event's description
	    public void setDescription (
	    		@PathParam("UserID")      int user_id, 
	    		@PathParam("MapID")       int map_id, 
	    		@PathParam("EventID")     int event_id, 
	    		@PathParam("Description") String description) {
	    	/*
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.setDescription(description);
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
					//on regarde si event_id appartient à map_id
					for(Long eid : m.getMyEvents()) {
						if(eid.intValue() == event_id) {
							Event event = pm.getObjectById(Event.class, event_id);
							event.setDescription(description);
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
	    //set an event's address
	    public void setAddress (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Address") String address) {
	    	/*
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.setaddress(address);
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
					//on regarde si event_id appartient à map_id
					for(Long eid : m.getMyEvents()) {
						if(eid.intValue() == event_id) {
							Event event = pm.getObjectById(Event.class, event_id);
							event.setAddress(address);
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
	    //add a label to an event
	    public void addLabel (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Label")   String label) {
	    	/*
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.getLabels().add(label);
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
					for(Long eid : m.getMyEvents()) {
						if(eid.intValue() == event_id) {
							Event event = pm.getObjectById(Event.class, event_id);
							event.getLabels().add(label);
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
	    @Path("/addPhoto/{Photo}")
	    //add a photo to an event
	    public int addPhoto (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Photo")   ImageIcon photo) {
	    	/*
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return -1;
			Photo p = new Photo(photo);
			e.getPhotos().add(p);
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
					for(Long eid : m.getMyEvents()) {
						if(eid.intValue() == event_id) {
							Event event = pm.getObjectById(Event.class, event_id);
							Photo nouvelle = pm.makePersistent(new Photo());
							id = nouvelle.getId().intValue();
							event.getPhotos().add(new Long(id));
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
	    //add a label to an event
	    public void removeLabel (
	    		@PathParam("UserID")   int user_id, 
	    		@PathParam("MapID")    int map_id, 
	    		@PathParam("EventID")  int event_id, 
	    		@PathParam("Label")    String label) {
	    	/*
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.getLabels().remove(label);
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
					for(Long eid : m.getMyEvents()) {
						if(eid.intValue() == event_id) {
							Event event = pm.getObjectById(Event.class, event_id);
							event.getLabels().remove(label);
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
	    //add a photo to an event
	    public void removePhoto (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id,
	    		@PathParam("PhotoID") int photo_id) {
	    	/*
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			List<Photo> photos = e.getPhotos();
			for(Photo p : photos) {
				if(p.getId()==photo_id) {
					photos.remove(p);
					break;
				}
			}
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
					for(Long eid : m.getMyEvents()) {
						if(eid.intValue() == event_id) {
							Event event = pm.getObjectById(Event.class, event_id);
							for(Long pid : event.getPhotos()) {
								if(pid.intValue() == photo_id) {
									event.getPhotos().remove(pid);
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
