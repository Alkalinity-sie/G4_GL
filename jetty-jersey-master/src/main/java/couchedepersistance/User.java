package couchedepersistance;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class User {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
    private Long id;

    private String username;
    private String password;
    private List<Long> sharedToMe;
    private List<Long> myMaps;
    
    public User (){
        this.username = "";
        this.password = "";
        this.sharedToMe = new ArrayList<Long>();
        this.myMaps = new ArrayList<Long>();
    }
    
    public User (String username, String password){
        this.username = username;
        this.password = password;
        this.sharedToMe = new ArrayList<Long>();
        this.myMaps = new ArrayList<Long>();
    }
    
    public String toString() {
    	System.out.println(" _________________________________________ ");
    	System.out.println("|@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@|");
    	String a = "	> Long id               : "+id.intValue() +"\n";
    	String b = "	> String username       : "+username      +"\n";
    	String c = "	> String password       : "+password      +"\n";

    	String sh = "";
    	if(this.sharedToMe != null) sh = sharedToMe.toString();
    	else sh =  "	> null";
    	
    	String my = "";
    	if(this.myMaps != null) my = myMaps.toString();
    	else sh =  "	> null";		
    	
    	String d = "	> List<Long> sharedToMe : "+sh +"\n";
    	String e = "	> List<Long> myMaps     : "+my;
    	return a+b+c+d+e;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Long> getSharedToMe() {
        return sharedToMe;
    }

    public void setSharedToMe(List<Long> sharedToMe) {
        this.sharedToMe = sharedToMe;
    }

    public List<Long> getMyMaps() {
        return myMaps;
    }

    public void setMyMaps(List<Long> myMaps) {
        this.myMaps = myMaps;
    }
    
    
}
