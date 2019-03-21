package orders;
/**
 * This is the OrderList class
 * 
 *
 */
import java.util.ArrayList;



public class OrderList {
	
	
	private ArrayList<Order> orderList;
	
	private static OrderList intstance;
	
	private OrderList() {
		orderList = new ArrayList<Order>();
	}
	
	public static OrderList getInstance() {
		if (intstance == null)
			intstance = new OrderList();
		return intstance;
	}
	

	public Iterable<Order> getAllOrders() {
		return orderList;
	}
	/**
	 * add order to list - synchronized as accessed by staff
	 * @param order
	 */
	public synchronized void addOrder(Order order)
	{
	
		
		if (order == null) {
			throw new IllegalStateException("Cannot add order to orderList as order object has value null");
		}
		else if (!(order instanceof Order))
		{
			throw new IllegalArgumentException("Cannot add order to orderList as order object is not of Order type");
		} else {
			orderList.add(order);
		}
		
	}
	/**
	 * remove order - synchronized
	 * @param order
	 */
	
	public synchronized void removeOrder(Order order)
	{
		
		
		if (orderList.size() == 0)
		{
			throw new IndexOutOfBoundsException("Cannot remove order, orderList is empty");
		}else {
			orderList.remove(order);
		}
	}
	
	public  synchronized int totalSales() {
		return orderList.size();
	}
	
	public  synchronized double totalIncome() {
		double total = 0;
		for(Order order : orderList) {
			total += order.getCost();
		}
		
		return total;
	}
	
	public String writeReport() {
		String report = new String();
		for(Order order : orderList) {
			report += order.getOrderID();
			report += "/";
			report += order.getCustomerID();
			report += "/";
			report += order.getTimeStamp();
			report += "/";
			report += order.getItemID();
			report += "/";
			report += String.format("%,.2f",order.getCost());
			report += "/";
			report += String.format("%,.2f", order.getDiscountAmount());
			report += "/";
			report += order.getStaffID();
			report += "\n";
		}

		
		return report;
	}
		
		
		
		//if (orderList.size() == 0)
		//{
		//	throw new IndexOutOfBoundsException("Cannot generate order report, orderList is empty");
		//}
		
	public synchronized int getNextOrderID() {
		int size = orderList.size() - 1;
		return orderList.get(size).getOrderID()+1;
	}
	
	
}

