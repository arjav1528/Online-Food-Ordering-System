import java.util.List;
import java.util.stream.Collectors;

public interface PriceFilter extends RestaurantFilter {
    double getMinPrice();
    double getMaxPrice();
    
    default List<Restaurant> filterByPrice(List<Restaurant> restaurants, double minPrice, double maxPrice) {
        // This is a simplified implementation
        return restaurants.stream()
            .filter(r -> {
                
                return true;
            })
            .collect(Collectors.toList());
    }
}