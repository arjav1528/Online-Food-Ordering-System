import java.util.ArrayList;
import java.util.List;

/**
 * Represents a restaurant in the online food ordering system.
 * Contains information about the restaurant including name, location, cuisine,
 * menus, operational status, and other business details.
 */
public class Restaurant {
    private String name;
    private Address location;
    private String cuisine;
    private List<Menu> menus;
    private boolean isOpen;
    private Double rating; 
    private Double averagePrice;
    private Double minimumOrderAmount;
    private Integer preparationTime; 

    /**
     * Creates a new restaurant with specified details.
     *
     * @param name     The name of the restaurant
     * @param location The address/location of the restaurant
     * @param cuisine  The type of cuisine the restaurant specializes in
     */
    public Restaurant(String name, Address location, String cuisine) {
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.menus = new ArrayList<>();
        this.isOpen = true;
        this.rating = 4.0; 
        this.averagePrice = 300.0;
        this.minimumOrderAmount = 100.0;
        this.preparationTime = 30;
    }

    /**
     * Creates a new restaurant with a default cuisine type of "Multiple".
     *
     * @param name     The name of the restaurant
     * @param location The address/location of the restaurant
     */
    public Restaurant(String name, Address location) {
        this(name, location, "Multiple");
    }

    /**
     * Adds a menu to the restaurant's list of available menus.
     *
     * @param menu The menu to be added to the restaurant
     */
    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    /**
     * Retrieves all menus available at this restaurant.
     *
     * @return A list of all the menus offered by the restaurant
     */
    public List<Menu> getMenus() {
        return menus;
    }

    /**
     * Checks if the restaurant is currently open for orders.
     *
     * @return true if the restaurant is open, false otherwise
     */
    public boolean isOpen() {
        return isOpen;
    }
    
    /**
     * Gets the name of the restaurant.
     *
     * @return The restaurant's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the location/address of the restaurant.
     *
     * @return The restaurant's address
     */
    public Address getLocation() {
        return location;
    }
    
    /**
     * Gets the cuisine type of the restaurant.
     *
     * @return The type of cuisine the restaurant specializes in
     */
    public String getCuisine() {
        return cuisine;
    }
    
    /**
     * Gets the current customer rating of the restaurant.
     *
     * @return The restaurant's rating (typically on a scale of 1-5)
     */
    public Double getRating() {
        return rating;
    }
    
    /**
     * Updates the restaurant's rating.
     *
     * @param rating The new rating value to set
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    /**
     * Gets the average price of menu items at this restaurant.
     *
     * @return The average price of items
     */
    public Double getAveragePrice() {
        return averagePrice;
    }
    
    /**
     * Sets the average price of menu items at this restaurant.
     *
     * @param averagePrice The new average price to set
     */
    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }
    
    /**
     * Sets the minimum order amount required for delivery.
     *
     * @param amount The minimum order amount in currency units
     */
    public void setMinimumOrderAmount(Double amount) {
        this.minimumOrderAmount = amount;
    }
    
    /**
     * Gets the minimum order amount required for delivery.
     *
     * @return The minimum order amount
     */
    public Double getMinimumOrderAmount() {
        return this.minimumOrderAmount;
    }
    
    /**
     * Sets the average preparation time for orders from this restaurant.
     *
     * @param minutes The preparation time in minutes
     */
    public void setPreparationTime(Integer minutes) {
        this.preparationTime = minutes;
    }
    
    /**
     * Gets the average preparation time for orders from this restaurant.
     *
     * @return The preparation time in minutes
     */
    public Integer getPreparationTime() {
        return this.preparationTime;
    }
}
