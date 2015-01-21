package org.wahlzeit.extension.handlers;

public class LocationArgumentException extends IllegalArgumentException {

	public LocationArgumentException() {
	}

	public LocationArgumentException(String s) {
		super(s);
	}

	public LocationArgumentException(Throwable cause) {
		super(cause);
	}

	public LocationArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

}
