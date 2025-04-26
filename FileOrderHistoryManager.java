import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOrderHistoryManager {
    private static final String ORDER_HISTORY_DIR = "orderhistory";
    
    
    public static List<Order> loadOrderHistory(String userEmail) {
        String filename = ORDER_HISTORY_DIR + File.separator + userEmail + ".txt";
        List<Order> previousOrders = new ArrayList<>();
        
        
        File historyFile = new File(filename);
        if (!historyFile.exists()) {
            return previousOrders;
        }
        
        try (Scanner scanner = new Scanner(historyFile)) {
            
            System.out.println("Previous order history found for " + userEmail);
        } catch (IOException e) {
            System.out.println("Error reading order history: " + e.getMessage());
        }
        
        return previousOrders;
    }
    
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
            
            writer.println("==== USER DETAILS ====");
            writer.println("Name: " + user.getName());
            writer.println("Email: " + user.getEmail());
            writer.println("Phone: " + user.getPhone());
            if (user.address != null) {
                writer.println("Address: " + user.address.getFullAddress());
            }
            writer.println();
            
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