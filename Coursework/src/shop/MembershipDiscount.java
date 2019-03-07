package shop;

import java.util.ArrayList;
import menu.*;
import customers.*;

public class MembershipDiscount extends Discount {


	
	public MembershipDiscount(ArrayList<MenuItems> unconfirmedOrder, int customerID, double tempBill) {
		super(unconfirmedOrder, customerID, tempBill);
		
	}
	
	
	public double getDiscount() {
		CustomerList customerList = CustomerList.getInstance();
		Customer customer = customerList.getCustomer(customerID);
		double memberDiscount = customer.getType().getDiscount() * this.unDiscountedBill;
		return memberDiscount; 
	}
}

