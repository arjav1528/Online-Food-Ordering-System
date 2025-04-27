/**
 * Interface for managing data persistence operations.
 * Provides methods for saving and loading data objects.
 */
public interface DataManager {
    /**
     * Saves the provided data object.
     *
     * @param data The object to be saved
     * @return true if the save operation was successful, false otherwise
     */
    boolean saveData(Object data);
    
    /**
     * Loads and returns previously saved data.
     *
     * @return The loaded data object, or null if no data is available
     */
    Object loadData();
}
