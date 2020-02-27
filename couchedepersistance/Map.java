package couchedepersistance;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private int id;
    private static int numberOfExistingMap = 0;
    private String name;
    private String description;
    private boolean isPublic; 
    private List<Location> myLocations;
    private List<User> sharedWith;
    
    public Map(){
        numberOfExistingMap++;
        this.id = numberOfExistingMap;
        this.name = "";
        this.description = "";
        this.isPublic = false;
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

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
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
