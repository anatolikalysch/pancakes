package org.wahlzeit.extension.domain;

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
	
	protected Location location = GPSLocation.EMPTY_LOCATION;
	protected Pancake pancake;
	
	/**
	 * @methodtype constructor
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	public PancakePhoto() {
		super();
		initialize();
	}

	/**
	 * @methodtype constructor
	 * @methodproperty composed
	 * @pre myId != null
	 * @post
	 */
	public PancakePhoto(PhotoId myId) {
		super(myId);
		initialize();
	}

	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public PancakePhoto(ResultSet rset) throws SQLException {
		super(rset);
	}
	
	
	/**
	 * @methodtype init
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	protected void initialize(){
		this.pancake = PancakeManager.getInstance().getPancakeFromId(-1);;
	}
	
	/**
	 * @collaboration PancakePhoto / Pancake, Pancake
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre pancake != null
	 * @post
	 */
	public Pancake getPancake() {
		//precondition
		if(pancake == null)
			throw new IllegalStateException();
		return pancake;
	}

	/**
	 * @collaboration PancakePhoto / Pancake, Pancake
	 * @methodtype set
	 * @methodproperty composed
	 * @pre pancake != null
	 * @post this.pancake == pancake
	 */
	public void setPancake(Pancake pancake) {
		//precondition
		if(pancake == null)
			throw new IllegalArgumentException("pancake");
	
		this.pancake = pancake;
		incWriteCount();
		//postcondition
		assert(this.pancake.equals(pancake));
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * @invariant
	 */
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (pancake != null);
		if (!isValid)
			throw new IllegalStateException("class invariant violated");
	}

	/**
	 * @collaboration location, Location
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre location initiated
	 * @post
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @collaboration location, Location
	 * @methodtype set
	 * @methodproperty primitive
	 * @pre
	 * @post this.location == location
	 */
	public void setLocation(Location location) {
		this.location = location;
		incWriteCount();
	}

	/**
	 * @collaboration serializer, PhotoManager; AbstractLocationFactory, FactoryProducer, AbstractLocationFactory
	 * @methodtype command
	 * @methodproperty
	 * @pre resultSet != null
	 * @post
	 */
	public void readFrom(ResultSet rset) throws SQLException {
		assert(rset != null);
		super.readFrom(rset);
		
		// read the location data from the database
		String locationType = rset.getString("location_type");
		FactoryProducer fp = new FactoryProducer();
		AbstractLocationFactory af = fp.getFactory(locationType);
		location = af.createLocation(rset.getString("location"));
		
		//read pancake data from the databese
		pancake = PancakeManager.getInstance().getPancakeFromId(rset.getInt("pancake_id"));
		assertInvariants();
	}

	/**
	 * @collaboration serializer, PhotoManager
	 * @methodtype command
	 * @methodproperty
	 * @pre location data should not be empty
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
