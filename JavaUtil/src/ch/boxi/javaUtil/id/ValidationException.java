package ch.boxi.javaUtil.id;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = -7298224284709071557L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

}
