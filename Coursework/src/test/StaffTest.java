package test;
import static org.junit.jupiter.api.Assertions.*;

//import main.StaffList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import staff.Staff;

class StaffTest {
	private int staffID = 123; 
	private String firstName = "John";
	private String lastName = "Smith";
	
	private Staff s;
	
	@BeforeEach 
	public void setup() {
		s = new Staff(staffID, firstName, lastName);
	}

	@Test
	public void testGetStaffID() {
		assertEquals(staffID, s.getStaffID());
	}

	@Test
	public void testGetStaffFirstName() {
		assertEquals(firstName, s.getStaffFirstName());
	}

	@Test
	public void testGetStaffLastName() {
		assertEquals(lastName, s.getStaffLastName());
	}
	
	@Test
	public void testSetStaffID() {
	s.setStaffID(321);
	assertEquals(321,s.getStaffID());	
	}
	
	@Test
	public void testSetStaffFirstName() {
	s.setStaffFirstName("Mary");
	assertEquals("Mary",s.getStaffFirstName());	
	}
	
	@Test
	public void testSetStaffLastName() {
	s.setStaffLastName("McDonald");
	assertEquals("McDonald",s.getStaffLastName());	
	}
	
}
