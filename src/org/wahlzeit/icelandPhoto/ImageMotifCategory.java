package org.wahlzeit.icelandPhoto;

public enum ImageMotifCategory{

	 WaFa("WaFa", "Waterfall", 0), 
	 Plt("Plt", "Plant", 1), 
	 Vul("Vul", "Vulcano", 2),
	 Glc("Glc", "Glacier", 3), 
	 Anl("Anl", "Animal", 4), 
	 Oth("Oth", "Other", 5); 
	 
	 private static final String[] valueNames = {
		 "Waterfall", "Plant",
		 "Vulcano", "Glacier", "Animal", "Other"	
	 };
	 
	 protected final String shortName; 
	 public final String fullName; 
	 protected final int categoryNumber; 
	 private ImageMotifCategory(String myShortName, 
	String myFullName, int myCategoryNumber 
	) { 
		 shortName = myShortName; 
		 fullName = myFullName; 
		 categoryNumber = myCategoryNumber; 
	} 

	 
	 public static ImageMotifCategory getFromString(String myString) throws IllegalArgumentException {
		 for (ImageMotifCategory name: ImageMotifCategory.values()) {
			 if (valueNames[name.categoryNumber].equals(myString)) {
				 return name;
			 }
		 }
		 throw new IllegalArgumentException("invalid ImageMotifCategory string: " + myString);
	 }

	 public String[] getAllMotifCategories() {
		 return valueNames;
	 }
}
