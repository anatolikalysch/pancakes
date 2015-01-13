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
	 * Public singleton access method
	 * 
	 * @methodtype get
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	public static synchronized PancakePhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic PancakePhotoFactory");
			setInstance(new PancakePhotoFactory());
		}
		
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of LocationFactory
	 * @methodtype set
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	protected static synchronized void setInstance(PancakePhotoFactory pancakePhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PancakePhotoFactory twice");
		}
		instance = pancakePhotoFactory;
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty convenience
	 * @pre
	 * @post
	 */
	public PancakePhotoFactory() {
		super();
	}
	
	/**
	 * @methodtype factory
	 * @methodproperty convenience
	 * @pre
	 * @post
	 */
	@Override
	public PancakePhoto createPhoto() {
		return new PancakePhoto();
	}
	
	/**
	 * @methodtype factory
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	@Override
	public PancakePhoto createPhoto(PhotoId id) {
		return new PancakePhoto(id);
	}
	
	/**
	 * @methodtype factory
	 * @methodproperty primitive
	 * @pre rset should not be null
	 * @post 
	 */
	@Override
	public PancakePhoto createPhoto(ResultSet rset) throws SQLException {
		if (rset == null)
			throw new SQLException("Cannot create new Photo from empty ResultSet!");
		else
			return new PancakePhoto(rset);
	}
}
