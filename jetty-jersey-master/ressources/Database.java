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
		
		Long jeanID = addData(jean, 'U');
		Long pierreID = addData(pierre, 'U');
		
		User macron   = new User("Macron",  "mdp123"); 
		User emmanuel = new User("Emmanuel","654321");
		
		Long macronID = addData(macron, 'U');
		Long emmanuelID = addData(emmanuel, 'U');
		
		//maps
		Map jeanMap1 = new Map("Resto ","Carte des restaurants", false); 
		Map pierreMap1 = new Map("Zoo","Carte des zoo", false);  
		
		Long jeanMap1Id = addData(jeanMap1, 'M');
		Long pierreMap1Id = addData(pierreMap1, 'M');
		
		//lieux et évènement de l'utilisateur Jean
		//Location otacos = new Location("O'Tacos", "Best tacos ever", "184 Avenue de Choisy, 75013 Paris");
		Event manifestation = new Event(
				"Manif des GJ","Manifestation à Paris des Gilets jaunes",
				"121 Av. des Champs-Élysées, 75008 Paris*48.872283508801225*2.298449277877808",
				"2020-10-06T17:00:00.000", 
				"2020-10-06T19:00:00.000");
				
				
		//lieux et évènement de l'utilisateur Pierre
		/*Location jardinAP = new Location(
				"Jardins Abbé-Pierre - Grands-Moulins", 
				"Beau jardin", "15 Rue Thomas Mann, 75013 Paris");*/
		Event anniversaire = new Event(
						"Mon anniversaire","Je vais fêter mon anniversaire le 22 juin à l'université",
						"5 Rue Thomas Mann, 75013 Paris*48.82928545763674*2.3816299438476567",
						"2020-06-10T00:00:00.000", 
						"2020-06-10T23:50:00.000");
				
		//Long otacosId = addData(otacos, 'L');
		//Long jardinAPId = addData(jardinAP, 'L');
		
		Long manifestationId = addData(manifestation, 'E');
		Long anniversaireId = addData(anniversaire, 'E');
				
		
		//-----------------------------------------------------
		Map macronMap1 = new Map("Cafés CC","Carte des cafés", true); 
		Long macronMap1ID = addData(macronMap1, 'M');
		
		Event macroncafe = new Event("MK2 Café", "Meilleur cafés au monde", "10 Quai de la Seine, 75019 Paris*48.89419*2.373000", 
				LocalDateTime.now().toString(), LocalDateTime.now().toString());
		macroncafe.getLabels().add("Café");
		macroncafe.getLabels().add("Cappucino");
		macroncafe.getLabels().add("Expresso");
		Long macroncafeID = addData(macroncafe, 'E');
		//-----------------------------------------------------
		Map emmanuelMap1 = new Map("CafCafé' ","Carte des cafés", true); 
		Long emmanuelMap1ID = addData(emmanuelMap1, 'M');
		
		Event emmanuelcafe = new Event("Au Bon Café", "Cafés extra !", "263 Rue du Faubourg Saint-Martin*48.888767*2.346360", 
				LocalDateTime.now().toString(), LocalDateTime.now().toString());
		emmanuelcafe.getLabels().add("Café");
		emmanuelcafe.getLabels().add("Abordable");
		emmanuelcafe.getLabels().add("Ambiance");
		Long emmanuelcafeID = addData(emmanuelcafe, 'E');
		//-----------------------------------------------------
		Map macronMap2 = new Map("Parcs aquatiques gratuits","Carte des parcs gratuits", true);
		Long macronMap2ID = addData(macronMap2, 'M');
		
		Event macronparcs = new Event("Parcs aquatiques", "De beaux poissons", "Place Paul Delouvrier*48.892152*2.385329", 
				LocalDateTime.now().toString(), LocalDateTime.now().toString());
		macronparcs.getLabels().add("Nénuphar");
		macronparcs.getLabels().add("Parcs");
		Long macronparcsID = addData(macronparcs, 'E');
		//-----------------------------------------------------
		Map emmanuelMap2 = new Map("Parcs ","Carte des parcs", true);
		Long emmanuelMap2ID = addData(emmanuelMap2, 'M');
		
		Event emmanuelparcs = new Event("Parcs aquatiques géants", "De beaux poissons", "Place Paul Delouvrier*48.892152*2.385329", 
				LocalDateTime.now().toString(), LocalDateTime.now().toString());
		emmanuelparcs.getLabels().add("Poissons");
		emmanuelparcs.getLabels().add("Géant");
		Long emmanuelparcsID = addData(emmanuelparcs, 'E');
		//-----------------------------------------------------
		Map macronMap3 = new Map("Restaurants chinois","Carte des restaurants", true);
		Long macronMap3ID = addData(macronMap3, 'M');
		
		Event macronrestaurant = new Event("Restaurant chinois", "Plats fabuleux !", "52 Rue de Clignancourt*48.887658*2.347862", 
				LocalDateTime.now().toString(), LocalDateTime.now().toString());
		macronrestaurant.getLabels().add("Nem");
		macronrestaurant.getLabels().add("Tradition");
		Long macronrestaurantID = addData(macronrestaurant, 'E');
		//-----------------------------------------------------
		Map emmanuelMap3 = new Map("Restaurants japonais ","Carte des restaurants", true);
		Long emmanuelMap3ID = addData(emmanuelMap3, 'M');
		
		Event emmanuelrestaurant = new Event("Restaurant japonais", "Plats délicieux !", "21 Place Marcel Aymé*48.887540*2.337813", 
				LocalDateTime.now().toString(), LocalDateTime.now().toString());
		emmanuelrestaurant.getLabels().add("Sushi");
		emmanuelrestaurant.getLabels().add("Poisson");
		Long emmanuelrestaurantID = addData(emmanuelrestaurant, 'E');
		//-----------------------------------------------------
		Map macronMap4 = new Map("Grands Supermarchés","Carte des Supermarchés", true);
		Long macronMap4ID = addData(macronMap4, 'M');
		
		Event macronsupermarche = new Event("Franprix", "C'est de l'arnaque mais c'est proche de chez moi",
				"19-13 Rue Joseph de Maistre*48.886372*2.333244", LocalDateTime.now().toString(), LocalDateTime.now().toString());
		macronsupermarche.getLabels().add("Petit");
		macronsupermarche.getLabels().add("Cher");
		Long macronsupermarcheID = addData(macronsupermarche, 'E');
		//-----------------------------------------------------
		Map emmanuelMap4 = new Map("Supermarchés","Carte des Supermarchés", true);
		Long emmanuelMap4ID = addData(emmanuelMap4, 'M');
		
		Event emmanuelsupermarche = new Event("Monoprix", "Supermarché très spacieux", "21 Place Marcel Aymé*48.887540*2.337813", 
				LocalDateTime.now().toString(), LocalDateTime.now().toString());
		emmanuelsupermarche.getLabels().add("Grand");
		Long emmanuelsupermarcheID = addData(emmanuelsupermarche, 'E');
		//-----------------------------------------------------
		
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			User j = pm.getObjectById(User.class, jeanID);
			j.getMyMaps().add(jeanMap1Id);
			Map mj = pm.getObjectById(Map.class, jeanMap1Id);
			//mj.getMyLocations().add(otacosId);
			mj.getMyEvents().add(manifestationId);
			
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
		
		pmf = JDOHelper.getPersistenceManagerFactory("Example");
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			
			//macron
			User m = pm.getObjectById(User.class, macronID);
			System.out.println("m : "+m.getId());
			m.getMyMaps().add(macronMap1ID);
			m.getMyMaps().add(macronMap2ID);
			m.getMyMaps().add(macronMap3ID);
			m.getMyMaps().add(macronMap4ID);
			Map m1 = pm.getObjectById(Map.class, macronMap1ID);
			System.out.println("m1 : "+m1.getId());
			Map m2 = pm.getObjectById(Map.class, macronMap2ID);
			System.out.println("m2 : "+m2.getId());
			Map m3 = pm.getObjectById(Map.class, macronMap3ID);
			System.out.println("m3 : "+m3.getId());
			Map m4 = pm.getObjectById(Map.class, macronMap4ID);
			System.out.println("m4 : "+m4.getId());
			m1.getMyEvents().add(macroncafeID);
			m2.getMyEvents().add(macronparcsID);
			m3.getMyEvents().add(macronrestaurantID);
			m4.getMyEvents().add(macronsupermarcheID);
			
			//emmanuel
			User e = pm.getObjectById(User.class, emmanuelID);
			System.out.println("e : "+e.getId());
			e.getMyMaps().add(emmanuelMap1ID);
			e.getMyMaps().add(emmanuelMap2ID);
			e.getMyMaps().add(emmanuelMap3ID);
			e.getMyMaps().add(emmanuelMap4ID);
			Map e1 = pm.getObjectById(Map.class, emmanuelMap1ID);
			System.out.println("e1 : "+e1.getId());
			Map e2 = pm.getObjectById(Map.class, emmanuelMap2ID);
			System.out.println("e2 : "+e2.getId());
			Map e3 = pm.getObjectById(Map.class, emmanuelMap3ID);
			System.out.println("e3 : "+e3.getId());
			Map e4 = pm.getObjectById(Map.class, emmanuelMap4ID);
			System.out.println("e4 : "+e4.getId());
			e1.getMyEvents().add(emmanuelcafeID);
			e2.getMyEvents().add(emmanuelparcsID);
			e3.getMyEvents().add(emmanuelrestaurantID);
			e4.getMyEvents().add(emmanuelsupermarcheID);
			
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
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
			System.out.println("database : "+e.getBeginning().toString());
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
		/*
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
						*/
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
