/**
 * Represents a menu item in the food ordering system.
 * This class stores information about individual food items including
 * their unique identifier, name, price, and category.
 */
public class MenuItem {
    /** The unique identifier for the menu item */
    private final int itemId;
    /** The name of the menu item */
    private final String name;
    /** The price of the menu item */
    double price;
    /** The category to which the menu item belongs */
    private final String category;

    /**
     * Constructs a new MenuItem with the specified properties.
     *
     * @param itemId   The unique identifier for the menu item
     * @param name     The name of the menu item
     * @param price    The price of the menu item
     * @param category The category to which the menu item belongs
     */
    public MenuItem(int itemId, String name, double price, String category) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /**
     * Returns the name of the menu item.
     *
     * @return The name of the menu item
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the price of the menu item.
     *
     * @return The price of the menu item
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Returns the category of the menu item.
     *
     * @return The category of the menu item
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Returns the unique identifier of the menu item.
     *
     * @return The unique identifier of the menu item
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Returns a formatted string with details about the menu item.
     *
     * @return A string containing the name, category, and price of the menu item
     */
    public String getDetails() {
        return name + " (" + category + ") ₹" + price;
    }
}
