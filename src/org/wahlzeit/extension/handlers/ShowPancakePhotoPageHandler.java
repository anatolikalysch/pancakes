/**
 * 
 */
package org.wahlzeit.extension.handlers;

import org.wahlzeit.extension.model.PancakePhoto;
import org.wahlzeit.extension.utils.ExtendedHtmlUtil;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.ShowPhotoPageHandler;
import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.UserSession;
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
			
		WebPart caption = createWebPart(us, PartUtil.CAPTION_INFO_FILE);
		caption.addString(Photo.CAPTION, getPhotoCaption(us, photo));
		//TODO tcb
		// pass over the domain data to be shown in the caption
		PancakePhoto temp = (PancakePhoto) photo;
		caption.addString("pancakeId", temp.getPancake().getIdAsString());
		caption.addString("name", temp.getPancake().getType().getName());
		caption.addString("recipe", temp.getPancake().getType().getRecipe().toString());
		// pass over ingredients as table
		caption.addString("ingredients", ExtendedHtmlUtil.asIngredientTable(temp.getPancake()));
		// pass over the location data to be shown in the caption
		caption.addString("location", temp.getLocation().toString());
		
		// write caption
		page.addWritable(Photo.CAPTION, caption);
	}
}
