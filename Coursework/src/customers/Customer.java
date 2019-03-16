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
		if(customerID < 1 || customerID > 2000000) {
			throw new InvalidCustomerIDException("Customer ID is not valid, must be between 1 and 2000000");
		}
		
		if(numberPreviousCoffees < 0 || numberPreviousCoffees > 4) {
			throw new IllegalStateException("Number of previous coffees must be between 0 and 4");
		}
		
		if(name.length() == 0) {
         		throw new IllegalStateException("Name can not be blank");
		}
		
	
		this.customerID = customerID;
		this.member = member;
		this.numberPreviousCoffees = numberPreviousCoffees;
		this.name = name;
	}

	/**
	 * Getters and setters for each of the instance variables
	 * 
	 */
	public int getCustomerId() {
		return customerID;
	}

	public void setCustomerId(int customerId) {
		this.customerID = customerId;
	}

	public int getNumberPreviousCoffees() {
		return numberPreviousCoffees;
	}

	public  synchronized void setNumberPreviousCoffees(int numberPreviousCoffees) {
		this.numberPreviousCoffees = numberPreviousCoffees;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public MembershipType getType() {
		return member;
	}

	public void setType(MembershipType member) {
		this.member = member;
	}	
}
