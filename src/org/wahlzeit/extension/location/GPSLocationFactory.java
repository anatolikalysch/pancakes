package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;

/**
 * This class is part of the AbstractFactory collaboration.
 * @author qwert
 *
 */
public class GPSLocationFactory extends AbstractLocationFactory {
	/**
	* Hidden singleton instance; needs to be initialized from the outside.
	*/
	private static GPSLocationFactory instance = null;
	
	/**
	 * Public singleton access method
	 * 
	 * @methodtype get
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	public static synchronized GPSLocationFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic GPSLocationFactory");
			setInstance(new GPSLocationFactory());
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
	protected static synchronized void setInstance(GPSLocationFactory gpsLocationFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize GPSLocationFactory twice");
		}
		instance = gpsLocationFactory;
	}

	/**
	 * @methodtype factory
	 * @methodproperty hook
	 * @pre location should be valid String (checked in teplate)
	 * @post location should be valid location (checked in template)
	 */
	@Override
	protected AbstractLocation doCreateLocation(String location) {
		try {
			GPSLocation result = new GPSLocation(location);
			//post
			assertLocation(result.toString());
			return result;
		} catch (AssertionError e) { //post
			throw new IllegalArgumentException("gps");
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
			GPSLocation result = new GPSLocation(rset);
			//post
			assertLocation(result.toString());
			return result;
		} catch (SQLException | AssertionError e) { //post
			throw new IllegalArgumentException("gps");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty hook
	 * @pre location is valid String (checked in template)
	 * @post latitude and longitude are set in the right interval for GPS-Coord
	 */
	@Override
	protected void doAssertLocation(String location) {
		String[] components = location.split(",");
		for (String component : components) {
			component.trim();
		}
		//post
		assert(components.length == 2);
		double x = Double.parseDouble(components[0]);
		double y = Double.parseDouble(components[1]);
		//post
		if (!(x <= 90.0 && x >= -90.0)) 
			throw new IllegalArgumentException("latitude");
		if (!(y <= 180.0 && y >= -180.0))
			throw new IllegalArgumentException("longitude");;
	}	
}
