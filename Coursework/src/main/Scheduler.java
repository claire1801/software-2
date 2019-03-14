package main;

import java.util.ArrayList;

import shop.Queue;
import staff.Staff;
import staff.StaffList;

/**
 * manages time keeping and staff threads - runs in its own thread
 * @author Sam
 *
 */
public class Scheduler implements Runnable {
	
	private int staffCounter = 0;
	public int Speed = 10;
	ArrayList<Integer> currentStaff;
	
	
	public Scheduler() {
		this.currentStaff = new ArrayList<Integer>();
		this.addStaff();
	}
	/**
	 * is staff working i.e. thread running?
	 * @param ID
	 * @return
	 */
	
	private boolean staffWorking(int ID) {
		return currentStaff.contains(ID);
		
	}
	
	private void addStaff() {
		//System.out.println("i am here");
		StaffList staffList = StaffList.getInstance();
		//System.out.println("i am here");
		Staff newStaff = staffList.getRandomStaff();
		while(this.staffWorking(newStaff.getStaffID()) == true) {
			newStaff = staffList.getRandomStaff();
		}
		if(staffList.size() > staffCounter) {
			currentStaff.add(newStaff.getStaffID());
			Thread staffThread = new Thread(newStaff);
			staffThread.start();
			staffCounter++;
		}
		
		
	}
	/**
	 * main loop of time manager
	 */
	
	public void run() {
		Queue queue = Queue.getInstance();
		while(queue.queueEmpty() == false) {
			if(queue.numberInQueue()/staffCounter >= 10 ) {
				this.addStaff();
			}
			queue.addRandomCustomer();
			try {
				Thread.sleep(Speed * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
	
	
	
