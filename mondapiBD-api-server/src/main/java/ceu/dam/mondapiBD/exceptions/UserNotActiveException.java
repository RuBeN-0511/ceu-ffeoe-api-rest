package ceu.dam.mondapiBD.exceptions;

public class UserNotActiveException extends Exception {

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
