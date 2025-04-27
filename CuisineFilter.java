import java.util.List;
import java.util.stream.Collectors;

/**
 * Interface that defines filtering capabilities for restaurants based on cuisine type.
 * This interface extends the RestaurantFilter interface to add cuisine-specific filtering.
 */
public interface CuisineFilter extends RestaurantFilter {
    /**
     * Gets the cuisine type used for filtering.
     *
     * @return the cuisine type as a String
     */
    String getCuisine();
    
    /**
     * Filters a list of restaurants based on a specific cuisine type.
     *
     * @param restaurants the list of restaurants to filter
     * @param cuisine the cuisine type to filter by
     * @return a filtered list containing only restaurants of the specified cuisine
     */
    default List<Restaurant> filterByCuisine(List<Restaurant> restaurants, String cuisine) {
        return restaurants.stream()
            .filter(r -> r.getCuisine().equals(cuisine))
            .collect(Collectors.toList());
    }
}