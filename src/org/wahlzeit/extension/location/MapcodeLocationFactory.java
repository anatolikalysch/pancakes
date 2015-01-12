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
	 * @methodtype Public singleton access method
	 * @methodproperty
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
	 * @methodtype Method to set the singleton instance of LocationFactory
	 * @methodproperty
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
	 * @methodtype command
	 * @methodproperty hook
	 * @pre
	 * @post
	 */
	@Override
	protected AbstractLocation doCreateLocation(String location) {
		return new MapcodeLocation(location);
	}
	
	/**
	 * @methodtype command
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	@Override
	protected AbstractLocation doCreateLocation(ResultSet rset) {
		try {
			return new MapcodeLocation(rset);
		} catch (SQLException e) {
			return GPSLocation.EMPTY_LOCATION;
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty hook
	 * @pre
	 * @post
	 */
	@Override
	protected void doAssertLocation(String location) {
		try {
			MapcodeCodec.decode(location);
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
				throw new AssertionError();
		}			
	}
}
