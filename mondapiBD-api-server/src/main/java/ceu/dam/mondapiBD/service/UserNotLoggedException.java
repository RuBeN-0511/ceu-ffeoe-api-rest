package ceu.dam.mondapiBD.service;

public class UserNotLoggedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1883937812876083880L;

	public UserNotLoggedException() {
	}

	public UserNotLoggedException(String message) {
		super(message);

	}

	public UserNotLoggedException(Throwable cause) {
		super(cause);

	}

	public UserNotLoggedException(String message, Throwable cause) {
		super(message, cause);

	}

	public UserNotLoggedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
