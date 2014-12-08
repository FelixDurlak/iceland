package org.wahlzeit.icelandPhoto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

public class IcelandPhoto extends Photo {
	
	public static final String IMAGE_MOTIF_CATEGORY = "image_motif_category";
	
	protected ImageMotifCategory motifCategory = ImageMotifCategory.Oth;

	public IcelandPhoto() {
		super();
	}


	public IcelandPhoto(PhotoId myId) {
		super(myId);
	}


	public IcelandPhoto(ResultSet rset) throws SQLException {
		super(rset);
	}

	public ImageMotifCategory getImageMotifCategory() {
		
		// precondition
		assert(motifCategory != null);
		
		return motifCategory;
	}

	public void setImageMotifCategory(ImageMotifCategory motifCategory) {
		
		// precondition
		assert(motifCategory != null);
		
		this.motifCategory = motifCategory;
	}

	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		
		// precondition
		assert(rset != null);
		
		super.writeOn(rset);
		if(motifCategory != null){
			rset.updateString(IMAGE_MOTIF_CATEGORY, motifCategory.fullName);
		}		
	}

	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		
		// precondition
		assert(rset != null);
		
		super.readFrom(rset);
		if(motifCategory != null){
			motifCategory = ImageMotifCategory.getFromString(rset.getString(IMAGE_MOTIF_CATEGORY));
		}
		// postcondition
		assert(motifCategory.fullName.equals(rset.getString(IMAGE_MOTIF_CATEGORY)));
	}

}
