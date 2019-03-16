package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidStaffIDException;
import staff.Staff;
import staff.StaffList;

class StaffListTest {
	
	Staff staff = new Staff(123, "John", "Smith");
	StaffList staffList;
	
	@BeforeEach
	public void setup() {
		//staffList = new StaffList();
		//staffList.addStaffToList(staff);
		StaffList.getInstance().addStaffToList(staff);
		
	}
	
	@Test
	void testAddStaffToList() {
		// assertTrue(staffList.getStaffList().containsKey(123));
		assertTrue(StaffList.getInstance().size()>0);
		  
	}

	@Test
	void testRemoveStaffFromList() {
		//staffList.getStaffList().remove(123);
		try {
			StaffList.getInstance().removeStaffFromList(123);
		} catch (InvalidStaffIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(StaffList.getInstance().size()>0);
		//assertFalse(staffList.getStaffList().containsKey(123));
		
	}

}
