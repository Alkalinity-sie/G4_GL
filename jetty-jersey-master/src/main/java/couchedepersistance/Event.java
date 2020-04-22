package couchedepersistance;

import java.time.LocalDateTime;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable="true")
public class Event extends Location {

    private LocalDateTime beginning;
    private LocalDateTime end;
    
    public Event(){
        super();
    }

    public Event(String name, String description, String address, LocalDateTime beginning, LocalDateTime end){
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
    
    public LocalDateTime getBeginning() {
        return beginning;
    }

    public void setBeginning(LocalDateTime beginning) {
        this.beginning = beginning;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    
}
