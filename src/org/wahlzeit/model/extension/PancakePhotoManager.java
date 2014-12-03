package org.wahlzeit.model.extension;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.model.PhotoFilter;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoManager;

public class PancakePhotoManager extends PhotoManager {
	
	/**
	 * 
	 */
	protected static final PancakePhotoManager instance = new PancakePhotoManager();

	/**
	 * In-memory cache for photos
	 */
	protected Map<PhotoId, PancakePhoto> pPhotoCache = new HashMap<PhotoId, PancakePhoto>();
	
	/**
	 * 
	 */
	public static final PancakePhoto getPancakePhoto(String id) {
		return getPancakePhoto(PhotoId.getIdFromString(id));
	}
	
	/**
	 * 
	 */
	public static final PancakePhoto getPancakePhoto(PhotoId id) {
		return instance.getPhotoFromId(id);
	}
	
	/**
	 * 
	 */
	public PancakePhotoManager() {
		photoTagCollector = PancakePhotoFactory.getInstance().createPhotoTagCollector();
	}
	
	/**
	 * 
	 */
	@Override
	public PancakePhoto getPhotoFromId(PhotoId id) {
		PancakePhoto photo = (PancakePhoto) super.getPhotoFromId(id);
		return photo;
	}
		
	/**
	 * @methodtype get
	 * @methodproperties primitive
	 */
	@Override
	protected PancakePhoto doGetPhotoFromId(PhotoId id) {
		return pPhotoCache.get(id);
	}
	
	/**
	 * 
	 */
	@Override
	protected PancakePhoto createObject(ResultSet rset) throws SQLException {
		return PancakePhotoFactory.getInstance().createPhoto(rset);
	}
	
	/**
	 * 
	 */
	public void addPhoto(PancakePhoto photo) {
		super.addPhoto(photo);
	}
	
	/**
	 * @methodtype command
	 * @methodproperties primitive
	 */
	protected void doAddPhoto(PancakePhoto myPhoto) {
		pPhotoCache.put(myPhoto.getId(), myPhoto);
	}
	
	/**
	 * 
	 */
	@Override
	public PancakePhoto getVisiblePhoto(PhotoFilter filter) {
		PancakePhoto photo = (PancakePhoto) super.getPhotoFromFilter(filter);

		return photo;
	}
	
	/**
	 * 
	 */
	@Override
	protected PancakePhoto getPhotoFromFilter(PhotoFilter filter) {
		PancakePhoto photo = (PancakePhoto) super.getPhotoFromFilter(filter);
		return photo;
	}
		
	/**
	 * 
	 */
	@Override
	public PancakePhoto createPhoto(File file) throws Exception {
		PancakePhoto photo = (PancakePhoto) super.createPhoto(file);
		return photo;
	}

}
