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

import couchedepersistance.Location;
import couchedepersistance.LocationDao;
import couchedepersistance.Photo;


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
    		@PathParam("EventID") int location_id) {
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
    public List<ImageIcon> getPhotos (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id){
		Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return null;
		List<Photo> photos = l.getPhotos();
		List<ImageIcon> res = new ArrayList<>();
		for(Photo p : photos) {
			res.add(p.getPhoto());
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
    		@PathParam("Name")String name) {
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.setName(name);
    }
    
    @POST
    @Path("/setDescription/{Description}")
    //set a location's description
    public void setDescription (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Description") String description) {
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.setDescription(description);
    }
    
    @POST
    @Path("/setAddress/{Address}")
    //set an location's address
    public void setAddress (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Address") String address) {
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.setaddress(address);
    }
    
    @POST
    @Path("/addLabel/{Label}")
    //add a label to a location
    public void addLabel (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Label") String label) {
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.getLabels().add(label);
    }
    
    @POST
    @Path("addPhoto/{Photo}")
    //add a photo to a location
    public void addPhoto (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Photo")      ImageIcon photo) {
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.getPhotos().add(new Photo(photo));
    }
    
    @POST
    @Path("/removeLabel/{Label}")
    //add a label to a location
    public void removeLabel (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id, 
    		@PathParam("Label")      String label) {
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		l.getLabels().remove(label);
    }
    
    @POST
    @Path("removePhoto/{PhotoID}")
    //add a photo to a location
    public void removePhoto (
    		@PathParam("UserID")     int user_id, 
    		@PathParam("MapID")      int map_id, 
    		@PathParam("LocationID") int location_id,
    		@PathParam("PhotoID")    int photo_id) {
    	Location l = Database.getLocation(user_id, map_id, location_id);
		if(l == null) return;
		List<Photo> photos = l.getPhotos();
		for(Photo p : photos) {
			if(p.getId()==photo_id) {
				photos.remove(p);
			}
		}
    }
    
}
