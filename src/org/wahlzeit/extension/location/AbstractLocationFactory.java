package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.utils.StringUtil;

/**
 * This class is part of the AbstractFactory collaboration and the PancakePhoto / Location collaboration.
 * @author qwert
 *
 */
public abstract class AbstractLocationFactory {
	
	/**
	 * @methodtype factory
	 * @methodproperty template
	 * @pre location should be valid String
	 * @post location should be valid location
	 */
	public AbstractLocation createLocation(String location) {
		//pre
		if (StringUtil.isNullOrEmptyString(location))
			return GPSLocation.EMPTY_LOCATION;
		else {
			//post
			assertLocation(location);
			return doCreateLocation(location);
		}	
	}
	
	protected abstract AbstractLocation doCreateLocation(String location);
	
	
	/**
	 * @methodtype assertion
	 * @methodproperty template
	 * @pre location != null
	 * @post location is a valid location
	 */
	protected void assertLocation(String location) throws AssertionError {
		if (StringUtil.isNullOrEmptyString(location))
			throw new AssertionError();
		doAssertLocation(location);
	}
	
	protected abstract void doAssertLocation(String location);
	
	/**
	 * @methodtype factory
	 * @methodproperty template
	 * @pre rset should not be empty
	 * @post location should be valid location
	 */
	public AbstractLocation createLocation(ResultSet rset) throws SQLException {
		if (rset == null)
			throw new SQLException("Empty ResultSet, cannot create Location!");
		else
			return doCreateLocation(rset);
	}
	
	protected abstract AbstractLocation doCreateLocation(ResultSet rset);
}
