package couchedepersistance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface EventDao {
    
    //récupération de tous les évènements existants toutes cartes confondues
    default List<Event> getEvents (){
        List<Event> events = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            events.add(new Event());
        }
        return events;
    }
    
    //récupération de l'id d'un évènement
    
    //récupération de la date+heure de début d'un évènement
    default LocalDateTime getBeginning (int event_id){
        return LocalDateTime.now();
    }
    //récupération de la date+heure de début d'un évènement
    default LocalDateTime getEnd (int event_id){
        return LocalDateTime.now();
    }
}
