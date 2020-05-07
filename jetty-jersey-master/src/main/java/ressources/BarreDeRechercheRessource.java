package ressources;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import couchedepersistance.Event;
import couchedepersistance.Location;
import couchedepersistance.Map;
import couchedepersistance.User;

@Path("/BarreDeRecherche")
@Produces(MediaType.APPLICATION_JSON)
public class BarreDeRechercheRessource {
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/{Entry}/{UserID}/{STATUS}/getMapResults")
	public List<Map> getMapResults(@PathParam("Entry") String entry, 
			 					   @PathParam("UserID") int user_id, 
			 					   @PathParam("STATUS") boolean STATUS){
		System.out.println("getMapResults appelée!");
		List<Map> tmp = new ArrayList<Map>();
		List<Map> res = new ArrayList<Map>();
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Map.class);
			q.declareParameters("boolean s");
			q.setFilter("status == s");
			tmp = (List<Map>) q.execute(STATUS);
			
			User current_user = pm.getObjectById(User.class, user_id);
			
			if(entry.contentEquals("all")) {
				for(Map m : tmp) {
					//si on possède déjà la map dans ses cartes perso ou partagés, elle ne doit pas apparaitre dans les résultats
					if(current_user.getMyMaps().contains(m.getId()) || current_user.getSharedToMe().contains(m.getId())) {
						continue;
					}
					res.add(Copy.copyMap(m));
				}
			} else {
				// ---- FILTRE ---
				String key_words[] = entry.split(" ");
				for(Map m : tmp) {			
					boolean accepted = false;
					String map_nom_words[] = m.getName().split(" ");
					String map_description_words[] = m.getDescription().split(" ");
					List<String> labels = new ArrayList<>();
					//récupère tous les labels contenues dans la map
					for(Long lid : m.getMyLocations()) {
						Location l = pm.getObjectById(Location.class, lid);
						for(String label : l.getLabels()) {
							labels.add(label);
						}
					}
					for(Long eid : m.getMyEvents()) {
						Event e = pm.getObjectById(Event.class, eid);
						for(String label : e.getLabels()) {
							labels.add(label);
						}		
					}
					//traitements des données
					for(int i = 0; i < key_words.length; i++) {
						for(int j = 0; j < map_nom_words.length; j++) {
							if(key_words[i].contentEquals(map_nom_words[j])) {
								accepted = true;
							}
						}
						for(int j = 0; j < map_description_words.length; j++) {
							if(key_words[i].contentEquals(map_description_words[j])) {
								accepted = true;
							}
						}
						
						for(String label : labels) {
							if(key_words[i].contentEquals(label)) {
								accepted = true;
							}
						}
					}
					if(accepted) {
						res.add(Copy.copyMap(m));
					}
				}
			}
			System.out.println("taille de res : "+res.size());
			tx.commit();
		} finally {
			if (tx.isActive()) tx.rollback();
			pm.close();
			pmf.close();
		}
		
		return res;
	}
	
	
}
