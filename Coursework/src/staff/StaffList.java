package staff;

import java.util.Hashtable;
import java.util.Set;

import exceptions.InvalidStaffIDException;
import exceptions.NoStaffAvailableException;


/**
 * 
 * @author frsrg
 *
 */

public class StaffList {

	private static StaffList instance;
	
	private Hashtable<Integer, Staff> staffList;
	
	// for Singleton
	
	
	private StaffList() {
		staffList = new Hashtable<Integer, Staff> ();		
	}
	
	// for Singleton
	public static StaffList getInstance() {
		if (instance == null) 
			instance = new StaffList();
		return instance;
	}
	
	public void addStaffToList(Staff staff) {
		staffList.put(staff.getStaffID(), staff);
	}

	
	
	public void removeStaffFromList(int staffID) throws InvalidStaffIDException {
		if(staffList.containsKey(staffID)) {
			staffList.remove(staffID);
		}else {
			throw new InvalidStaffIDException(String.format("Staff with ID:" + staffID + " does not exist" ));
		}
		
	}
	/**
	 * get staff member
	 * @param ID
	 * @return staff if exists or null if not
	 */
	
	public Staff getStaff(int ID) {
		if(this.staffExists(ID)) {
			return staffList.get(ID);
		}else {
			return null;
		}
		
	}
	
	public int size() {
		return this.staffList.size();
		
		
	}
	
	public Staff getRandomStaff() {
		//System.out.println("i am here2");
		double randomNumber = Math.random();
		int ID = (int) (randomNumber * this.size() + 1);
		//System.out.println(this.size());
		while(this.staffExists(ID) == false) {
			//System.out.println("id no" + ID);
			randomNumber = Math.random();
			ID = (int) (randomNumber * this.staffList.size() + 1);
		}
		return staffList.get(ID);
		
	}
	
	
	public boolean staffExists(int ID) {
		return staffList.containsKey(ID);
	}
	
	
	public Staff getNextAvailableServer() throws NoStaffAvailableException {
		Set<Integer> keys = staffList.keySet();
		for(int key : keys) {
			Staff staff = staffList.get(key);
			if(staff.isStaffAtWork() == true && staff.isStaffServing() == false) {
				return staff;
			} else throw new NoStaffAvailableException("There are no staff working that are free to serve");
		}
		return null;
	}
	
	
//	public void removeStaffFromList(int id) {
//		staffList.remove(id);
//	}
	
}

	

