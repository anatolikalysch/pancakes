package org.wahlzeit.extension.location;

public abstract class AbstractLocation implements Location {
	
	boolean hasLocation = false;

	@Override
	public String toString() {
		return asString();
	}
	
	public String asString() {
		if (hasLocation)
			return doLocationAsString();
		else
			return "";
	}
	
	protected void setLocation(String location){
		doSetLocation(location);
		hasLocation = true;
	}
	
	@Override
	public AbstractLocation getLocation(){
		if (hasLocation)
			return doGetLocation();
		else
			return null;
	}

	@Override
	public String getLocationFormat() {
		return "Abstract";
	}
	
	protected abstract void doSetLocation(String location);
	
	protected abstract String doLocationAsString();
	
	protected abstract AbstractLocation doGetLocation();

}
