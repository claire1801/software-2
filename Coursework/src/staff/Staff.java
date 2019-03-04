package staff;

/**
 * This is the staff class
 * 
 *  
 * @author frsrg
 *
 */


public class Staff {
	
	private int StaffID;
	private String firstName;
	private String lastName;
	// could possibly add wage, no. of sales etc.
	
	
	public Staff(int staffID, String firstname, String lastname) {
		this.StaffID  = staffID;
		this.firstName = firstname;
		this.lastName = lastname;				
	}
	
	
	public int getStaffID() {
		return StaffID;
	}
	
	public String getStaffFirstName() {
		return firstName;
	}
	
	public String getStaffLastName() {
		return lastName;
	}
	
	public void setStaffID(int id) {
		this.StaffID = id;
	}
	
	public void setStaffFirstName(String fName) {
		this.firstName = fName;
	}
	
	public void setStaffLastName(String lName) {
		this.lastName = lName;


	}

}
