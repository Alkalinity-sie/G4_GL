package ressources;

import java.time.LocalDateTime;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import couchedepersistance.*;


public class Database {
	
	
	public static Long addData (Object o, char classe) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Long id = null;
		try {
			tx.begin();
			switch (classe) {
				case 'U': 
					User copy_user = pm.makePersistent((User) o); 
					id = copy_user.getId();
					break;
				case 'M': 
					Map copy_map = pm.makePersistent((Map) o); 
					id = copy_map.getId();
					break;
				case 'L': 
					Location copy_location = pm.makePersistent((Location) o); 
					id = copy_location.getId();
					break;
				case 'E': 
					Event copy_event = pm.makePersistent((Event) o); 
					id = copy_event.getId();
					break;
				case 'P': 
					Photo copy_photo = pm.makePersistent((Photo) o);
					id = copy_photo.getId();
					break;
			}
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		return id;
	}
	
	/**
	 * Remplit la BDD avec des données par défaut
	 * Cette méthode n'est appelée qu'une fois (au début)
	 */
	public static void initialise () {
		//utilisateurs
		User jean   = new User("Jean",  "mdp123"); 
		User pierre = new User("Pierre","654321"); 
		
		//maps
		Map jeanMap1 = new Map("Resto' ","Carte des restaurants", false); 
		Map pierreMap1 = new Map("Parcs ","Carte des parcs", false);     
		
		//lieux et évènement de l'utilisateur Jean
		//Location otacos = new Location("O'Tacos", "Best tacos ever", "184 Avenue de Choisy, 75013 Paris");
		Event manifestation = new Event(
				"Manif des GJ","Manifestation à Paris des Gilets jaunes",
				"121 Av. des Champs-Élysées, 75008 Paris*48.872283508801225*2.298449277877808",
				LocalDateTime.parse("2020-10-06T17:00:00.000"), 
				LocalDateTime.parse("2020-10-06T19:00:00.000"));
		
		
		//lieux et évènement de l'utilisateur Pierre
		/*Location jardinAP = new Location(
				"Jardins Abbé-Pierre - Grands-Moulins", 
				"Beau jardin", "15 Rue Thomas Mann, 75013 Paris");*/
		Event anniversaire = new Event(
						"Mon anniversaire","Je vais fêter mon anniversaire le 22 juin à l'université",
						"5 Rue Thomas Mann, 75013 Paris*48.82928545763674*2.3816299438476567",
						LocalDateTime.parse("2020-06-10T00:00:00.000"), 
						LocalDateTime.parse("2020-06-10T23:50:00.000"));
		
		//Long otacosId = addData(otacos, 'L');
		//Long jardinAPId = addData(jardinAP, 'L');
		
		Long manifestationId = addData(manifestation, 'E');
		Long anniversaireId = addData(anniversaire, 'E');
		
		Long jeanMap1Id = addData(jeanMap1, 'M');
		Long pierreMap1Id = addData(pierreMap1, 'M');
		
		Long jeanID = addData(jean, 'U');
		Long pierreID = addData(pierre, 'U');
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User j = pm.getObjectById(User.class, jeanID);
			j.getMyMaps().add(jeanMap1Id);
			j.getMyMaps().add(pierreMap1Id);
			j.getSharedToMe().add(pierreMap1Id);
			Map mj = pm.getObjectById(Map.class, jeanMap1Id);
			//mj.getMyLocations().add(otacosId);
			mj.getMyEvents().add(manifestationId);
			mj.getMyEvents().add(anniversaireId);
			
			User p = pm.getObjectById(User.class, pierreID);
			p.getMyMaps().add(pierreMap1Id);
			Map mp = pm.getObjectById(Map.class, pierreMap1Id);
			//mp.getMyLocations().add(jardinAPId);
			mp.getMyEvents().add(anniversaireId);
			
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		
		System.out.println("User Jean infos :\n"+Database.getUser(jeanID.intValue()).toString());
		System.out.println("User Jean infos :\n"+Database.getUser(pierreID.intValue()).toString());
		System.out.println("MapID0 infos :\n"+Database.getMap(jeanID.intValue(), jeanMap1Id.intValue()));
		System.out.println("MapID1  infos :\n"+Database.getMap(pierreID.intValue(), pierreMap1Id.intValue()));
	}
	
	/**
	 * Renvoie une copie de l'utilisateur d'id user_id dans la BDD. Renvoie null si inexistant.
	 * @param user_id
	 * @return Une copie
	 */
	public static User getUser (int user_id){
		User copy = null;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User u = pm.getObjectById(User.class, user_id);
			System.out.println("Fonction Database.getUser : u.username = "+u.getUsername());
			copy = Copy.copyUser(u);
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		return copy;
		
	}
	
	/**
	 * Renvoie une copie d'une map personnel d'un utilisateur. Renvoie null si inexistant ou si l'utilisateur n'a pas cette map.
	 */
	public static Map getMap (int user_id, int map_id){
		// recherche de l'utilisateur
		User u = getUser(user_id);
		if(u == null) return null; // si l'utilisateur n'existe pas
		
		// recherche de la carte dans ses cartes personnels
		boolean found = false;
		for(Long l : u.getMyMaps()) { // recherche dans ses cartes personnels
			if(l.intValue() == map_id) {
				found = true;
				break;
			}
		}
		if(found == false) {
			for(Long l : u.getSharedToMe()) { // recherche dans ses cartes personnels
				if(l.intValue() == map_id) {
					found = true;
					break;
				}
			}
		}
		
		if(found == false) {
			return null;
		}
		
		// l'utilisateur existe et la carte existe
		Map copy = null;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Map m = pm.getObjectById(Map.class, map_id);
			copy = Copy.copyMap(m);
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		return copy;
	}
	
	/**
	 * Renvoie une copie d'un location d'une map. Renvoie null si inexistant ou si l'objet n'appartient pas au bon couple (user_id, map_id).
	 */
	public static Location getLocation (int user_id, int map_id, int location_id){
		// recherche de la map
		Map m = getMap(user_id, map_id);
		if(m == null) {
			return null;
		}
		
		// recherche du location
		boolean found = false;
		for(Long l : m.getMyLocations()) {
			if(l.intValue() == location_id) {
				found = true;
				break;
			}
		}
		if(found == false) return null; // si la map existe mais n'a pas ce location
		
		// la map existe et le location existe
		Location copy = null;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Location l = pm.getObjectById(Location.class, location_id);
			copy = Copy.copyLocation(l);
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		return copy;
	}
	
	/**
	 * Renvoie une copie d'un event d'une map. Renvoie null si inexistant ou si le lieu d'appartient pas au bon couple (user_id, map_id).
	 */
	public static Event getEvent (int user_id, int map_id, int event_id){	
		// recherche de la map
		Map m = getMap(user_id, map_id);
		if(m == null) {
			return null;
		}
				
		// recherche du location
		boolean found = false;
		for(Long l : m.getMyEvents()) {
			if(l.intValue() == event_id) {
				found = true;
				break;
			}
		}
		if(found == false) return null; // si la map existe mais n'a pas ce location
				
		// la map existe et le location existe
		Event copy = null;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
					
			Event e = pm.getObjectById(Event.class, event_id);
			copy = Copy.copyEvent(e);
					
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		return copy;
	}
	
	/**
	 * Renvoie une copie de la photo dans la BDD. Renvoie null si inexistant.
	 * @param user_id
	 * @return Une copie
	 */
	public static Photo getPhoto (int user_id, int map_id, int locationORevent_id, int photo_id){
		// recherche de location/event
		Location l = getLocation(user_id, map_id, locationORevent_id);
		Event e = getEvent(user_id, map_id, locationORevent_id);
		if(l == null || e == null) return null;
						
		// recherche du location
		boolean found = false;
		for(Long id : l.getPhotos()) {
			if(id.intValue() == photo_id) {
				found = true;
				break;
			}
		}
		
		if(found == false) {
			for(Long id : e.getPhotos()) {
				if(id.intValue() == photo_id) {
					found = true;
					break;
				}
			}
		}
		
		if(found == false) return null; 
						
		//recuperation
		Photo copy = null;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
							
			Photo p = pm.getObjectById(Photo.class, photo_id);
			copy = Copy.copyPhoto(p);
							
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		return copy;
		
	}
	
	
}
