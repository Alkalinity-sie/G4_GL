package ressources;

import couchedepersistance.EventDao;
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
			return new Event();
		}
	
		@GET
		@Path("/getBeginning")
		//retrieval of the date and hour of begginning of an event
    	public LocalDateTime getBeginning (
        		@PathParam("UserID")  int user_id, 
        		@PathParam("MapID")   int map_id, 
        		@PathParam("EventID") int event_id) {
			return LocalDateTime.now();
    	}
		@GET
		@Path("/getEnd")
    	//retrieval of the date and hour of ending of an event
        public LocalDateTime getEnd (
        		@PathParam("UserID")  int user_id, 
        		@PathParam("MapID")   int map_id, 
        		@PathParam("EventID") int event_id) {
			return LocalDateTime.now();
        }
		
		@GET
		@Path("/getName")
		//retrieval of a location's name
	    public String getName (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id) {
	    	return "random_location_name";
	    }
		
		@GET
		@Path("/getDescription")
	    //retrieval of a location's description
	    public String getDescription (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id) {
	    	return "random_description";
	    }
		
		@GET
		@Path("/getAddress")
	    //retrieval of a location's address
	    public String getAddress (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id) {
	    	return "random_address";
	    }
		
		@GET
		@Path("/getLabels")
	    //retrieval of a location's list of labels
	    public List<String> getLabels (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id){
			List<String> labels = new ArrayList<>();
	        for(int i = 0; i < 5; i++){
	            labels.add("label "+i);
	        }
	        return labels;
	    }
		
		@GET
		@Path("/getPhotos")
	    //retrieval of a location's list of photos
	    public List<ImageIcon> getPhotos (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID") int event_id){
			List<ImageIcon> photos = new ArrayList<>();
	        for(int i = 0; i < 10; i++){
	            photos.add(new ImageIcon());
	        }
	        return photos;
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
	    		@PathParam("Minute")     int minute) {}
		
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
	    		@PathParam("Minute")     int minute) {}
	    
	    @POST
	    @Path("/setName/{Name}")
	    //set an event's name
	    public void setName (
	    		@PathParam("UserID")     int user_id, 
	    		@PathParam("MapID")      int map_id, 
	    		@PathParam("EventID")    int event_id, 
	    		@PathParam("Name")       String name) {}
	    
	    @POST
	    @Path("/setDescription/{Description}")
	    //set an event's description
	    public void setDescription (
	    		@PathParam("UserID")      int user_id, 
	    		@PathParam("MapID")       int map_id, 
	    		@PathParam("EventID")     int event_id, 
	    		@PathParam("Description") String description) {}
	    
	    @POST
	    @Path("/setAddress/{Address}")
	    //set an event'ss address
	    public void setAddress (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Address") String address) {}
	    
	    @POST
	    @Path("/addLabel/{Label}")
	    //add a label to an event
	    public void addLabel (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Label")   String label) {}
	    
	    @POST
	    @Path("/addPhoto/{Photo}")
	    //add a photo to an event
	    public void addPhoto (
	    		@PathParam("UserID")  int user_id, 
	    		@PathParam("MapID")   int map_id, 
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Photo")   ImageIcon photo) {}
}
