package customers;

import exceptions.InvalidCustomerIDException;

public class Customer {
	

	/**
	 * Variables for Customer
	 * 
	 * customerID : unique identifier for customer
	 * member     : each customer has a different membership type
	 * numberPrevious coffees   : how many coffees a customer has previously ordered
	 * name                     : customer's name
	 * 
	 */
	private int customerID; 
	private MembershipType member; 
	private int numberPreviousCoffees;
	private String name;
	
	/**
	 * Constructor for creating a customer object
	 * 
	 * @param customerID
	 * @param member
	 * @param numberPreviousCoffees
	 * @param name
	 * @throws InvalidCustomerIDException 
	 */
	public Customer(int customerID, MembershipType member, int numberPreviousCoffees, String name) throws InvalidCustomerIDException {
		
		
		
		
		
	
		this.setCustomerId(customerID);
		this.setType(member);
		this.setNumberPreviousCoffees(numberPreviousCoffees);
		this.setName(name);
	}

	/**
	 * Getters and setters for each of the instance variables
	 * 
	 */
	public int getCustomerId() {
		return customerID;
	}
	/**
	 * set customer ID
	 * @param customerId int - ID
	 * @throws InvalidCustomerIDException
	 */

	public void setCustomerId(int customerId) throws InvalidCustomerIDException {
		if(customerId < 1 || customerId > 2000000) {
			throw new InvalidCustomerIDException("Customer ID is not valid, must be between 1 and 2000000");
		
		}else {
			this.customerID = customerId;
		}
		
	}
/**
 * get number of coffees - used for discount
 * @return int - coffees
 */
	public int getNumberPreviousCoffees() {
		return numberPreviousCoffees;
	}
	/**
	 * set number of coffees
	 * @param numberPreviousCoffees
	 */
	public  synchronized void setNumberPreviousCoffees(int numberPreviousCoffees) {
		if(numberPreviousCoffees < 0 || numberPreviousCoffees > 4) {
			throw new IllegalStateException("Number of previous coffees must be between 0 and 4");
		} else {
			this.numberPreviousCoffees = numberPreviousCoffees;
		}
		
	}
	/**
	 * get name of customer
	 * @return String name
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * set name of customer (first and second)
	 * @param name - String
	 */
	public void setName(String name) {
		if(name.length() == 0) {
     		throw new IllegalStateException("Name can not be blank");
		}	else {
			this.name = name;
		}
	}
	
	/**
	 * return membership type
	 * @return
	 */

	public MembershipType getType() {
		return member;
	}
	/**
	 * set membership type
	 * @param member
	 */

	public void setType(MembershipType member) {
		this.member = member;
	}	
}
