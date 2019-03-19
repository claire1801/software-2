package shop;

import java.util.ArrayList;
import menu.*;

public class MealDealDiscount extends Discount {
	
	private static final double MEAL_DEAL_DISCOUNT = 1.5;

	
	public MealDealDiscount(ArrayList<MenuItems> unconfirmedOrder, int customerID, double unDiscountedBill) {
		super(unconfirmedOrder, customerID, unDiscountedBill);
	}
	
	/**
	 * get discount for meal deals
	 */
	public double getDiscount() {
		if (super.unconfirmedOrder.size() <3) {
			return 0;
		}
		int countOfFoodItems = 0, countOfSoftDrinks = 0, countOfSnacks = 0; // counters for the meal deal discount

		for (MenuItems item : super.unconfirmedOrder) {
			if(item.getID().substring(0, 5).equals("MEALS")) {
				countOfFoodItems++;
			}
			else if(item.getID().substring(0, 5).equals("DRINK")) {
				countOfSoftDrinks++;
			}
			else if(item.getID().substring(0, 5).equals("SNACK")) {
				countOfSnacks++;
			}
		}
		int numberOfFullMealDeals = Math.min(countOfFoodItems, Math.min(countOfSoftDrinks, countOfSnacks));
		double mealDealDiscount = numberOfFullMealDeals*MEAL_DEAL_DISCOUNT;
		return mealDealDiscount;
	}

}