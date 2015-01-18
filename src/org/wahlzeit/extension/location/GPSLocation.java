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
	
	/**
	 * 
	 * ---------- AbstractFactory collaboration ----------
	 */

	protected double latitude;
	protected double longitude;
	
	public static final GPSLocation EMPTY_LOCATION = new GPSLocation();
	
	/**
	 * @methodtype constructor
	 * @methodproperty convenience
	 * @pre
	 * @post
	 */
	protected GPSLocation() {
		setLocation("0, 0");
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty 
	 * @pre String is valid String (checked in setLocation)
	 * @post
	 */
	protected GPSLocation(String location) {
		setLocation(location);
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre ResultSet != null
	 * @post
	 */
	protected GPSLocation(ResultSet rset) throws SQLException {
		assert(rset != null);
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
			if (components.length == 2) {
				this.latitude = Double.parseDouble(components[0]);
				this.longitude = Double.parseDouble(components[1]);
			} else {
				//user did not use "." to specify the lat & lon values
				if (components.length == 4) 
					repairBrokenValue(components);
				else
					throw new IllegalArgumentException("location");
			}
				
		}
	}
	
	private void repairBrokenValue(String[] components) {
		this.latitude = Double.parseDouble(components[0] + "." + components[1]);
		this.longitude = Double.parseDouble(components[2] + "." + components[3]);
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
	 * 
	 * ---------- Location collaboration ----------
	 */
	
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
			return "n/a";
	}
}
