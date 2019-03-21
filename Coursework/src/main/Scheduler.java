package main;

import java.util.ArrayList;

import GUI.MainApplicationWindow;
import exceptions.NoStaffAvailableException;
import shop.Queue;
import staff.Staff;
import staff.StaffList;

/**
 * manages time keeping and staff threads - runs in its own thread
 *
 */
public class Scheduler implements Runnable {
	
	private int staffCounter = 0;
	/**
	 * speed variable controls the speed note: speed 1 is fastest 10 slowest
	 */
	public int speed = 5;
	public boolean isOpen = false;
	ArrayList<Thread> threadList;
	ArrayList<Staff> staff;
	
	public Scheduler() {
		this.threadList = new ArrayList<Thread>();
		this.staff = new ArrayList<Staff>();
		
	}
	
	/**
	 * add staff and thread to lists
	 * @param s - staff to add
	 * @param t - corresponding thread to add
	 */
	private void addStaffThread(Staff s, Thread t) {
		staff.add(s);
		threadList.add(t);
	}
	
	/**
	 * remove thread and staff from lists
	 * @param i - index of thread/staff
	 */
	
	private void removeStaffThread(int i) {
		staff.remove(i);
		threadList.remove(i);
	}
	
	/**
	 * add server staff to working list
	 * @return the new staff on duty
	 * @throws NoStaffAvailableException
	 */
	
	public synchronized  Staff addServerStaff() throws NoStaffAvailableException {
		StaffList sl = StaffList.getInstance();
		if(staffCounter < sl.size() ) {
			staffCounter++;
			Staff newServerStaff = sl.getNextAvailableServer();
			newServerStaff.isServing(true);
			Thread servingStaffThread = new Thread(newServerStaff);
			servingStaffThread.start();
			this.addStaffThread(newServerStaff, servingStaffThread);
			//System.out.println("new thread with server: " + newServerStaff.getStaffID());
			return newServerStaff;
		}
		return null;

	}
	
	/**
	 * remove staff from duty
	 * @throws NullPointerException
	 */
	public synchronized  boolean removeServerStaff() throws NullPointerException {
		// could possibly add in functionality so that order being processed is not lost
		
		//remove last server to be added
		if(this.staff.size() != 0) {
			this.staff.get(staff.size()-1).isServing(false);
			this.removeStaffThread(staff.size()-1);
			//System.out.println("heelo");
			//this.staff.remove(staff.size()-1);
			staffCounter--;
			StaffList.getInstance().notifyObservers();
			return true;
		}
		return false;
	}
	
	
	/**
	 * main loop of time manager - adds new staff threads as needed and created new random customers
	 */
	public void run() {
		Queue queue = Queue.getInstance();
		
		
		
		while(queue.queueEmpty() == false) {
			
			if(queue.getShopOpen() == false){
				continue;
			}
			
		
			if(queue.numberInQueue()/(staffCounter + 1) >= 10 && staffCounter < 12) { 
				try {
					Staff newstaff = this.addServerStaff();
					MainApplicationWindow.addBox(newstaff);
					StaffList.getInstance().notifyObservers();
					Queue.getInstance().notifyObservers();
				} catch (NoStaffAvailableException e) {
					e.printStackTrace();
				}
			}
			
			if (staffCounter * 4 > queue.numberInQueue()) {
				this.removeServerStaff();
				
			}
			queue.addRandomCustomer();
			try {
				Thread.sleep(speed * 1300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

		while(staff.size() > 0) {
			this.removeServerStaff();
		}
		
		StaffList.getInstance().notifyObservers();
		Queue.getInstance().notifyObservers();

		
	}
	/**
	 * get the current speed - 10 is slowest 1 fastest
	 * @return int of speed 1- 10
	 */

	public synchronized  int getSpeed() {
		return speed;
	}
	/**
	 * increase the speed
	 * @return int - new speed note: returns inverse of speed i.e 1 slowest 10 fastest
	 */

	public synchronized  int incSpeed() {
		if(speed > 1) {
			speed--;
		}
		return (10 - speed) + 1;
	}
	/**
	 * Decrease the speed
	 * @return int - new speed note: returns inverse of speed i.e 1 slowest 10 fastest
	 */
	public synchronized  int decSpeed() {
		if(speed < 10) {
			speed++;
		}
		return (10 - speed) + 1;
	}

}
