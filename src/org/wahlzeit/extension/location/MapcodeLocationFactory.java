package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;

import com.mapcode.MapcodeCodec;
import com.mapcode.UnknownMapcodeException;

public class MapcodeLocationFactory extends AbstractFactory {
	/**
	* Hidden singleton instance; needs to be initialized from the outside.
	*/
	private static MapcodeLocationFactory instance = null;
	
	/**
	* Public singleton access method.
	*/
	public static synchronized MapcodeLocationFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic MapcodeLocationFactory");
			setInstance(new MapcodeLocationFactory());
		}
		
		return instance;
	}
	
	/**
	* Method to set the singleton instance of LocationFactory.
	*/
	protected static synchronized void setInstance(MapcodeLocationFactory mapcodeLocationFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize MapcodeLocationFactory twice");
		}
		instance = mapcodeLocationFactory;
	}

	@Override
	protected AbstractLocation doCreateLocation(String location) {
		return new MapcodeLocation(location);
	}
	
	@Override
	protected AbstractLocation doCreateLocation(ResultSet rset) {
		try {
			return new MapcodeLocation(rset);
		} catch (SQLException e) {
			return GPSLocation.EMPTY_LOCATION;
		}
	}

	@Override
	protected void doAssertLocation(String location) {
		try {
			MapcodeCodec.decode(location);
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
				throw new AssertionError();
		}			
	}
	
	

	
		
	
}
