package main;

import java.util.ArrayList;
import java.util.Set;

import GUI.MainApplicationWindow;
import exceptions.NoStaffAvailableException;
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
	public int speed = 1;
	public boolean isOpen = false;
	ArrayList<Thread> threadList;
	ArrayList<Staff> staff;
	
	public Scheduler() {
		this.threadList = new ArrayList<Thread>();
		this.staff = new ArrayList<Staff>();
		//this.addStaff();
	}
	
	
//	/**
//	 * is staff working i.e. thread running?
//	 * @param ID
//	 * @return
//	 */
//	private boolean staffWorking(int ID) {
//		return currentStaff.contains(ID);
//		
//	}
	
	

	private void addStaffThread(Staff s, Thread t) {
		staff.add(s);
		threadList.add(t);
	}
	
	
	
	private void removeStaffThread(int i) {
		staff.remove(i);
		threadList.remove(i);
	}
	

	
	public synchronized  Staff addServerStaff() throws NoStaffAvailableException {
		staffCounter++;
		Staff newServerStaff = StaffList.getInstance().getNextAvailableServer();
		newServerStaff.isServing(true);
		Thread servingStaffThread = new Thread(newServerStaff);
		servingStaffThread.start();
		this.addStaffThread(newServerStaff, servingStaffThread);
		System.out.println("new thread with server: " + newServerStaff.getStaffID());
		return newServerStaff;
	}
	
	
	public synchronized  void removeServerStaff() throws NullPointerException {
		// could possibly add in functionality so that order being processed is not lost
		
		//remove last server to be added
		this.staff.get(staff.size()).isServing(false);
		this.removeStaffThread(staff.size());
		staffCounter--;
	}
	
	
//	private void addStaff() {
//		//System.out.println("i am here");
//		StaffList staffList = StaffList.getInstance();
//		//System.out.println("i am here");
//		Staff newStaff = staffList.getRandomStaff();
//		while(this.staffWorking(newStaff.getStaffID()) == true) {
//			newStaff = staffList.getRandomStaff(); // done this already?
//		}
//		if(staffList.size() > staffCounter) {
//			currentStaff.add(newStaff.getStaffID());
//			Thread staffThread = new Thread(newStaff);
//			staffThread.start();
//			staffCounter++;
//		}
//	}
	

	
	
	/**
	 * main loop of time manager
	 */
	public void run() {
		Queue queue = Queue.getInstance();
		
		
		
		while(queue.queueEmpty() == false) {
			
			if(queue.getShopOpen() == false){
				continue;
			}
			//if(staffCounter < 4) { //staffcounter is initialised to 0 this is bad
		
			if(queue.numberInQueue()/(staffCounter + 1) >= 10 && staffCounter < 12) { //staffcounter is initialised to 0 this is bad
				//this.addStaff();
				try {
					Staff newstaff = this.addServerStaff();
					MainApplicationWindow.addBox(newstaff);
					StaffList.getInstance().notifyObservers();
					Queue.getInstance().notifyObservers();
				} catch (NoStaffAvailableException e) {
					e.printStackTrace();
				}
			}
			queue.addRandomCustomer();
			try {
				Thread.sleep(speed * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
