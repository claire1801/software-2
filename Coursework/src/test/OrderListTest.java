package test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Timestamp;
import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orders.Order;
import orders.OrderList;

class OrderListTest {

	Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	
	Order order = new Order(1, 100, timestamp, "FOOD123", 2.00, 0.20, 1);
	
	OrderList orderList = new OrderList();
	

	@BeforeEach
	public void setup() {
	orderList = new OrderList();
	orderList.addOrder(order);
	}

	@Test
	void testAddOrder() {
	assertTrue(orderList.getOrderList().contains(order));
	}

	@Test
	void testRemoveOrder() {
		Order order2 = new Order(0002, 101, timestamp, "DRINK123", 3.0, 0.2, 2);
		orderList.removeOrder(order2);
		assertFalse(orderList.getOrderList().contains(order2));
	}

	@Test
	void testTotalSales() {
		assertEquals(1, orderList.getOrderList().size());
	}

	@Test
	void testTotalIncome() {
		assertEquals(2, orderList.totalIncome());
	}

	@Test
	void testWriteReport(){
		String tstamp1 = timestamp.toString();
		String report = orderList.writeReport();
		report = report.substring(0, 49);
		assertEquals("1/100/" + tstamp1 + "/FOOD123/2.00/0.20/1", report);
	}

}
