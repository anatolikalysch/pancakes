package org.wahlzeit.extension.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;

public class PancakeFactory extends PhotoFactory {
	
	/**
	* Hidden singleton instance; needs to be initialized from the outside.
	*/
	private static PancakeFactory instance = null;
	
	/**
	* Public singleton access method.
	*/
	public static synchronized PancakeFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic PhotoFactory");
			setInstance(new PancakeFactory());
		}
		
		return instance;
	}
	
	/**
	* Method to set the singleton instance of PhotoFactory.
	*/
	protected static synchronized void setInstance(PancakeFactory pancakeFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PancakeFactory twice");
		}
		instance = pancakeFactory;
	}
	
	/**
	*
	*/
	protected PancakeFactory() {
		super();
	}
	
	public Pancake createPancake(Integer id) {
		return new Pancake(id);
	}
	
	public Pancake createPancake(ResultSet rset) throws SQLException {
		return new Pancake(rset);
	}
	
	/**
	* @methodtype factory
	*/
	@Override
	public Photo createPhoto() {
		return new PancakePhoto();
	}
	
	/**
	*
	*/
	@Override
	public Photo createPhoto(PhotoId id) {
		return new PancakePhoto(id);
	}
	
	/**
	*
	*/
	@Override
	public Photo createPhoto(ResultSet rset) throws SQLException {
		return new PancakePhoto(rset);
	}
}
