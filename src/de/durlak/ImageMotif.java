package de.durlak;

import java.util.Objects;

//This class is part of the Type Object Collaboration (imageMotif has the field imageMotifCategory)
//This class is part of the IcelandPhoto / ImageMotif Collaboration (IcelandPhoto has the field imageMotif)

public class ImageMotif{
	
	public static final String IMAGE_MOTIF_CATEGORY = "image_motif_category";
	public static final String IMAGE_MOTIF_NAME = "image_motif_name";

	// default values
	private ImageMotifCategory motifCategory = ImageMotifCategory.Oth;
	private String name = "unnamed";
	
	//Type Object Collaboration

	public String toString() {
		return motifCategory.fullName + " (name: " + name + ")";
	}

	public boolean equals(ImageMotif imageMotif) {
		if (imageMotif == null) return false;
		if (imageMotif == this) return true;
		return Objects.equals(name, imageMotif.name)
				&& Objects.equals(motifCategory, imageMotif.motifCategory);
	}

	// IcelandPhoto / ImageMotif Collaboration
	
	/**
	 * @return the motifCategory
	 */
	public ImageMotifCategory getMotifCategory() {
		return motifCategory;
	}

	/**
	 * @param motifCategory the motifCategory to set
	 */
	public void setMotifCategory(ImageMotifCategory motifCategory) {
		this.motifCategory = motifCategory;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



}
