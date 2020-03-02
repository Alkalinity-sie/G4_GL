package com.groupe4.server_bouchon3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.groupe4.server_bouchon3.Location;
import com.groupe4.server_bouchon3.database.Database;

public class LocationService {
	
	private Map<Integer, Location> location = Database.getLocation();
	
	public List<Location> getAllLocation(){
		return new ArrayList<>(location.values());
	}
	
	public Location getLocation(int id) {
		return location.get(id);
	}
	
	public void addLocation(Location m) {
		location.put(location.size()+1,m);
	}
	public void removeLocation(int id) {
		location.remove(id);
	}
	
	public Location updateLocation(Location m) {
		location.put(m.getId(),m);
		return m;
	}

}
