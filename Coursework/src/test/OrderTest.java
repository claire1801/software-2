package test;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orders.Order;

class OrderTest {
	
	private int orderID = 0001;
	private int customerID = 123;
	private Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	private String itemID = "FOOD123";
	private double cost = 1.50;
	private double discountAmount = 0.10;
	private int staffID = 1;

	private Order order;
	
	@BeforeEach
	public void setup() {
	order = new Order(orderID, customerID, timestamp, itemID, cost, discountAmount, staffID);
	}
	
	
	@Test
	public void testGetOrderID() {
		assertEquals(orderID, order.getOrderID());
		
	}

	@Test
	void testGetCustomerID() {
		assertEquals(customerID, order.getCustomerID());
	}

	@Test
	void testGetTimeStamp() {
		assertEquals(timestamp, order.getTimeStamp());
	}

	@Test
	void testGetItemID() {
		assertEquals(itemID, order.getItemID());
	}

	@Test
	void testGetCost() {
		assertEquals(cost, order.getCost(), 0);
	}

	@Test
	void testGetDiscountAmount() {
		assertEquals(discountAmount,order.getDiscountAmount(), 0);
	}
	
	@Test
	void testGetStaffID() {
		assertEquals(staffID, order.getStaffID(), 0);
	}
	
	@Test
	public void testSetOrderID() {
		order.setOrderID(4567);
		assertEquals(4567, order.getOrderID(), 0);
	}

	@Test
	void testSetCustomerID() {
		order.setCustomerID(456);
		assertEquals(456, order.getCustomerID());
	}

	@Test
	void testSetTimeStamp() {
		order.setTimeStamp(timestamp);
		assertEquals(timestamp, order.getTimeStamp());
	}

	@Test
	void testSetItemID() {
		order.setItemID("SNACK456");
		assertEquals("SNACK456", order.getItemID());
	}

	@Test
	void testSetCost() {
		order.setCost(4.00);
		assertEquals(4.00, order.getCost(), 0);
	}

	@Test
	void testSetDiscountAmount() {
		order.setDiscount(0.50);
		assertEquals(0.50,order.getDiscountAmount(), 0);
	}

}
