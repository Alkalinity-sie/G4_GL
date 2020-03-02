package com.groupe4.server_bouchon3.ressource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.groupe4.server_bouchon3.Event;
import com.groupe4.server_bouchon3.service.EventService;

@Path("/Event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventRessource {

		EventService eventService = new EventService();
		
		@GET
		public List<Event> getEvent(){
			return eventService.getAllEvent();
		}
		
		@POST 
		@Consumes(MediaType.APPLICATION_JSON)
		public Event addEvent(Event u) {
			eventService.addEvent(u);
			return u;
		}
		
		@GET
		@Path("/{EventID}")
		public Event getEvent(@PathParam("EventID") int id) {
			return eventService.getEvent(id);
		}

		


}
