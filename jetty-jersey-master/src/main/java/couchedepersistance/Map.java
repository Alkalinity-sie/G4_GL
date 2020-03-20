package couchedepersistance;

import java.util.ArrayList;
import java.util.List;

public class Map {
    
    private static int numberOfExistingMap;
    private int id;
    private String name;
    private String description;
    private boolean status; //true = public, false = private
    private List<Location> myLocations;
    private List<User> sharedWith;
    
    public Map(){
        numberOfExistingMap++;
        this.id = numberOfExistingMap;
        this.name = "";
        this.description = "";
        this.status = false;
        this.myLocations = new ArrayList<>();
        this.sharedWith = new ArrayList<>();
    }
    
    public Map(String name, String description, boolean status){
        numberOfExistingMap++;
        this.id = numberOfExistingMap;
        this.name = name;
        this.description = description;
        this.status = status;
        this.myLocations = new ArrayList<>();
        this.sharedWith = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
    	return this.status;
    }
    
    public void setStatus(boolean status) {
    	this.status = status;
    }
 

    public List<Location> getMyLocations() {
        return myLocations;
    }

    public void setMyLocations(List<Location> myLocations) {
        this.myLocations = myLocations;
    }

    public List<User> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<User> sharedWith) {
        this.sharedWith = sharedWith;
    }
}
