package com.groupe4.server_bouchon3.database;

import java.util.HashMap;
import java.util.Map;
import com.groupe4.server_bouchon3.User;
import com.groupe4.server_bouchon3.Location;
import com.groupe4.server_bouchon3.Event;
import com.groupe4.server_bouchon3.Maps;

public class Database {
	private static Map<String,User> users = new HashMap<>();
	private static Map<Integer,Location> location = new HashMap<>();
	private static Map<Integer,Event> event = new HashMap<>();
	private static Map<Integer,Maps> map = new HashMap<>();

	public static Map<String,User> getUsers(){
		return users;
	}
	public static Map<Integer, Event> getEvent() {
		return event;
	}
	public static Map<Integer, Location> getLocation() {
		return location;
	}
	public static Map<Integer, Maps> getMap() {
		return map;
	}
	
}
