package shop;


import menu.*;
import java.util.ArrayList;

public abstract class Discount {
	protected ArrayList<MenuItems> unconfirmedOrder;
	protected int customerID;
	protected double unDiscountedBill;

	protected Discount(ArrayList<MenuItems> unconfirmedOrder, int customerID, double unDiscountedBill) {
		this.unconfirmedOrder = unconfirmedOrder;
		this.customerID = customerID;
		this.unDiscountedBill = unDiscountedBill;		
	} 

	public abstract double getDiscount();

}
