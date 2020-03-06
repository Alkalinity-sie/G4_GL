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


@Path("/Location")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationRessource implements LocationDao {
	
	@GET
	@Path("/{LocationID}/Name")
	//retrieval of a location's name
    public String getName (@PathParam("LocationID") int location_id) {
    	return "random_location_name";
    }
	
	@GET
	@Path("/{LocationID}/Description")
    //retrieval of a location's description
    public String getDescription (@PathParam("LocationID") int location_id) {
    	return "random_description";
    }
	
	@GET
	@Path("/{LocationID}/Address")
    //retrieval of a location's address
    public String getAddress (@PathParam("LocationID") int location_id) {
    	return "random_address";
    }
	
	@GET
	@Path("/{LocationID}/Labels")
    //retrieval of a location's list of labels
    public List<String> getLabels (@PathParam("LocationID") int location_id){
		List<String> labels = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            labels.add("label "+i);
        }
        return labels;
    }
	
	@GET
	@Path("/{LocationID}/Photos")
    //retrieval of a location's list of photos
    public List<ImageIcon> getPhotos (@PathParam("LocationID") int location_id){
		List<ImageIcon> photos = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            photos.add(new ImageIcon());
        }
        return photos;
    }
	
	
    @POST
    @Path("/{LocationID}/{Name}/Name")
    //set a location's name
    public void setName (@PathParam("LocationID") int location_id, @PathParam("Name")String name) {}
    
    @POST
    @Path("/{LocationID}/{Description}/Description")
    //set a location's description
    public void setDescription (@PathParam("LocationID") int location_id, @PathParam("Description") String Description) {}
    
    @POST
    @Path("/{LocationID}/{Address}/Address")
    //set an location's address
    public void setAddress (@PathParam("LocationID") int location_id, @PathParam("Address") String address) {}
    
    @POST
    @Path("/{LocationID}/{Label}/Label")
    //add a label to a location
    public void addLabel (@PathParam("LocationID") int location_id, @PathParam("Label") String label) {}
    
    @POST
    @Path("/{LocationID}/{Photo}/Photo")
    //add a photo to a location
    public void addPhoto (@PathParam("LocationID") int location_id, @PathParam("Photo") ImageIcon photo) {}
}
