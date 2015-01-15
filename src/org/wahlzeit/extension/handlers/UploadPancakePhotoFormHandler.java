/**
 * 
 */
package org.wahlzeit.extension.handlers;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.wahlzeit.extension.location.AbstractLocationFactory;
import org.wahlzeit.extension.location.FactoryProducer;
import org.wahlzeit.extension.location.GPSLocation;
import org.wahlzeit.extension.location.Location;
import org.wahlzeit.extension.model.ExtendedUserSession;
import org.wahlzeit.extension.model.Ingredients;
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
	 * @methodtype command
	 * @methodproperty primitive
	 * @pre
	 * @post
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
	 * @methodtype command
	 * @methodproperty composed (primitive in original Wahlzeit)
	 * @pre photo instanceof PancakePhoto
	 * @post
	 */
	@Override
	protected String doHandlePost(UserSession us, Map args) {
		ExtendedUserSession eus = new ExtendedUserSession(us);
		
		String tags = us.getAndSaveAsString(args, Photo.TAGS);
		
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
			doHandleLocationData(photo, eus, args);
			
				
			// add domain data to the photo if correct data is given and the Photo is a domain Photo
			doHandleDomainData(photo, eus, args);
			
			
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
	 * @methodtype command
	 * @methodproperty primitive
	 * @pre photo instanceof PancakePhoto
	 * @post
	 */
	private void doHandleLocationData(PancakePhoto photo, ExtendedUserSession eus, Map args){
		if (photo instanceof PancakePhoto) {
			FactoryProducer fp = new FactoryProducer();
			AbstractLocationFactory af;
			Location temp;
			// get the location data
			String latitude = eus.getAndSaveAsString(args, "lat");
			String longitude = eus.getAndSaveAsString(args, "long");
			String mapcode = eus.getAndSaveAsString(args, "mapcode");
			
			if (StringUtil.isNullOrEmptyString(mapcode)) {
				try {
					af = fp.getFactory("GPS");
					temp = af.createLocation(latitude+", "+longitude);
					photo.setLocation(temp);
				} catch (AssertionError | IllegalStateException e2) { 
					photo.setLocation(GPSLocation.EMPTY_LOCATION);
				} catch (IllegalArgumentException e) {
					SysLog.logThrowable(e);
					eus.setMessage((eus.cfg()).getLocationIllegalArguments(e.getMessage()));
				}
			} else 
				if (!mapcode.isEmpty()) {
					try {
						af = fp.getFactory("Mapcode");
						temp = af.createLocation(mapcode);
						photo.setLocation(temp);
					} catch (AssertionError e3) {
						photo.setLocation(GPSLocation.EMPTY_LOCATION);
					}
					
				}
		}
	}
	
	/**
	 * @methodtype command
	 * @methodproperty primitive
	 * @pre photo instanceof PancakePhoto
	 * @post pancake created
	 */
	private void doHandleDomainData(PancakePhoto photo, UserSession eus, Map args) {
		if (photo instanceof PancakePhoto) {
			PancakeManager panMgr = PancakeManager.getInstance();
			String newPancake = eus.getAndSaveAsString(args, "newPancake");
			Pancake pancake = null;
			
			if(newPancake.equals("0")) {
				String pancakeId = eus.getAndSaveAsString(args, "pancakeId");
				pancake = panMgr.getPancakeFromId(Integer.decode(pancakeId));
				photo.setPancake(pancake);
			} else 
				if (newPancake.equals("1")){
					String pancakeName = eus.getAndSaveAsString(args, "pancakeName");
					String pancakeIngredients = eus.getAndSaveAsString(args, "pancakeIngredients");
					String pacakeRecipe = eus.getAndSaveAsString(args, "pancakeRecipe");
					
					try {
						pancake = panMgr.createPancake();
						PancakeType type = new PancakeType(pancakeName, 
								Ingredients.getInstance(pancakeIngredients),
								Recipe.getInstance(pacakeRecipe));
						pancake.setType(type);
						photo.setPancake(pancake);
						panMgr.savePancake(pancake);
					} catch (Exception e) {
						SysLog.logThrowable(e);
						eus.setMessage(eus.cfg().getPhotoUploadFailed());
					}
					
				}
			assert(pancake!=null);
		}
	}
}


