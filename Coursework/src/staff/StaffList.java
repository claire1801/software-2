package staff;

import java.util.Hashtable;

/**
 * 
 * @author frsrg
 *
 */

public class StaffList {

	public Hashtable<Integer, Staff> staffList;
	
	
	public StaffList() {
		staffList = new Hashtable<Integer, Staff> ();		
	}
	
	public Hashtable<Integer, Staff> getStaffList() {
		return staffList;
	}
	
	public void addStaffToList(Staff staff) {
		staffList.put(staff.getStaffID(), staff);
	}

	
	// this would be better if we can remove by staff id 
	public void removeStaffFromList(Staff staff) {
		staffList.remove(staff.getStaffID());
	}
	public boolean staffExists(int ID) {
		return staffList.containsKey(ID);
	}
	
//	public void removeStaffFromList(int id) {
//		staffList.remove(id);
//	}
	
}

	

