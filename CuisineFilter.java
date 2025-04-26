import java.util.List;
import java.util.stream.Collectors;


public interface CuisineFilter extends RestaurantFilter {
    String getCuisine();
    
    default List<Restaurant> filterByCuisine(List<Restaurant> restaurants, String cuisine) {
        return restaurants.stream()
            .filter(r -> r.getCuisine().equals(cuisine))
            .collect(Collectors.toList());
    }
}