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
	private Log log;
	private Basket unprocessedOrder;
	private int CurrentCustomerID = 0;
	//private int CurrentOrderID = 0;
	
	
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
		log = Log.getInstance();
	}
	
	public void run() {
		 System.out.println ("Thread " + Thread.currentThread().getId() + " is running server id: " + this.StaffID);
		while(!queue.queueEmpty() && this.serving == true) {
			try {
				
				processOrder();
				unprocessedOrder.setCurrentStaffID(StaffID);
				
				//this.CurrentOrderID = unprocessedOrder.get
				unprocessedOrder.getTotalDiscount();
				unprocessedOrder.getFinalBill();
				unprocessedOrder.confirmedAndPaid();
				
				
			} catch (InterruptedException e) {
				
			}
		}
	
	}
	
	private void processOrder() throws InterruptedException {
		unprocessedOrder = queue.getNextInQueue();
		this.CurrentCustomerID = unprocessedOrder.getCurrentCustomerID();
		//System.out.println("online: " + unprocessedOrder.getOnline());
		log.writeToFile(updateLog());
		int speed = Main.sched.speed;
		Thread.sleep( speed * 200 + (speed * 10 * unprocessedOrder.numberOfItems()));
		log.writeToFile(updateLog2());
		
	}
	
	
	public String updateLog() {
		//System.out.println("Customer ID: " + CurrentCustomerID +  " order is being processed by Server: " + StaffID +" ("+ this.firstName +" " +this.lastName + ") This order contains " + unprocessedOrder.getItemsInBasket());
		String order = new String();
		order += "Customer ID: " + CurrentCustomerID +  " order is being processed by Server: " + StaffID +" ("+ this.firstName +" " +this.lastName + ") \n"; 
		order += "This order contains items: " + unprocessedOrder.getItemsInBasketString() + "\n";
		return order;
	}
	
	
	public String updateLog2() {
		//System.out.println("Customer ID: " + CurrentCustomerID +  " has been processed");
		String order = new String();
		order += "The order for Customer: " + CurrentCustomerID + " has been processed by: " + StaffID;
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
	/**
	 * get the basket currently being processed
	 * @return current basket
	 */
	
	public Basket getCurrentBasket() {
		return this.unprocessedOrder;
	}

}
