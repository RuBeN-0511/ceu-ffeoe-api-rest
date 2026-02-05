package ceu.dam.mondapiBD.exceptions;

public class UserExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937730925776166065L;

	public UserExistsException() {
	}

	public UserExistsException(String message) {
		super(message);

	}

	public UserExistsException(Throwable cause) {
		super(cause);

	}

	public UserExistsException(String message, Throwable cause) {
		super(message, cause);

	}

	public UserExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
