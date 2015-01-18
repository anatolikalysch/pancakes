package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;

import com.mapcode.MapcodeCodec;
import com.mapcode.UnknownMapcodeException;

/**
 * This class is part of the AbstractFactory collaboration.
 * @author qwert
 *
 */
public class MapcodeLocationFactory extends AbstractLocationFactory {
	/**
	* Hidden singleton instance; needs to be initialized from the outside.
	*/
	private static MapcodeLocationFactory instance = null;
	
	/**
	 * Public singleton access method
	 * 
	 * @methodtype get
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	public static synchronized MapcodeLocationFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic MapcodeLocationFactory");
			setInstance(new MapcodeLocationFactory());
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
	protected static synchronized void setInstance(MapcodeLocationFactory mapcodeLocationFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize MapcodeLocationFactory twice");
		}
		instance = mapcodeLocationFactory;
	}
	
	/**
	 * @methodtype factory
	 * @methodproperty hook
	 * @pre location should be valid String
	 * @post location should be valid location
	 */
	@Override
	protected AbstractLocation doCreateLocation(String location) {
		try {
			MapcodeLocation result = new MapcodeLocation(location);
			assertLocation(result.toString());
			return result;
		} catch (AssertionError e) {
			throw new IllegalArgumentException("mapcode");
		}
		
	}
	
	/**
	 * @methodtype factory
	 * @methodproperty hook
	 * @pre rset should not be empty (checked in template)
	 * @post location should be valid location 
	 */
	@Override
	protected AbstractLocation doCreateLocation(ResultSet rset) {
		try {
			MapcodeLocation result = new MapcodeLocation(rset);
			//post
			assertLocation(result.toString());
			return result;
		} catch (SQLException | AssertionError e) { //post
			throw new AssertionError("mapcode");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty hook
	 * @pre location is valid String (checked in template)
	 * @post valid mapcode was generated
	 */
	@Override
	protected void doAssertLocation(String location) {
		try {
			MapcodeCodec.decode(location);
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			//post
				throw new AssertionError();
		}			
	}
}
