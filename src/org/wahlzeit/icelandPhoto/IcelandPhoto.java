package org.wahlzeit.icelandPhoto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

//This class is part of the IcelandPhoto / ImageMotif Collaboration (IcelandPhoto has the field imageMotif)
//This class is part of the Factory Collaboration (IcelandPhoto creation is controlled by IcelandPhotoFactory)
//This class is part of the Serializer Collaboration (because it extends Photo which extends DataObject)

public class IcelandPhoto extends Photo {

	protected ImageMotif imageMotif;


	// Factory Collaboration

	public IcelandPhoto() {
		super();
	}


	public IcelandPhoto(PhotoId myId) {
		super(myId);
	}


	public IcelandPhoto(ResultSet rset) throws SQLException {
		super(rset);
	}

	// Serializer Collaboration and IcelandPhoto / ImageMotif Collaboration

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

	// ImageMotif Collaboration

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
