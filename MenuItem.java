
public class MenuItem {
    private final int itemId;
    private final String name;
    double price;
    private final String category;

    public MenuItem(int itemId, String name, double price, String category) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public int getItemId() {
        return itemId;
    }

    public String getDetails() {
        return name + " (" + category + ") ₹" + price;
    }
}
