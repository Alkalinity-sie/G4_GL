package couchedepersistance;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.ImageIcon;

public interface EventDao {
	
	/* GET */
	
    //retrieval of the date and hour of begginning of an event
    LocalDateTime getBeginning (int user_id, int map_id, int event_id);
    //retrieval of the date and hour of ending of an event
    LocalDateTime getEnd (int user_id, int map_id, int event_id);
   
    //retrieval of an event's name
    String getName (int user_id, int map_id, int event_id);
    //retrieval of an event's description
    String getDescription (int user_id, int map_id, int event_id);
    //retrieval of an event's address
    String getAddress (int user_id, int map_id, int event_id);
    //retrieval of an event's list of labels
    List<String> getLabels (int user_id, int map_id, int event_id);
    //retrieval of an event's list of photos
    List<ImageIcon> getPhotos (int user_id, int map_id, int event_id);
    
    /* POST */
    
    //set a beginning to an event
    void setBeginning (int user_id, int map_id, int event_id, int year, int month, int dayOfMonth, int hour, int minute);
    //set an end to an event
    void setEnd (int user_id, int map_id, int event_id, int year, int month, int dayOfMonth, int hour, int minute);
    //set an event's name
    
    void setName (int user_id, int map_id, int event_id, String name);
    //set an event's description
    void setDescription (int user_id, int map_id, int event_id, String Description);
    //set an event's address
    void setAddress (int user_id, int map_id, int event_id, String address);
    //add a label to an event
    void addLabel (int user_id, int map_id, int event_id, String label);
    //add a photo to an event
    void addPhoto (int user_id, int map_id, int event_id, ImageIcon photo);
    
    /* NO PUT OR DELETE REQUIRED HERE */
    
}
