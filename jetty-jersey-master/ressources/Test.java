package ressources;

import java.util.ArrayList;
import java.util.List;


public class Test {

	
	public static void main(String[] args) {
		List<Long> L = new ArrayList<Long>();
		L.add(new Long(1));
		if(L.contains(new Long(1))) {
			System.out.println("oui");
		} else {
			System.out.println("non");
		}

		L.remove(new Long(2));
	}

}
