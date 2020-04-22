package couchedepersistance;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.swing.ImageIcon;

@PersistenceCapable
public class Photo {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	private Long id;

	@Persistent(defaultFetchGroup="true")
	private ImageIcon photo;
	
	public Photo () {
	}
	
	public Photo (ImageIcon photo) {
		this.photo = photo;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public ImageIcon getPhoto() {
		return this.photo;
	}
	
	public void setPhoto(ImageIcon photo) {
		this.photo = photo;
	}
}
