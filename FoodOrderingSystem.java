import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class FoodOrderingSystem {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("\n" + BOLD + "Welcome to Zomato Food Delivery Service" + RESET);
            System.out.println("─".repeat(50));
            
            System.out.println(BOLD + "\n📋 " + CYAN + " USER INFORMATION" + RESET);
            System.out.println("  " + "─".repeat(50));
            
            System.out.print(BOLD + "  👤 Name: " + RESET);
            String userName = scanner.nextLine();
            
            System.out.print(BOLD + "  📧 Email: " + RESET);
            String userEmail = scanner.nextLine();
            
            System.out.print(BOLD + "  📱 Phone: " + RESET);
            String userPhone = scanner.nextLine();
            
            System.out.println(BOLD + "\n🏠 " + CYAN + " DELIVERY ADDRESS" + RESET);
            System.out.println("  " + "─".repeat(50));
            
            System.out.print(BOLD + "  Street: " + RESET);
            String street = scanner.nextLine();
            
            System.out.print(BOLD + "  City: " + RESET);
            String city = scanner.nextLine();
            
            System.out.print(BOLD + "  State: " + RESET);
            String state = scanner.nextLine();
            
            System.out.print(BOLD + "  Zipcode: " + RESET);
            String zipcode = scanner.nextLine();
            
            Address userAddress = new Address(street, city, state, zipcode);
            User user = new User(userName, userEmail, userPhone);
            user.address = userAddress;
            
            List<Order> previousOrders = FileOrderHistoryManager.loadOrderHistory(userEmail);
            if (!previousOrders.isEmpty()) {
                for (Order prevOrder : previousOrders) {
                    user.addOrder(prevOrder);
                }
                System.out.println(GREEN + "\n✓ Loaded " + previousOrders.size() + " previous orders." + RESET);
            }
            
            String[] restaurants = {
                RestaurantConstants.TAJ_RESTAURANT,
                RestaurantConstants.MAINLAND_CHINA,
                RestaurantConstants.PIZZA_HUT,
                RestaurantConstants.BIRYANI_HOUSE,
                RestaurantConstants.SUSHI_PALACE
            };
            
            String[] cuisines = {
                RestaurantConstants.CUISINE_INDIAN,
                RestaurantConstants.CUISINE_CHINESE,
                RestaurantConstants.CUISINE_ITALIAN,
                RestaurantConstants.CUISINE_HYDERABADI,
                RestaurantConstants.CUISINE_JAPANESE
            };
            
            List<Restaurant> allRestaurants = new ArrayList<>();
            for (int i = 0; i < restaurants.length; i++) {
                Address restaurantAddress = RestaurantConstants.getRestaurantAddress(restaurants[i]);
                Restaurant restaurant = new Restaurant(restaurants[i], restaurantAddress, cuisines[i]);
                // Set some sample values
                restaurant.setRating((double)(3 + (int)(Math.random() * 20) / 10.0)); // Random rating between 3.0-5.0
                restaurant.setAveragePrice(200.0 + (Math.random() * 600)); // Random price between 200-800
                allRestaurants.add(restaurant);
            }

            System.out.println(BOLD + "\n🔍 " + YELLOW + " FILTER RESTAURANTS" + RESET);
            System.out.println("  " + "─".repeat(50));
            System.out.println("  1. " + BOLD + "Show all restaurants" + RESET);
            System.out.println("  2. " + BOLD + "Filter by cuisine" + RESET);
            System.out.println("  3. " + BOLD + "Filter by price range" + RESET);
            System.out.println("  4. " + BOLD + "Skip filtering" + RESET);

            int filterChoice = 0;
            boolean validFilterChoice = false;

            while (!validFilterChoice) {
                System.out.print(BOLD + "\n  Select filter option (1-4): " + RESET);
                try {
                    filterChoice = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    if (filterChoice >= 1 && filterChoice <= 4) {
                        validFilterChoice = true;
                    } else {
                        System.out.println(RED + "  ⚠️ Invalid selection. Please enter a number between 1 and 4." + RESET);
                    }
                } catch (Exception e) {
                    System.out.println(RED + "  ⚠️ Please enter a valid number." + RESET);
                    scanner.nextLine();
                }
            }

            List<Restaurant> filteredRestaurants = allRestaurants;

            if (filterChoice == 2) {
                System.out.println("  Available cuisines:");
                for (int i = 0; i < cuisines.length; i++) {
                    System.out.println("  " + (i+1) + ". " + cuisines[i]);
                }
                
                System.out.print(BOLD + "  Select cuisine (1-" + cuisines.length + "): " + RESET);
                int cuisineChoice = scanner.nextInt();
                scanner.nextLine();
                
                if (cuisineChoice >= 1 && cuisineChoice <= cuisines.length) {
                    String selectedCuisine = cuisines[cuisineChoice-1];
                    RestaurantSearchService searchService = new RestaurantSearchService(selectedCuisine, 0, 10000);
                    filteredRestaurants = searchService.filter(allRestaurants);
                    System.out.println(GREEN + "  ✓ Filtered by: " + BOLD + selectedCuisine + RESET);
                }
            } else if (filterChoice == 3) {
                // Filter by price range
                System.out.print(BOLD + "  Minimum price (₹): " + RESET);
                double minPrice = scanner.nextDouble();
                scanner.nextLine();
                
                System.out.print(BOLD + "  Maximum price (₹): " + RESET);
                double maxPrice = scanner.nextDouble();
                scanner.nextLine(); 
                
                RestaurantSearchService searchService = new RestaurantSearchService("", minPrice, maxPrice);
                filteredRestaurants = searchService.filter(allRestaurants);
                System.out.println(GREEN + "  ✓ Filtered by price range: ₹" + minPrice + " - ₹" + maxPrice + RESET);
            }

           
            if (filterChoice != 4) {
                System.out.println(BOLD + "\n  Filtered Results:" + RESET);
                for (int i = 0; i < filteredRestaurants.size(); i++) {
                    Restaurant r = filteredRestaurants.get(i);
                    System.out.printf("  " + CYAN + "%d. " + RESET + BOLD + "%-20s" + RESET + " │ %s │ ★%.1f │ Avg: ₹%.0f%n", 
                            (i + 1), r.getName(), r.getCuisine(), r.getRating(), r.getAveragePrice());
                }
                
                System.out.println("\n  " + BOLD + "Continue with restaurant selection..." + RESET);
            }

           
            System.out.println(BOLD + "\n🍽️  " + YELLOW + " AVAILABLE RESTAURANTS" + RESET);
            System.out.println("  " + "─".repeat(50));
            
        
            for (int i = 0; i < restaurants.length; i++) {
                System.out.printf("  " + CYAN + "%d. " + RESET + BOLD + "%-20s" + RESET + " │ %s%n", 
                        (i + 1), restaurants[i], cuisines[i]);
            }

            int restaurantChoice = 0;
            boolean validRestaurantChoice = false;
            
            while (!validRestaurantChoice) {
                System.out.print(BOLD + "\n  Select a restaurant (1-5): " + RESET);
                try {
                    restaurantChoice = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (restaurantChoice >= 1 && restaurantChoice <= 5) {
                        validRestaurantChoice = true;
                        System.out.println(GREEN + "  ✓ Selected: " + BOLD + restaurants[restaurantChoice-1] + RESET);
                    } else {
                        System.out.println(RED + "  ⚠️  Invalid selection. Please enter a number between 1 and 5." + RESET);
                    }
                } catch (Exception e) {
                    System.out.println(RED + "  ⚠️  Please enter a valid number." + RESET);
                    scanner.nextLine();
                }
            }

            String restaurantName;
            String cuisine;

            switch (restaurantChoice) {
                case 1:
                    restaurantName = RestaurantConstants.TAJ_RESTAURANT;
                    cuisine = RestaurantConstants.CUISINE_INDIAN;
                    break;
                case 2:
                    restaurantName = RestaurantConstants.MAINLAND_CHINA;
                    cuisine = RestaurantConstants.CUISINE_CHINESE;
                    break;
                case 3:
                    restaurantName = RestaurantConstants.PIZZA_HUT;
                    cuisine = RestaurantConstants.CUISINE_ITALIAN;
                    break;
                case 4:
                    restaurantName = RestaurantConstants.BIRYANI_HOUSE;
                    cuisine = RestaurantConstants.CUISINE_HYDERABADI;
                    break;
                case 5:
                    restaurantName = RestaurantConstants.SUSHI_PALACE;
                    cuisine = RestaurantConstants.CUISINE_JAPANESE;
                    break;
                default:
                    System.out.println(RED + "  Invalid selection. Defaulting to " + RestaurantConstants.TAJ_RESTAURANT + RESET);
                    restaurantName = RestaurantConstants.TAJ_RESTAURANT;
                    cuisine = RestaurantConstants.CUISINE_INDIAN;
            }
            
            Address restaurantAddress = RestaurantConstants.getRestaurantAddress(restaurantName);
            Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress, cuisine);
            
            Menu lunchMenu = RestaurantConstants.getRestaurantMenu(restaurantName, RestaurantConstants.LUNCH_MENU_ID);
            restaurant.addMenu(lunchMenu);
            
            System.out.println(GREEN + "\n✓ " + RESET + "You selected: " + BOLD + restaurantName + RESET);
            System.out.println("  Address: " + restaurantAddress.getStreet() + ", " + restaurantAddress.getCity());
            System.out.println("  Cuisine: " + cuisine);
            
            System.out.println(BOLD + "\n📝 " + PURPLE + " MENU" + RESET);
            System.out.println("  " + "─".repeat(50));
            
            List<MenuItem> menuItems = lunchMenu.getItems();
            String currentCategory = "";
            
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem item = menuItems.get(i);
                
                if (!item.getCategory().equals(currentCategory)) {
                    currentCategory = item.getCategory();
                    System.out.println("\n  " + BOLD + CYAN + "• " + currentCategory + RESET);
                }
                
                System.out.printf("  %2d. %-30s ₹%.2f%n", 
                        i + 1, item.getName(), item.getPrice());
            }
            
            List<MenuItem> orderedItems = new ArrayList<>();
            boolean orderingComplete = false;
            System.out.println("\n  " + BOLD + "Add items to your cart:" + RESET);
            
            while (!orderingComplete) {
                System.out.print(BOLD + "  Item # (or 0 to finish): " + RESET);
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                
                if (choice == 0) {
                    orderingComplete = true;
                } else if (choice >= 1 && choice <= menuItems.size()) {
                    MenuItem selectedItem = menuItems.get(choice - 1);
                    orderedItems.add(selectedItem);
                    System.out.println(GREEN + "  ✓ Added: " + selectedItem.getName() + RESET);
                } else {
                    System.out.println(RED + "  ⚠️ Invalid selection. Please try again." + RESET);
                }
            }
            
            
            if (orderedItems.isEmpty()) {
                System.out.println(YELLOW + "\n⚠️ No items selected. Exiting." + RESET);
                return;
            }
            
            Order order = new Order(1001, user, orderedItems);
            
           
            System.out.println(BOLD + "\n🛒 " + BLUE + " YOUR ORDER" + RESET);
            System.out.println("  " + "─".repeat(50));
            System.out.println("  Restaurant: " + BOLD + restaurantName + RESET);
            
            double totalAmount = 0;
            for (MenuItem item : orderedItems) {
                System.out.printf("  • %-30s ₹%.2f%n", item.getName(), item.getPrice());
                totalAmount += item.getPrice();
            }
            System.out.println("  " + "─".repeat(50));
            System.out.printf("  " + BOLD + "Total Amount: ₹%.2f%n" + RESET, totalAmount);
            
            System.out.println(BOLD + "\n🎁 " + PURPLE + " AVAILABLE PROMOTIONS" + RESET);
            System.out.println("  " + "─".repeat(50));

            int rewardPoints = user.getRewards().getPoints();
            System.out.println("  Your Reward Points: " + BOLD + rewardPoints + RESET);

            Map<String, Double> promos = user.getRewards().getAllPromos();
            if (!promos.isEmpty()) {
                System.out.println("  Available Promo Codes:");
                for (Map.Entry<String, Double> promo : promos.entrySet()) {
                    System.out.printf("  • %-15s ₹%.2f discount%n", 
                            promo.getKey(), promo.getValue());
                }
                
                System.out.print(BOLD + "\n  Apply a promo code? (y/n): " + RESET);
                String applyPromo = scanner.nextLine().trim().toLowerCase();
                
                if (applyPromo.equals("y")) {
                    System.out.print(BOLD + "  Enter promo code: " + RESET);
                    String promoCode = scanner.nextLine().trim().toUpperCase();
                    
                    boolean applied = user.applyPromoCode(promoCode, order);
                    if (applied) {
                        System.out.println(GREEN + "  ✓ Promo code applied successfully!" + RESET);
                    } else {
                        System.out.println(RED + "  ⚠️ Invalid promo code." + RESET);
                    }
                }
            }

            if (rewardPoints > 0) {
                System.out.print(BOLD + "\n  Use reward points for discount? (y/n): " + RESET);
                String usePoints = scanner.nextLine().trim().toLowerCase();
                
                if (usePoints.equals("y")) {
                    System.out.println("  You have " + rewardPoints + " points (1 point = ₹0.25)");
                    System.out.print(BOLD + "  How many points to use: " + RESET);
                    
                    try {
                        int pointsToUse = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (pointsToUse > 0 && pointsToUse <= rewardPoints) {
                            double discount = pointsToUse * 0.25;
                            boolean pointsUsed = user.getRewards().usePoints(pointsToUse);
                            
                            if (pointsUsed) {
                                System.out.printf(GREEN + "  ✓ Used %d points for ₹%.2f discount!%n" + RESET, 
                                        pointsToUse, discount);
                            }
                        } else {
                            System.out.println(RED + "  ⚠️ Invalid number of points." + RESET);
                        }
                    } catch (Exception e) {
                        System.out.println(RED + "  ⚠️ Please enter a valid number." + RESET);
                        scanner.nextLine();
                    }
                }
            }
            
            
            System.out.println(BOLD + "\n💳 " + YELLOW + " PAYMENT METHOD" + RESET);
            System.out.println("  " + "─".repeat(50));
            System.out.println("  1. " + BOLD + "Credit Card" + RESET);
            System.out.println("  2. " + BOLD + "Digital Wallet" + RESET);
            
            int paymentChoice = 0;
            boolean validPaymentChoice = false;
            
            while (!validPaymentChoice) {
                System.out.print(BOLD + "\n  Select payment method (1-2): " + RESET);
                try {
                    paymentChoice = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    if (paymentChoice == 1 || paymentChoice == 2) {
                        validPaymentChoice = true;
                    } else {
                        System.out.println(RED + "  ⚠️ Invalid selection. Please enter 1 or 2." + RESET);
                    }
                } catch (Exception e) {
                    System.out.println(RED + "  ⚠️ Please enter a valid number." + RESET);
                    scanner.nextLine(); 
                }
            }
            
            PaymentProcessor payment = null;
            
            if (paymentChoice == 1) {
                System.out.println("  " + BOLD + "Enter credit card details:" + RESET);
                System.out.print("  Card Number (XXXX XXXX XXXX XXXX): ");
                String cardNumber = scanner.nextLine();
                
                while (!cardNumber.matches("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}")) {
                    System.out.println(RED + "  ⚠️ Invalid format. Example: 9999 8888 7777 6666" + RESET);
                    System.out.print("  Card Number: ");
                    cardNumber = scanner.nextLine();
                }
                
                System.out.print("  Cardholder Name: ");
                String cardholderName = scanner.nextLine();
                
                // Handle CVV (obscured)
                System.out.print("  CVV (3 digits): ");
                Console console = System.console();
                String cvv;
                
                if (console != null) {
                    char[] cvvChars = console.readPassword();
                    cvv = new String(cvvChars);
                } else {
                    cvv = scanner.nextLine();
                    System.out.println("  ***"); 
                }
                
                while (!cvv.matches("\\d{3}")) {
                    System.out.println(RED + "  ⚠️ Invalid CVV. Please enter 3 digits." + RESET);
                    System.out.print("  CVV (3 digits): ");
                    if (console != null) {
                        char[] cvvChars = console.readPassword();
                        cvv = new String(cvvChars);
                    } else {
                        cvv = scanner.nextLine();
                        System.out.println("  ***");
                    }
                }
                
                payment = new CreditCardPayment(totalAmount, cardNumber, cardholderName, cvv);
                System.out.println(GREEN + "  ✓ Card details received" + RESET);
            } else if (paymentChoice == 2) {
                System.out.println("  " + BOLD + "Enter digital wallet details:" + RESET);
                System.out.print("  Wallet ID: ");
                String walletId = scanner.nextLine();
                
                System.out.print("  Wallet Provider (e.g., PayTM, PhonePe): ");
                String provider = scanner.nextLine();
                
                payment = new DigitalWalletPayment(totalAmount, walletId, provider);
                System.out.println(GREEN + "  ✓ Wallet details received" + RESET);
            }
            
            order.assignPaymentMethod(payment);
            
            try {
                System.out.println(YELLOW + "\n⏳ Processing your order..." + RESET);
                Thread.sleep(1500);
                order.placeOrder();
                user.addOrder(order);
                System.out.println(GREEN + "✓ Order placed successfully!" + RESET);
                
                DeliveryTracker tracker = new DeliveryTracker();
                tracker.updateStatus(String.valueOf(order.getOrderId()), "Order Received");
                tracker.startTracking();
                
                System.out.println(BOLD + "\n🚚 " + BLUE + " TRACKING YOUR ORDER" + RESET);
                System.out.println("  " + "─".repeat(50));
                
                Thread.sleep(2000);
                System.out.println(YELLOW + "  ⏳ Order Received - Restaurant notified" + RESET);
                tracker.updateStatus(String.valueOf(order.getOrderId()), "Preparing Food");
                
                Thread.sleep(2000);
                System.out.println(YELLOW + "  ⏳ Preparing Food - Chef is cooking your meal" + RESET);
                tracker.updateStatus(String.valueOf(order.getOrderId()), "Out for Delivery");
                
                Thread.sleep(2000);
                System.out.println(YELLOW + "  ⏳ Out for Delivery - Your food is on the way" + RESET);
                tracker.updateStatus(String.valueOf(order.getOrderId()), "Delivered");
                
                Thread.sleep(1500);
                System.out.println(GREEN + "  ✓ Delivered - Enjoy your meal!" + RESET);
                
                System.out.println(YELLOW + "\n⏳ Saving your order history..." + RESET);
                Thread.sleep(1000);
                boolean saved = FileOrderHistoryManager.saveOrderHistory(user);
                if (saved) {
                    System.out.println(GREEN + "✓ Order history saved successfully!" + RESET);
                    System.out.println("  File: orderhistory/" + userEmail + ".txt");
                    System.out.println("  Total orders: " + user.getOrderHistory().size());
                } else {
                    System.out.println(RED + "⚠️ Failed to save order history." + RESET);
                }

                tracker.stopTracking();
                
                System.out.println(BOLD + "\n🎉 " + GREEN + " ORDER CONFIRMATION" + RESET);
                System.out.println("  " + "─".repeat(50));
                System.out.println("  Thank you for your order, " + BOLD + user.getName() + RESET + "!");
                System.out.println("  Order #" + BOLD + order.getOrderId() + RESET + " placed at " + order.getOrderTime());
                System.out.println("  Your food from " + BOLD + restaurantName + RESET + " will be delivered to:");
                System.out.println("  " + BOLD + street + ", " + city + RESET);
                
            } catch (OrderException e) {
                System.out.println(RED + "⚠️ Error placing order: " + e.getErrorMessage() + RESET);
            }
            
        } catch (Exception e) {
            System.out.println(RED + "\n⚠️ An error occurred: " + e.getMessage() + RESET);
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}