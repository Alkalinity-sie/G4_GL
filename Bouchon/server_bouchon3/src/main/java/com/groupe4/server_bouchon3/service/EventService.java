package com.groupe4.server_bouchon3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.groupe4.server_bouchon3.Event;
import com.groupe4.server_bouchon3.database.Database;

public class EventService {
	
private Map<Integer, Event> event = Database.getEvent();
	
	public List<Event> getAllEvent(){
		return new ArrayList<>(event.values());
	}
	
	public Event getEvent(int id) {
		return event.get(id);
	}
	
	public void addEvent(Event m) {
		event.put(event.size()+1,m);
	}
	public void removeEvent(int id) {
		event.remove(id);
	}
	
	public Event updateEvent(Event m) {
		event.put(m.getId(),m);
		return m;
	}

}
