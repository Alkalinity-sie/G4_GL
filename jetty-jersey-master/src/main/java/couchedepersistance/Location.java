package couchedepersistance;

import java.util.ArrayList;
import java.util.List;


public class Location {
    
    private static int numberOfExistingLocation;
    private int id;
    private String name;
    private String description;
    private String address;
    private List<String> labels;
    private List<Photo> photos;
    
    public Location (){
        numberOfExistingLocation++;
        this.id = numberOfExistingLocation;
        this.name = "";
        this.description = "";
        this.address = "";
        this.labels = new ArrayList<>();
        this.photos = new ArrayList<>();
    }
    
    public Location (String name, String description, String address){
        numberOfExistingLocation++;
        this.id = numberOfExistingLocation;
        this.name = name;
        this.description = description;
        this.address = address;
        this.labels = new ArrayList<>();
        this.photos = new ArrayList<>();
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

    public String getAddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
    
    public List<Photo> getPhotos() {
        return this.photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    
}
