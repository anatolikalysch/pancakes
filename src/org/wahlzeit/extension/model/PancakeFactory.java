package org.wahlzeit.extension.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;

/**
 * This class is part of the PancakeFactory collaboration.
 * 
 * @author qwert
 *
 */
public class PancakeFactory {
	
	/**
	* Hidden singleton instance; needs to be initialized from the outside.
	*/
	private static PancakeFactory instance = null;
	

	/**
	 * Public singleton access method.
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public static synchronized PancakeFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic PancakeFactory");
			setInstance(new PancakeFactory());
		}
		
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PancakeFactory.
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	protected static synchronized void setInstance(PancakeFactory pancakeFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PancakeFactory twice");
		}
		instance = pancakeFactory;
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre
	 * @post
	 */
	protected PancakeFactory() {
		//nope
	}
	
	/**
	 * @methodtype factory
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Pancake createPancake(Integer id) {
		return new Pancake(id);
	}
	
	/**
	 * @methodtype factory
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Pancake createPancake(ResultSet rset) throws SQLException {
		return new Pancake(rset);
	}
	
}
