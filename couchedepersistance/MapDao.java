package couchedepersistance;

import java.util.List;

public interface MapDao {
	
	/* GET */
    //retrieval of a map's name
    String getName (int user_id, int map_id);
    //retrieval of a map's description
    String getDescription (int user_id, int map_id);
    //retrieval of a map's status. Return true if it's public
    boolean getStatus (int user_id, int map_id);
    //retrieval of map's list of locations
    List<Location> getLocations (int user_id, int map_id);
    //retrieval of the list of users to whom this map has been shared
    List<User> getSharedWith (int user_id, int map_id);
    
    /* POST */
    
    //set a map's name
    void setName (int user_id, int map_id, String name);
    //set a map's description
    void setDescription (int user_id, int map_id, String description);
    //set a map's status. Return true if it's public
    void setStatus (int user_id, int map_id, boolean status); //true = public, false = private
    //add a user to the list of users to whom this map has been shared
    void addUserToSharedWith (int user_id, int map_id, int user_id_to_add);
    
    /* PUT */
    
    //add a location to the map and returns the new event's id
    int addLocation (int user_id, int map_id);
    //add an event to the map and returns the new event's id
    int addEvent(int user_id, int map_id);
    
    
    /* DELETE */
    
    //remove a location
    void removeLocation(int user_id, int map_id, int location_id);
    //remove an event
    void removeEvent(int user_id, int map_id, int event_id);
    
    
    
}
