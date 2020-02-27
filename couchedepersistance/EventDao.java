package couchedepersistance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface EventDao {
    
    //retrieval of the list of all existing events for all maps
    default List<Event> getEvents (){
        List<Event> events = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            events.add(new Event());
        }
        return events;
    }
    
    //retrieval of the date and hour of begginning of an event
    default LocalDateTime getBeginning (int event_id){
        return LocalDateTime.now();
    }
    //retrieval of the date and hour of ending of an event
    default LocalDateTime getEnd (int event_id){
        return LocalDateTime.now();
    }
}
