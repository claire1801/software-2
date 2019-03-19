package menu;

import exceptions.InvalidItemIdentifierException;

public abstract class MenuItems {
	
	protected String uniqueID;
	protected double cost; 
	protected String description;
	protected String allergens;
	protected String name;
	
	public MenuItems(String name, String id, double cost, String desc, String algns) throws InvalidItemIdentifierException{
		if((id.startsWith("COFEE"))||(id.startsWith("MEALS"))||(id.startsWith("SNACK"))||(id.startsWith("DRINK"))) {
			
			try {
				this.name = name;
				uniqueID = id;
				this.cost = cost;
				description = desc;
				allergens = algns; 
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (NullPointerException e)
			{
				System.out.println(e.getMessage());
			}
		}else {
			throw new InvalidItemIdentifierException(String.format("Invalid ID on product with name: %s", name));
		}
	}
	
	/**
	 * get the name of the item
	 * @return string name
	 */
	public String getName() { return name; }
	/**
	 * get the item ID
	 * @return String - ID 
	 */
	public String getID() { return uniqueID; }
	/**
	 * set the ID
	 * @param newID - string
	 */
	public void setID(String newID) { 
		
		uniqueID = newID; 
	}
	/**
	 * get cost
	 * @return cost of item
	 */
	public double getCost() { return cost; }
	/**
	 * set cost of item
	 * @param newCost
	 */
	public void setCost(double newCost) { cost = newCost; }
	/**
	 * get description of item 
	 * @return string 
	 */
	
	public String getDescription() { return description; }
	/**
	 * set Description
	 * @param newDescription
	 */
	
	public void setDescription(String newDescription) { description = newDescription; }
	/**
	 * get allergens
	 * @return
	 */
	public String getAllergens() { return allergens; }
	/**
	 * get allergens 
	 * @param newAllergens
	 */
	public void setAllergens(String newAllergens) { allergens = newAllergens; }
	
	

}
