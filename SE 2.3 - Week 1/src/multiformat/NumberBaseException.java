package multiformat;

/**
 * Exception that is thrown when a number is out of bounds of the selected numeral system.
 * 
 * @author Tobias Schlichter
 * @version 1.0
 */
public class NumberBaseException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates new number base exception object.
	 * @param message String for when the exception is thrown.
	 */
	public NumberBaseException(String message) {
		super(message);
	}
	
}