package test;

import org.junit.jupiter.api.Test;
import main.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 
 * @author clairecullen
 *
 */


public class ManagerTest {
	

		@Test
		public void testReadCustomers() {
			
			
			final String filePath = "customerList.txt";
			
			try {
				
				FileReadIn.readCustomers(filePath);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			Path path = Paths.get(filePath);
			long lineCount = 0;
			
			try {
				lineCount = Files.lines(path).count();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			assertEquals(Main.customerList.getSize(), lineCount);
			
		
		}
		

		
		@Test
		public void testReadStaff() {
			
			
			final String filename = "StaffList.txt";
			
			try {
				
				FileReadIn.readStaff(filename);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			Path path = Paths.get(filename);
			long lineCount = 0;
			
			try {
				lineCount = Files.lines(path).count();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			assertEquals(Main.staffList.staffList.size(), lineCount);
			
		
		}
		
		@Test
		public void testReadMenuItems() {
			
			
			final String filename = "menuItems.txt";
			
			try {
				
				FileReadIn.readMenuItems(filename);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			Path path = Paths.get(filename);
			long lineCount = 0;
			
			try {
				lineCount = Files.lines(path).count();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			assertEquals(Main.menuList.menuList.size(), lineCount);
			
		
		}
		
		@Test
		public void testPrintToFile() {
			
		}
		
//		@Test
//		public void testWriteReport() {
//		
//			
//			File filename = new File("report.txt");
//			Main.writeReport("report.txt");
//			assertTrue(filename.exists());
//		}
		
		@Test
		public void testUpdateFiles() {
			
		}
		
		
		
		
		
		

	}
