package couchedepersistance;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.ImageIcon;

public interface EventDao {
	
	/* GET */
	
    //retrieval of the date and hour of begginning of an event
    LocalDateTime getBeginning (int event_id);
    //retrieval of the date and hour of ending of an event
    LocalDateTime getEnd (int event_id);
   
    //retrieval of an event's name
    String getName (int event_id);
    //retrieval of an event's description
    String getDescription (int event_id);
    //retrieval of an event's address
    String getAddress (int event_id);
    //retrieval of an event's list of labels
    List<String> getLabels (int event_id);
    //retrieval of an event's list of photos
    List<ImageIcon> getPhotos (int event_id);
    
    /* POST */
    
    //set a beginning to an event
    void setBeginning (int event_id, int year, int month, int dayOfMonth, int hour, int minute);
    //set an end to an event
    void setEnd (int event_id, int year, int month, int dayOfMonth, int hour, int minute);
    //set an event's name
    
    void setName (int event_id, String name);
    //set an event's description
    void setDescription (int event_id, String Description);
    //set an event's address
    void setAddress (int event_id, String address);
    //add a label to an event
    void addLabel (int event_id, String label);
    //add a photo to an event
    void addPhoto (int event_id, ImageIcon photo);
    
}
