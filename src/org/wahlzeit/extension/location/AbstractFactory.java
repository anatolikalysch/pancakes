package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractFactory {
	
	/**
	 * @methodtype factory method
	 */
	public AbstractLocation createLocation(String location) {
		if (location == null)
			return GPSLocation.EMPTY_LOCATION;
		else {
			assertLocation(location);
			return doCreateLocation(location);
		}	
	}
	
	protected abstract AbstractLocation doCreateLocation(String location);
	
	/**
	 * @pre location != null
	 * @post 
	 * @param location
	 * @return
	 * @throws AssertionError
	 */
	protected void assertLocation(String location) throws AssertionError {
		if (location == null)
			throw new AssertionError();
		doAssertLocation(location);
	}
	
	protected abstract void doAssertLocation(String location);
	
	/**
	 * @methodtype factory method
	 */
	public AbstractLocation createLocation(ResultSet rset) throws SQLException {
		return doCreateLocation(rset);
	}
	
	protected abstract AbstractLocation doCreateLocation(ResultSet rset);
}
