package ressources;


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
import couchedepersistance.LocationDao;


@Path("/User/{UserID}/Map/{MapID}/Location/{LocationID}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationRessource implements LocationDao {
	
	/* GET */
	
	@GET
	@Path("/getName")
	//retrieval of a location's name
    public String getName (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id) {
    	return "random_location_name";
    }
	
	@GET
	@Path("/getDescription")
    //retrieval of a location's description
    public String getDescription (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id) {
    	return "random_description";
    }
	
	@GET
	@Path("/getAddress")
    //retrieval of a location's address
    public String getAddress (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id) {
    	return "random_address";
    }
	
	@GET
	@Path("/getLabels")
    //retrieval of a location's list of labels
    public List<String> getLabels (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id,
    		@PathParam("LocationID") int location_id){
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
    		@PathParam("LocationID") int location_id){
		List<ImageIcon> photos = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            photos.add(new ImageIcon());
        }
        return photos;
    }
	
	/* POST */
	
    @POST
    @Path("/setName/{Name}")
    //set a location's name
    public void setName (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Name")String name) {}
    
    @POST
    @Path("/setDescription/{Description}")
    //set a location's description
    public void setDescription (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Description") String Description) {}
    
    @POST
    @Path("/setAddress/{Address}")
    //set an location's address
    public void setAddress (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Address") String address) {}
    
    @POST
    @Path("/addLabel/{Label}")
    //add a label to a location
    public void addLabel (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Label") String label) {}
    
    @POST
    @Path("addPhoto/{Photo}")
    //add a photo to a location
    public void addPhoto (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Photo") ImageIcon photo) {}
    
}
