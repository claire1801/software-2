package shop;

import java.util.ArrayList;
import java.util.Random;

import customers.CustomerList;
import menu.MenuItems;
import menu.MenuList;
import orders.Order;
import orders.OrderList;
/**
 * queue class - uses singleton design pattern
 * this class may be accessed by multiple threads at one time hence some methods are synchronized.
 * @author samth
 *
 */
public class Queue {
	private static Queue instance;
	
	private ArrayList<Basket> queue;
	/**
	 * constructor
	 */
	private Queue() {
		this.queue = new ArrayList<Basket>();
	
	}
	/**
	 * get the instance of Queue
	 * @return Queue instance
	 */
	
	public static Queue getInstance() {
		if (instance == null)
			instance = new Queue();
		return instance;
	}
	/**
	 * loads in existing orders into the queue. must be called after orders are imported
	 */
	
	public void setupQueue() {
		OrderList ol = OrderList.getInstance();
		MenuList ml = MenuList.getInstance();
		ArrayList<Order> orderList = ol.getOrderList();
		//Queue queue = Queue.getInstance();
		int prevOrderID = 0;
		Basket basket = new Basket();
		for(Order order: orderList) {
			if(order.getOrderID() == prevOrderID) {
				MenuItems item = ml.getItem(order.getItemID());
				basket.addItemToUnconfirmedOrder(item);
			}
			else {
				this.addToQueue(basket);
				basket = new Basket();
				MenuItems item = ml.getItem(order.getItemID());
				basket.addItemToUnconfirmedOrder(item);
				prevOrderID = order.getOrderID();
			}
		}
		System.out.println(this.numberInQueue());
	}
	/**
	 * add an basket (customer) to the end of the queue. This method is synchronized
	 * @param newBasket - the basket to add
	 */
	public synchronized void addToQueue(Basket newBasket) {
		this.queue.add(newBasket);
	}
	/**
	 * returns the basket (customer) at the beginning of the queue ( index 0) and removes it from the queue. This method is synchronized
	 * @return the next basket in the queue
	 */
	public synchronized Basket getNextInQueue() {
		Basket nextBasket = this.queue.get(0);
		this.queue.remove(0);
		return nextBasket;
		
	}
	
	public int numberInQueue() {
		return queue.size();
	}
	
	public boolean queueEmpty(){
		if(this.queue.isEmpty()) {
			return false;		
		} else {
		return true;
		}
	}
		
	
	
	public void addRandomCustomer() {
		CustomerList cl = CustomerList.getInstance();
		
		double randomNumber = Math.random();
		int numberOfItems = (int) (randomNumber * 10) + 1;
		int customerId = (int) randomNumber * cl.getSize() + 1;
		for(int x = 0; x < numberOfItems;) {
			//loop and add items to basket  
		}
	}
	

}
