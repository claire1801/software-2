package menu;
import java.util.TreeMap;

public class MenuList {
	
	/**
	 * Variable for MenuList class
	 * menuList : List of menu items with a string as the key and a menu item object for the value
	 */
	public static TreeMap<String, MenuItems> menuList; 

	/**
	 * Constructor for creating a tree map for menu list
	 */
	public MenuList() { 
		menuList = new TreeMap<String, MenuItems> ();
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
		if (menuList.containsKey(key)) {
			throw new IllegalArgumentException ("Key already exists");  
		}
		menuList.put(key, value);
	}

	/**
	 * Get a menu item from the menu list
	 * @param key
	 * @return key
	 */
	public MenuItems getItem(String key) {
		if (menuList.containsKey(key)) {
			return menuList.get(key);
		} throw new IllegalArgumentException ("Key does not exist"); 
	}
	 
	/**
	 * Remove a menu item from the menu list
	 * @param key
	 */
	public void remove(String key){
		if (!menuList.containsKey(key)) {
			throw new IllegalArgumentException ("Item does not exist");
		} 
		menuList.remove(key);
	}
	
	
	/**
	 * method for returning the number of items that are currently on the Menu
	 * 
	 * @return the size of the menu, i.e. number of items
	 */
	public int getNumberofMenuItemsInList() {
		return menuList.size();
	}
	
	/**
	 * Allows other classes to traverse the items in the menu list
	 * 
	 * @return
	 */
	public Iterable<MenuItems> getAllMenuItems() {
		return menuList.values();
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
	
}
