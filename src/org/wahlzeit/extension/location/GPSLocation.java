package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.utils.StringUtil;

import com.mapcode.MapcodeCodec;

public class GPSLocation extends AbstractLocation {

	protected double latitude;
	protected double longitude;
	
	public static final GPSLocation EMPTY_LOCATION = new GPSLocation();
	
	protected GPSLocation() {
		setLocation("0, 0");
	}
	
	public GPSLocation(String location) {
		setLocation(location);
	}
	
	
	public GPSLocation(ResultSet rset) throws SQLException {
		latitude = rset.getDouble("latitude");
		longitude = rset.getDouble("longitude");
	}

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
	
	@Override
	public String getLocationFormat() {
		return "GPS";
	}
	
	public String asMapcodeString() {
		return MapcodeCodec.encodeToShortest(latitude, longitude).asInternationalISO();
	}
	
	@Override
	public double[] asGPSCoordinates() {
		double [] result = {this.latitude,this.longitude};
		return result;
	}

	@Override
	protected String doLocationAsString() {
		return latitude+", "+longitude;
		
	}

	@Override
	protected AbstractLocation doGetLocation() {
		return this;
	}
}
