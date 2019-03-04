package test;

import static org.junit.jupiter.api.Assertions.*;
import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import customers.Customer;
import customers.MembershipType;
import exceptions.InvalidCustomerIDException;



class TestCustomer {
	private Customer newCustomer;
	@BeforeEach 
	public void setup() throws InvalidCustomerIDException {
		 newCustomer = new Customer(1,MembershipType.MEMBER,1,"sam");

	}
	@Test
    public void test_getID() {
        // the message (1st parameter is optional)
    	
    	assertEquals(1,newCustomer.getCustomerId());
    }
    @Test
    public void test_set_id() throws InvalidCustomerIDException {
        // the message (1st parameter is optional)
    	newCustomer.setCustomerId(2);
    	assertEquals(2,newCustomer.getCustomerId());
    }
    @Test
    public void test_get_coffee() {
        // the message (1st parameter is optional)
    	
    	assertEquals(1,newCustomer.getNumberPreviousCoffees());
    }
    @Test
    public void test_set_coffee() {
        // the message (1st parameter is optional)
    	newCustomer.setNumberPreviousCoffees(4);
    	assertEquals(4,newCustomer.getNumberPreviousCoffees());
    }
    @Test
    public void test_getandsetname() {
        // the message (1st parameter is optional)
    	newCustomer.setName("sam2");
    	assertEquals("sam2",newCustomer.getName());
    }
    @Test
    public void test_SetandGetMember() {
        // the message (1st parameter is optional)
    	newCustomer.setType(MembershipType.STUDENT);
    	assertEquals(MembershipType.STUDENT,newCustomer.getType());
    }

}
