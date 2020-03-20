package ressources;

import couchedepersistance.EventDao;
import couchedepersistance.Photo;
import couchedepersistance.Event;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			return e;
		}
	
		@GET
		@Path("/getBeginning")
		//retrieval of the date and hour of begginning of an event
    	public LocalDateTime getBeginning (
        		@PathParam("UserID")  int user_id, 
        		@PathParam("MapID")   int map_id, 
        		@PathParam("EventID") int event_id) {
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getBeginning();
    	}
		@GET
		@Path("/getEnd")
    	//retrieval of the date and hour of ending of an event
        public LocalDateTime getEnd (
        		@PathParam("UserID")  int user_id, 
        		@PathParam("MapID")   int map_id, 
        		@PathParam("EventID") int event_id) {
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getEnd();
        }
		
		@GET
		@Path("/getName")
		//retrieval of a location's name
	    public String getName (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id) {
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getName();
	    }
		
		@GET
		@Path("/getDescription")
	    //retrieval of a location's description
	    public String getDescription (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id) {
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getDescription();
	    }
		
		@GET
		@Path("/getAddress")
	    //retrieval of a location's address
	    public String getAddress (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id) {
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			return e.getAddress();
	    }
		
		@GET
		@Path("/getLabels")
	    //retrieval of a location's list of labels
	    public List<String> getLabels (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id){
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
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
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return null;
			List<Photo> photos = e.getPhotos();
			List<ImageIcon> res = new ArrayList<>();
			for(Photo p : photos) {
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
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			String chaine = ""+year+"-"+month+"-"+dayOfMonth+"T"+hour+":"+minute+":"+"00.000"; 
			//exemple : "2020-10-06T17:00:00.000"
			LocalDateTime beginning = LocalDateTime.parse(chaine);
			e.setBeginning(beginning);
			
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
			Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			String chaine = ""+year+"-"+month+"-"+dayOfMonth+"T"+hour+":"+minute+":"+"00.000"; 
			//exemple : "2020-10-06T17:00:00.000"
			LocalDateTime ending = LocalDateTime.parse(chaine);
			e.setBeginning(ending);
		}
	    
	    @POST
	    @Path("/setName/{Name}")
	    //set an event's name
	    public void setName (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id, 
	    		@PathParam("Name")       String name) {
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.setName(name);
	    }
	    
	    @POST
	    @Path("/setDescription/{Description}")
	    //set an event's description
	    public void setDescription (
	    		@PathParam("UserID")      int user_id, 
	    		@PathParam("MapID")       int map_id, 
	    		@PathParam("EventID")     int event_id, 
	    		@PathParam("Description") String description) {
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.setDescription(description);
	    }
	    
	    @POST
	    @Path("/setAddress/{Address}")
	    //set an event'ss address
	    public void setAddress (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Address") String address) {
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.setaddress(address);
	    }
	    
	    @POST
	    @Path("/addLabel/{Label}")
	    //add a label to an event
	    public void addLabel (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Label")   String label) {
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.getLabels().add(label);
	    }
	    
	    @POST
	    @Path("/addPhoto/{Photo}")
	    //add a photo to an event
	    public void addPhoto (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Photo")   ImageIcon photo) {
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.getPhotos().add(new Photo(photo));
	    }
	    
	    @POST
	    @Path("/removeLabel/{Label}")
	    //add a label to a location
	    public void removeLabel (
	    		@PathParam("UserID")   int user_id, 
	    		@PathParam("MapID")    int map_id, 
	    		@PathParam("EventID")  int event_id, 
	    		@PathParam("Label")    String label) {
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			e.getLabels().remove(label);
	    }
	    
	    @POST
	    @Path("removePhoto/{PhotoID}")
	    //add a photo to a location
	    public void removePhoto (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id,
	    		@PathParam("PhotoID") int photo_id) {
	    	Event e = (Event) Database.getEvent(user_id, map_id, event_id);
			if(e == null) return;
			List<Photo> photos = e.getPhotos();
			for(Photo p : photos) {
				if(p.getId()==photo_id) {
					photos.remove(p);
					break;
				}
			}
	    }
}
