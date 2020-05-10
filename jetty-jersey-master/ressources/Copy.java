package ressources;

import java.util.ArrayList;
import java.util.List;

import couchedepersistance.Event;
import couchedepersistance.Location;
import couchedepersistance.Map;
import couchedepersistance.Photo;
import couchedepersistance.User;

public class Copy {

	/**
	 * Retourne une copie profonde d'une photo
	 * @param p Une photo
	 * @return Une copie de p
	 */
	public static Photo copyPhoto (Photo p) {
		Photo copy = new Photo();
		// copie du champ : Long id;
		Long id = new Long(p.getId().intValue());
		copy.setId(id);
		copy.setPhoto(new String(p.getPhoto()));
		
		return copy;
	}
	
	public static String intToProperString(int n) {
		return (n<10)?("0"+n):(""+n);
	}
	
	/**
	 * Retourne une copie profonde d'un evenement
	 * @param e Un evenement
	 * @return Une copie de e
	 */
	public static Event copyEvent (Event e) {
		Event copy = new Event();
		
		// copie du champ : Long id
		Long id = new Long(e.getId().intValue());
		copy.setId(id);
		
		// copie du champ : LocalDateTime beginning
		/*
		String Byear = intToProperString(e.getBeginning().getYear());
		String Bmonth = intToProperString(e.getBeginning().getMonthValue());
		String BdayOfMonth = intToProperString(e.getBeginning().getDayOfMonth());
		String Bhour = intToProperString(e.getBeginning().getHour());
		String Bminute = intToProperString(e.getBeginning().getMinute());
		String Bchaine = ""+Byear+"-"+Bmonth+"-"+BdayOfMonth+"T"+Bhour+":"+Bminute+":"+"00.000"; 
		
			//exemple : "2020-10-06T17:00:00.000"
		LocalDateTime beginning = LocalDateTime.parse(Bchaine);*/
		copy.setBeginning(new String(e.getBeginning()));
		/*
		// copie du champ : LocalDateTime end
		String Eyear = intToProperString(e.getEnd().getYear());
		String Emonth = intToProperString(e.getEnd().getMonthValue());
		String EdayOfMonth = intToProperString(e.getEnd().getDayOfMonth());
		String Ehour = intToProperString(e.getEnd().getHour());
		String Eminute = intToProperString(e.getEnd().getMinute());
		String Echaine = ""+Eyear+"-"+Emonth+"-"+EdayOfMonth+"T"+Ehour+":"+Eminute+":"+"00.000"; 
		LocalDateTime end = LocalDateTime.parse(Echaine);*/
		copy.setEnd(new String(e.getEnd()));
		
		// copie du champ : String name
		copy.setName(new String(e.getName()));
		
		// copie du champ : String description
		copy.setDescription(new String(e.getDescription()));
		
		// copie du champ : String address;
		copy.setAddress(new String(e.getAddress()));
		
		// copie du champ : List<String> labels;
		List<String> labelsCopy = new ArrayList<String>();
		for(String s : e.getLabels()) labelsCopy.add(new String(s));
		copy.setLabels(labelsCopy);
		
		// copie du champ : List<Long> photos;
		List<Long> photosCopy = new ArrayList<Long>();
		for(Long l : e.getPhotos()) photosCopy.add(new Long(l.intValue()));
		copy.setPhotos(photosCopy);
		
		System.out.println("debut : "+copy.getBeginning().toString());
		System.out.println("fin : "+copy.getEnd().toString());
		return copy;
	}
	
	/**
	 * Retourne une copie profonde d'un objet de type location
	 * @param l Un lieu
	 * @return Une copie de l
	 */
	public static Location copyLocation (Location l) {
		Location copy = new Location();
		
		// copie du champ : Long id
		Long id = new Long(l.getId().intValue());
		copy.setId(id);
		
		// copie du champ : String name
		copy.setName(new String(l.getName()));
		
		// copie du champ : String description
		copy.setDescription(new String(l.getDescription()));
		
		// copie du champ : String address;
		copy.setAddress(new String(l.getAddress()));
		
		// copie du champ : List<String> labels;
		List<String> labelsCopy = new ArrayList<String>();
		for(String s : l.getLabels()) labelsCopy.add(new String(s));
		copy.setLabels(labelsCopy);
		
		// copie du champ : List<Long> photos;
		List<Long> photosCopy = new ArrayList<Long>();
		for(Long lid : l.getPhotos()) photosCopy.add(new Long(lid.intValue()));
		copy.setPhotos(photosCopy);
		
		return copy;
	}
	
	/**
	 * Retourne une copie profonde d'une map
	 * @param m Une map
	 * @return Une copie de e
	 */
	public static Map copyMap (Map m) {
		Map copy = new Map();
		
		// copie du champ : Long id
		Long id = new Long(m.getId().intValue());
		copy.setId(id);
		
		// copie du champ : String name
		copy.setName(new String(m.getName()));
		
		// copie du champ : String description
		copy.setDescription(new String(m.getDescription()));
		
		// copie du champ : status; //true = public, false = private
		copy.setStatus(m.getStatus());

		// copie du champ : List<Long> myLocations;
		List<Long> locationsCopy = new ArrayList<Long>();
		for(Long lid : m.getMyLocations()) locationsCopy.add(new Long(lid.intValue()));
		copy.setMyLocations(locationsCopy);
		
		// copie du champ : List<Long> myEvents;
		List<Long> eventsCopy = new ArrayList<Long>();
		for(Long lid : m.getMyEvents()) eventsCopy.add(new Long(lid.intValue()));
		copy.setMyEvents(eventsCopy);
		
		// copie du champ : List<Long> sharedWith;
		List<Long> sharedWithCopy = new ArrayList<Long>();
		for(Long lid : m.getSharedWith()) sharedWithCopy.add(new Long(lid.intValue()));
		copy.setSharedWith(sharedWithCopy);
		
		return copy;
	}
	
	/**
	 * Retourne une copie profonde d'un user
	 * @param m Un user
	 * @return Une copie de u
	 */
	public static User copyUser (User u) {
		User copy = new User();
		
		// copie du champ : Long id
		Long id = new Long(u.getId().intValue());
		copy.setId(id);
		
		// copie du champ : String username
		copy.setUsername(new String(u.getUsername()));
		
		// copie du champ : String password
		copy.setPassword(new String(u.getPassword()));

		// copie du champ : List<Long> sharedToMe;
		List<Long> sharedToMeCopy = new ArrayList<Long>();
		for(Long lid : u.getSharedToMe()) sharedToMeCopy.add(new Long(lid.intValue()));
		copy.setSharedToMe(sharedToMeCopy);
		
		// copie du champ : List<Long> myMaps
		List<Long> myMapsCopy = new ArrayList<Long>();
		for(Long lid : u.getMyMaps()) myMapsCopy.add(new Long(lid.intValue()));
		copy.setMyMaps(myMapsCopy);
		
		return copy;
	}
	
	
	
}
