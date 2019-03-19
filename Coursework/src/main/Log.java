package main;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import shop.Queue;


public class Log {
	
	private static Log instance;
	
	public static Log getInstance() {
		if (instance == null)
			instance = new Log();
		return instance;
	}
	
	private  Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	
	/**
	 * write details to log file (logFile.txt)
	 * @param details - string to write to file
	 */
	public synchronized void writeToFile(String details)  {
		try {
			FileWriter fw = new FileWriter("logFile.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			bw.write(timestamp +": " + details);
			bw.newLine();
			bw.close();

		}catch (IOException e) {
			System.out.println("A write error has occured");
		}
		
	}
	
}
