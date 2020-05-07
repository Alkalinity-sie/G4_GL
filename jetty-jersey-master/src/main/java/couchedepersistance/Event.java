package couchedepersistance;

import java.time.LocalDateTime;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable="true")
public class Event extends Location {

    private String beginning;
    private String end;
    
    public Event(){
        super();
        this.beginning = LocalDateTime.now().toString();
        this.end = LocalDateTime.now().toString();
    }

    public Event(String name, String description, String address, String beginning, String end){
        super(name, description, address);
        this.beginning = beginning;
        this.end = end;
    }
    
    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }
    
    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    
}
