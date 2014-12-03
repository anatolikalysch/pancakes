package org.wahlzeit.handlers.extension;

import java.util.Map;

import org.wahlzeit.handlers.AdminUserPhotoFormHandler;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.PhotoStatus;
import org.wahlzeit.model.Tags;
import org.wahlzeit.model.UserLog;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.model.extension.PancakePhoto;
import org.wahlzeit.model.extension.PancakePhotoManager;
import org.wahlzeit.webparts.WebPart;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

public class AdminUserPancakePhotoFormHandler extends AdminUserPhotoFormHandler {

	public AdminUserPancakePhotoFormHandler() {
		initialize(PancakePartUtil.ADMIN_USER_PANCAKEPHOTO_FORM_FILE, AccessRights.ADMINISTRATOR);
	}
	
	/**
	 * 
	 */
	protected void doMakeWebPart(UserSession us, WebPart part) {
		super.doMakeWebPart(us, part);
		
		Map<String, Object> args = us.getSavedArgs();
		String photoId = us.getAndSaveAsString(args, "photoId");
		Photo photo = PhotoManager.getPhoto(photoId);
		part.addString(Photo.LOCATION, photo.getLocationAsString());
		
		if (photo instanceof PancakePhoto) {
			PancakePhoto pPhoto = (PancakePhoto) photo;
			part.addString(PancakePhoto.INGREDIENT1, pPhoto.getIngredient(1));
			part.addString(PancakePhoto.INGREDIENT2, pPhoto.getIngredient(2));
			part.addString(PancakePhoto.INGREDIENT3, pPhoto.getIngredient(3));
			part.addString(PancakePhoto.INGREDIENT4, pPhoto.getIngredient(4));
			part.addString(PancakePhoto.INGREDIENT5, pPhoto.getIngredient(5));
		}
		
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String id = us.getAndSaveAsString(args, "photoId");
		PancakePhoto photo = (PancakePhoto) PhotoManager.getPhoto(id);
		
		if (photo instanceof PancakePhoto) {
			
			doHandlePancakePhotoLocation(photo, us, args);
			doHandlePancakePhoto(photo, us, args);
		}
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

	private void doHandlePancakePhoto(PancakePhoto photo, UserSession us, Map args) {
		double[] coord = new double[2];
		Point point;
		Mapcode mapcode = null;
		boolean gps = true;
		// Werte einholen
		try {
			coord[0] = Double.parseDouble(us.getAndSaveAsString(args, "lat"));
			coord[1] = Double.parseDouble(us.getAndSaveAsString(args, "lon"));
			gps = true;
		} catch (Exception e) {
			coord[0] = 0;
			coord[1] = 0;
			try {
				point = MapcodeCodec.decode(us.getAndSaveAsString(args, "mapcode"));
				mapcode = MapcodeCodec.encodeToInternational(point.getLatDeg(), 
						point.getLonDeg());
				gps = false;
			} catch (IllegalArgumentException | UnknownMapcodeException ex) {
				e.printStackTrace();
				mapcode = MapcodeCodec.encodeToInternational(0, 0);
				gps = true;
			}
		}
		
		//Location uebergeben
		if (gps)
			photo.setLocation(coord);
		else {
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
