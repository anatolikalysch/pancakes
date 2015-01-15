package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.utils.StringUtil;

import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

/**
 * This class is part of the Location and the Abstract Factory collaborations.
 * @author qwert
 *
 */
public class MapcodeLocation extends AbstractLocation {
	
	/**
	 * 
	 * ---------- AbstractFactory collaboration ----------
	 */

	protected String mapcode;
	
	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre location is valid String
	 * @post
	 */
	protected MapcodeLocation(String location) {
		if (!StringUtil.isNullOrEmptyString(location))
			setLocation(location);
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre
	 * @post
	 */
	protected MapcodeLocation(ResultSet rset) throws SQLException {
		mapcode = rset.getString("mapcode");
	}

	/**
	 * @methodtype set
	 * @methodproperty hook
	 * @pre location is valid String
	 * @post mapcode != null
	 */
	@Override
	public void doSetLocation(String location) {
		if (!StringUtil.isNullOrEmptyString(location)) {
			mapcode = location;
			assert(mapcode != null);
		}
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 * @pre mapcode != null
	 * @post
	 */
	public String asGPSString() {
		if (!StringUtil.isNullOrEmptyString(mapcode))
			try {
				Point point = MapcodeCodec.decode(this.mapcode);
				return point.getLatDeg()+", "+point.getLonDeg();
			} catch (IllegalArgumentException | UnknownMapcodeException e) {
				return "None";
			}
		else
			return "None";
	}
	
	/**
	 * @methodtype  get
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	@Override
	public String getLocationFormat() {
		return "Mapcode";
	}
	
	/**
	 * 
	 * ---------- Location collaboration ----------
	 */
	
	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	@Override
	public double[] asGPSCoordinates() throws IllegalStateException {
		if (!hasLocation)
			throw new IllegalStateException();
		else {
			double[] result = new double[2];
			try {
				Point point = MapcodeCodec.decode(this.mapcode);
				result[0] = point.getLatDeg();
				result[1] = point.getLonDeg();
			} catch (IllegalArgumentException | UnknownMapcodeException e) {
				result[0] = 0;
				result[1] = 0;
			}
			
			return result;
		}
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty hook
	 * @pre has a location
	 * @post
	 */
	@Override
	protected String doLocationAsString() {
		if (hasLocation)
			return mapcode;
		else
			return "";
	}
}
