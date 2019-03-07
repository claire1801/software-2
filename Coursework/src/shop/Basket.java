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
	private int currentStaffID; // should this be added to the constructor, i.e. will we know staff/customer before basket screen
	private int currentCustomerID; // will we need more than one basket ever? probably not
	private static final double MEAL_DEAL_DISCOUNT = 1.5;
	


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
	public void addItemToUnconfirmedOrder(MenuItems item) {   // setUnconfirmedOrder in class diagram
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
		
// 	
// 	/**
// 	 * 
// 	 * Discounts for meals deals, only full meal deals (i.e. meal drink and snack) count
// 	 */
//	private void getMealDealDiscount() {
//		int countOfFoodItems = 0, countOfSoftDrinks = 0, countOfSnacks = 0; // counters for the meal deal discount
//
//		for (MenuItems item : unconfirmedOrder) {
//			if(item.getID().substring(0, 5).equals("MEALS")) {
//				countOfFoodItems++;
//			}
//			else if(item.getID().substring(0, 5).equals("DRINK")) {
//				countOfSoftDrinks++;
//			}
//			else if(item.getID().substring(0, 5).equals("SNACK")) {
//				countOfSnacks++;
//			}
//		}
//		int numberOfFullMealDeals = Math.min(countOfFoodItems, Math.min(countOfSoftDrinks, countOfSnacks));
//		double mealDealDiscount = numberOfFullMealDeals*MEAL_DEAL_DISCOUNT;
//		discount += mealDealDiscount;
//	}
//	
//	
//	/**
//	 * 
//	 * Coffee is taken off the bill if it is the customers 5th coffee
//	 */
//	private void getCoffeeDiscount() {
//		double coffeeLoyaltyDiscount = 0;
//		Customer customer = Main.customerList.getCustomer(currentCustomerID);
//		int previousCoffees = customer.getNumberPreviousCoffees();
//		for(MenuItems item : unconfirmedOrder) 
//		{
//			previousCoffees = customer.getNumberPreviousCoffees();
//			if(item.getID().substring(0, 5).equals("COFEE") ) {
//				if( previousCoffees == 4) 
//				{
//					coffeeLoyaltyDiscount += item.getCost();
//					customer.setNumberPreviousCoffees(0);
//				} 
//				else 
//				{
//					customer.setNumberPreviousCoffees(previousCoffees + 1);
//				}
//			}
//			
//		}
//		discount += coffeeLoyaltyDiscount;
//	}
//	
//	/**
//	 * 
//	 * gets value of discount returned after member type reduction
//	 */
//	private void getMemberDiscount() {
//		double tempDiscountedBill = unDiscountedBill - discount;
//		Customer customer = Main.customerList.getCustomer(currentCustomerID);	
//		double memberDiscount = customer.getType().getDiscount() * tempDiscountedBill;
//		discount += memberDiscount;
//	}
	
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
//		this.getMealDealDiscount();
//		this.getCoffeeDiscount();
//		this.getMemberDiscount();
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
		for (MenuItems item: unconfirmedOrder) {
			int lastOrderID = orderList.totalSales(); //total sales returns relative size
			int id = lastOrderID ++;
			Order newOrder = new Order(id, currentCustomerID, time, item.getID(), item.getCost(), discount/numberOfItems,this.currentStaffID);
			// note at some point we should probably include staff id in with orders too
			orderList.addOrder(newOrder);  
		}
		this.clearBasket();
	}
	

	
}
