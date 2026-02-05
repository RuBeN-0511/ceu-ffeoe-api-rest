package ceu.dam.mondapiBD.exceptions;

public class UsernameUsedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2922276737637924039L;

	public UsernameUsedException() {
	}

	public UsernameUsedException(String message) {
		super(message);

	}

	public UsernameUsedException(Throwable cause) {
		super(cause);

	}

	public UsernameUsedException(String message, Throwable cause) {
		super(message, cause);

	}

	public UsernameUsedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
