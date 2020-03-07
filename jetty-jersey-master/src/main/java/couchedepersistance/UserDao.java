package couchedepersistance;

import java.util.List;

public interface UserDao {
	
	/* GET */
	
	//get a user
	User getUser (int user_id);
    //retrieval of a username
    String getUsername (int user_id);
    //retrieval of a user's password 
    String getPassword (int user_id);
    //retrieval of user's list of maps that he created
    List<Map> getPersonnalMaps (int user_id);
    //retrieval of a user's list of map that other users shared with him
    List<Map> getMapsSharedToMe (int user_id);
    
    /* POST */
    
    //set of a username
    void setUsername (int user_id, String username);
    //set of a user's password 
    void setPassword (int user_id, String password);
    
    /* PUT */
    
    //add a new personnal map
    int addPersonnalMap (int user_id);
    //add a new shared map 
    void addMapToSharedToMe (int FROM_user_id, int user_id, int map_id);
    
    /* DELETE */
    
    //remove a personnal map
    void removePersonnalMap(int user_id, int map_id);
    //remove a shared map
    void removeSharedMap(int user_id, int map_id);
}
