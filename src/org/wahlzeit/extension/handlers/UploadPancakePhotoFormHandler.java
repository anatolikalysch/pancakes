/**
 * 
 */
package org.wahlzeit.extension.handlers;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.wahlzeit.extension.location.GPSLocation;
import org.wahlzeit.extension.location.MapcodeLocation;
import org.wahlzeit.extension.model.Pancake;
import org.wahlzeit.extension.model.PancakeManager;
import org.wahlzeit.extension.model.PancakePhoto;
import org.wahlzeit.extension.model.PancakeType;
import org.wahlzeit.extension.model.Recipe;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.UploadPhotoFormHandler;
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
 * @author qwert
 *
 */
public class UploadPancakePhotoFormHandler extends UploadPhotoFormHandler {
	/**
	 *
	 */
	public UploadPancakePhotoFormHandler() {
		super();
	}
	
	/**
	 * 
	 */
	@Override
	protected void doMakeWebPart(UserSession us, WebPart part) {
		super.doMakeWebPart(us, part);
		StringBuffer buffer = new StringBuffer();
		Collection<Pancake> list = PancakeManager.getInstance().loadPancakes();
		for (Pancake pancake : list) {
			buffer.append("<option ");
			buffer.append("value=\""+pancake.getId()+"\"");
			if(pancake.getId().equals(-1)) 
				buffer.append(" selected");
		
			buffer.append(">");
			buffer.append(pancake.getType().getName());
			buffer.append("</option>");
		}
		
		part.addString("pancake", buffer.toString());
	}
	
	/**
	 * 
	 */
	@Override
	protected String doHandlePost(UserSession us, Map args) {
		String tags = us.getAndSaveAsString(args, Photo.TAGS);
		
		// get the location data
		String latitude = us.getAndSaveAsString(args, "lat");
		String longitude = us.getAndSaveAsString(args, "long");
		String mapcode = us.getAndSaveAsString(args, "mapcode");
		
		// get the domain data
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
			PancakePhoto photo = (PancakePhoto) pm.createPhoto(file);
			String targetFileName = SysConfig.getBackupDir().asString() + photo.getId().asString();
			createBackup(sourceFileName, targetFileName);
			User user = (User) us.getClient();
			user.addPhoto(photo);
			photo.setTags(new Tags(tags));
			
			// add location data to the photo if correct data is given
			if (!latitude.isEmpty() && !longitude.isEmpty()) {
				try {
					photo.setLocation(new GPSLocation(latitude+", "+longitude));
				} catch (AssertionError e2) { //do nothing if invalid data is given
						
				}	
			} else 
				if (!mapcode.isEmpty()) {
					try {
						photo.setLocation(new MapcodeLocation(mapcode));
					} catch (AssertionError e3) {//do nothing if invalid data is given
							
					}
					
				}
				
			// add domain data to the photo if correct data is given and the Photo is a domain Photo
			PancakeManager panMgr = PancakeManager.getInstance();
			if(newPancake.equals("0")) {
				Pancake pancake = panMgr.getPancakeFromId(Integer.decode(pancakeId));
				photo.setPancake(pancake);
			} else 
				if (newPancake.equals("1")){
					Pancake pancake = panMgr.createPancake();
					PancakeType type = new PancakeType(pancakeName, 
							Recipe.getInstance(pacakeRecipe));
					pancake.setType(type);
					photo.setPancake(pancake);
					panMgr.savePancake(pancake);
				}
			
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
}
