package couchedepersistance;

import java.util.List;
import javax.swing.ImageIcon;

public interface LocationDao {
	
	/* GET */
	
    //retrieval of a location's name
    String getName (int location_id);
    //retrieval of a location's description
    String getDescription (int location_id);
    //retrieval of a location's address
    String getAddress (int location_id);
    //retrieval of a location's list of labels
    List<String> getLabels (int location_id);
    //retrieval of a location's list of photos
    List<ImageIcon> getPhotos (int location_id);
    
    /* POST */
    
    //set a location's name
    void setName (int location_id, String name);
    //set a location's description
    void setDescription (int location_id, String Description);
    //set an location's address
    void setAddress (int location_id, String address);
    //add a label to a location
    void addLabel (int location_id, String label);
    //add a photo to a location
    void addPhoto (int location_id, ImageIcon photo);
}
