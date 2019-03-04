package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import staff.Staff;
import staff.StaffList;

class StaffListTest {
	
	Staff staff = new Staff(123, "John", "Smith");
	StaffList staffList;
	
	@BeforeEach
	public void setup() {
		staffList = new StaffList();
		staffList.addStaffToList(staff);
		
	}
	
	@Test
	void testAddStaffToList() {
		 assertTrue(staffList.getStaffList().containsKey(123));
		  
	}

	@Test
	void testRemoveStaffFromList() {
		staffList.getStaffList().remove(123);
		assertFalse(staffList.getStaffList().containsKey(123));
	}

}
