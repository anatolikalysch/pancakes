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
import java.util.Map;

import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.Tags;
import org.wahlzeit.model.User;
import org.wahlzeit.model.UserLog;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.model.extension.PancakePhoto;
import org.wahlzeit.model.extension.PancakeRecipe;
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
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String tags = us.getAndSaveAsString(args, Photo.TAGS);
		if (!StringUtil.isLegalTagsString(tags)) {
			us.setMessage(us.cfg().getInputIsInvalid());
			return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
		}
		

		try {
			PhotoManager pm = PhotoManager.getInstance();
			String sourceFileName = us.getAsString(args, "fileName");
			File file = new File(sourceFileName);
			PancakePhoto photo = pm.createPhoto(file);

			String targetFileName = SysConfig.getBackupDir().asString() + photo.getId().asString();
			createBackup(sourceFileName, targetFileName);
		
			User user = (User) us.getClient();
			user.addPhoto(photo); 
			
			photo.setTags(new Tags(tags));
			doHandlePancakePhotoLocation(photo, us, args);
			doHandlePancakePhoto(photo, us, args);
			
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
	 * @param photo
	 * @param us
	 * @param args
	 */
	public void doHandlePancakePhotoLocation(PancakePhoto photo, UserSession us, Map args){
		// adap-ws14-hw02:
		double lat;
		double lon;
		boolean isEmpty = true;
		String mapcode;
		// Werte einholen
		try {
			lat = Double.parseDouble(us.getAndSaveAsString(args, Photo.LAT));
			lon = Double.parseDouble(us.getAndSaveAsString(args, Photo.LON));
			isEmpty = false;
		} catch (Exception e) {
			lat = 0;
			lon = 0;
		}
		mapcode = us.getAndSaveAsString(args, Photo.MAPCODE);
		if (isEmpty == false && mapcode.length() > 5)
			isEmpty = true;
		
		//Location uebergeben
		if (isEmpty == false)
			photo.setLocation(lat, lon);
		else
			photo.setLocation(mapcode);
	}	
	
	/**
	 * 
	 */
	public void doHandlePancakePhoto(PancakePhoto photo, UserSession us, Map args){
	
		String ingredient1 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT1);
		String ingredient2 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT2);
		String ingredient3 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT3);
		String ingredient4 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT4);
		String ingredient5 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT5);
			
		photo.setRecipe(ingredient1, ingredient2, ingredient3, ingredient4, ingredient5);
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
