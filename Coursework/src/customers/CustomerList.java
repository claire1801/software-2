package customers;
import java.util.Hashtable;

import menu.MenuList;


public class CustomerList {
	
	/**
	 * Variable for Customer List class
	 * 
	 * customerList : List of customers with an integer as the key and a customer object for the value
	 */
	private Hashtable<Integer, Customer> customerList;
	
	
	private static CustomerList customers;

	/**
	 * Constructor for creating a hash table for customer list
	 */
	private CustomerList() {
		customerList = new Hashtable<Integer, Customer> ();
	}
	
	/**
	 * return the instance of CustomerList
	 * @return
	 */
	public static CustomerList getInstance() {
		if (customers == null) 
			customers = new CustomerList();
		return customers;
	}
	

	/**
	 * Add a customer to customer list
	 * @param key
	 * @param value
	 */
	public  synchronized void addCustomer(Integer key, Customer value) {
		
		if(value == null) {
			throw new IllegalArgumentException ("Can not add a new customer as not all the details are provided");
		}
		else if(key == null) {
			throw new IllegalArgumentException ("Key must contain a customer ID");
		}	else {
			customerList.put(key, value);
		}
	}

	/**
	 * Get a customer from the list
	 * @param key
	 * @return key
	 */
	public Customer getCustomer(Integer key) {
        return customerList.get(key);
	}
	
	/**
	 * Remove a customer from the list
	 * @param key
	 */
	public void removeCustomer(Integer key) {
		
		if (customerList.containsKey(key)) {
			customerList.remove(key);
		}
		else  {
			throw new IllegalArgumentException ("Customer ID doesn't exist");
		}
	}  
	/**
	 * does customer exist
	 * @param key - the ID 
	 * @return true , false
	 */
	public boolean customerExists(int key) {
		if(customerList.containsKey(key)) {
			return true;
		}
		else {
			return false;
		}
	}
	public int getSize() {
		return customerList.size();
	}
	
	
	/**
	 * Write a report of all the customers in customer list with their details
	 * @return report
	 */
	public String writeReport() {
		String report = new String();
		
		for(Customer customer: customerList.values()) {
			report += customer.getName() + "/";
			report += customer.getCustomerId() + "/";
			report += customer.getNumberPreviousCoffees()+ "/";
			report += customer.getType() + "\n";	
		}
//		for(Entry<Integer, Customer> customer : customerList.entrySet()){
//		//report += customer.getValue();
//		report += ((Customer) customer).getName() + "/";
//		report += customer.getKey() + "/";
//		report += ((Customer) customer).getType() + "/";
//		report += ((Customer) customer).getNumberPreviousCoffees();
//	
//		}
		
		return report;
				
	}
}


