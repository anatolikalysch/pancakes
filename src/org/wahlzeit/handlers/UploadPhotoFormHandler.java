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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import org.wahlzeit.extension.location.GPSLocation;
import org.wahlzeit.extension.location.MapcodeLocation;
import org.wahlzeit.extension.model.Pancake;
import org.wahlzeit.extension.model.PancakeManager;
import org.wahlzeit.extension.model.PancakePhoto;
import org.wahlzeit.extension.model.Recipe;
import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.Tags;
import org.wahlzeit.model.User;
import org.wahlzeit.model.UserLog;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.services.SysLog;
import org.wahlzeit.utils.StringUtil;
import org.wahlzeit.webparts.WebPart;

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
		
		StringBuffer buffer = new StringBuffer();
		Collection<Pancake> list = PancakeManager.getInstance().loadPancakes();
		for (Pancake pancake : list) {
			buffer.append("<option ");
			buffer.append("value=\""+pancake.getId()+"\"");
			if(pancake.getId().equals(-1)) 
				buffer.append(" selected");
		
			buffer.append(">");
			buffer.append(pancake.getName());
			buffer.append("</option>");
		}
		
		part.addString("pancake", buffer.toString());
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String tags = us.getAndSaveAsString(args, Photo.TAGS);
		
		// get the POST variables for location data
		String latitude = us.getAndSaveAsString(args, "lat");
		String longitude = us.getAndSaveAsString(args, "long");
		String mapcode = us.getAndSaveAsString(args, "mapcode");
		
		// get the POST variables for domain data
		String newPancake = us.getAndSaveAsString(args, "newPancake");
		String pancakeId = us.getAndSaveAsString(args, "pancakeId");
		String pancakeName = us.getAndSaveAsString(args, "pancakeName");
		String pacakeRecipe = us.getAndSaveAsString(args, "pancakeRecipe");
		
		if (!StringUtil.isLegalTagsString(tags)) {
			us.setMessage(us.cfg().getInputIsInvalid());
			return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
		}
		
		try {
			PhotoManager pm = PhotoManager.getInstance();
			String sourceFileName = us.getAsString(args, "fileName");
			File file = new File(sourceFileName);
			Photo photo = pm.createPhoto(file);
			PancakePhoto pPhoto = null;
			String targetFileName = SysConfig.getBackupDir().asString() + photo.getId().asString();
			createBackup(sourceFileName, targetFileName);
			User user = (User) us.getClient();
			user.addPhoto(photo);
			photo.setTags(new Tags(tags));
			
			
			if(photo instanceof PancakePhoto) {
				pPhoto = (PancakePhoto) photo;
				// add location data to the photo if correct data is given. do nothing if invalid data is given.
				if (!latitude.isEmpty() && !longitude.isEmpty()) {
					try {
						pPhoto.setLocation(new GPSLocation(latitude+","+longitude));
					} catch (AssertionError e2) {
						
					}
				} else 
					if (!mapcode.isEmpty()) {
						try {
							pPhoto.setLocation(new MapcodeLocation(mapcode));
						} catch (AssertionError e3) {
							
						}
					}
				
				// add domain data to the photo if correct data is given and the Photo is a domain Photo. do nothing if invalid data is given.
				PancakeManager panMgr = PancakeManager.getInstance();
				if(newPancake.equals("0")) {
					Pancake pancake = panMgr.getPancakeFromId(Integer.decode(pancakeId));
					pPhoto.setPancake(pancake);
				} else 
					if (newPancake.equals("1")){
					Pancake pancake = panMgr.createPancake();
					pancake.setRecipe(Recipe.getInstance(pacakeRecipe));
					pancake.setName(pancakeName);
					pPhoto.setPancake(pancake);
					panMgr.savePancake(pancake);
				}
			}
			
			if (pPhoto == photo) {
				pm.savePhoto(pPhoto);
				StringBuffer sb = UserLog.createActionEntry("UploadPhoto");
				UserLog.addCreatedObject(sb, "Photo", pPhoto.getId().asString());
				UserLog.log(sb);
				us.setTwoLineMessage(us.cfg().getPhotoUploadSucceeded(), us.cfg().getKeepGoing());
			} else {
				pm.savePhoto(photo);
				StringBuffer sb = UserLog.createActionEntry("UploadPhoto");
				UserLog.addCreatedObject(sb, "Photo", photo.getId().asString());
				UserLog.log(sb);
				us.setTwoLineMessage(us.cfg().getPhotoUploadSucceeded(), us.cfg().getKeepGoing());
			}
			
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
