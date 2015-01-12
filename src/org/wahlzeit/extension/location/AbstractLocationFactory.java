package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is part of the AbstractFactory collaboration and the PancakePhoto / Location collaboration.
 * @author qwert
 *
 */
public abstract class AbstractLocationFactory {
	
	/**
	 * @methodtype factory
	 * @methodproperty
	 * @pre
	 * @post
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
	 * @methodtype
	 * @methodproperty
	 * @pre location != null
	 * @post location is a valid location
	 */
	protected void assertLocation(String location) throws AssertionError {
		if (location == null)
			throw new AssertionError();
		doAssertLocation(location);
	}
	
	protected abstract void doAssertLocation(String location);
	
	/**
	 * @methodtype factory
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public AbstractLocation createLocation(ResultSet rset) throws SQLException {
		return doCreateLocation(rset);
	}
	
	/**
	 * @methodtype factory
	 * @methodproperty
	 * @pre
	 * @post
	 */
	protected abstract AbstractLocation doCreateLocation(ResultSet rset);
}
