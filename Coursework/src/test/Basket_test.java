package test;

import static org.junit.Assert.*;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import customers.Customer;
import customers.CustomerList;
import customers.MembershipType;
import exceptions.InvalidCustomerIDException;
import exceptions.InvalidItemIdentifierException;
import main.Main;
import menu.Drinks;
import menu.Meals;
import menu.MenuItems;
import menu.Snacks;
import orders.OrderList;
import shop.Basket;

class Basket_test {
	
	private Basket basket;
	private Timestamp currentTimestamp;
//	private MembershipType EMPLOYEE;
//	private MembershipType MEMBER;
//	private MembershipType STUDENT;
	//public Customer(int customerID, MembershipType member, int numberPreviousCoffees, String name)
	
	
	
	//1/EMPLOYEE/0/Olton Chambers
	//private Customer customer1;
	//private Customer customer3 = new Customer(3,)
	// Scarlett Gillespie/16/4/STUDENT
	private Customer customer16;
	//Alvin Pennington/42/0/MEMBER
	private Customer customer42;
	
	
	
	//public Drinks(String name, String id, double cost, String desc, String algns) 	
	//public Meals(String name, String id, double cost, String desc, String algns)
	//public Snacks(String name, String id, double cost, String desc, String algns) 
	
	//Chocolate Muffin/SNACK001/2.5/Chocolate Muffin/Milk
//	private Order item3 = new Order(3, 1, currentTimestamp , "SNACK001", 2.5, 0.0);
	private MenuItems item1;
	
//Diet Coke/DRINK023/1.5/500ml bottle of Diet Coke./None
//	private Order item4 = new Order(4, 1, currentTimestamp , "DRINK023", 1.5, 0.0);
	private MenuItems item2;
	//Large Americano/COFEE001/3.0/Espresso shots topped with hot water to produce a light layer of crema./None
//	private Order item5 = new Order(5, 34, currentTimestamp , "COFEE001", 3.0, 0.0);
	private MenuItems item3;
	//Wholegrain Toast/MEALS014/2.0/Crunchy toast./None
//	private Order item9 = new Order(9, 13, currentTimestamp , "MEALS014", 2.0, 0.0);
	private MenuItems item4;
	private int i;
	private Customer customer1;
	@BeforeEach
	public void setUp() {
		i = 10;
		basket = new Basket();
		try {
			
			
			currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			
			
			item1 = new Snacks("Chocolate Muffin", "SNACK001", 2.5, "Chocolate Muffin", "Milk");
			item2 = new Drinks("Diet Coke", "DRINK023", 1.5, "500ml bottle of Diet Coke", "None");
			item3 = new Drinks("large Americano", "COFEE001", 3.0,
					"Espresso shots topped with hot water to produce a light layer of crema", "None");
			item4 = new Meals("Wholegrain Toast", "MEALS014", 2.0, "Crunchy toast", "None");

		}

		catch (InvalidItemIdentifierException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestgetItemsInBasket()
	{
		basket.addItemToUnconfirmedOrder(item1);
		Iterable<MenuItems> actual = basket.getItemsInBasket();
		ArrayList<MenuItems> expected = new ArrayList<MenuItems>();
		expected.add(item1);
		
		Assert.assertEquals(expected, actual);
	}

	
	@Test
	public void TestgetTotalDiscountForMealDeal()
	{
//		
		try {
			customer1 = new Customer(1, MembershipType.EMPLOYEE, 0, "Olton Chambers");
			assertNotNull(customer1);
			}catch(InvalidCustomerIDException e)
			{
				e.printStackTrace();
			}
		CustomerList.getInstance().addCustomer(1, customer1);
		basket.setCurrentCustomerID(1);
		basket.setCurrentStaffID(001);
		basket.addItemToUnconfirmedOrder(item1);
		basket.addItemToUnconfirmedOrder(item2);
    	basket.addItemToUnconfirmedOrder(item4);
		double actual = basket.getTotalDiscount();
		double expected = 2.625;
		double delta = 0.1;
		Assert.assertEquals(expected, actual, delta);
	}
	
	@Test
	public void TestgetTotalDiscountForCoffee()
	{
		try {
			customer16 = new Customer(16, MembershipType.STUDENT, 4, "Scarlett Gillespie");
			assertNotNull(customer16);
			}catch(InvalidCustomerIDException e)
			{
				e.printStackTrace();
			}
		CustomerList.getInstance().addCustomer(16, customer16);
		basket.setCurrentCustomerID(16);
		basket.setCurrentStaffID(001);
		basket.addItemToUnconfirmedOrder(item3);
		double actual = basket.getTotalDiscount();
		double expected = 3;
		double delta = 0.1;
		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void TestgetDiscount()
	{
		try {
			customer42 = new Customer(42, MembershipType.MEMBER, 0, "Alvin Pennington");
			assertNotNull(customer42);
			}catch(InvalidCustomerIDException e)
			{
				e.printStackTrace();
			}
		CustomerList.getInstance().addCustomer(42, customer42);
		basket.setCurrentCustomerID(42);
		basket.setCurrentStaffID(001);
		basket.addItemToUnconfirmedOrder(item1);
		basket.addItemToUnconfirmedOrder(item2);
		basket.addItemToUnconfirmedOrder(item3);
		double actual = basket.getTotalDiscount();
		double expected = 1.05;
		double delta = 0.1;
		Assert.assertEquals(expected, actual, delta);
	}
	@Test
	public void TestgetFinalBill()
	{
		try {
			customer42 = new Customer(42, MembershipType.MEMBER, 0, "Alvin Pennington");
			assertNotNull(customer42);
			}catch(InvalidCustomerIDException e)
			{
				e.printStackTrace();
			}
		
		CustomerList.getInstance().addCustomer(42, customer42);
		basket.setCurrentCustomerID(42);
		basket.setCurrentStaffID(001);
		basket.addItemToUnconfirmedOrder(item1);
		basket.addItemToUnconfirmedOrder(item2);
		basket.addItemToUnconfirmedOrder(item3);
		basket.getTotalDiscount();
		double actual = basket.getFinalBill();
		double expected = 5.95;
		double delta = 0.1;
		Assert.assertEquals(expected, actual, delta);
	}
	@Test
	public void TestconfirmedAndPaid()
	{
		try {
			customer42 = new Customer(42, MembershipType.MEMBER, 0, "Alvin Pennington");
			assertNotNull(customer42);
			}catch(InvalidCustomerIDException e)
			{
				e.printStackTrace();
			}
		CustomerList.getInstance().addCustomer(42, customer42);
		basket.setCurrentCustomerID(42);
		basket.setCurrentStaffID(001);
		basket.addItemToUnconfirmedOrder(item1);
	    basket.confirmedAndPaid();
		OrderList.getInstance().totalSales();
		assertEquals(1,OrderList.getInstance().totalSales());
		
		
	}
}
