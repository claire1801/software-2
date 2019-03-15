package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import customers.Customer;
import customers.CustomerList;
import customers.MembershipType;
import exceptions.InvalidCustomerIDException;
import main.*;

class TestCustomerList {
	private CustomerList b;

	@BeforeEach 
	public void setup() throws InvalidCustomerIDException {
		Customer newCustomer = new Customer(1,MembershipType.MEMBER,1,"sam");
    	//list.put(1, newCustomer);
       // b = new CustomerList();
      //  b.addCustomer(1, newCustomer);
		CustomerList.getInstance().addCustomer(1, newCustomer);
	}
	 @Test
	    public void test_report() {
	        // the message (1st parameter is optional)
	    	
	    	String Report = CustomerList.getInstance().writeReport();
	    	Report = Report.substring(0, 14);
	        assertEquals("sam/1/1/MEMBER", Report);
	    }
	    
	    @Test
	    public void test_get_customer() throws InvalidCustomerIDException {
	        // the message (1st parameter is optional)
	    	Customer newCustomer = new Customer(2,MembershipType.MEMBER,2,"Bob");
	    	CustomerList.getInstance().addCustomer(2, newCustomer);
	    	
	        assertEquals(newCustomer, CustomerList.getInstance().getCustomer(2));
	    }
	    @Test
	    public void test_get_customer2() throws InvalidCustomerIDException {
	        // the message (1st parameter is optional)
	    	Customer newCustomer = new Customer(2,MembershipType.MEMBER,2,"Bob");
	    	CustomerList.getInstance().addCustomer(2, newCustomer);
	    	//System.out.println(b.getCustomer(3));
	        assertEquals(null, CustomerList.getInstance().getCustomer(3));
	    }
	    
	    @Test
	    public void test_Remove_customer() throws InvalidCustomerIDException {
	        // the message (1st parameter is optional)
	    	Customer newCustomer = new Customer(2,MembershipType.MEMBER,2,"Bob");
	    	CustomerList.getInstance().addCustomer(2, newCustomer);
	    	CustomerList.getInstance().removeCustomer(2);
	    	//System.out.println(b.getCustomer(3));
	        assertEquals(null, CustomerList.getInstance().getCustomer(2));
	    }
	    @Test
	    public void test_Add_null_customer() throws InvalidCustomerIDException {
	        // the message (1st parameter is optional)
	    	
	    	try {
	    		Customer newCustomer = new Customer(2,MembershipType.MEMBER,2,"Bob");
	    		CustomerList.getInstance().addCustomer(null, newCustomer);
	    		fail("should not be here");
	    	}catch(IllegalArgumentException e){
	    		assertTrue(e.getMessage().contains("contain"));

	    	}
	    	//System.out.println(b.getCustomer(3));
	        
	    }
	    
	 
	    
	    
	    @Test
	    public void test_remove_non_customer() {
	        // the message (1st parameter is optional)
	    	
	    	try {
	    		CustomerList.getInstance().removeCustomer(5);
	    		fail("should not be here");
	    	}catch(IllegalArgumentException e3){
	    		assertTrue(e3.getMessage().contains("ustomer ID doesn't exist"));

	    	}
	    	//System.out.println(b.getCustomer(3));
	        
	    }
	    
	    @Test
	    public void test_customerExists1() {

	    	assertEquals(true,CustomerList.getInstance().customerExists(1));
 
	    }
	    @Test
	    public void test_customerExists2() {

	    	assertEquals(false,CustomerList.getInstance().customerExists(2));
 
	    }


}
