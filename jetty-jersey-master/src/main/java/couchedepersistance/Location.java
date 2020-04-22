package couchedepersistance;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Location {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
    private Long id;

    private String name;
    private String description;
    private String address;
    private List<String> labels;
    private List<Long> photos;
    
    public Location (){
        this.name = "";
        this.description = "";
        this.address = "";
        this.labels = new ArrayList<>();
        this.photos = new ArrayList<>();
    }
    
    public Location (String name, String description, String address){
        this.name = name;
        this.description = description;
        this.address = address;
        this.labels = new ArrayList<>();
        this.photos = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
    
    public List<Long> getPhotos() {
        return this.photos;
    }

    public void setPhotos(List<Long> photos) {
        this.photos = photos;
    }
}
