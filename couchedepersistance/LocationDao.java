package couchedepersistance;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public interface LocationDao {
    //récupération de tous les lieux existants toutes cartes confondues
    default List<Location> getLocations (){
        List<Location> locations = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            locations.add(new Location());
        }
        return locations;
    }
    //récupération du nom d'un lieu
    default String getName (int location_id){
        return "random name";
    }
    //récupération de la description d'une map
    default String getDescription (int location_id){
        return "random description";
    }
    //récupération de l'adresse d'un lieu
    default String getAddress (int location_id){
        return "random address";
    }
    //récupération de la liste des lieux d'une map
    default List<String> getLabels (int location_id){
        List<String> labels = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            labels.add("label "+i);
        }
        return labels;
    }
    //récupération de la liste des utilisateurs auxquels cette carte a été partagée
    default List<ImageIcon> getPhotos (int location_id){
        List<ImageIcon> photos = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            photos.add(new ImageIcon());
        }
        return photos;
    }
}
