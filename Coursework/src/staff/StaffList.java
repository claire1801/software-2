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
 * contains list of staff
 *
 */

public class StaffList implements Subject {

	private static StaffList instance;
	
	private Hashtable<Integer, Staff> staffList;
	

	
	
	private StaffList() {
		staffList = new Hashtable<Integer, Staff> ();		
	}
	/**
	 * get instance of StaffList
	 * @return StaffList instance
	 */

	public static StaffList getInstance() {
		if (instance == null) 
			instance = new StaffList();
		return instance;
	}
	/**
	 * add staff to list
	 * @param staff
	 */
	
	public void addStaffToList(Staff staff) {
		staffList.put(staff.getStaffID(), staff);
	}

	/**
	 * remove staff from list
	 * @param staffID - staff ID 
	 * @throws InvalidStaffIDException
	 */
	
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
	/**
	 * get number of staff in list
	 * @return int of staff
	 */
	public int size() {
		return this.staffList.size();
		
		
	}
	
	/**
	 * does a staff with ID exist
	 * @param ID - staff looking for
	 * @return true/false
	 */
	
	public boolean staffExists(int ID) {
		return staffList.containsKey(ID);
	}
	
	/**
	 * get the next available sever
	 * @return next Staff
	 * @throws NoStaffAvailableException
	 */
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
	/**
	 * staff is on duty 
	 * @return true / false
	 */
	public String onDutyList() {
		String message = "Staff on duty...\n";
		Set<Integer> keys = staffList.keySet();
		for(int key : keys) {
			Staff staff = staffList.get(key);
			if (staff.isStaffAtWork()) 
				message += String.format("Staff No. %d, %s %s staff. Currently ",
						staff.getStaffID(), staff.getStaffFirstName(), staff.getStaffLastName());
			if (staff.isStaffServing()) {
				try {
					if(staff.getCurrentBasket().getCurrentCustomerID() == 0) {
						message += "waiting.\n";
					}else {
						String items = "";
						
							items = staff.getCurrentBasket().getItemsInBasketString();
						
						
						int cutID = staff.getCurrentBasket().getCurrentCustomerID();
						message += "serving customer: " + cutID + " (" + items + ") .\n"; 
					}
				}catch(NullPointerException e1) {
					//System.out.println("1");
				}
				
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
	
	
}

	

