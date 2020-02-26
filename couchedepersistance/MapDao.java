package couchedepersistance;

import java.util.ArrayList;
import java.util.List;

public interface MapDao {
    //récupération de toutes les maps existantes tous utilisateurs confondus
    default List<Map> getMaps (){
        List<Map> maps = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            maps.add(new Map());
        }
        return maps;
    }
    //récupération du nom d'une map
    default String getName (int map_id){
        return "random map name";
    }
    //récupération de la description d'une map
    default String getDescription (int map_id){
        return "random map description";
    }
    //récupération du statut d'une map. Renvoie True si elle est publique
    default boolean isPublic (int map_id){
        return true;
    }
    //récupération de la liste des lieux d'une map
    default List<Location> getLocations (int map_id){
        List<Location> locations = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            locations.add(new Location());
        }
        return locations;
    }
    //récupération de la liste des utilisateurs auxquels cette carte a été partagée
    default List<User> getSharedWith (int map_id){
        List<User> sharedWith = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            sharedWith.add(new User());
        }
        return sharedWith;
    }
}
