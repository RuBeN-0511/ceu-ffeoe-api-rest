package ceu.dam.mondapiBD.exceptions;

public class UserNotLoggedException extends Exception {

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
