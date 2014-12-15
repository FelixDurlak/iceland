package org.wahlzeit.icelandPhoto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

public class IcelandPhoto extends Photo {
	
	protected ImageMotif imageMotif;

	public IcelandPhoto() {
		super();
	}


	public IcelandPhoto(PhotoId myId) {
		super(myId);
	}


	public IcelandPhoto(ResultSet rset) throws SQLException {
		super(rset);
	}


	@Override
	public void writeOn(ResultSet rset) throws SQLException {		
		
		// precondition
		assert(rset != null);
		
		super.writeOn(rset);
		if(imageMotif != null){
			rset.updateString(ImageMotif.IMAGE_MOTIF_CATEGORY, imageMotif.getMotifCategory().fullName);
			rset.updateString(ImageMotif.IMAGE_MOTIF_NAME, imageMotif.getName());
		}		
	}

	@Override
	public void readFrom(ResultSet rset) throws SQLException {
	
		// precondition
		assert(rset != null);
		
		super.readFrom(rset);
		if(imageMotif != null){
			imageMotif.setMotifCategory(ImageMotifCategory.getFromString(rset.getString(ImageMotif.IMAGE_MOTIF_CATEGORY)));
			imageMotif.setName(ImageMotif.IMAGE_MOTIF_NAME);
		}
		// postcondition
		assert(imageMotif.getMotifCategory().fullName.equals(rset.getString(ImageMotif.IMAGE_MOTIF_CATEGORY)));
	}


	/**
	 * @return the imageMotif
	 */
	public ImageMotif getImageMotif() {
		// precondition
		assert(imageMotif != null);
		
		return imageMotif;
	}


	/**
	 * @param imageMotif the imageMotif to set
	 */
	public void setImageMotif(ImageMotif imageMotif) {
		// precondition
		assert(imageMotif != null);
		
		this.imageMotif = imageMotif;
	}

}
