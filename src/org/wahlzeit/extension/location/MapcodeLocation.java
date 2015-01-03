package org.wahlzeit.extension.location;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

public class MapcodeLocation extends AbstractLocation {

	protected String mapcode;
	
	public MapcodeLocation(String location) {
		setLocation(location);
	}
	
	public MapcodeLocation(ResultSet rset) throws SQLException {
		mapcode = rset.getString("mapcode");
	}

	@Override
	public void doSetLocation(String location) {
		mapcode = location;
	}
	
	public String asGPSString() {
		try {
			Point point = MapcodeCodec.decode(this.mapcode);
			return point.getLatDeg()+", "+point.getLonDeg();
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			return "None";
		}
	}
	
	@Override
	public String getLocationFormat() {
		return "Mapcode";
	}
	
	@Override
	public double[] asGPSCoordinates() {
		double[] result = new double[2];
		try {
			Point point = MapcodeCodec.decode(this.mapcode);
			result[0] = point.getLatDeg();
			result[1] = point.getLonDeg();
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			
		}
		return result;
	}
	
	@Override
	protected String doLocationAsString() {
		return mapcode;
	}

	@Override
	protected AbstractLocation doGetLocation() {
		return this;
	}

}
