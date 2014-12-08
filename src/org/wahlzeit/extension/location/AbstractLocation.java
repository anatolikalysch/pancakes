package org.wahlzeit.extension.location;

public abstract class AbstractLocation implements Location {

	@Override
	public String toString() {
		return asString();
	}
	
	@Override
	public String asString() {
		return doLocationAsString();
	}

	@Override
	public void setLocation(String location) {
		assertIsValidLocation(location);
		doSetLocation(location);
	}

	@Override
	public String getLocationFormat() {
		return "Abstract";
	}
	
	protected abstract void assertIsValidLocation(String location) throws AssertionError;
	
	protected abstract void doSetLocation(String location);
	
	protected abstract String doLocationAsString();

}
