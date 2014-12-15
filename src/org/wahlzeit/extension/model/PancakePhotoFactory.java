package org.wahlzeit.extension.model;

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
			SysLog.logSysInfo("setting generic PancakePhotoFactory");
			setInstance(new PancakePhotoFactory());
		}
		
		return instance;
	}
	
	/**
	* Method to set the singleton instance of PancakeFactory.
	*/
	protected static synchronized void setInstance(PancakePhotoFactory pancakePhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PancakePhotoFactory twice");
		}
		instance = pancakePhotoFactory;
	}
	
	/**
	 * @methodtype constructor
	 * 
	 */
	public PancakePhotoFactory() {
		super();
	}
	
	/**
	* @methodtype factory method
	*/
	@Override
	public PancakePhoto createPhoto() {
		return new PancakePhoto();
	}
	
	/**
	* @methodtype factory method
	*/
	@Override
	public PancakePhoto createPhoto(PhotoId id) {
		return new PancakePhoto(id);
	}
	
	/**
	* @methodtype factory method
	*/
	@Override
	public PancakePhoto createPhoto(ResultSet rset) throws SQLException {
		return new PancakePhoto(rset);
	}
}
