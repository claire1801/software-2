package exceptions;

@SuppressWarnings("serial")
public class NoStaffAvailableException extends Exception {

	public NoStaffAvailableException(String message) {
		super(message);
	}

}
