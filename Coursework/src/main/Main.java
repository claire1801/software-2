package main;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

import GUI.MainGUI;
import customers.CustomerList;
import exceptions.InvalidCustomerIDException;
import exceptions.InvalidItemIdentifierException;

import menu.MenuList;

import orders.OrderList;
import shop.*;


import java.text.ParseException;


/**
 * Reads and out files and reports
 * @author samth
 *
 */
public class Main {
	
	public static Basket basket = new Basket();
	//public static Queue queue = Queue.getInstance();
	

	public static void main(String[] args) throws InvalidCustomerIDException, InvalidItemIdentifierException {
		try {
			FileReadIn.readCustomers("customerList.txt");
			FileReadIn.readStaff("StaffList.txt");
			FileReadIn.readMenuItems("MenuItems.txt");
			FileReadIn.readOrders("orderList.txt");
			Queue queue = Queue.getInstance();
			queue.setupQueue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//		Date date= new Date();
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		
//		Order testorder1 = new Order("0002",0001,timestamp,"COFEE001",20.1,1.1);
		
		  javax.swing.SwingUtilities.invokeLater(new Runnable() {
			  
			  public void run() {
			   
			      createAndShowGUI(); 
			   
			  }
			   
			    });

	}
	
/**
 * create the GUI interface
 */
	private static void createAndShowGUI() {
   	 
  	  //Create and set up the window.
  	 
  	  JFrame frame = new MainGUI();
  	 
  	  //Display the window.
  	 
  	  frame.pack();
  	 
  	  frame.setVisible(true);
  	 
  	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	 
  	 }
	
	/**
	 * runs shutdown procedure
	 */
	
	public static void progExit() {
		updateFiles();
		writeReport("report.txt");
		System.exit(0);
		
	}
	
	
	/**
	 * update all data files
	 */
	
	private static void updateFiles() {
		
		MenuList menuList = MenuList.getInstance();
		OrderList orderList = OrderList.getInstance();
		CustomerList customerList = CustomerList.getInstance();
		
		String order = orderList.writeReport();
		printToFile("orderList.txt",order);
		
		String menu = menuList.writeReport();
		printToFile("MenuItems.txt",menu);
//		
//		String menu = staffList.writeReport();    // not needed?
//		printToFile("StaffList.txt",menu);
//		
		String customer = customerList.writeReport();
		printToFile("customerList.txt",customer);

		
	}
	
	/**
	 * Write to file
	 * @param filename - name of file to write to
	 * @param details - string to write
	 */
	private static void printToFile(String filename, String details)  {
		try {
			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(details);
			pw.close();
		} catch (FileNotFoundException e1) {
			System.out.println("input file does not exist!");
			System.exit(1);
		}catch (IOException e2) {
			e2.printStackTrace();
			System.exit(1);
		}
		
	}
	
	/**
	 * 
	 * write final report
	 * @param filename - name of file to write to.
	 */
	private static  void writeReport(String filename) {
		
		OrderList orderList = OrderList.getInstance();
		
		String details = "Summary of Cafe\n";
		int sales = orderList.totalSales();
		double income = orderList.totalIncome();
		details += "In total there have been " + sales + " orders made.\n";
		details += "This gives a total income of " + income + " (£)\n\n";
		details += "The following is a full list of all items Ordered:\n";
		details += orderList.writeReport();
		
		printToFile(filename, details);
		
		
		
	}
	
	

}
