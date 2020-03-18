package couchedepersistance;

import java.time.LocalDateTime;

public class Event extends Location {
    private int id;
    private static int numberOfExistingEvent = 0;
    private LocalDateTime beginning;
    private LocalDateTime end;
    
    public Event(){
        super();
        numberOfExistingEvent++;
        this.id = numberOfExistingEvent;
    }

    public Event(String name, String description, String address, LocalDateTime beginning, LocalDateTime end){
        super(name, description, address);
        numberOfExistingEvent++;
        this.id = numberOfExistingEvent;
        this.beginning = beginning;
        this.end = end;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
