package shop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import customers.CustomerList;
import menu.MenuItems;
import menu.MenuList;
import main.Log;
import main.Observer;
import main.Subject;
import orders.Order;
import orders.OrderList;
/**
 * queue class - uses singleton design pattern
 * this class may be accessed by multiple threads at one time hence some methods are synchronized.
 * @author samth
 *
 */
public class Queue implements Subject {
	private static Queue instance;
	
	private ArrayList<Basket> queue;
	private boolean shopOpen = false;
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
	 * shop open true/false
	 * @return boolean - true/false
	 */
	
	public synchronized boolean getShopOpen() {
		return this.shopOpen;
	}
	
	/**
	 * set the to be open (true) or closed (false)
	 * @param open - boolean 
	 */
	public synchronized void setShopOpen(boolean open) {
		 this.shopOpen = open;
	}
	
	
	/**
	 * loads in existing orders into the queue. must be called after orders are imported
	 */
	public void setupQueue() {
		OrderList ol = OrderList.getInstance();
		MenuList ml = MenuList.getInstance();
		Iterable<Order> orderList = ol.getAllOrders();
		
		int prevOrderID = 0;
		Basket basket = new Basket();
		for(Order order: orderList) {
			basket.setCurrentCustomerID(order.getCustomerID());
			if(order.getOrderID() == prevOrderID || prevOrderID == 0) {
				MenuItems item = ml.getItem(order.getItemID());
				basket.addItemToUnconfirmedOrder(item);
				prevOrderID = order.getOrderID();
			}
			else {
				this.addToQueue(basket);
				basket = new Basket();
				MenuItems item = ml.getItem(order.getItemID());
				basket.addItemToUnconfirmedOrder(item);
				prevOrderID = order.getOrderID();
			}
		}
	
	}
	
	
	/**
	 * add an basket (customer) to the end of the queue. This method is synchronized
	 * @param newBasket - the basket to add
	 */
	public synchronized void addToQueue(Basket newBasket) {
		if (newBasket.getOnline() == true) {
			this.queue.add(0, newBasket);
		}else {
			this.queue.add(newBasket);
		}
		System.out.println(this.numberInQueue());

		
	}
	
	
	
	/**
	 * returns the basket (customer) at the beginning of the queue ( index 0) and removes it from the queue. This method is synchronized
	 * @return the next basket in the queue
	 */
	public synchronized Basket getNextInQueue() {
		Basket nextBasket = this.queue.get(0);
		this.queue.remove(0);
		//System.out.println("no in queue" + this.numberInQueue());
		return nextBasket;
		
	}
	
	/**
	 * get the details of all baskets in queue
	 * @return string - of details
	 */
	
	
	public synchronized String getQueueDetails() {
		String output = "Current Queue...\n";
		for (Basket b:queue) {
			String orderType = "";
			if(b.getOnline()) {
				orderType += "Online";
			} else { orderType += "In store";}
			output += String.format("Customer %d with %d items. %s order.\n", b.getCurrentCustomerID(), b.numberOfItems(), orderType);
		}
		return output;
	}
	
	/**
	 * Gives the number of baskets in the queue
	 * @return int - number of baskets in queue
	 */
	
	public synchronized int numberInQueue() {
		return queue.size();
	}
	/**
	 * is the queue empty
	 * @return true/false
	 */
	public synchronized boolean queueEmpty(){

		return this.queue.isEmpty();
	}
	/**
	 * creates a new random basket and adds it to the queue
	 */
	
	public void addRandomCustomer() {
		Log log = Log.getInstance();
		Basket basket = new Basket();
		CustomerList cl = CustomerList.getInstance();
		MenuList ml = MenuList.getInstance();
		double randomNumber = Math.random();
		int numberOfItems = (int) (randomNumber * 5) + 1;
		randomNumber = Math.random();
		int customerId = (int) (randomNumber * cl.getSize() + 1) ;
		if(cl.customerExists(customerId)== false) {
			customerId = 1;
		}
		randomNumber = Math.random();
		if (randomNumber <= 0.1) { 
			basket.setOnline(true); }
			else { basket.setOnline(false); }

		
		basket.setCurrentCustomerID(customerId);
	
		for(int x = 0; x < numberOfItems; x++) {
			MenuItems item = ml.getRandomItem();
			basket.addItemToUnconfirmedOrder(item);
		}
		log.writeToFile("Customer " + basket.getCurrentCustomerID() + " was added to the queue \n");	
		this.addToQueue(basket);
	
	}
	
	/**
	 * List to hold any observers
	 */
	private List<Observer> registeredObservers = new LinkedList<Observer>();

	/**
	 * Register an observer with this subject
	 */
	public void registerObserver(Observer obs) {
		registeredObservers.add(obs);
	}

	/**
	 * De-register an observer with this subject
	 */
	public void removeObserver(Observer obs) {
		registeredObservers.remove(obs);
	}

	/**
	 * Inform all registered observers that there's been an update
	 */
	public void notifyObservers() {
		for (Observer obs : registeredObservers)
			obs.update();
	}
	

}
