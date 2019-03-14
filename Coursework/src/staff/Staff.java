package staff;


import main.Log;
import main.Main;

import shop.Basket;
import shop.Queue;

/**
 * This is the staff class
 * 
 *  
 * @author frsrg
 *
 */


public class Staff implements Runnable {
	
	private int StaffID;
	private String firstName;
	private String lastName;

	private Queue queue;
	private Basket unprocessedOrder;
	
	
	// staff have to 'log in' to be on duty and serving
	private boolean atWork = true; // set to True until a 'home' screen where the on duty staff can be set
	private boolean serving = false;
	
	

	//private static final int orderTime = 5000; //milliseconds

	// could possibly add wage, no. of sales etc.
	
	
	public Staff(int StaffID, String firstname, String lastname) {
		this.StaffID  = StaffID;
		this.firstName = firstname;
		this.lastName = lastname;	
		
		queue = Queue.getInstance();
	}
	
	public void run() {
		
		while(!queue.queueEmpty()) {
			try {
				 System.out.println ("Thread " + Thread.currentThread().getId() + " is running");
				processOrder();
				unprocessedOrder.getFinalBill();
				unprocessedOrder.confirmedAndPaid();
				
				
			} catch (InterruptedException e) {
				
			}
		}
	
	}
	
	private void processOrder() throws InterruptedException {
		unprocessedOrder = queue.getNextInQueue();
		updateLog();

		System.out.println("online: " + unprocessedOrder.getOnline());
		Log.writeToFile("Customer ID: " + unprocessedOrder.getCurrentCustomerID() + " is being processed by Staff " + StaffID);
		Thread.sleep(Main.sched.Speed * 500);

		Log.writeToFile("Customer ID: " + unprocessedOrder.getCurrentCustomerID() + " order is complete");
		updateLog2();
	}
	
	
	public String updateLog() {
		System.out.println("Customer ID: " + unprocessedOrder.getCurrentCustomerID() +  " order is being processed by Server " + StaffID +
				"\n"+ "This order contains " + unprocessedOrder.getItemsInBasket());
		String order = new String();
		order += "Customer ID: " + unprocessedOrder.getCurrentCustomerID() +  " order is being processed\n"; 
		order += "This order contains items" + unprocessedOrder.getItemsInBasket() + "\n";
		return order;
	}
	
	
	public String updateLog2() {
		System.out.println("Customer ID: " + unprocessedOrder.getCurrentCustomerID() +  " has been processed");
		String order = new String();
		order += "orderID: " + "has been processed";
		return order;
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
	
	/**
	 * method to set a staff member as on duty
	 */
	public void isAtWork(boolean val) {
		this.atWork = val;
	}
	
	/**
	 *  method to return if a staff member is on duty
	 */
	public boolean isStaffAtWork() {
		return this.atWork;
	}
	
	/**
	 * method to set a staff member as serving
	 */
	public void isServing(boolean val) {
		this.serving = val;
	}
	
	/**
	 *  method to return if a staff member is on duty
	 */
	public boolean isStaffServing() {
		return this.serving;
	}

}
