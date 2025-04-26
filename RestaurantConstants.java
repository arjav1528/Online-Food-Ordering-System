import java.util.HashMap;
import java.util.Map;


public class RestaurantConstants {    // Restaurant names
    public static final String TAJ_RESTAURANT = "Taj Restaurant";
    public static final String MAINLAND_CHINA = "Mainland China";
    public static final String PIZZA_HUT = "Pizza Hut";
    public static final String BIRYANI_HOUSE = "Biryani House";
    public static final String SUSHI_PALACE = "Sushi Palace";
    
    public static final String CUISINE_INDIAN = "Indian";
    public static final String CUISINE_CHINESE = "Chinese";
    public static final String CUISINE_ITALIAN = "Italian";
    public static final String CUISINE_HYDERABADI = "Hyderabadi";
    public static final String CUISINE_JAPANESE = "Japanese";
    
    private static final Map<String, Address> RESTAURANT_ADDRESSES = new HashMap<>();
    
    public static final int LUNCH_MENU_ID = 1;
    public static final int DINNER_MENU_ID = 2;
    public static final int SPECIALS_MENU_ID = 3;
    
    static {
        RESTAURANT_ADDRESSES.put(TAJ_RESTAURANT, 
            new Address("123 Food Street", "Mumbai", "Maharashtra", "400001"));
        RESTAURANT_ADDRESSES.put(MAINLAND_CHINA, 
            new Address("456 Dragon Road", "Delhi", "Delhi", "110001"));
        RESTAURANT_ADDRESSES.put(PIZZA_HUT, 
            new Address("789 Italy Avenue", "Bangalore", "Karnataka", "560001"));
        RESTAURANT_ADDRESSES.put(BIRYANI_HOUSE, 
            new Address("42 Spice Lane", "Hyderabad", "Telangana", "500001"));
        RESTAURANT_ADDRESSES.put(SUSHI_PALACE, 
            new Address("88 Ocean Drive", "Kolkata", "West Bengal", "700001"));
    }
    
    public static Address getRestaurantAddress(String restaurantName) {
        return RESTAURANT_ADDRESSES.get(restaurantName);
    }
    
    public static Menu getRestaurantMenu(String restaurantName, int menuId) {
        Menu menu = new Menu(menuId);
        
        if (restaurantName.equals(TAJ_RESTAURANT)) {
            if (menuId == LUNCH_MENU_ID) {
                menu.addItems(
                    new MenuItem(101, "Butter Chicken", 350.0, "Main Course"),
                    new MenuItem(102, "Paneer Tikka", 250.0, "Starter"),
                    new MenuItem(103, "Garlic Naan", 50.0, "Bread"),
                    new MenuItem(104, "Gulab Jamun", 120.0, "Dessert"),
                    new MenuItem(105, "Dal Makhani", 200.0, "Main Course")
                );
            } else if (menuId == DINNER_MENU_ID) {
                menu.addItems(
                    new MenuItem(201, "Chicken Biryani", 400.0, "Main Course"),
                    new MenuItem(202, "Malai Kofta", 280.0, "Main Course"),
                    new MenuItem(203, "Tandoori Roti", 40.0, "Bread"),
                    new MenuItem(204, "Rasmalai", 150.0, "Dessert"),
                    new MenuItem(205, "Chicken Tikka", 320.0, "Starter")
                );
            }
        } else if (restaurantName.equals(MAINLAND_CHINA)) {
            if (menuId == LUNCH_MENU_ID) {
                menu.addItems(
                    new MenuItem(301, "Chicken Manchurian", 320.0, "Starter"),
                    new MenuItem(302, "Veg Hakka Noodles", 220.0, "Main Course"),
                    new MenuItem(303, "Spring Rolls", 180.0, "Starter"),
                    new MenuItem(304, "Date Pancake", 200.0, "Dessert"),
                    new MenuItem(305, "Kung Pao Chicken", 350.0, "Main Course")
                );
            }
        } else if (restaurantName.equals(PIZZA_HUT)) {
            if (menuId == LUNCH_MENU_ID) {
                menu.addItems(
                    new MenuItem(401, "Margherita Pizza", 299.0, "Pizza"),
                    new MenuItem(402, "Pepperoni Pizza", 399.0, "Pizza"),
                    new MenuItem(403, "Garlic Bread", 149.0, "Sides"),
                    new MenuItem(404, "Choco Lava Cake", 99.0, "Dessert"),
                    new MenuItem(405, "Farmhouse Pizza", 349.0, "Pizza")
                );
            }
        } else if (restaurantName.equals(BIRYANI_HOUSE)) {
            if (menuId == LUNCH_MENU_ID) {
                menu.addItems(
                    new MenuItem(501, "Hyderabadi Chicken Biryani", 380.0, "Main Course"),
                    new MenuItem(502, "Mutton Biryani", 450.0, "Main Course"),
                    new MenuItem(503, "Veg Biryani", 280.0, "Main Course"),
                    new MenuItem(504, "Chicken 65", 220.0, "Starter"),
                    new MenuItem(505, "Phirni", 120.0, "Dessert")
                );
            }
        } else if (restaurantName.equals(SUSHI_PALACE)) {
            if (menuId == LUNCH_MENU_ID) {
                menu.addItems(
                    new MenuItem(601, "Salmon Nigiri", 420.0, "Sushi"),
                    new MenuItem(602, "California Roll", 350.0, "Sushi"),
                    new MenuItem(603, "Miso Soup", 180.0, "Soup"),
                    new MenuItem(604, "Tempura Prawns", 400.0, "Starter"),
                    new MenuItem(605, "Green Tea Ice Cream", 220.0, "Dessert")
                );
            }
        }
        
        return menu;
    }
}