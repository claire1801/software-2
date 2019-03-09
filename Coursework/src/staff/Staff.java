package staff;


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
	private static final int orderTime = 5000; //milliseconds
	// could possibly add wage, no. of sales etc.
	
	
	public Staff(int StaffID, String firstname, String lastname) {
		this.StaffID  = StaffID;
		this.firstName = firstname;
		this.lastName = lastname;				
	}
	
	public Staff(int StaffID, String firstname, String lastname, Queue queue) {
		this.StaffID  = StaffID;
		this.firstName = firstname;
		this.lastName = lastname;
		this.queue = queue;
	}
	
	public void run() {
		
		while(!queue.queueEmpty()) {
			try {
				 System.out.println ("Thread " + Thread.currentThread().getId() + " is running");
				processOrder();
				unprocessedOrder.confirmedAndPaid();
				updateLog2();
				
			} catch (InterruptedException e) {
				
			}
		}
	
	}
	
	private void processOrder() throws InterruptedException {
		unprocessedOrder = queue.getNextInQueue();
		updateLog();
		Thread.sleep(orderTime);
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

}
