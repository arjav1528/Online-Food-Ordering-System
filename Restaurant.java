import java.util.ArrayList;
import java.util.List;


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

    public Restaurant(String name, Address location) {
        this(name, location, "Multiple");
    }

    // Methods
    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public boolean isOpen() {
        return isOpen;
    }
    
    // Added getter methods
    public String getName() {
        return name;
    }
    
    public Address getLocation() {
        return location;
    }
    
    public String getCuisine() {
        return cuisine;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    public Double getAveragePrice() {
        return averagePrice;
    }
    
    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }
    
    // Wrapper method examples
    public void setMinimumOrderAmount(Double amount) {
        this.minimumOrderAmount = amount;
    }
    
    public Double getMinimumOrderAmount() {
        return this.minimumOrderAmount;
    }
    
    public void setPreparationTime(Integer minutes) {
        this.preparationTime = minutes;
    }
    
    public Integer getPreparationTime() {
        return this.preparationTime;
    }
}
