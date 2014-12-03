package org.wahlzeit.model.extension;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;

public class PancakePhotoFactory extends PhotoFactory {

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static PancakePhotoFactory instance = null;
	
	/**
	 * Public singleton access method.
	 */
	public static synchronized PancakePhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic PhotoFactory");
			setInstance(new PancakePhotoFactory());
		}		
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PancakePhotoFactory.
	 */
	protected static synchronized void setInstance(PancakePhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PancakePhotoFactory twice");
		}
		
		instance = photoFactory;
	}
	
	/**
	 * 
	 */
	protected PancakePhotoFactory() {
		// nope
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public PancakePhoto createPhoto() {
		return new PancakePhoto();
	}
	
	/**
	 * 
	 */
	@Override
	public PancakePhoto createPhoto(PhotoId id) {
		return new PancakePhoto(id);
	}
	
	/**
	 * 
	 */
	@Override
	public PancakePhoto createPhoto(ResultSet rs) throws SQLException {
		return new PancakePhoto(rs);
	}

}
