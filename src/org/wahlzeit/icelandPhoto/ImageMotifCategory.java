package org.wahlzeit.icelandPhoto;

public class ImageMotifCategory {

	private String category;
	

	public ImageMotifCategory(String category) {
		assert(category != null);
		this.category = category;
		assert(this.category == category);
	}

	public void setImageMotifCategory(String category){
		assert(category != null);
		this.category= category;
		assert(this.category == category);
	}

	public String getImageMotifCategoryAsString(){
		assert(category != null);
		return category;
	}

	public boolean hasImageMotifCategory(String newCategory) {
		assert(category != null);
		assert(newCategory != null);
		if(newCategory.equals(category))
			return true;
		return false;
	}

}
