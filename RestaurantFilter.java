import java.util.List;

/**
 * Interface for filtering restaurants based on specific criteria.
 * Implementations of this interface can define different filtering strategies.
 */
public interface RestaurantFilter {
    
    /**
     * Filters a list of restaurants based on specific criteria.
     *
     * @param restaurants The list of restaurants to be filtered
     * @return A filtered list of restaurants that meet the criteria
     */
    List<Restaurant> filter(List<Restaurant> restaurants);
}