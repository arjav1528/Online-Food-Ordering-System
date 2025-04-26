import java.util.List;


public interface RestaurantFilter {
    List<Restaurant> filter(List<Restaurant> restaurants);
}