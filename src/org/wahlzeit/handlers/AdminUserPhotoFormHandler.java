/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.handlers;

import java.util.*;

import org.wahlzeit.icelandPhoto.IcelandPhoto;
import org.wahlzeit.location.GpsLocation;
import org.wahlzeit.location.Location;
import org.wahlzeit.model.*;
import org.wahlzeit.webparts.*;

/**
 * 
 * @author dirkriehle
 *
 */
public class AdminUserPhotoFormHandler extends AbstractWebFormHandler {

	/**
	 *
	 */
	public AdminUserPhotoFormHandler() {
		initialize(PartUtil.ADMIN_USER_PHOTO_FORM_FILE, AccessRights.ADMINISTRATOR);
	}
	
	/**
	 * 
	 */
	protected void doMakeWebPart(UserSession us, WebPart part) {
		Map<String, Object> args = us.getSavedArgs();

		String photoId = us.getAndSaveAsString(args, "photoId");

		Photo photo = PhotoManager.getPhoto(photoId);
		part.addString(Photo.THUMB, getPhotoThumb(us, photo));

		part.addString("photoId", photoId);
		part.addString(Photo.ID, photo.getId().asString());
		part.addSelect(Photo.STATUS, PhotoStatus.class, (String) args.get(Photo.STATUS));
		part.maskAndAddStringFromArgsWithDefault(args, Photo.TAGS, photo.getTags().asString());
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String id = us.getAndSaveAsString(args, "photoId");
		Photo photo = PhotoManager.getPhoto(id);
		
		double latitude;
		double longitude;
		String imageMotivCategory = us.getAndSaveAsString(args, Photo.IMAGE_MOTIV_CATEGORY);
		
		try
		{
			latitude = Double.parseDouble(us.getAndSaveAsString(args, Photo.LATITUDE));
			longitude = Double.parseDouble(us.getAndSaveAsString(args, Photo.LONGITUDE));
		} catch(Exception e)
		{
			latitude = 0.0;
			longitude = 0.0;
		}

		String mapcode = us.getAndSaveAsString(args, Photo.MAPCODE);
		Location location = new GpsLocation(latitude, longitude);

		if(mapcode != ""  &&  (latitude == 0.0 && longitude == 0.0) || (Math.abs(latitude) > 90 && Math.abs(longitude) > 180))
		{
			location.readInMapcode(mapcode);
		}
		
		photo.setLocation(location);
		
		((IcelandPhoto) photo).getImageMotifCategory().setImageMotifCategory(imageMotivCategory);
	
		String tags = us.getAndSaveAsString(args, Photo.TAGS);
		photo.setTags(new Tags(tags));
		String status = us.getAndSaveAsString(args, Photo.STATUS);
		photo.setStatus(PhotoStatus.getFromString(status));

		PhotoManager pm = PhotoManager.getInstance();
		pm.savePhoto(photo);
		
		StringBuffer sb = UserLog.createActionEntry("AdminUserPhoto");
		UserLog.addUpdatedObject(sb, "Photo", photo.getId().asString());
		UserLog.log(sb);
		
		us.setMessage(us.cfg().getPhotoUpdateSucceeded());

		return PartUtil.SHOW_ADMIN_PAGE_NAME;
	}
	
}
