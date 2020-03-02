package com.groupe4.server_bouchon3;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public interface LocationDao {
    //retrieval of the list of all existing locations (all maps included)
    default List<Location> getLocations (){
        List<Location> locations = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            locations.add(new Location());
        }
        return locations;
    }
    //retrieval of a location's name
    default String getName (int location_id){
        return "random name";
    }
    //retrieval of a location's description
    default String getDescription (int location_id){
        return "random description";
    }
    //retrieval de a location's address
    default String getAddress (int location_id){
        return "random address";
    }
    //retrieval of a location's list of labels
    default List<String> getLabels (int location_id){
        List<String> labels = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            labels.add("label "+i);
        }
        return labels;
    }
    //retrieval of a location's list of photos
    default List<ImageIcon> getPhotos (int location_id){
        List<ImageIcon> photos = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            photos.add(new ImageIcon());
        }
        return photos;
    }
}
