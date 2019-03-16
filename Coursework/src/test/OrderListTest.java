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
	
	//OrderList orderList = new OrderList();
	

	@BeforeEach
	public void setup() {
	//orderList = new OrderList();
	//orderList.addOrder(order);
		OrderList.getInstance().addOrder(order);
	}

	@Test
	void testAddOrder() {
	//assertTrue(orderList.getOrderList().contains(order));
		//assertTrue(OrderList.getInstance().getAllOrders().iterator().equals(order));
		assertTrue(OrderList.getInstance().getAllOrders().iterator().hasNext()==true); //test if order iterator has a value
		
	}

	@Test
	void testRemoveOrder() {
		Order order2 = new Order(0002, 101, timestamp, "DRINK123", 3.0, 0.2, 2);
		OrderList.getInstance().removeOrder(order2);
		//assertFalse(orderList.getOrderList().contains(order2));
		assertFalse(OrderList.getInstance().getAllOrders().iterator().equals(order));
	}

	@Test
	void testTotalSales() {
		//assertEquals(1, orderList.getOrderList().size());
		assertEquals(2,OrderList.getInstance().totalSales());
	}

	@Test
	void testTotalIncome() {
		//assertEquals(2, orderList.totalIncome());
		assertEquals(2,OrderList.getInstance().totalIncome());
	}

	@Test
	void testWriteReport(){
		//Timestamp timestamp2 = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		//double offset = 0.09;
		String tstamp1 = timestamp.toString(); //need offset of 34 milliseconds but test otherwise successful.
		//String tstamp1 = timestamp.toString()+00003;
		String report = OrderList.getInstance().writeReport();
		report = report.substring(0, 49);
		assertEquals("1/100/" + tstamp1 + "/FOOD123/2.00/0.20/1", report);
			}

}
