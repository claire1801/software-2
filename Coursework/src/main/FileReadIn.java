package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import customers.Customer;
import customers.CustomerList;
import customers.MembershipType;
import exceptions.InvalidCustomerIDException;
import exceptions.InvalidItemIdentifierException;
import menu.Drinks;
import menu.Meals;
import menu.MenuItems;
import menu.MenuList;
import menu.Snacks;
import orders.Order;
import orders.OrderList;
import staff.*;

/**
 * reads in files (orderlist, customerlist, stafflist , menulist) should be called from Main
 * @author samth
 *
 */
public class FileReadIn {
	
	/**
	 * read in menu items (name/ID/cost/description/allergy)
	 * @param fileName - name of file to read in
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	
	
	public static void readMenuItems(String fileName) throws FileNotFoundException, NumberFormatException, ArrayIndexOutOfBoundsException {
		

		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		
		MenuList menuList = MenuList.getInstance();
		
		while (scanner.hasNextLine()) {
			
			String line = scanner.nextLine();
			String[] item = line.split("/");
			if(item.length < 5) {
				continue;
			}
			
			double cost = Double.parseDouble(item[2]);
			//System.out.println(item[0]);
			//String code = item[1].
			try {
			
			if(item[1].substring(0, 5).equals("DRINK")||item[1].substring(0, 5).equals("COFEE")) {
				MenuItems newMenuItem = new Drinks(item[0],item[1],cost,item[3],item[4]);
				//System.out.println("Drink");
				//System.out.println(item[0]);
				menuList.addItem(item[1], newMenuItem);
			}
			else if(item[1].substring(0, 5).equals("MEALS")) {
				MenuItems newMenuItem = new Meals(item[0],item[1],cost,item[3],item[4]);
				//System.out.println("meal");
				menuList.addItem(item[1], newMenuItem);
			}
			else if(item[1].substring(0, 5).equals("SNACK")) {
				MenuItems newMenuItem = new Snacks(item[0],item[1],cost,item[3],item[4]);
				//System.out.println("snack");
				menuList.addItem(item[1], newMenuItem);
			}
			}catch(InvalidItemIdentifierException e1) {
				e1.printStackTrace();
				String error = item[0] + " ID not formated correct";
				System.out.println(error);
			}
			//counter += 1;
			//System.out.println(counter);
			
		//	menuList.addItem(newMenuItem);
		}
		scanner.close();
	}
	
	/**
	 * read in orders - id/customerid/timestamp/item/cost/discount
	 * @param fileName - name of file to read in
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws ParseException
	 * @throws InvalidCustomerIDException
	 * @throws InvalidItemIdentifierException
	 */
	public static void readOrders(String fileName)  throws FileNotFoundException, NumberFormatException, ArrayIndexOutOfBoundsException, ParseException, InvalidCustomerIDException, InvalidItemIdentifierException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		
		OrderList orderList = OrderList.getInstance();
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] order = line.split("/");
			if(order.length < 5) {//catch errors
				continue;
			}
			double cost = Double.parseDouble(order[4]);
			double discount = Double.parseDouble(order[5]);
			int customerID = Integer.parseInt(order[1]);
			int orderID = Integer.parseInt(order[0]);
			int staffId = Integer.parseInt(order[6]);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate = dateFormat.parse(order[2]);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			
			//System.out.println(timestamp);
			
			Order newOrder = new Order(orderID,customerID, timestamp, order[3],cost,discount,staffId);
			orderList.addOrder(newOrder);
		}
		scanner.close();
		
	}
	/**
	 * read in staff name/id
	 * @param fileName - file name to read in
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public static void readStaff(String fileName)  throws FileNotFoundException, NumberFormatException, ArrayIndexOutOfBoundsException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		
		StaffList staffList = StaffList.getInstance();
		
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] staff = line.split("/");
			int ID = Integer.parseInt(staff[1]);
			String name = staff[0];
			String[] names = name.split("\\W+");//split name into first last using whitespace
			int lastIndex = names.length - 1;// index of last name
			//int noDrinks = Integer.parseInt(staff[2]);
			//System.out.print(names[0] + " ");
			//System.out.println(names[lastIndex]);
			Staff staffmember = new Staff(ID,names[0],names[lastIndex]);
			staffList.addStaffToList(staffmember);
		}
		scanner.close();
		
	}
	
	/**
	 * read in customers name/id/number of coffees 
	 * @param fileName - file name to read in
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public static  void readCustomers(String fileName)  throws FileNotFoundException, NumberFormatException, ArrayIndexOutOfBoundsException {
		
		
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		
		CustomerList customerList = CustomerList.getInstance();
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] customer = line.split("/");
			if(customer.length < 2) {
				continue;
			}
			int ID = Integer.parseInt(customer[1]);
			int noDrinks = Integer.parseInt(customer[2]);
			String member = customer[3];
			MembershipType memType;
			if(member.charAt(0) == 'E') {
				memType = MembershipType.EMPLOYEE;
			}else if(member.charAt(0) == 'S') {
				memType = MembershipType.STUDENT;
			}else  {
				memType = MembershipType.MEMBER;
			}
			
			 
			
			//System.out.println(customer[0]);
			//Customer newCustomer = new Customer(customer[0],ID,noDrinks);
			Customer newCustomer;
			try {
				newCustomer = new Customer(ID,memType,noDrinks,customer[0]);
				customerList.addCustomer(ID,newCustomer);//name?
			} catch (InvalidCustomerIDException e) {
				System.out.println("ID of customer incorect format");
			}
			//System.out.println(newCustomer.isMember());
			

		}
		scanner.close();
	}
	
	

}
