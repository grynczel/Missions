/**
 * Create customs errors related to the interpreter
 */
public class InvalidExpressionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidExpressionException() {
	}

	public InvalidExpressionException(String message) {
		// TODO Message
		super(message);
	}

	public InvalidExpressionException(Throwable cause) {
		super(cause);
	}

	public InvalidExpressionException(String message, Throwable cause) {
		super(message, cause);
	}
}
