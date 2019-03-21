package GUI;
import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import customers.Customer;
import customers.CustomerList;
import customers.MembershipType;
import exceptions.InvalidCustomerIDException;
/**
 * Secondary gui interface for Adding new customer.
 */
public class AddCustomerGUI extends JFrame implements ActionListener{
	
	
	
	  //GUI components
    JTextArea details;
    
    JButton AddCustomer = new JButton("Add Customer");
    private JTextField name;
    private JTextField ID;
    private JTextField member;
    
   

 
    
	public AddCustomerGUI() {
		

		
	}
	/**
	 * set up window
	 */
	
	public void setUpGUI() {
		
		//set up window title
        this.setTitle("Find Competitor");
        //disable standard close button
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		//call panels here
		panel();

        //pack and set visible
        this.pack();
        this.setVisible(true);
        this.setLocation(50, 50);
	}
	/**
	 * set up panel
	 */

	private void panel() {
		//this panel will add a search function.
		name = new JTextField(5);
		name.setText("Enter Customer name");
		ID = new JTextField(5);
		ID.setText("Enter ID");
		member = new JTextField(5);
		member.setText("membership type (m/s/e)");

        JPanel STHpanel = new JPanel();
        STHpanel.setLayout(new GridLayout(3,3));
        //add text enter field
        STHpanel.add(new JLabel("Add Customer"));   
        
        //add search button
        //Find = new JButton("Find"); 
        STHpanel.add(name);  
        STHpanel.add(ID);  
        STHpanel.add(member);  
        STHpanel.add(AddCustomer);    
   
        AddCustomer.addActionListener(this) ;

        
        //results area
        details= new JTextArea(3,3);     
        details.setEditable(false);
        //combine all panels
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2,2));
        southPanel.add(STHpanel);
        southPanel.add(details);
      
        //add panel to the content pane
        this.add(southPanel, BorderLayout.CENTER);   	
    }
	
	
	/**
	 * button press listener
	 */
	
	public void actionPerformed(ActionEvent e) { 
    	if (e.getSource() == AddCustomer) {
    		
    		CustomerList customerList = CustomerList.getInstance();
    		
    		String IDnumber = ID.getText().trim();
    		String Customername = name.getText().trim();
    		String CustomerMember = member.getText().trim();
    		int ID = 0;
    		try {
    		ID = Integer.parseInt(IDnumber);
    		}catch(NumberFormatException e2) {
    			details.setText("ID must be an integer");
    		}
    		
    		if(customerList.customerExists(ID) == false) {
    			MembershipType memType;
    			Customer newCustomer;

    			if(CustomerMember.charAt(0) == 'M'||CustomerMember.charAt(0) == 'm') {
    				memType = MembershipType.MEMBER;
    				try {
    					newCustomer= new Customer(ID,memType,0,Customername);
    					customerList.addCustomer(ID, newCustomer);
    					setVisible(false);
    				}catch(InvalidCustomerIDException e3) {
    					details.setText("ID must be an integer");
    				}
    				
    				//setVisible(false);
    			}else if(CustomerMember.charAt(0) == 'S'||CustomerMember.charAt(0) == 's') {
    				memType = MembershipType.STUDENT;
    				try {
    					newCustomer= new Customer(ID,memType,0,Customername);
    					customerList.addCustomer(ID, newCustomer);
    					setVisible(false);
    				}catch(InvalidCustomerIDException e3) {
    					details.setText("ID must be an integer");
    				}
//    				 Manager.customerList.addCustomer(ID, newCustomer);
//    				 setVisible(false);
    			}else if(CustomerMember.charAt(0) == 'e'||CustomerMember.charAt(0) == 'E') { 
    				memType = MembershipType.EMPLOYEE;
    				try {
    					newCustomer= new Customer(ID,memType,0,Customername);
    					customerList.addCustomer(ID, newCustomer);
    					setVisible(false);
    				}catch(InvalidCustomerIDException e3) {
    					details.setText("ID must be an integer");
    				}
    				
    				
    				//Manager.customerList.addCustomer(ID, newCustomer);
    				//setVisible(false);
    			} else {
    				details.setText("Enter Membership type (m/e/s)");
    			}
    		
    			
    			
    			
    		}else {
    			details.setText("this ID \"" + IDnumber + "\" allready exists");
    		}
    		
    		
    		
    		
    		
    		
    	}
    }
	


}

	

	


    
   
