package shop;
import java.sql.Timestamp;
import java.util.ArrayList;


import customers.*;
import main.Main;
import menu.*;
import orders.*;

/**
 * This is the basket class - not case!
 * 
 * It will store orders before they have been committed to the OrderList
 * @author frsrg
 *
 */


public class Basket {

	/**
	 * Global variable used in the Basket class
	 * 
	 * unconfirmedOrder		:			the array list of menu items in basket
	 * unDiscountBill		:			total bill without discount
	 * totalDiscount		:			total value of discount to be applied
	 * finalBill			:			final bill, i.e. total bill - discount
	 * staffID				:			ID of staff member for transaction
	 * customerID			:			ID of customer for transaction
	 * MEAL_DEAL_DISCOUNT	:			deduction applied when a qualifying meal deal is purchased
	 * 
	 */
	private ArrayList<MenuItems> unconfirmedOrder;
	private double unDiscountedBill = 0;
	private double discount = 0;
	private double finalBill = 0;
	private int currentStaffID;
	private int currentCustomerID; 
	private static final double MEAL_DEAL_DISCOUNT = 1.5;
	private boolean online = false;
	


	/**
	 * 
	 * Simple Basket constructor of ArrayList
	 */
	public Basket() {
		unconfirmedOrder = new ArrayList<MenuItems> ();
	}
		
	
	/**
	 * add menu item to the basket
	 * @param item
	 */
	public void addItemToUnconfirmedOrder(MenuItems item) { 
		unconfirmedOrder.add(item);
		unDiscountedBill += item.getCost();
	}
	
	
	/**
	 * clear the contents of the basket once the order has been confirmed / cancelled
	 */
	public void clearBasket() {
		unconfirmedOrder.clear();
		this.currentCustomerID = 0;
		this.currentStaffID = 0;
		this.unDiscountedBill = 0;
		this.discount = 0;
		this.finalBill = 0;		
	}

	/**
	 * sets the baskets current customer ID
	 * @param id
	 */
	public void setCurrentCustomerID(int id) {
		this.currentCustomerID = id;
	}
	
	/**
	 * sets the baskets current customer ID
	 * @param id
	 */
	public void setCurrentStaffID(int id) {
		this.currentStaffID = id;
	}
	
	/**
	 * gets the basket arraylist as an iterable list
	 * @return iterable basket list
	 */
	public Iterable<MenuItems> getItemsInBasket(){
		return unconfirmedOrder;
	}
	/**
	 * get all items in basket as string
	 * @return
	 */
	public String getItemsInBasketString(){
		String items = "";
		for(MenuItems item: unconfirmedOrder) {
			items += item.getName() + ", ";
		}
		return items;
	}
		

	
	/**
	 * works out the total discount
	 * 
	 * @return discount
	 */
	public double getTotalDiscount() {
		Discount mealDealDiscount = new MealDealDiscount(this.unconfirmedOrder, this.currentCustomerID, this.unDiscountedBill);
		Discount coffeeLoyaltyDiscount = new CoffeeLoyaltyDiscount(this.unconfirmedOrder, this.currentCustomerID, this.unDiscountedBill);
		double tempBill = unDiscountedBill - mealDealDiscount.getDiscount() - coffeeLoyaltyDiscount.getDiscount();
		Discount membershipDiscount = new MembershipDiscount(this.unconfirmedOrder, this.currentCustomerID, tempBill);
		tempBill -= membershipDiscount.getDiscount();
		discount = unDiscountedBill - tempBill;

		return discount;
	}
	
	
	/**
	 * works out the actual cost to pay
	 * 
	 * @return finalBill
	 */
	public double getFinalBill() {
		finalBill = unDiscountedBill - discount;
		return finalBill;
		}
	
	
	/**
	 * creates orders from the basket once confirmed, paid and sends them to the order list
	 * 
	 */
	public void confirmedAndPaid() {
		OrderList orderList = OrderList.getInstance();
		int numberOfItems = unconfirmedOrder.size();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		int id = orderList.getNextOrderID(); //total sales returns relative size
		
		for (MenuItems item: unconfirmedOrder) {
			
			Order newOrder = new Order(id, currentCustomerID, time, item.getID(), item.getCost(), discount/numberOfItems,this.currentStaffID);
			orderList.addOrder(newOrder);  
		}
		this.clearBasket();
	}
	/**
	 * get the customer ID for this basket
	 * @return int ID
	 */
	public int getCurrentCustomerID() {
		return this.currentCustomerID;
	}
	/**
	 * is the basket online (true/false)
	 * @return boolean
	 */
	public boolean getOnline() 
	{
		return this.online;
	}
	/**
	 * set online 
	 * @param online
	 */
	public void setOnline(boolean online)  
	{
		this.online = online;
	}
	/**
	 * number of items in basket
	 * @return
	 */
	
	public int numberOfItems() {
		return unconfirmedOrder.size();
	}

	

	
}
