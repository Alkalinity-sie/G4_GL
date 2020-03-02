package com.groupe4.server_bouchon3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.groupe4.server_bouchon3.Maps;
import com.groupe4.server_bouchon3.User;
import com.groupe4.server_bouchon3.database.Database;



public class UserService {

	private Map<String, User> user = Database.getUsers();
	
	public UserService() {
		User u1 = new User();
		u1.setUsername("Fayas");
		u1.setId(0);
		user.put(u1.getUsername(), u1);
		User u2 = new User();
		u2.setUsername("Tamime");
		u2.setId(1);
		user.put(u2.getUsername(), u2);
	}
	public List<User> getAllUser(){
		return new ArrayList<>(user.values());
	}
	
	public User getUser(String UserName) {
		return user.get(UserName);
	}
	
	public void addUser(User u) {
		user.put(u.getUsername(), u);
	}
	
	public void updateUser(User u) {
		user.put(u.getUsername(),u);
	}
	public void removeUser(String UserName) {
		user.remove(UserName);
	}
	
	public User addMap(User u,Maps m) {
		u.addMap(m);
		return u;
	}
	
	public List<Maps> getAllMaps(User u){
		return user.get(u.getUsername()).getMyMaps();
	}
	public Maps getMap(User u, int MapID){
		for(Maps m : u.getMyMaps()) {
			if(m.getId() == MapID) {
				return m;
			}
		}
		return null;
	}
	
	public boolean isPublic(Maps m) {
		return m.isIsPublic();
	}
	
}
