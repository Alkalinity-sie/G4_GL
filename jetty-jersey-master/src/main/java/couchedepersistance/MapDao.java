package couchedepersistance;

import java.util.List;

public interface MapDao {
	
	/* GET */
	
    //retrieval of a map's name
    String getName (int map_id);
    //retrieval of a map's description
    String getDescription (int map_id);
    //retrieval of a map's status. Return true if it's public
    boolean isPublic (int map_id);
    //retrieval of map's list of locations
    List<Location> getLocations (int map_id);
    //retrieval of the list of users to whom this map has been shared
    List<User> getSharedWith (int map_id);
    
    /* POST */
    
    //set a map's name
    void setName (int map_id, String name);
    //retrieval of a map's description
    void setDescription (int map_id, String description);
    //retrieval of a map's status. Return true if it's public
    void setStatus (int map_id, boolean status); //true = public, false = private
    //retrieval of map's list of locations
    void addLocation (int map_id, int location_id);
    //retrieval of the list of users to whom this map has been shared
    void addUserToSharedWith (int map_id, int user_id);
}
