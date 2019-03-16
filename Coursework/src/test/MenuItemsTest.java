/**
 * 
 * 
 * @author frsrg
 */
 
 
 package test;
 

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import exceptions.InvalidItemIdentifierException;

import menu.Drinks;
import menu.Meals;
import menu.Snacks;

class MenuItemsTest {


	
	
	@Test
	void test_drink_exception() throws InvalidItemIdentifierException {
		String key = "DRINK001";
		try {
			Drinks drinkTest = new Drinks("drink test",key,1.0,"test","test");
		}catch(InvalidItemIdentifierException e){
			assertTrue(e.getMessage().contains("Invalid ID on product with name"));		
		}
	}
	
	@Test
	void test_coffee_exception() throws InvalidItemIdentifierException {
		String key = "COFEY001";
		try {
			Drinks coffeeTest = new Drinks("drink test",key,1.0,"test","test");
		}catch(InvalidItemIdentifierException e){
			assertTrue(e.getMessage().contains("Invalid ID on product with name"));		
		}
	}
	
	@Test
	void test_meals_exception() throws InvalidItemIdentifierException {
		String key = "MEATS001";
		try {
			Meals mealsTest = new Meals("meal test",key,1.0,"test","test");
		}catch(InvalidItemIdentifierException e){
			assertTrue(e.getMessage().contains("Invalid ID on product with name"));		
		}
	}
	
	@Test
	void test_Snacks_exception() throws InvalidItemIdentifierException {
		String key = "SNECK001";
		try {
			Snacks snacksTest = new Snacks("meal test",key,1.0,"test","test");
		}catch(InvalidItemIdentifierException e){
			assertTrue(e.getMessage().contains("Invalid ID on product with name"));		
		}
	}

	@Test
	void test_Drinks_okay() throws InvalidItemIdentifierException {
		String key = "DRINK001";
		double cost = 1.0;
		double test = 0;
		try {
			Drinks drinksTest = new Drinks("drink test",key,cost,"test","test");
			test = drinksTest.getCost();
		}catch(InvalidItemIdentifierException e){
			fail();
		}
		assertEquals(cost,test);
	}
	
	@Test
	void test_Snacks_okay() throws InvalidItemIdentifierException {
		String key = "SNACK001";
		double cost = 1.0;
		double test = 0;
		try {
			Snacks snacksTest = new Snacks("meal test",key,cost,"test","test");
			test = snacksTest.getCost();
		}catch(InvalidItemIdentifierException e){
			fail();
		}
		assertEquals(cost,test);
	}
	
	@Test
	void test_Coffee_okay() throws InvalidItemIdentifierException {
		String key = "COFEE001";
		double cost = 1.0;
		double test = 0;
		try {
			Drinks drinksTest = new Drinks("drink test",key,cost,"test","test");
			test = drinksTest.getCost();
		}catch(InvalidItemIdentifierException e){
			fail();
		}
		assertEquals(cost,test);
	}
	
	@Test
	void test_Meals_okay() throws InvalidItemIdentifierException {
		String key = "MEALS001";
		double cost = 1.0;
		double test = 0;
		try {
			Meals mealsTest = new Meals("meal test",key,cost,"test","test");
			test = mealsTest.getCost();
		}catch(InvalidItemIdentifierException e){
			fail();
		}
		assertEquals(cost,test);
	}
}
