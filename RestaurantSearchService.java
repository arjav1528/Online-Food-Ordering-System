import java.util.List;

/**
 * Service for searching and filtering restaurants based on various criteria.
 * Implements cuisine and price filtering functionality.
 */
public class RestaurantSearchService implements CuisineFilter, PriceFilter {
    private String cuisine;
    private double minPrice;
    private double maxPrice;
    
    /**
     * Constructs a new RestaurantSearchService with specified filtering criteria.
     * 
     * @param cuisine The type of cuisine to filter by
     * @param minPrice The minimum price range for filtering
     * @param maxPrice The maximum price range for filtering
     */
    public RestaurantSearchService(String cuisine, double minPrice, double maxPrice) {
        this.cuisine = cuisine;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    
    /**
     * Gets the cuisine type used for filtering.
     * 
     * @return The cuisine type string
     */
    @Override
    public String getCuisine() {
        return cuisine;
    }
    
    /**
     * Gets the minimum price limit used for filtering.
     * 
     * @return The minimum price value
     */
    @Override
    public double getMinPrice() {
        return minPrice;
    }
    
    /**
     * Gets the maximum price limit used for filtering.
     * 
     * @return The maximum price value
     */
    @Override
    public double getMaxPrice() {
        return maxPrice;
    }
    
    /**
     * Filters a list of restaurants by both cuisine and price range.
     * 
     * @param restaurants The original list of restaurants to filter
     * @return A filtered list of restaurants matching criteria
     */
    @Override
    public List<Restaurant> filter(List<Restaurant> restaurants) {
        List<Restaurant> byCuisine = filterByCuisine(restaurants, cuisine);
        return filterByPrice(byCuisine, minPrice, maxPrice);
    }
    
    /**
     * Searches for restaurants near a specific geographical location.
     * 
     * @param latitude The latitude coordinate of the search location
     * @param longitude The longitude coordinate of the search location
     * @param cuisines Optional array of cuisine types to filter results
     * @return A list of nearby restaurants matching the cuisine criteria
     */
    public List<Restaurant> searchNearby(double latitude, double longitude, String... cuisines) {
        System.out.println("Searching for " + cuisines.length + " cuisines near location");
        return List.of(); // Return empty list for now
    }
    
    /**
     * Searches for restaurants that match specific tags.
     * 
     * @param tags The tags to search for (e.g., "family-friendly", "outdoor-seating")
     * @return A list of restaurants that match the specified tags
     */
    public List<Restaurant> searchByTags(String... tags) {
        System.out.println("Searching for restaurants with " + tags.length + " tags");
        return List.of(); // Return empty list for now
    }
}