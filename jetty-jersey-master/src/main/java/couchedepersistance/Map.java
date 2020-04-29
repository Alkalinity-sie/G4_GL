package couchedepersistance;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Map {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
    private Long id;

    private String name;
    private String description;
    private boolean status; //true = public, false = private
    private List<Long> myLocations;// id de location
    private List<Long> myEvents;   // id d'event
    private List<Long> sharedWith; //id d'utilisateurs auxquels cette map a été partagé
    
    public String toString() {
    	System.out.println("|#########################################|");
    	String a = "	> Long id                : " + id.intValue() + "\n";
    	String b = "	> String name            : " + name          + "\n";
    	String c = "	> String description     : " + description   + "\n";

    	String myL = "";
    	if(this.myLocations != null) myL = myLocations.toString();
    	else myL = "	> List<Long> myLocations : null";
    	
    	String myE = "";
    	if(this.myEvents != null) myE = myEvents.toString();
    	else myE = "	> List<Long> myEvents    : null";
    	
    	String sh = "";
    	if(this.sharedWith != null) sh = sharedWith.toString();
    	else sh =  "	> List<Long> sharedWith  : null";
    	
    	String d = "	> List<Long> myLocations : " + myL + "\n";
    	String e = "	> List<Long> myEvents    : " + myE + "\n";
    	String f = "	> List<Long> sharedWith  : " + sh;
    	return a+b+c+d+e+f;
    }
    
    
    public Map(){
        this.name = "Sans nom";
        this.description = "Sans description";
        this.status = false;
        this.myLocations = new ArrayList<>();
        this.myEvents = new ArrayList<>();
        this.sharedWith = new ArrayList<>();
    }
    
    public Map(String name, String description, boolean status){
        this.name = name;
        this.description = description;
        this.status = status;
        this.myLocations = new ArrayList<>();
        this.myEvents = new ArrayList<>();
        this.sharedWith = new ArrayList<>();
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

    public boolean getStatus() {
    	return this.status;
    }
    
    public void setStatus(boolean status) {
    	this.status = status;
    }
 
    public List<Long> getMyLocations() {
        return myLocations;
    }

    public void setMyLocations(List<Long> myLocations) {
        this.myLocations = myLocations;
    }
    
    public List<Long> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(List<Long> myEvents) {
        this.myEvents = myEvents;
    }

    public List<Long> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<Long> sharedWith) {
        this.sharedWith = sharedWith;
    }
}
