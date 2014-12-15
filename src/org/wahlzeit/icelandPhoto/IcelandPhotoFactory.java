package org.wahlzeit.icelandPhoto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;

public class IcelandPhotoFactory extends PhotoFactory {
	

	private static IcelandPhotoFactory instance = null;

	/**
	 * Public singleton access method.
	 */
	public static synchronized IcelandPhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic IcelandPhotoFactory");
			setInstance(new IcelandPhotoFactory());
		}
		
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(IcelandPhotoFactory icelandPhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize IcelandPhotoFactory twice");
		}
		
		instance = icelandPhotoFactory;
	}


	protected IcelandPhotoFactory() {
		super();
	}


	/**
	 * @methodtype factory
	 */
	public Photo createPhoto() {
		return new IcelandPhoto();
	}
	
	/**
	 * 
	 */
	public Photo createPhoto(PhotoId id) {
		return new IcelandPhoto(id);
	}
	
	/**
	 * 
	 */
	public Photo createPhoto(ResultSet rs) throws SQLException {
		return new IcelandPhoto(rs);
	}

	/**
	 *
	 */

}
