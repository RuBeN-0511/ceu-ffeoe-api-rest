package ceu.dam.mondapiBD.exceptions;

public class AlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8763983854709378328L;

	public AlreadyExistsException() {
	}

	public AlreadyExistsException(String message) {
		super(message);

	}

	public AlreadyExistsException(Throwable cause) {
		super(cause);

	}

	public AlreadyExistsException(String message, Throwable cause) {
		super(message, cause);

	}

	public AlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
