package com.groupe4.server_bouchon3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.groupe4.server_bouchon3.Maps;
import com.groupe4.server_bouchon3.database.Database;

public class MapService {
	
	private Map<Integer, Maps> maps = Database.getMap();
	
	
	public List<Maps> getAllMaps(){
		return new ArrayList<>(maps.values());
	}
	
	public Maps getMap(int id) {
		return maps.get(id);
	}
	
	public void addMap(Maps m) {
		maps.put(maps.size()+1,m);
	}
	public void removeMap(int id) {
		maps.remove(id);
	}
	
	public Maps updateMap(Maps m) {
		maps.put(m.getId(),m);
		return m;
	}
	
	public String getMapname(Maps m) {
		return m.getName();
	}
	
	public String getMapDescription(Maps m) {
		return m.getDescription();
	}
	

}
