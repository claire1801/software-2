package staff;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import exceptions.InvalidStaffIDException;
import exceptions.NoStaffAvailableException;
import main.*;


/**
 * 
 * 
 *
 */

public class StaffList implements Subject {

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
	
//	public Staff getRandomStaff() {
//		//System.out.println("i am here2");
//		double randomNumber = Math.random();
//		int ID = (int) (randomNumber * this.size() + 1);
//		//System.out.println(this.size());
//		while(this.staffExists(ID) == false) {
//			//System.out.println("id no" + ID);
//			randomNumber = Math.random();
//			ID = (int) (randomNumber * this.staffList.size() + 1);
//		}
//		return staffList.get(ID);
//		
//	}
	
	
	public boolean staffExists(int ID) {
		return staffList.containsKey(ID);
	}
	
	
	public Staff getNextAvailableServer() throws NoStaffAvailableException {
		Set<Integer> keys = staffList.keySet();
		for(int key : keys) {
			Staff staff = staffList.get(key);
			if(staff.isStaffAtWork() == true && staff.isStaffServing() == false) {
				return staff;
			} 
		}
		throw new NoStaffAvailableException("There are no staff working that are free to serve");
		
	}
	
	public String onDutyList() {
		String message = "Staff on duty...\n";
		Set<Integer> keys = staffList.keySet();
		for(int key : keys) {
			Staff staff = staffList.get(key);
			if (staff.isStaffAtWork()) 
				message += String.format("Staff No. %d, %s %s staff. Currently ",
						staff.getStaffID(), staff.getStaffFirstName(), staff.getStaffLastName());
			if (staff.isStaffServing()) {
				message += "serving.\n"; 
			} else {message += "free.\n";}
		}
		return message;
	}

	
	/**
	 * List to hold any observers
	 */
	private List<Observer> registeredObservers = new LinkedList<Observer>();

	/**
	 * Register an observer with this subject
	 */
	public void registerObserver(Observer obs) {
		registeredObservers.add(obs);
	}

	/**
	 * De-register an observer with this subject
	 */
	public void removeObserver(Observer obs) {
		registeredObservers.remove(obs);
	}

	/**
	 * Inform all registered observers that there's been an update
	 */
	public void notifyObservers() {
		for (Observer obs : registeredObservers)
			obs.update();
	}
	
	
//	public void removeStaffFromList(int id) {
//		staffList.remove(id);
//	}
	
}

	

