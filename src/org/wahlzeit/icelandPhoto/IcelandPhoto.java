package org.wahlzeit.icelandPhoto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

public class IcelandPhoto extends Photo {

	private ImageMotifCategory motifCategory;
	
	public static final String IMAGE_MOTIF_CATEGORY = "image_motif_category";

	public IcelandPhoto() {
		super();
		motifCategory = new ImageMotifCategory("");
	}


	public IcelandPhoto(PhotoId myId) {
		super(myId);
		motifCategory = new ImageMotifCategory("");
	}


	public IcelandPhoto(ResultSet rset) throws SQLException {
		super(rset);
		motifCategory = new ImageMotifCategory("");
	}

	public ImageMotifCategory getImageMotifCategory() {
		assert(motifCategory != null);
		return motifCategory;
	}

	public void setBeerCategory(ImageMotifCategory motifCategory) {
		assert(motifCategory != null);
		this.motifCategory = motifCategory;
	}


	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		assert(rset != null);
		super.writeOn(rset);
		if(motifCategory != null){
			rset.updateString(IMAGE_MOTIF_CATEGORY, motifCategory.getImageMotifCategoryAsString());
		}		
	}

	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		assert(rset != null);
		super.readFrom(rset);
		if(motifCategory != null){
			motifCategory.setImageMotifCategory(rset.getString(IMAGE_MOTIF_CATEGORY));
		}
		assert(motifCategory.equals(rset.getString(IMAGE_MOTIF_CATEGORY)));
	}

}
