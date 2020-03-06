package ressources;

import couchedepersistance.EventDao;

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

@Path("/Event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventRessource implements EventDao {
		@GET
		@Path("/{EventID}/Beginning")
		//retrieval of the date and hour of begginning of an event
    	public LocalDateTime getBeginning (@PathParam("EventID") int event_id) {
			return LocalDateTime.now();
    	}
		@GET
		@Path("/{EventID}/End")
    	//retrieval of the date and hour of ending of an event
        public LocalDateTime getEnd (@PathParam("EventID") int event_id) {
			return LocalDateTime.now();
        }
		
		@GET
		@Path("/{EventID}/Name")
		//retrieval of a location's name
	    public String getName (@PathParam("LocationID") int location_id) {
	    	return "random_location_name";
	    }
		
		@GET
		@Path("/{EventID}/Description")
	    //retrieval of a location's description
	    public String getDescription (@PathParam("LocationID") int location_id) {
	    	return "random_description";
	    }
		
		@GET
		@Path("/{EventID}/Address")
	    //retrieval of a location's address
	    public String getAddress (@PathParam("LocationID") int location_id) {
	    	return "random_address";
	    }
		
		@GET
		@Path("/{EventID}/Labels")
	    //retrieval of a location's list of labels
	    public List<String> getLabels (@PathParam("LocationID") int location_id){
			List<String> labels = new ArrayList<>();
	        for(int i = 0; i < 5; i++){
	            labels.add("label "+i);
	        }
	        return labels;
	    }
		
		@GET
		@Path("/{EventID}/Photos")
	    //retrieval of a location's list of photos
	    public List<ImageIcon> getPhotos (@PathParam("LocationID") int location_id){
			List<ImageIcon> photos = new ArrayList<>();
	        for(int i = 0; i < 10; i++){
	            photos.add(new ImageIcon());
	        }
	        return photos;
	    }
		
		@POST
		@Path("/{EventId}/{Year}/{Month}/{DayOfMonth}/{Hour}/{Minute}/Beginning")
		//set a beginning to an event
	    public void setBeginning (
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Year") int year, 
	    		@PathParam("Month") int month, 
	    		@PathParam("DayOfMonth") int dayOfMonth, 
	    		@PathParam("Hour") int hour, 
	    		@PathParam("Minute") int minute) {
			return;
		}
		
		@POST
		@Path("/{EventId}/{Year}/{Month}/{DayOfMonth}/{Hour}/{Minute}/End")
	    //set an end to an event
	    public void setEnd (
	    		@PathParam("EventID") int event_id, 
	    		@PathParam("Year") int year, 
	    		@PathParam("Month") int month, 
	    		@PathParam("DayOfMonth") int dayOfMonth, 
	    		@PathParam("Hour") int hour, 
	    		@PathParam("Minute") int minute) {
	    	return;
	    }
	    
	    @POST
	    @Path("/{EventID}/{Name}/Name")
	    //set an event's name
	    public void setName (@PathParam("EventID") int event_id, @PathParam("Name") String name) {}
	    
	    @POST
	    @Path("/{EventID}/{Description}/Description")
	    //set an event's description
	    public void setDescription (@PathParam("EventID") int event_id, @PathParam("Description") String description) {}
	    
	    @POST
	    @Path("/{EventID}/{Address}/Address")
	    //set an event'ss address
	    public void setAddress (@PathParam("EventID") int event_id, @PathParam("Address") String address) {}
	    
	    @POST
	    @Path("/{EventID}/{Label}/Label")
	    //add a label to an event
	    public void addLabel (@PathParam("EventID") int event_id, @PathParam("Label") String label) {}
	    
	    @POST
	    @Path("/{EventID}/{Photo}/Photo")
	    //add a photo to an event
	    public void addPhoto (@PathParam("EventID") int event_id, @PathParam("Photo") ImageIcon photo) {}
}
