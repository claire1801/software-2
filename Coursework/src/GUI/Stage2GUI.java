package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import customers.CustomerList;
import exceptions.NoStaffAvailableException;
import main.Main;
import menu.MenuItems;
import menu.MenuList;
import orders.Order;
import orders.OrderList;
import staff.Staff;
import staff.StaffList;

public class Stage2GUI extends JFrame implements ActionListener {

	int CustomerID = 0;

	private JTextArea details;
	private JPanel southPanel = new JPanel();
	
	private JScrollPane scrollPane;
	private JScrollPane scrollDetails;
	private JList list;
	private JButton exit;
	private JTextField enteredID;
	private JButton increment;
	private JButton decrement;
	private JButton AddServer;
	private JButton RemoveServer;
	private JTextField serverAdded;
	private JLabel label;
	private int serverIncrement = 1;
	private int totalElements = 8;
	private int queueCount = 10;

	String[] ID;

	public Stage2GUI() {

		// set flow layout for the frame
		//this.getContentPane().setLayout(new FlowLayout());

		this.getContentPane().setLayout(new BorderLayout());
		MenuList menuList = MenuList.getInstance();

		MenuList menulist = MenuList.getInstance();

		int size = menuList.getNumberofMenuItemsInList();
		String[] data = new String[size];
		
		ID = new String[size];
		int i = 0;
		Iterable<MenuItems> menuListValues = menulist.getAllMenuItems();
		for (MenuItems entry : menuListValues) {
			data[i] = entry.getName();
			ID[i] = entry.getID();
			i++;
		}

		
		scrollPane = new JScrollPane();
		scrollDetails = new JScrollPane();
		list = new JList(data);
		//scrollPane.setViewportView("There are currently " + queueCount + "people waiting in the queue");
		label = new JLabel("There are currently " + queueCount + " people waiting in the queue");
		
		label.setVerticalAlignment(SwingConstants.TOP);
		scrollPane.setColumnHeaderView(label);
		//scrollPane.setViewportView(label);
		scrollPane.setViewportView(list);
		exit = new JButton("Exit and Write Report");
		increment = new JButton("Speed up time (PAGE_END)");
		decrement = new JButton("Slow down time");
		AddServer = new JButton("Add another server");
		RemoveServer = new JButton("Remove a server");

		serverAdded = new JTextField(5);
		
		serverAdded.setText("New server added, number: " + serverIncrement);

		details = new JTextArea(3, 3);
		details.setEditable(false);
		scrollDetails.setViewportView(details);
		exit.addActionListener(this);
		increment.addActionListener(this);
		decrement.addActionListener(this);
		AddServer.addActionListener(this);
		RemoveServer.addActionListener(this);

		// add list to frame
		southPanel.setLayout(new GridLayout(2, 2));
		southPanel.add(scrollPane, BorderLayout.PAGE_END);
		southPanel.add(exit, BorderLayout.PAGE_END);
		southPanel.add(scrollDetails, BorderLayout.LINE_START);
		southPanel.add(increment, BorderLayout.PAGE_END);
		southPanel.add(decrement, BorderLayout.PAGE_END);
		southPanel.add(AddServer, BorderLayout.PAGE_END);
		southPanel.add(RemoveServer, BorderLayout.PAGE_END);
		southPanel.add(serverAdded, BorderLayout.CENTER);

		add(southPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Remove a server")) {
			Component[] componentList = southPanel.getComponents();
			for (Component component : componentList) 
			{
				//only remove a server if one exists and only remove if it is the last element in the panel.
				if (component instanceof JTextField && serverIncrement > 0 && totalElements == componentList.length) 
				{

					// add functionality to remove a server thread here.
					southPanel.remove(component);
					serverIncrement--;
					totalElements--; //decrement total elements in panel
				}

			}
			southPanel.revalidate();
			southPanel.repaint();
			details.setText("server removed");
		}

		if (e.getActionCommand().equals("Add another server")) {
			
			// my code here
			String message;

			try 
			{
				Staff server = StaffList.getInstance().getNextAvailableServer();
				Thread newServer = new Thread(server);
				server.isServing(true);
				newServer.start();
				serverIncrement++;  // I'm not sure what these are for?
				totalElements++; 	// I'm not sure what these are for?
				message = "New server added, number: " + serverIncrement;
			} 
			
			catch (NoStaffAvailableException excep) 
			{
				message = excep.getMessage();
			}
		
			
			//serverIncrement++;
			//totalElements++; //increment total elements in panel
			serverAdded = new JTextField(5);
			//serverAdded.setText("New server added, number: " + serverIncrement);
			serverAdded.setText(message);
			
			
			southPanel.add(serverAdded, BorderLayout.CENTER);
			// serverList.add(serverAdded);
			String output = "";
			output += String.format("%s\n", "a new server is available");
			details.setText(output);

			southPanel.revalidate();
			southPanel.repaint();
//			// add functionality to call new server thread here.
//			serverIncrement++;
//			totalElements++; //increment total elements in panel
//			serverAdded = new JTextField(5);
//			serverAdded.setText("New server added, number: " + serverIncrement);
//
//			southPanel.add(serverAdded, BorderLayout.CENTER);
//			// serverList.add(serverAdded);
//			String output = "";
//			output += String.format("%s\n", "a new server is available");
//			details.setText(output);
//
//			southPanel.revalidate();
//			southPanel.repaint();

		}

		if (e.getActionCommand().equals("Speed up time")) {
			// add functionality to speed up simulation
			details.setText("time sped up");
		}

		if (e.getActionCommand().equals("Slow down time")) {
			// add functionality to slow down simulation
			details.setText("time slowed down");
		}

		if (e.getActionCommand().equals("Exit and Write Report")) {
			Main.progExit();
		}

	}

}
