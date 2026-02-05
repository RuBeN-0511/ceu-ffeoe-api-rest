package ceu.dam.mondapiBD.service;

public class IncorrectPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1186760937463040062L;

	public IncorrectPasswordException() {
	}

	public IncorrectPasswordException(String message) {
		super(message);

	}

	public IncorrectPasswordException(Throwable cause) {
		super(cause);

	}

	public IncorrectPasswordException(String message, Throwable cause) {
		super(message, cause);

	}

	public IncorrectPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
