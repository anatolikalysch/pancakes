package org.wahlzeit.extension.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;

public class PancakeFactory {
	
	/**
	* Hidden singleton instance; needs to be initialized from the outside.
	*/
	private static PancakeFactory instance = null;
	
	/**
	* Public singleton access method.
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
	*/
	protected static synchronized void setInstance(PancakeFactory pancakeFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PancakeFactory twice");
		}
		instance = pancakeFactory;
	}
	
	/**
	* @methodtype constuctor
	*/
	protected PancakeFactory() {
		super();
	}
	
	/**
	 * @methodtype factory method
	 */
	public Pancake createPancake(Integer id) {
		return new Pancake(id);
	}
	
	/**
	 * @methodtype factory method
	 */
	public Pancake createPancake(ResultSet rset) throws SQLException {
		return new Pancake(rset);
	}
	
}
