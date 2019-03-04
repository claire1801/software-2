package exceptions;

@SuppressWarnings("serial")
public class InvalidItemIdentifierException extends Exception{

	public InvalidItemIdentifierException(String message) {
		super(message);
	}
}
