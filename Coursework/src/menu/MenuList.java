package menu;

import java.util.TreeMap;

public class MenuList {
	
	/**
	 * Variable for MenuList class
	 * menuList : List of menu items with a string as the key and a menu item object for the value
	 */
	private  TreeMap<String, MenuItems> menuList; 

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
	/**
	 *  return a random item from the list - used for creating a random customer order
	 * @return random item
	 */
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
	/**
	 * return a list of all items in menu
	 * @return string of items (formated into list)
	 */
	public String getAllMenuItemsString() {
		Iterable<MenuItems> list = this.getAllMenuItems();
		String details = "";
		for(MenuItems item : list) {
			details += String.format("%30s ID: %s Cost: £%.2f - %s\n",item.getName(),item.getID(),item.getCost(), item.getDescription());
		}
		return details;
	}
	
}
