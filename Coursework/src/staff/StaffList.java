package staff;

import java.util.Hashtable;

import menu.MenuList;

/**
 * 
 * @author frsrg
 *
 */

public class StaffList {


	private Hashtable<Integer, Staff> staffList;
	
	// for Singleton
	private static StaffList staff;
	
	private StaffList() {
		staffList = new Hashtable<Integer, Staff> ();		
	}
	
	// for Singleton
	public static StaffList getInstance() {
		if (staff == null) 
			staff = new StaffList();
		return staff;
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

	

