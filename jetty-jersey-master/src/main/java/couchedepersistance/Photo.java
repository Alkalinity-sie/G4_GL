package couchedepersistance;

import javax.swing.ImageIcon;

public class Photo {
	
	private static int numberOfExistingPhoto;
	private int id;
	private ImageIcon photo;
	
	public Photo (ImageIcon photo) {
		numberOfExistingPhoto++;
		this.id = numberOfExistingPhoto;
		this.photo = photo;
	}
	
	public int getId() {
		return this.id;
	}
	
	public ImageIcon getPhoto() {
		return this.photo;
	}
	
}
