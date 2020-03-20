package ressources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import couchedepersistance.*;

public class Database {

	private static List<User> users = new ArrayList<>();
	
	public static User getUser (int user_id){
		for(User u : users) {
			if(u.getId()==user_id) return u;
		}
		return null; //renvoie null si l'utilisateur n'apparait pas
	}
	
	public static Map getPersonnalMap (int user_id, int map_id){
		User u = getUser(user_id);
		if(u==null) return null; //renvoie null si aucun utilisateur de cet id
		
		for(Map m : u.getMyMaps()) {
			if(m.getId()==map_id) return m;
		}
		return null; //renvoie null si l'utilisateur n'aucune map de cet id 	
	}
	
	public static Map getSharedMap (int user_id, int map_id){
		User u = getUser(user_id);
		if(u==null) return null; //renvoie null si aucun utilisateur de cet id
		
		for(Map m : u.getSharedToMe()) {
			if(m.getId()==map_id) return m;
		}
		return null; //renvoie null si l'utilisateur n'aucune map de cet id 	
	}
	
	//getLocation retourne uniquement un lieu (pas un evenement même si c'est aussi un lieu au fond)
	public static Location getLocation (int user_id, int map_id, int location_id){
		Map m = getPersonnalMap(user_id, map_id);
		if(m==null) m = getSharedMap(user_id, map_id);
		if(m==null) return null;
		
		for(Location l : m.getMyLocations()) {
			if(l.getId()==location_id && (l instanceof Event)==false) return l;
		}
		return null;	
	}
	
	//getLocation retourne uniquement un lieu (pas un evenement même si c'est aussi un lieu)
	public static Location getEvent (int user_id, int map_id, int event_id){
		Map m = getPersonnalMap(user_id, map_id);
		if(m==null) m = getSharedMap(user_id, map_id);
		if(m==null) return null;
		
		for(Location l : m.getMyLocations()) {
			if(l.getId()==event_id && (l instanceof Event)) return l;
		}
		return null;	
	}
	
	
	public static void fillDatabase() {
		//utilisateurs
		User jean = new User("Jean","mdp123");
		User pierre = new User("Pierre","654321");
		
		//maps
		Map jeanMap1 = new Map("Resto' ","Carte des restaurants", false); // id = 1
		Map pierreMap1 = new Map("Parcs ","Carte des parcs", false);      // id = 2

		//listes de l'utilisateur Jean
		List<Map> jeanPersonnalMaps = new ArrayList<>();
		List<Location> jeanLocations = new ArrayList<>();
		
		//listes de l'utilisateur Pierre
		List<Map> pierrePersonnalMaps = new ArrayList<>();
		List<Location> pierreLocations = new ArrayList<>();
		
		//lieux et évènement de l'utilisateur Jean
		Location otacos = new Location("O'Tacos", "Best tacos ever", "184 Avenue de Choisy, 75013 Paris");
		Event manifestation = new Event(
				"Manif des GJ","Manifestation à Paris des Gilets jaunes",
				"121 Av. des Champs-Élysées, 75008 Paris",
				LocalDateTime.parse("2020-10-06T17:00:00.000"), 
				LocalDateTime.parse("2020-10-06T19:00:00.000"));
		jeanLocations.add(otacos);
		jeanLocations.add(manifestation);
		
		//lieux et évènement de l'utilisateur Pierre
		Location jardinAP = new Location(
				"Jardins Abbé-Pierre - Grands-Moulins", 
				"Beau jardin", "15 Rue Thomas Mann, 75013 Paris");
		Event anniversaire = new Event(
						"Mon anniversaire","Je vais fêter mon anniversaire le 22 juin à l'université",
						"5 Rue Thomas Mann, 75013 Paris",
						LocalDateTime.parse("2020-06-10T00:00:00.000"), 
						LocalDateTime.parse("2020-06-10T23:50:00.000"));
		pierreLocations.add(jardinAP);
		pierreLocations.add(anniversaire);
		
		//ajouts de l'utilisateur Jean 
		jeanMap1.setMyLocations(jeanLocations);
		jeanPersonnalMaps.add(jeanMap1);
		jean.setMyMaps(jeanPersonnalMaps);
		users.add(jean);
		
		//ajouts de l'utilisateur Pierre
		pierreMap1.setMyLocations(pierreLocations);
		pierrePersonnalMaps.add(pierreMap1);
		pierre.setMyMaps(pierrePersonnalMaps);
		users.add(pierre);
		
	}
	
}
