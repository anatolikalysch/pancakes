/**
 * 
 */
package org.wahlzeit.extension.handlers;

import org.wahlzeit.extension.UIInteraction.ExtendedUserSession;
import org.wahlzeit.extension.domain.Pancake;
import org.wahlzeit.extension.domain.PancakePhoto;
import org.wahlzeit.extension.domain.PancakeType;
import org.wahlzeit.extension.location.GPSLocation;
import org.wahlzeit.extension.utils.ExtendedHtmlUtil;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.ShowPhotoPageHandler;
import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.SysLog;
import org.wahlzeit.webparts.WebPart;

/**
 * @author qwert
 *
 */
public class ShowPancakePhotoPageHandler extends ShowPhotoPageHandler {
	/**
	 * 
	 */
	public ShowPancakePhotoPageHandler() {
		initialize(PartUtil.SHOW_PHOTO_PAGE_FILE, AccessRights.GUEST);
	}
		
	/**
	 * @methodtype command
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	@Override
	protected void makePhotoCaption(UserSession us, WebPart page) {
		PancakePhoto photo = (PancakePhoto) us.getPhoto();
		// String photoId = photo.getId().asString();
		ExtendedUserSession eus = new ExtendedUserSession(us);
			
		WebPart caption = createWebPart(us, PartUtil.CAPTION_INFO_FILE);
		caption.addString(Photo.CAPTION, getPhotoCaption(us, photo));
		// pass over the domain data to be shown in the caption
		PancakePhoto temp = (PancakePhoto) photo;
		try {
			caption.addString("pancakeId", temp.getPancake().getIdAsString());
			caption.addString("name", temp.getPancake().getType().getName());
			caption.addString("recipe", temp.getPancake().getType().getRecipe().toString());
			// pass over ingredients as table
			caption.addString("ingredients", ExtendedHtmlUtil.asIngredientTable(temp.getPancake()));
		} catch (IllegalArgumentException e) {
			SysLog.logThrowable(e);
			us.setMessage(eus.cfg().getPancakeIllegalArguments(e.getMessage()));
		} catch (AssertionError e) { 
			SysLog.logThrowable(e);
			us.setMessage(eus.cfg().getPancakePostViolation(e.getMessage()));
		} catch (Exception omega) { //something beyond my control happened
			PancakeType empty = new PancakeType();
			caption.addString("name", empty.getName());
			caption.addString("recipe", empty.getRecipe().toString());
			// pass over ingredients as table
			caption.addString("ingredients", empty.getIng().toString());
		}
		
		try {
			// pass over the location data to be shown in the caption
			caption.addString("location", temp.getLocation().toString());
		} catch (IllegalArgumentException e) {
			SysLog.logThrowable(e);
			us.setMessage(eus.cfg().getLocationIllegalArguments(e.getMessage()));
		} catch (AssertionError e) { 
			SysLog.logThrowable(e);
			us.setMessage(eus.cfg().getPancakePostViolation(e.getMessage()));
		} catch (Exception omega) { //something beyond my control happened
			caption.addString("location", GPSLocation.EMPTY_LOCATION.toString());
		}
		
		// write caption
		page.addWritable(Photo.CAPTION, caption);
	}
}
