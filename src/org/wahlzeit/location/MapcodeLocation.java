package org.wahlzeit.location;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;



public class MapcodeLocation extends AbstractLocation {
	protected Point location = null;
	protected Mapcode mapcode = null;

	public MapcodeLocation(String mapcode) {
		try {
			location = MapcodeCodec.decode(mapcode);
			this.mapcode = MapcodeCodec.encodeToInternational(location.getLatDeg(),
					location.getLonDeg());
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			e.printStackTrace();
			this.mapcode = MapcodeCodec.encodeToInternational(0, 0);
		}
		hasLocation = true;
	}
	
	public Mapcode getMapcode() {
		return mapcode;
	}
	
	@Override
	public double[] getLocation() {
		double[] result = new double[2];
		result[0] = getLatitude();
		result[1] = getLongtitude();
		return result;
	}
	
	@Override
	public double getLatitude(){
		return location.getLatDeg();
	}
	
	@Override
	public double getLongtitude() {
		return location.getLonDeg();
	}
	
	@Override
	public String getFormat() {
		return "Mapcode";
	}

	@Override
	public void setLocation(double lat, double lon) {
		mapcode = MapcodeCodec.encodeToInternational(lat, lon);
		try {
			location = MapcodeCodec.decode(mapcode.asInternationalFullName());
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			//This should never happen as it is prior encoded by the application 
			e.printStackTrace();
		}
		hasLocation = true;
	}

	public void setLocation(Mapcode mapcode) {
		try {
			location = MapcodeCodec.decode(mapcode.asInternationalFullName());
			this.mapcode = MapcodeCodec.encodeToInternational(location.getLatDeg(), location.getLonDeg());
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			e.printStackTrace();
			this.mapcode = MapcodeCodec.encodeToInternational(0, 0);
		}
		hasLocation = true;
	}

	@Override
	public boolean isEqual(Mapcode mapcode) {
		return (this.mapcode == mapcode);
	}

	@Override
	public boolean isEqual(double lat, double lon) {
		return (lat == getLatitude() && lon == getLongtitude());
	}

	@Override
	public boolean hasLocation() {
		return hasLocation;
	}

	@Override
	public String asString() {
		return String.valueOf(getLatitude())+", "+String.valueOf(getLongtitude())+"; "+mapcode.asInternationalFullName();
	}
	
	@Override
	public void deleteLocation() {
		super.deleteLocation();
		mapcode = null;
	}

}
