package ressources;

import couchedepersistance.Map;
import couchedepersistance.Photo;
import couchedepersistance.User;
import couchedepersistance.Event;
import couchedepersistance.EventDao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
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
    	public String getBeginning (
        		@PathParam("UserID")  int user_id, 
        		@PathParam("MapID")   int map_id, 
        		@PathParam("EventID") int event_id) {
			Event e = Database.getEvent(user_id, map_id, event_id);
			return e.getBeginning().toString();
    	}
		@GET
		@Path("/getEnd")
    	//retrieval of the date and hour of ending of an event
        public String getEnd (
        		@PathParam("UserID")  int user_id, 
        		@PathParam("MapID")   int map_id, 
        		@PathParam("EventID") int event_id) {
			Event e = Database.getEvent(user_id, map_id, event_id);
			return e.getEnd().toString();
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
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return "null";
			String adresse = e.getAddress();
			return adresse;
	    }
		
		@GET
		@Path("/getLabels")
	    //retrieval of a location's list of labels
	    public List<String> getLabels (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id){

			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getLabels();
	    }
		
		@GET
		@Path("/getPhotos")
	    //retrieval of a location's list of photos
	    public List<Long> getPhotos (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id){
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			/*
			List<Photo> res = new ArrayList<>();
			for(Long pid : e.getPhotos()){
				Photo p = Database.getPhoto(user_id, map_id, event_id, pid.intValue());
				res.add(p);
			}
			System.out.println("taille de la liste de photos : "+res.size());
			for(int i = 0; i < res.size(); i++) {
				if(res.get(i)!=null) System.out.println(res.get(i).getId().intValue());
				else System.out.println("null");
			}*/
			List<Long> res = new ArrayList<>();
			for(Long pid : e.getPhotos()){
				res.add(new Long(pid.intValue()));
			}
			return res;
	    }
		
		@GET
		@Path("/getPhoto/{PhotoID}/{indexDebut}/{indexFin}")
	    public String getPhotos (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id,
	    		@PathParam("PhotoID")    int photo_id,
	    		@PathParam("indexDebut") int indexDebut,
	    		@PathParam("indexFin")   int indexFin){
			Event e = Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			
			Photo p = Database.getPhoto(user_id, map_id, event_id, photo_id);
			
			if(indexDebut==-1 && indexFin==-1) {
				int taille = p.getPhoto().length();
				return p.getPhoto().length()+"";
			}
			String res = p.getPhoto().substring(indexDebut, indexFin);
			System.out.println(res.substring(0, 30));
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
	
			System.out.println(year);
			System.out.println(month);
			System.out.println(dayOfMonth);
			System.out.println(hour);
			System.out.println(minute);
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				
				
				Event e = pm.getObjectById(Event.class, event_id);
				
				String Bchaine = ""+Copy.intToProperString(year)+"-"
						+Copy.intToProperString(month)+"-"
						+Copy.intToProperString(dayOfMonth)+"T"
						+Copy.intToProperString(hour)+":"
						+Copy.intToProperString(minute)+":"+"00.000"; 
				/*
				//exemple : "2020-10-06T17:00:00.000"
				LocalDateTime beginning = LocalDateTime.parse(Bchaine);
				System.out.println(Bchaine);*/
				e.setBeginning(Bchaine);
				System.out.println("changement à : "+e.getBeginning().toString());
				tx.commit();
			} finally {
				if (tx.isActive()) tx.rollback();
				pm.close();
				pmf.close();
			}
			pmf = JDOHelper.getPersistenceManagerFactory("Example");
			pm = pmf.getPersistenceManager();
			tx = pm.currentTransaction();
			try {
				tx.begin();
				Event e = pm.getObjectById(Event.class, event_id);
				//exemple : "2020-10-06T17:00:00.000"
				System.out.println("Après recup à : "+e.getBeginning());
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
			
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try {
				tx.begin();
				
				
				Event e = pm.getObjectById(Event.class, event_id);
				
				String Echaine = ""+Copy.intToProperString(year)+"-"
						+Copy.intToProperString(month)+"-"
						+Copy.intToProperString(dayOfMonth)+"T"
						+Copy.intToProperString(hour)+":"
						+Copy.intToProperString(minute)+":"+"00.000";  
				
				//exemple : "2020-10-06T17:00:00.000"
				//LocalDateTime end = LocalDateTime.parse(Echaine);
				e.setEnd(Echaine);
				
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
	    	System.out.println("setName");
	    	
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
	    @Path("/setLabels/{Text}")
	    //add a label to an event
	    public void setLabels (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
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
					for(Long eid : m.getMyEvents()) {
						if(eid.intValue() == event_id) {
							Event event = pm.getObjectById(Event.class, event_id);
							event.setLabels(new ArrayList<String>());
							for(int i = 0; i < labels.length; i++) {
								event.getLabels().add(labels[i]);
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
	    @Path("/addPhoto/")
	    //add a photo to an event 
	    public int addPhoto (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		String photo) { //le contenu de la photo
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
							Photo n = new Photo(); n.setPhoto(photo);
							Photo nouvelle = pm.makePersistent(n);
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
