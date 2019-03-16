package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import customers.CustomerList;
import exceptions.NoStaffAvailableException;
import main.Main;
import main.Observer;
import menu.MenuItems;
import menu.MenuList;
import shop.Basket;
import shop.Queue;
import staff.Staff;
import staff.StaffList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainApplicationWindow extends JFrame implements Observer {

	private JFrame frame;
	private JList list;
	private JTextField staffID;
	private JTextField custId;
	private JTextArea details;
	private int customerID = 0;
	private String[] ID ;
	private static final Border border = BorderFactory.createLineBorder(Color.BLACK);
	
	private JTextArea queueDetails;
	private JTextArea staffOnDuty;
	
	private static JPanel serverActivityPanel;
	
	private Basket newBasket = new Basket();
	
	
	
	
	public void update() {
		
		String queueDetailsMessage = Queue.getInstance().getQueueDetails();		
		queueDetails.setText(queueDetailsMessage);
		
		String staffOnDutyDetails = StaffList.getInstance().onDutyList();
		staffOnDuty.setText(staffOnDutyDetails);
		
	}


	/**
	 *    Gui visual testing method
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainApplicationWindow window = new MainApplicationWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public static void addBox(Staff newStaff) {		

		JTextArea serverBox = new JTextArea();
		String message = "Staff ID: " + newStaff.getStaffID() + " Name: " + newStaff.getStaffFirstName() +" " + newStaff.getStaffLastName() ;
	    serverBox.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	
//		try 
//		{
			//message +=  "new";
//		} 
//		catch (NoStaffAvailableException excep) 
//		{
//			message += excep.getMessage();
//		}
		serverActivityPanel.add(serverBox);
		serverBox.setText(message);
		Queue queue = Queue.getInstance();
		queue.setShopOpen(true);
	}

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	

	/**
	 *  initialise the GUI
	 */
	public MainApplicationWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height-200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		StaffList.getInstance().registerObserver(this);
//		Queue.getInstance().registerObserver(this);
		

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		/**
		 *  label for staff
		 */
		JLabel lblEnterServingStaff = new JLabel("Enter Serving Staff ID:");
		lblEnterServingStaff.setBounds(290, 14, 149, 16);
		getContentPane().add(lblEnterServingStaff);
		
		/**
		 *  label for customer
		 */
		JLabel lblEnterCustomerId = new JLabel("Enter Customer ID:");
		lblEnterCustomerId.setBounds(290, 48, 125, 16);
		getContentPane().add(lblEnterCustomerId);
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		/**
		 *  input text field for staff
		 */
		staffID = new JTextField();
		staffID.setText("ID");
		staffID.setBounds(432, 9, 34, 26);
		getContentPane().add(staffID);
		staffID.setColumns(10);
		
		/**
		 *  input text field for customer
		 */
		custId = new JTextField();
		custId.setText("ID");
		custId.setBounds(432, 43, 34, 26);
		getContentPane().add(custId);
		custId.setColumns(10);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		/**
		 *  button to set staff id for interactive basket
		 */
		JButton btnSetStaffId = new JButton("Set Staff ID");
		btnSetStaffId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String IDnumber = staffID.getText().trim();
				int ID = 0;
				try{
					ID = Integer.parseInt(IDnumber);
					if(StaffList.getInstance().staffExists(ID)) {
						//CustomerID = ID;
						details.setText("\"" + IDnumber + "\" is set for Staff");
						newBasket.setCurrentStaffID(ID);
					} else {
						details.setText("There is no Staff with ID:\"" + IDnumber + "\" ");
					}
				}
				catch(NumberFormatException e1) {
					details.setText("\"" + IDnumber + "\" is not a correct format for ID (Integer)");
				}
			}
		});
		btnSetStaffId.setBounds(488, 9, 117, 29);
		getContentPane().add(btnSetStaffId);
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		/**
		 *   button to set customer id for interactive basket
		 */
		JButton btnSetCustId = new JButton("Set Customer ID");
		btnSetCustId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String IDnumber = custId.getText().trim();
	        	int ID = 0;
				try{
					ID = Integer.parseInt(IDnumber);
					if(CustomerList.getInstance().customerExists(ID)) {
						customerID = ID;
						details.setText("\"" + IDnumber + "\" is set");
						newBasket.setCurrentCustomerID(customerID);
					} else {
						details.setText("There is no customer with ID:\"" + IDnumber + "\" ");
					}
				}
				catch(NumberFormatException e1) {
					details.setText("\"" + IDnumber + "\" is not a correct format for ID (001)");
				}	
			}
		});
		btnSetCustId.setBounds(478, 43, 136, 29);
		getContentPane().add(btnSetCustId);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
				
		/**
		 *    Display menu in scroll
		 */
		JPanel menuPanel = new JPanel();
		GridBagConstraints gbc_menuPanel = new GridBagConstraints();
		gbc_menuPanel.insets = new Insets(0, 0, 0, 5);
		gbc_menuPanel.fill = GridBagConstraints.BOTH;
		gbc_menuPanel.gridx = 0;
		gbc_menuPanel.gridy = 2;
		gbc_menuPanel.gridheight = 2;
		gbc_menuPanel.gridwidth = 2;
		
		MenuList menuList = MenuList.getInstance();
	    int size = menuList.getNumberofMenuItemsInList();
	    String[] data = new String[size];
	    ID = new String[size];
	    int i = 0;
	    Iterable<MenuItems> menuListValues = menuList.getAllMenuItems();
	    for(MenuItems entry: menuListValues) {
	    	data[i] = entry.getName();
	    	ID[i] = entry.getID();
	    	i++;
	    }
	    list = new JList(data);
		
		JScrollPane menuScrollPane = new JScrollPane(list);
		menuScrollPane.setViewportView(list);
		menuPanel.setBounds(6, 6, 272, 150);
		menuPanel.add(menuScrollPane);
		getContentPane().add(menuPanel);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		
		/**
		 *  button to add item from menu to the interactive basket
		 */
		JButton btnNewButton = new JButton("Add to basket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
	            String uniquID = ID[index];
	            
	            newBasket.addItemToUnconfirmedOrder(menuList.getItem(uniquID));
	            
	            Iterable<MenuItems> basketList = newBasket.getItemsInBasket();
	            String output = "";
	            for(MenuItems item : basketList) {
	            	output += String.format("%s\n", item.getID());
	            }
	            details.setText(output);
			}
		});
		btnNewButton.setBounds(322, 87, 117, 29);
		getContentPane().add(btnNewButton);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		/**
		 *  Button to go to checkout for interactive order
		 */
		JButton btnCheckout = new JButton("Add to Queue");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	if(customerID == 0) {
	        		details.setText("please enter customer ID");
	        	}else  {
	        		details.setText("");
	         		CheckoutGUI checkout = new CheckoutGUI(newBasket);
	        		checkout.setUpGUI(); 
	        	}
			}
		});
		btnCheckout.setBounds(474, 87, 117, 29);
		getContentPane().add(btnCheckout);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		/**
		 *  where details of interactive order is displayed
		 */
		details = new JTextArea();
		details.setBounds(617, 6, 331, 142);
		getContentPane().add(details);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		/**
		 *  Display details of current customer queue 	
		 */
		queueDetails = new JTextArea();
		queueDetails.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		queueDetails.setText(Queue.getInstance().getQueueDetails());
		JScrollPane queueScroll = new JScrollPane(queueDetails);
		queueScroll.setBounds(6, 168, 615, 249);
		getContentPane().add(queueScroll);
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		/**
		 *  Display details of staff on duty
		 */
		staffOnDuty = new JTextArea();
		staffOnDuty.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		staffOnDuty.setText(StaffList.getInstance().onDutyList());
		JScrollPane staffOnDutyScroll = new JScrollPane(staffOnDuty);
		staffOnDutyScroll.setBounds(633, 168, 647, 249);
		getContentPane().add(staffOnDutyScroll);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		/**
		 *  button to exit GUI, application and write report
		 */
		JButton btnExitWrite = new JButton("Exit & Write report");
		btnExitWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.progExit();	     
			}
		});
		btnExitWrite.setBounds(1275, 19, 145, 45);
		getContentPane().add(btnExitWrite);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
				
		/**
		 *  button to open new customer GUI
		 */
		JButton btnAddNewCustomer = new JButton("Add new customer");
		btnAddNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCustomerGUI addCustomer = new AddCustomerGUI();
        		addCustomer.setUpGUI(); 
			}
		});
		btnAddNewCustomer.setBounds(1271, 74, 149, 29);
		getContentPane().add(btnAddNewCustomer);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		/**
		 *  server activity panel, where servers are added to
		 */
		//JPanel serverActivityPanel = new JPanel();
		serverActivityPanel = new JPanel();
		GridBagLayout serverActivityDesk = new GridBagLayout();
		serverActivityPanel.setLayout(serverActivityDesk);
		JScrollPane serverActivityScrollPane = new JScrollPane(serverActivityPanel);
		serverActivityScrollPane.setBounds(6, 429, 1285, 225);
		getContentPane().add(serverActivityScrollPane);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
		
		/**
		 *  button to speed up order processing
		 */
		JButton btnSpeedUp = new JButton("Speed Up");
		btnSpeedUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int speed = Main.sched.incSpeed();
				String message = "New Speed: " + speed;
				details.setText(message);
	     
			}
		});
		btnSpeedUp.setBounds(1303, 429, 117, 29);
		getContentPane().add(btnSpeedUp);
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	
		/**
		 *  button to slow down order processing
		 */
		JButton btnSlowDown = new JButton("Slow down");
		btnSlowDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int speed = Main.sched.decSpeed();
				String message = "New Speed: " + speed;
				details.setText(message);
	     
			}
		});
		btnSlowDown.setBounds(1303, 470, 117, 29);
		getContentPane().add(btnSlowDown);
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		/**
		 *  button to add server to queue (and display on panel)
		 */
		JButton btnAddServer = new JButton("Add server");
		btnAddServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff newstaff;
				try {
					newstaff =  Main.sched.addServerStaff();
					if(newstaff != null) {
						addBox(newstaff);
					}
				} catch (NoStaffAvailableException e1) {
					e1.printStackTrace();
				}
				

				
				
				
//				JTextArea serverBox = new JTextArea();
//				Staff newstaff;
//				String message = "";
//			    serverBox.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
//			 
//			    
//				try 
//				{
//					//serverBox.setText("just a box");
//					
//					//serverBox.setText(message);
//					//serverActivityPanel.add(serverBox);
//					//serverBox.setText(Main.sched.detailsOfServerJustAdded());
//					newstaff =  Main.sched.addServerStaff();
//					message = "" + newstaff.getStaffID();
//					
//	
//				} 
//				catch (NoStaffAvailableException excep) 
//				{
//					message += excep.getMessage();
//				}
//				serverActivityPanel.add(serverBox);
//				serverBox.setText(message);
//				Queue queue = Queue.getInstance();
//				queue.setShopOpen(true);
				
			
				
				
			
			}
		});
		btnAddServer.setBounds(1303, 525, 117, 29);
		getContentPane().add(btnAddServer);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
				
		/**
		 *  button to remove server to queue (and display on panel)
		 */
		JButton btnRemoveServer = new JButton("Remove server");
		btnRemoveServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.sched.removeServerStaff();
				int size = serverActivityPanel.getComponentCount();
				serverActivityPanel.remove(size-1);
				serverActivityPanel.validate();
				// could remove boxes too (would maybe need to keep a running list of boxes added?
			}
		});
		btnRemoveServer.setBounds(1303, 566, 117, 29);
		getContentPane().add(btnRemoveServer);

		
		update();
	}
	
	
	
	
}
