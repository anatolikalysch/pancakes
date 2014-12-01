package org.wahlzeit.location;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

public class LocationTranslater {
	
	protected String location;
	protected Mapcode mapcode;
	protected double[] gps;

	public LocationTranslater(Mapcode mapcode) {
		this.mapcode = mapcode;
	}
	
	public LocationTranslater(double[] gps) {
		this.gps = gps;
	}
	
	/**
	 * @pre mapcode connot be null
	 * @post gps[] cannot be null
	 * @methodtype
	 * @return
	 */
	public double[] translateToGPS() {
		this.assertMapcode();
		this.doTranslateToGPS();
		return gps;
		
	}
	
	private void doTranslateToGPS() {
		Point point;
		double[] temp = new double[2];
		temp[0] = 0;
		temp[1] = 0;
		try {
			point = MapcodeCodec.decode(mapcode.asInternationalFullName());
			gps[0] = point.getLatDeg();
			gps[1] = point.getLonDeg();
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			e.printStackTrace();
			gps = temp;
		}
		
	}

	protected boolean assertMapcode(){
		if (mapcode != null)
			return true;
		else 
			return false;
	}
	
	/**
	 * @pre gps[] connot be null
	 * @post mapcode cannot be null
	 * @methodtype
	 * @return
	 */
	public Mapcode translateToMapcode() {
		this.assertGPS();
		this.doTranslateToMapcode();
		return mapcode;
	}
	
	private void doTranslateToMapcode() {
		mapcode = MapcodeCodec.encodeToInternational(gps[0], gps[1]);
	}

	protected boolean assertGPS() {
		if (gps != null) 
			return true;
		else 
			return false;
	}

}
