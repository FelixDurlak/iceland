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
import java.io.*;

import org.wahlzeit.location.*;
import org.wahlzeit.model.*;
import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;
import org.wahlzeit.webparts.*;

import de.durlak.GpsException;
import de.durlak.GpsLocation;
import de.durlak.IcelandPhoto;
import de.durlak.ImageMotif;
import de.durlak.ImageMotifCategory;
import de.durlak.Location;
import de.durlak.MapcodeLocation;

/**
 * 
 * @author dirkriehle
 *
 */
public class UploadPhotoFormHandler extends AbstractWebFormHandler {
	
	/**
	 *
	 */
	public UploadPhotoFormHandler() {
		initialize(PartUtil.UPLOAD_PHOTO_FORM_FILE, AccessRights.USER);
	}
	
	/**
	 * 
	 */
	protected void doMakeWebPart(UserSession us, WebPart part) {
		Map<String, Object> args = us.getSavedArgs();
		part.addStringFromArgs(args, UserSession.MESSAGE);

		part.maskAndAddStringFromArgs(args, Photo.TAGS);
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String tags = us.getAndSaveAsString(args, Photo.TAGS);

		double latitude = 0;
		double longitude = 0;
		boolean noGPS = false;
		String imageMotifCategory = us.getAndSaveAsString(args, ImageMotif.IMAGE_MOTIF_CATEGORY);
		String imageMotifName = us.getAndSaveAsString(args, ImageMotif.IMAGE_MOTIF_NAME);
		

		try
		{
			latitude = Double.parseDouble(us.getAndSaveAsString(args, Photo.LATITUDE));
			longitude = Double.parseDouble(us.getAndSaveAsString(args, Photo.LONGITUDE));
		} catch(Exception e)
		{
			noGPS = true;
		}

		Location location = null;
		String mapcode = us.getAndSaveAsString(args, Photo.MAPCODE);

		if(mapcode != ""  &&  noGPS || (Math.abs(latitude) > 90 && Math.abs(longitude) > 180))
		{
			location = new MapcodeLocation(mapcode);
		} else {
			
			try {
				location = new GpsLocation(latitude, longitude);
			} catch (GpsException e) {
				e.printStackTrace();
			}
		}

		if (!StringUtil.isLegalTagsString(tags)) {
			us.setMessage(us.cfg().getInputIsInvalid());
			return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
		}

		try {
			PhotoManager pm = PhotoManager.getInstance();
			String sourceFileName = us.getAsString(args, "fileName");
			File file = new File(sourceFileName);
			Photo photo = pm.createPhoto(file);

			String targetFileName = SysConfig.getBackupDir().asString() + photo.getId().asString();
			createBackup(sourceFileName, targetFileName);
		
			User user = (User) us.getClient();
			user.addPhoto(photo); 
			
			photo.setLocation(location);
			
			ImageMotif imageMotif = new ImageMotif();
			
			try {
				imageMotif.setMotifCategory(ImageMotifCategory.getFromString(imageMotifCategory));
				imageMotif.setName(imageMotifName);
				IcelandPhoto icelandPhoto = (IcelandPhoto)photo;
				icelandPhoto.setImageMotif(imageMotif);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			photo.setTags(new Tags(tags));

			pm.savePhoto(photo);

			StringBuffer sb = UserLog.createActionEntry("UploadPhoto");
			UserLog.addCreatedObject(sb, "Photo", photo.getId().asString());
			UserLog.log(sb);
			
			us.setTwoLineMessage(us.cfg().getPhotoUploadSucceeded(), us.cfg().getKeepGoing());
		} catch (Exception ex) {
			SysLog.logThrowable(ex);
			us.setMessage(us.cfg().getPhotoUploadFailed());
		}
		
		return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
	}
	
	/**
	 * 
	 */
	protected void createBackup(String sourceName, String targetName) {
		try {
			File sourceFile = new File(sourceName);
			InputStream inputStream = new FileInputStream(sourceFile);
			File targetFile = new File(targetName);
			OutputStream outputStream = new FileOutputStream(targetFile);
			// @FIXME IO.copy(inputStream, outputStream);
		} catch (Exception ex) {
			SysLog.logSysInfo("could not create backup file of photo");
			SysLog.logThrowable(ex);			
		}
	}
}
