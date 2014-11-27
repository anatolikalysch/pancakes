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

import java.util.Map;

import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.PhotoStatus;
import org.wahlzeit.model.Tags;
import org.wahlzeit.model.UserLog;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.model.extension.PancakePhoto;
import org.wahlzeit.webparts.WebPart;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

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

		PancakePhoto photo = (PancakePhoto) PhotoManager.getPhoto(photoId);
		part.addString(Photo.THUMB, getPhotoThumb(us, photo));

		part.addString("photoId", photoId);
		part.addString(Photo.ID, photo.getId().asString());
		part.addSelect(Photo.STATUS, PhotoStatus.class, (String) args.get(Photo.STATUS));
		part.addString(PancakePhoto.RECIPE, photo.getRecipeAsString());
		part.maskAndAddStringFromArgsWithDefault(args, Photo.TAGS, photo.getTags().asString());
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String id = us.getAndSaveAsString(args, "photoId");
		PancakePhoto photo = (PancakePhoto) PhotoManager.getPhoto(id);
	
		String tags = us.getAndSaveAsString(args, Photo.TAGS);
		photo.setTags(new Tags(tags));
		String status = us.getAndSaveAsString(args, Photo.STATUS);
		photo.setStatus(PhotoStatus.getFromString(status));
		
		doHandlePancakePhotoLocation(photo, us, args);
		doHandlePancakePhoto(photo, us, args);
		
		PhotoManager pm = PhotoManager.getInstance();
		pm.savePhoto(photo);
		
		StringBuffer sb = UserLog.createActionEntry("AdminUserPhoto");
		UserLog.addUpdatedObject(sb, "Photo", photo.getId().asString());
		UserLog.log(sb);
		
		us.setMessage(us.cfg().getPhotoUpdateSucceeded());

		return PartUtil.SHOW_ADMIN_PAGE_NAME;
	}

	private void doHandlePancakePhoto(PancakePhoto photo, UserSession us, Map args) {
		double[] gps = new double[2];
		boolean isEmpty = true;
		Point point = null;
		Mapcode mapcode;
		// Werte einholen
		try {
			gps[0] = Double.parseDouble(us.getAndSaveAsString(args, Photo.LAT));
			gps[1] = Double.parseDouble(us.getAndSaveAsString(args, Photo.LON));
			isEmpty = false;
		} catch (Exception e) {
			gps[0] = 0;
			gps[1] = 0;
		}
		
		//Location uebergeben
		if (isEmpty == false)
			photo.setLocation(gps);
		else {
			try {
				point = MapcodeCodec.decode(us.getAndSaveAsString(args, Photo.MAPCODE));
				mapcode = MapcodeCodec.encodeToInternational(point.getLatDeg(), 
						point.getLonDeg());
			} catch (IllegalArgumentException | UnknownMapcodeException e) {
				e.printStackTrace();
				mapcode = MapcodeCodec.encodeToInternational(0, 0);
			}
			
			photo.setLocation(mapcode);
		}
		
	}

	private void doHandlePancakePhotoLocation(PancakePhoto photo, UserSession us,
			Map args) {

		String ingredient1 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT1);
		String ingredient2 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT2);
		String ingredient3 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT3);
		String ingredient4 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT4);
		String ingredient5 = us.getAndSaveAsString(args, PancakePhoto.INGREDIENT5);
			
		photo.setRecipe(ingredient1, ingredient2, ingredient3, ingredient4, ingredient5);
	}
}
