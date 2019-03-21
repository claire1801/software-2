package staff;


import main.Log;
import main.Main;

import shop.Basket;
import shop.Queue;

/**
 * This is the staff class
 * 
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

	/**
	 * staff have to 'log in' to be on duty and serving - currently always true
	 * set to True until a 'home' screen where the on duty staff can be set
	 */
	private boolean atWork = true; // 
	private boolean serving = false;
	
	private double cost;
	private double discount;
	

	/**
	 * constructor
	 */
	
	public Staff(int StaffID, String firstname, String lastname) {
		this.StaffID  = StaffID;
		this.firstName = firstname;
		this.lastName = lastname;	
		
		queue = Queue.getInstance();
		log = Log.getInstance();
	}
	/**
	 * part of threads - main loop
	 */
	
	public void run() {
		// System.out.println ("Thread " + Thread.currentThread().getId() + " is running server id: " + this.StaffID);
		while(!queue.queueEmpty() && this.serving == true) {
			try {
				
				processOrder();
				unprocessedOrder.setCurrentStaffID(StaffID);
				discount = unprocessedOrder.getTotalDiscount();
				cost = unprocessedOrder.getFinalBill();
				unprocessedOrder.confirmedAndPaid();
				log.writeToFile(updateLog2());
				
			} catch (InterruptedException e) {
				
			}
		}
		StaffList.getInstance().notifyObservers();
		Queue.getInstance().notifyObservers();
	
	}
	/**
	 * process order and wait given amount of time
	 * @throws InterruptedException
	 */
	
	private void processOrder() throws InterruptedException {
		unprocessedOrder = queue.getNextInQueue();
		this.CurrentCustomerID = unprocessedOrder.getCurrentCustomerID();
		log.writeToFile(updateLog());
		int speed = Main.sched.getSpeed();
		Thread.sleep( speed * 200 + (speed * 10 * unprocessedOrder.numberOfItems()));
		StaffList.getInstance().notifyObservers();
		Queue.getInstance().notifyObservers();
	}
	
	/**
	 * update the log - being severed 
	 * @return
	 */
	public String updateLog() {
		//System.out.println("Customer ID: " + CurrentCustomerID +  " order is being processed by Server: " + StaffID +" ("+ this.firstName +" " +this.lastName + ") This order contains " + unprocessedOrder.getItemsInBasket());
		String order = new String();
		order += "Customer ID: " + CurrentCustomerID +  " order is being processed by Server: " + StaffID +" ("+ this.firstName +" " +this.lastName + ") \n";
		order += String.format("Cost: £%.2f  Discount: £%.2f\n", this.cost, this.discount);
		order += "This order contains items: " + unprocessedOrder.getItemsInBasketString() + "\n";
		return order;
	}
	/**
	 * update log - finished serving
	 * @return
	 */
	
	public String updateLog2() {
		//System.out.println("Customer ID: " + CurrentCustomerID +  " has been processed");
		String order = new String();
		order += "The order for Customer: " + CurrentCustomerID + " has been processed by: " + StaffID;
		return order;
	}
	
	
	/**
	 * get stafff ID
	 * @return
	 */
	public int getStaffID() {
		return StaffID;
	}
	/**
	 * get staff first name
	 * @return
	 */
	public String getStaffFirstName() {
		return firstName;
	}
	/**
	 * get staff last name
	 * @return
	 */
	public String getStaffLastName() {
		return lastName;
	}
	/**
	 * set ID
	 * @param id
	 */
	public void setStaffID(int id) {
		this.StaffID = id;
	}
	/**
	 * set first name
	 * @param fName
	 */
	public void setStaffFirstName(String fName) {
		this.firstName = fName;
	}
	/**
	 * set last name
	 * @param lName
	 */
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
