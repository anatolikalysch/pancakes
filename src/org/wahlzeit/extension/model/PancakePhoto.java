package org.wahlzeit.extension.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.extension.location.GPSLocation;
import org.wahlzeit.extension.location.Location;
import org.wahlzeit.extension.location.MapcodeLocation;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

/**
 * This Class extends the Photo.java class and is supposed to be part of the "design
 * by contract" architecture.
 * @author qwert
 *
 */
public class PancakePhoto extends Photo {
	
	protected Pancake pancake;
	
	protected Location location = Location.EMPTY_LOCATION;
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
		incWriteCount();
	}

	
	public Pancake getPancake() {
		return pancake;
	}
	
	public void setPancake(Pancake pancake) {
		//precondition
		if(pancake == null)
			throw new IllegalArgumentException();
	
		this.doSetPancake(pancake);
		//postcondition
		assert(this.pancake.equals(pancake));
	}
	
	protected void doSetPancake(Pancake pancake) {
		this.pancake = pancake;
		incWriteCount();
	}
	
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (pancake != null);
		if (!isValid)
			throw new IllegalStateException("class invariant violated");
	}
	
	public PancakePhoto() {
		super();
		initialize();
	}
	
	public PancakePhoto(PhotoId myId) {
		super(myId);
		initialize();
	}
	
	public PancakePhoto(ResultSet rset) throws SQLException {
		super(rset);
	}
	
	protected void initialize(){
		this.pancake = PancakeManager.getInstance().getPancakeFromId(-1);;
	}
	
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		// read the location data from the database
		String locationType = rset.getString("location_type");
		switch(locationType) {
			case "GPS": 
				location = new GPSLocation(rset.getString("location"));
				break;
			case "Mapcode": 
				location = new MapcodeLocation(rset.getString("location"));
				break;
			default:
				location = Location.EMPTY_LOCATION;
				break;
		}
		
		pancake = PancakeManager.getInstance().getPancakeFromId(rset.getInt("pancake_id"));
	}
	
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		// write location data to database
		rset.updateString("location_type", location.getLocationFormat());
		rset.updateString("location", location.asString());
		rset.updateInt("pancake_id", pancake.getId());
	}
}
