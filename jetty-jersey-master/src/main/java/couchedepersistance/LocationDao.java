package couchedepersistance;

import java.util.List;

import javax.swing.ImageIcon;

public interface LocationDao {
	
	/* GET */
	
	//get a location
	Location getLocation (int user_id, int map_id, int location_id);
    //retrieval of a location's name
    String getName (int user_id, int map_id, int location_id);
    //retrieval of a location's description
    String getDescription (int user_id, int map_id, int location_id);
    //retrieval of a location's address
    String getAddress (int user_id, int map_id, int location_id);
    //retrieval of a location's list of labels
    List<String> getLabels (int user_id, int map_id, int location_id);
    //retrieval of a location's list of photos
    List<Photo> getPhotos (int user_id, int map_id, int location_id);
    
    /* POST */
    
    //set a location's name
    void setName (int user_id, int map_id, int location_id, String name);
    //set a location's description
    void setDescription (int user_id, int map_id, int location_id, String Description);
    //set an location's address
    void setAddress (int user_id, int map_id, int location_id, String address);
    //add a label to a location
    void addLabel (int user_id, int map_id, int location_id, String label);
    //add a photo to a location
    int addPhoto (int user_id, int map_id, int location_id, ImageIcon photo);
    //remove a label
    void removeLabel (int user_id, int map_id, int location_id, String label);
    //remove a photo
    void removePhoto (int user_id, int map_id, int location_id, int photo_id);
    
    /* NO PUT OR DELETE REQUIRED HERE */
    
}
