import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the persistence of order history to the file system.
 * This class handles saving and loading user order histories to/from text files.
 */
public class FileOrderHistoryManager {
    /** Directory where order history files are stored */
    private static final String ORDER_HISTORY_DIR = "orderhistory";
    
    /**
     * Loads the order history for a specific user from a file.
     *
     * @param userEmail The email of the user whose order history should be loaded
     * @return A list of orders previously placed by the user
     */
    public static List<Order> loadOrderHistory(String userEmail) {
        String filename = ORDER_HISTORY_DIR + File.separator + userEmail + ".txt";
        List<Order> previousOrders = new ArrayList<>();
        
        File historyFile = new File(filename);
        if (!historyFile.exists()) {
            return previousOrders;
        }
        
        try (Scanner scanner = new Scanner(historyFile)) {
            // TODO: Implement actual parsing of order history from file
            System.out.println("Previous order history found for " + userEmail);
        } catch (IOException e) {
            System.out.println("Error reading order history: " + e.getMessage());
        }
        
        return previousOrders;
    }
    
    /**
     * Saves the user's order history to a file.
     * Creates a new file or updates an existing one with the current order history.
     *
     * @param user The user whose order history should be saved
     * @return true if the save operation was successful, false otherwise
     */
    public static boolean saveOrderHistory(User user) {
        // Create directory if it doesn't exist
        File directory = new File(ORDER_HISTORY_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        String filename = ORDER_HISTORY_DIR + File.separator + user.getEmail() + ".txt";
        
        boolean fileExists = Files.exists(Paths.get(filename));
        if (fileExists) {
            System.out.println("Updating existing order history file for " + user.getEmail());
        } else {
            System.out.println("Creating new order history file for " + user.getEmail());
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write user details section
            writer.println("==== USER DETAILS ====");
            writer.println("Name: " + user.getName());
            writer.println("Email: " + user.getEmail());
            writer.println("Phone: " + user.getPhone());
            if (user.address != null) {
                writer.println("Address: " + user.address.getFullAddress());
            }
            writer.println();
            
            // Write order history section
            writer.println("==== ORDER HISTORY ====");
            List<Order> orderHistory = user.getOrderHistory();
            
            for (Order order : orderHistory) {
                writer.println("Order ID: " + order.getOrderId());
                writer.println("Order Time: " + order.getOrderTime());
                writer.println("Payment Status: " + (order.isOrderPaid() ? "Paid" : "Unpaid"));
                
                writer.println("Ordered Items:");
                for (MenuItem item : order.getOrderedItems()) {
                    writer.printf("  - %s - ₹%.2f (%s)%n", 
                        item.getName(), item.getPrice(), item.getCategory());
                }
                writer.println("Total Amount: ₹" + order.getTotalPrice());
                writer.println("--------------------");
            }
            
            return true;
        } catch (IOException e) {
            System.out.println("Error writing order history: " + e.getMessage());
            return false;
        }
    }
}