package org.wahlzeit.extension.model;

public class PancakeArgumentException extends IllegalArgumentException {

	public PancakeArgumentException() {
	}

	public PancakeArgumentException(String s) {
		super(s);
	}

	public PancakeArgumentException(Throwable cause) {
		super(cause);
	}

	public PancakeArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

}
