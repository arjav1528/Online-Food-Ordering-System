import java.util.List;


public class RestaurantSearchService implements CuisineFilter, PriceFilter {
    private String cuisine;
    private double minPrice;
    private double maxPrice;
    
    public RestaurantSearchService(String cuisine, double minPrice, double maxPrice) {
        this.cuisine = cuisine;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    
    @Override
    public String getCuisine() {
        return cuisine;
    }
    
    @Override
    public double getMinPrice() {
        return minPrice;
    }
    
    @Override
    public double getMaxPrice() {
        return maxPrice;
    }
    
    @Override
    public List<Restaurant> filter(List<Restaurant> restaurants) {
        List<Restaurant> byCuisine = filterByCuisine(restaurants, cuisine);
        return filterByPrice(byCuisine, minPrice, maxPrice);
    }
    
    public List<Restaurant> searchNearby(double latitude, double longitude, String... cuisines) {
        System.out.println("Searching for " + cuisines.length + " cuisines near location");
        return List.of(); // Return empty list for now
    }
    
    public List<Restaurant> searchByTags(String... tags) {
        System.out.println("Searching for restaurants with " + tags.length + " tags");
        return List.of(); // Return empty list for now
    }
}