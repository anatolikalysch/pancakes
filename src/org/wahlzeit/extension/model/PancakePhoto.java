package org.wahlzeit.extension.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.extension.location.AbstractLocationFactory;
import org.wahlzeit.extension.location.FactoryProducer;
import org.wahlzeit.extension.location.GPSLocation;
import org.wahlzeit.extension.location.Location;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

/**
 * This Class extends the Photo.java class and is supposed to be part of the "design
 * by contract" architecture. Photo.java was not made into an abstract superclass be
 * cause it is part of the original wahlzeit deployment which should be left as unch
 * anged as possible.
 * 
 * @author qwert
 *
 */
public class PancakePhoto extends Photo {
	
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public PancakePhoto() {
		super();
		initialize();
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public PancakePhoto(PhotoId myId) {
		super(myId);
		initialize();
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public PancakePhoto(ResultSet rset) throws SQLException {
		super(rset);
	}
	
	/** 
	 * ---------- PancakePhoto / Pancake collaboration ----------
	 * 
	 */
	protected Pancake pancake;
	
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	protected void initialize(){
		this.pancake = PancakeManager.getInstance().getPancakeFromId(-1);;
	}
	
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Pancake getPancake() {
		return pancake;
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void setPancake(Pancake pancake) {
		//precondition
		if(pancake == null)
			throw new IllegalArgumentException();
	
		this.pancake = pancake;
		incWriteCount();
		//postcondition
		assert(this.pancake.equals(pancake));
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (pancake != null);
		if (!isValid)
			throw new IllegalStateException("class invariant violated");
	}

	
	/**
	 * ---------- PancakePhoto / Location collaboration ----------
	 */
	protected Location location = GPSLocation.EMPTY_LOCATION;

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void setLocation(Location location) {
		this.location = location;
		incWriteCount();
	}

	
	/**
	 * ---------- Serializer collaboration ----------
	 */
	
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		
		// read the location data from the database
		String locationType = rset.getString("location_type");
		FactoryProducer fp = new FactoryProducer();
		AbstractLocationFactory af = fp.getFactory(locationType);
		location = af.createLocation(rset.getString("location"));
		
		//read pancake data from the databese
		pancake = PancakeManager.getInstance().getPancakeFromId(rset.getInt("pancake_id"));
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		// write location data to database
		rset.updateString("location_type", location.getLocationFormat());
		rset.updateString("location", location.toString());
		rset.updateInt("pancake_id", pancake.getId());
	}
}
