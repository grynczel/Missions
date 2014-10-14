/**
 * @author Grynczel Wojciech
 */

public class InterpreterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InterpreterException() {
	}

	public InterpreterException(String message) {
		// TODO Message
		super(message);
	}

	public InterpreterException(Throwable cause) {
		super(cause);
	}

	public InterpreterException(String message, Throwable cause) {
		super(message, cause);
	}
}
