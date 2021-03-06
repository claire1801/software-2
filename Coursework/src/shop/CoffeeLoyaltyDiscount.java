package shop;

import menu.*;
import customers.*;

import java.util.ArrayList;

public class CoffeeLoyaltyDiscount extends Discount {


	public CoffeeLoyaltyDiscount(ArrayList<MenuItems> unconfirmedOrder, int customerID, double unDiscountedBill) {
		super(unconfirmedOrder, customerID, unDiscountedBill);
	}
	/**
	 * get discount due to coffee
	 */

	public double getDiscount() {
		double coffeeLoyaltyDiscount = 0;
		CustomerList customerList = CustomerList.getInstance();
		Customer customer = customerList.getCustomer(customerID);
		//int previousCoffees = customer.getNumberPreviousCoffees();
		for(MenuItems item : unconfirmedOrder) 
		{
			int previousCoffees = customer.getNumberPreviousCoffees();
			if(item.getID().substring(0, 5).equals("COFEE") && previousCoffees >= 4) 
			{
				coffeeLoyaltyDiscount += item.getCost();
				customer.setNumberPreviousCoffees(0);
			} 
			else if (item.getID().substring(0, 5).equals("COFEE"))
			{
				customer.setNumberPreviousCoffees(previousCoffees + 1);
			}
		}
		return coffeeLoyaltyDiscount;
	}
}


