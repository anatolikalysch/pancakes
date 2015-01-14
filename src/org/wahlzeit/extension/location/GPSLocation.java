package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.utils.StringUtil;

import com.mapcode.MapcodeCodec;

/**
 * This class is part of the Location and the Abstract Factory collaborations.
 * @author qwert
 *
 */
public class GPSLocation extends AbstractLocation {

	protected double latitude;
	protected double longitude;
	
	public static final GPSLocation EMPTY_LOCATION = new GPSLocation();
	
	/**
	 * @methodtype constructor
	 * @methodproperty convenience constructor
	 * @pre
	 * @post
	 */
	protected GPSLocation() {
		setLocation("0, 0");
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty 
	 * @pre String is valid String
	 * @post
	 */
	protected GPSLocation(String location) {
		if (!StringUtil.isNullOrEmptyString(location))
			setLocation(location);
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre
	 * @post
	 */
	protected GPSLocation(ResultSet rset) throws SQLException {
		latitude = rset.getDouble("latitude");
		longitude = rset.getDouble("longitude");
	}

	/**
	 * @methodtype set
	 * @methodproperty hook
	 * @pre location is valid String
	 * @post lat & lon are set
	 */
	protected void doSetLocation(String location) {
		if (!StringUtil.isNullOrEmptyString(location)) {
			String[] components = location.split(",");
			for (String component : components) {
				component.trim();
			}
			this.latitude = Double.parseDouble(components[0]);
			this.longitude = Double.parseDouble(components[1]);
		}
	}
	
	/**
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	@Override
	public String getLocationFormat() {
		return "GPS";
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 * @pre has a location
	 * @post String is valid
	 */
	public String asMapcodeString() throws IllegalStateException{
		String result = "n/a";
		if (hasLocation) {
			result = MapcodeCodec.encodeToShortest(latitude, longitude).asInternationalISO();
			//post
			if(StringUtil.isNullOrEmptyString(result))
				throw new IllegalStateException();
		}
		
		return result;
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 * @pre has a location
	 * @post result is valid double
	 */
	@Override
	public double[] asGPSCoordinates() throws IllegalStateException {
		if (!hasLocation) {
			throw new IllegalStateException();
		} else {
			double [] result = {this.latitude,this.longitude};
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
			return latitude+", "+longitude;
		else
			return "";
	}

	/**
	 * @methodtype get
	 * @methodproperty hook
	 * @pre has a location
	 * @post
	 */
	@Override
	protected AbstractLocation doGetLocation() {
		if (hasLocation)
			return this;
		else
			return GPSLocation.EMPTY_LOCATION;
	}
}
