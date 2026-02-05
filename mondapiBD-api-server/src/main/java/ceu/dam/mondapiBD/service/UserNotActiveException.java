package ceu.dam.mondapiBD.service;

public class UserNotActiveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8104253757228809191L;

	public UserNotActiveException() {
	}

	public UserNotActiveException(String message) {
		super(message);

	}

	public UserNotActiveException(Throwable cause) {
		super(cause);

	}

	public UserNotActiveException(String message, Throwable cause) {
		super(message, cause);

	}

	public UserNotActiveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
