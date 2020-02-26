package couchedepersistance;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private List<Map> sharedToMe;
    private List<Map> myMaps;
    
    public User (){
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

    public List<Map> getSharedToMe() {
        return sharedToMe;
    }

    public void setSharedToMe(List<Map> sharedToMe) {
        this.sharedToMe = sharedToMe;
    }

    public List<Map> getMyMaps() {
        return myMaps;
    }

    public void setMyMaps(List<Map> myMaps) {
        this.myMaps = myMaps;
    }
    
}
