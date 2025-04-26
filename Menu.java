
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Menu {
    private final List<MenuItem> items;

    public Menu(int menuId) {
        this.items = new ArrayList<>();
    }

    public void addItems(MenuItem... items) { // varargs method
        this.items.addAll(Arrays.asList(items));
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
