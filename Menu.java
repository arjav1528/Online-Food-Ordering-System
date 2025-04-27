import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a menu containing multiple food items.
 * This class manages a collection of MenuItem objects that can be displayed
 * to customers in a food ordering system.
 */
public class Menu {
    /** The list of menu items contained in this menu */
    private final List<MenuItem> items;

    /**
     * Creates a new Menu with the specified ID.
     *
     * @param menuId The unique identifier for this menu
     */
    public Menu(int menuId) {
        this.items = new ArrayList<>();
    }

    /**
     * Adds one or more menu items to this menu.
     *
     * @param items The menu items to be added to this menu
     */
    public void addItems(MenuItem... items) { // varargs method
        this.items.addAll(Arrays.asList(items));
    }

    /**
     * Returns all menu items in this menu.
     *
     * @return A list containing all menu items
     */
    public List<MenuItem> getItems() {
        return items;
    }
}
