package orders;

/**
 * This is the Order class
 * 
 *  
 * @author Jamie Morse (jtm1)
 *
 */
import java.sql.Timestamp;



public class Order{
	
	private int orderID;
	private int customerID;
	private Timestamp timestamp;
	private String itemID;
	private double cost;
	private double discountAmount;
	private int staffID;
	private boolean online;

	
	public Order(int orderID, int customerID, Timestamp timestamp, String itemID, 
			double cost, double discountAmount, int staffID, boolean online)
	
	{
		try {
		this.orderID = orderID;
		this.customerID = customerID;
		this.timestamp = timestamp;
		this.itemID = itemID;
		this.cost = cost;
		this.discountAmount = discountAmount;
		this.staffID = staffID;
		this.online = online;
		}
		catch (IllegalArgumentException e) 
		{
			System.out.println(e.getMessage());
		}
		catch (NullPointerException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	public int getStaffID() {
		return this.staffID;
	}
	
	public int getOrderID()
	{ 
		return orderID;	
	}
	
	public void setOrderID(int orderID)	
	{
		 this.orderID = orderID;
	}
	
	public int getCustomerID() 
	{
		return customerID;
	}
	
	public void setCustomerID(int customerID)	
	{
		this.customerID = customerID;
	}
	
	public Timestamp getTimeStamp() 
	{
		return timestamp;
	}
	
	public void setTimeStamp(Timestamp timestamp) 
	{
		this.timestamp = timestamp;
	}
	
	public String getItemID() 
	{
		return itemID;
	}
	
	public void setItemID(String itemID) 
	{
		this.itemID = itemID;
	}
	
	public double getCost() 
	{
		return cost;
	}	
	
	public void setCost(double cost)	
	{
		this.cost = cost;
	}
	
	public double getDiscountAmount() 
	{
		return discountAmount;
	}
	
	public void setDiscount(double discountAmount)
	{
		this.discountAmount = discountAmount;
	}
	
	public void setOnline(boolean online)  
	{
		this.online = online;
	}
	
	public boolean getOnline() 
	{
		return online;
	}
	
	
}	
