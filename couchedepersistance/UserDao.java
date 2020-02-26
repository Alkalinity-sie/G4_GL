package couchedepersistance;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    //récupération des utilisateurs
    default List<User> getUsers (){
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            users.add(new User());
        }
        return users;
    }
    //récupération de l'id d'un utilisateur à partir de son nom (pseudo). On suppose qu'un nom est unique tout comme un id.
    default int getUserId (String username){
        int id = 0;
        for(int i = 0; i < username.length(); i++){
            id += username.charAt(i);
        }
        return id;
    }
    //récupération du nom d'un utilisateur
    default String getUsername (int user_id){
        return "random username";
    }
    //récupération du mot de passe d'un utilisateur
    default String getPassword (int user_id){
        return "random password";
    }
    //récupération des maps qu'a créé un certain utilisateur
    default List<Map> getPersonnelMaps (int user_id){
        List<Map> personnelMaps = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            personnelMaps.add(new Map());
        }
        return personnelMaps;
    }
    //récupération des maps partagés à un utilisateur
    default List<Map> getMapsSharedToHim (int user_id){
        List<Map> sharedToHim = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            sharedToHim.add(new Map());
        }
        return sharedToHim;
    }
}
