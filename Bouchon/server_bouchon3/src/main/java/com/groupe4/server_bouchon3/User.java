package com.groupe4.server_bouchon3;

import java.util.ArrayList;
import java.util.List;



public class User {
    private int id;
    private static int numberOfExistingUser = 0;
    private String username;
    private String password;
    private List<Maps> sharedToMe;
    private List<Maps> myMaps;
    
    
    public User (){
        setNumberOfExistingUser(getNumberOfExistingUser() + 1);
        this.id = getNumberOfExistingUser();
        this.username = "";
        this.password = "";
        this.sharedToMe = new ArrayList<>();
        this.myMaps = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Maps> getSharedToMe() {
        return sharedToMe;
    }

    public void setSharedToMe(List<Maps> sharedToMe) {
        this.sharedToMe = sharedToMe;
    }

    public List<Maps> getMyMaps() {
        return myMaps;
    }

    public void addMap(Maps m) {
    	this.getMyMaps().add(m);
    }
    public void setMyMaps(List<Maps> myMaps) {
        this.myMaps = myMaps;
    }

	public static int getNumberOfExistingUser() {
		return numberOfExistingUser;
	}

	public static void setNumberOfExistingUser(int numberOfExistingUser) {
		User.numberOfExistingUser = numberOfExistingUser;
	}
    
}
