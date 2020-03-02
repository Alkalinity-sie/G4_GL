package com.groupe4.server_bouchon3;

import java.util.ArrayList;
import java.util.List;

public interface MapDao {
    //retrival of the lsit of all existing maps (all users included)
    default List<Maps> getMaps (){
        List<Maps> maps = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            maps.add(new Maps());
        }
        return maps;
    }
    //retrieval of a map's name
    default String getName (int map_id){
        return "random map name";
    }
    //retrieval of a map's description
    default String getDescription (int map_id){
        return "random map description";
    }
    //retrieval of a map's status. Return true if it's public
    default boolean isPublic (int map_id){
        return true;
    }
    //retrieval of map's list of locations
    default List<Location> getLocations (int map_id){
        List<Location> locations = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            locations.add(new Location());
        }
        return locations;
    }
    //retrieval of the list of users to whom this map has been shared
    default List<User> getSharedWith (int map_id){
        List<User> sharedWith = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            sharedWith.add(new User());
        }
        return sharedWith;
    }
}
