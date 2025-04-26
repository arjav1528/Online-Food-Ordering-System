import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;
    Address address;
    private List<Order> orderHistory;
    private Rewards rewards;
    
    public static class Rewards {
        private int points;
        private Map<String, Double> availablePromos;
        
        public Rewards() {
            this.points = 0;
            this.availablePromos = new HashMap<>();
            this.availablePromos.put("WELCOME50", 50.0);
            this.availablePromos.put("FREESHIP", 40.0);
        }
        
        public void addPoints(int orderAmount) {
            this.points += orderAmount / 10;
        }
        
        public boolean usePoints(int pointsToUse) {
            if (pointsToUse <= points) {
                points -= pointsToUse;
                return true;
            }
            return false;
        }
        
        public int getPoints() {
            return points;
        }
        
        public Double getPromoDiscount(String promoCode) {
            return availablePromos.getOrDefault(promoCode, 0.0);
        }
        
        public void addPromo(String promoCode, Double discount) {
            availablePromos.put(promoCode, discount);
        }
        
        public Map<String, Double> getAllPromos() {
            return new HashMap<>(availablePromos);
        }
    }

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.orderHistory = new ArrayList<>();
        this.rewards = new Rewards(); 
    }

    public User(String name, Address address) {
        this.name = name;
        this.address = address;
        this.orderHistory = new ArrayList<>();
        this.rewards = new Rewards(); 
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
        rewards.addPoints((int)order.getTotalPrice());
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public String trackOrder(int orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId() == orderId) {
                return order.getOrderTime();
            }
        }
        return "Order not found!";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    
    public Rewards getRewards() {
        return rewards;
    }
    
    public boolean applyPromoCode(String promoCode, Order order) {
        Double discount = rewards.getPromoDiscount(promoCode);
        if (discount > 0) {
            System.out.println("Applied promo: " + promoCode + " with discount: ₹" + discount);
            return true;
        }
        return false;
    }
}
