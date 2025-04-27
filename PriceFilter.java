import java.util.List;
import java.util.stream.Collectors;

/**
 * Interface for filtering restaurants based on price ranges.
 * Extends the RestaurantFilter interface to provide price-specific filtering capabilities.
 */
public interface PriceFilter extends RestaurantFilter {
    
    /**
     * Gets the minimum price threshold for filtering.
     * 
     * @return the minimum price value
     */
    double getMinPrice();
    
    /**
     * Gets the maximum price threshold for filtering.
     * 
     * @return the maximum price value
     */
    double getMaxPrice();
    
    /**
     * Filters a list of restaurants based on their price range.
     * 
     * @param restaurants the list of restaurants to filter
     * @param minPrice the minimum price threshold (inclusive)
     * @param maxPrice the maximum price threshold (inclusive)
     * @return a new list containing only restaurants within the specified price range
     */
    default List<Restaurant> filterByPrice(List<Restaurant> restaurants, double minPrice, double maxPrice) {
        // This is a simplified implementation
        return restaurants.stream()
            .filter(r -> {
                // TODO: Implement actual price filtering logic here
                return true;
            })
            .collect(Collectors.toList());
    }
}