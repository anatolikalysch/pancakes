package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;

public class GPSLocationFactory extends AbstractFactory {
	/**
	* Hidden singleton instance; needs to be initialized from the outside.
	*/
	private static GPSLocationFactory instance = null;
	
	/**
	* Public singleton access method.
	*/
	public static synchronized GPSLocationFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic GPSLocationFactory");
			setInstance(new GPSLocationFactory());
		}
		
		return instance;
	}
	
	/**
	* Method to set the singleton instance of LocationFactory.
	*/
	protected static synchronized void setInstance(GPSLocationFactory gpsLocationFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize GPSLocationFactory twice");
		}
		instance = gpsLocationFactory;
	}

	@Override
	protected AbstractLocation doCreateLocation(String location) {
		return new GPSLocation(location);
	}
	

	@Override
	protected AbstractLocation doCreateLocation(ResultSet rset) {
		try {
			return new GPSLocation(rset);
		} catch (SQLException e) {
			return GPSLocation.EMPTY_LOCATION;
		}
	}

	@Override
	protected void doAssertLocation(String location) {
		String[] components = location.split(",");
		for (String component : components) {
			component.trim();
		}
		assert(components.length == 2);
		double x = Double.parseDouble(components[0]);
		double y = Double.parseDouble(components[1]);
		assert ((x <= 90.0 && x >= -90.0) && (y <= 180.0 && y >= -180.0));
	}	
}
