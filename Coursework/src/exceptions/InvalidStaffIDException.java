package exceptions;

@SuppressWarnings("serial")
public class InvalidStaffIDException extends Exception  {
	
	public InvalidStaffIDException(String message) {
		super(message);
	}

}
