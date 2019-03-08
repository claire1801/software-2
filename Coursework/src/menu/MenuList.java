package menu;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class MenuList {
	
	/**
	 * Variable for MenuList class
	 * menuList : List of menu items with a string as the key and a menu item object for the value
	 */
	private static TreeMap<String, MenuItems> menuList; 

	private static MenuList menulist;
	
	/**
	 * Constructor for creating a tree map for menu list
	 */
	private MenuList() { 
		this.menuList = new TreeMap<String, MenuItems> ();
	}
	
	/**
	 * public method to return instance of the MenuList
	 * @return
	 */
	public static MenuList getInstance() {
		if (menulist == null) 
			menulist = new MenuList();
		return menulist;
	}
	
	
//	/**
//	 *  Getter for returning the menu list
//	 * @return menuList
//	 */
//	public TreeMap<String, MenuItems> getMenuList() { // I don't really understand the point of this method
//		String message = "";
//		try { 
//			return menuList; 
//		} catch (NullPointerException e) {
//			message = e.getMessage() + "Could not get menuList";	
//			System.out.println(message); 
//			return null;
//		}
//	}

	/**
	 * Add menu items to menu list 
	 * @param key
	 * @param value
	 */
	public void addItem(String key, MenuItems value) {
		if (this.menuList.containsKey(key)) {
			throw new IllegalArgumentException ("Key already exists");  
		}
		this.menuList.put(key, value);
	}

	/**
	 * Get a menu item from the menu list
	 * @param key
	 * @return key
	 */
	public MenuItems getItem(String key) {
		
		if (this.menuList.containsKey(key)) {
			return this.menuList.get(key);
		} throw new IllegalArgumentException ("Key does not exist"); 
	}
	 
	/**
	 * Remove a menu item from the menu list
	 * @param key
	 */
	public void remove(String key){
		if (!this.menuList.containsKey(key)) {
			throw new IllegalArgumentException ("Item does not exist");
		} 
		this.menuList.remove(key);
	}
	
	
	/**
	 * method for returning the number of items that are currently on the Menu
	 * 
	 * @return the size of the menu, i.e. number of items
	 */
	public int getNumberofMenuItemsInList() {
		return this.menuList.size();
	}
	
	/**
	 * Allows other classes to traverse the items in the menu list
	 * 
	 * @return
	 */
	public Iterable<MenuItems> getAllMenuItems() {
		return this.menuList.values();
	}
	
	
	
	/**
	 * Write a report of all the menu items in menu list with their details
	 * @return report
	 */
	public String writeReport() {
		String report = new String();
		for(MenuItems entry: menuList.values()) {
			report += entry.getName() + "/";
			report += entry.getID()+ "/";
			report += entry.getCost() + "/";
			report += entry.getDescription() + "/";
			report += entry.getAllergens() + "\n";
		}
		return report;
	}
	
	public MenuItems getRandomItem() {
		double randomNumber = Math.random();
		int index = (int) (randomNumber * this.getNumberofMenuItemsInList());
		int i = 0;
		for(MenuItems item : this.menuList.values()) {
			if(i == index ) {
				return item;
			}else {
				i++;
			}
		}
		return null;
		
	}
	
}
