import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents an order placed by a user.
 */
public class Order {
    private final int orderId;
    private final List<MenuItem> orderedItems;
    private final double totalPrice;
    private PaymentProcessor paymentMethod;
    private boolean isPaid;
    private final LocalDateTime orderTime;

    public Order(int orderId, User customer, List<MenuItem> orderedItems) {
        this.orderId = orderId;
        this.orderedItems = orderedItems;
        this.totalPrice = calculateTotal();
        this.isPaid = false;
        this.orderTime = LocalDateTime.now();
    }

    private double calculateTotal() {
        double total = 0;
        for (MenuItem item : orderedItems) {
            total += item.getPrice(); 
        }
        return total;
    }

    public void assignPaymentMethod(PaymentProcessor paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void placeOrder() throws OrderException {
        if (paymentMethod == null) {
            throw new OrderException("Payment method not set!", 401);
        }
        boolean success = paymentMethod.process(totalPrice);
        if (!success) {
            throw new OrderException("Payment failed!", 402);
        }
        this.isPaid = true;
        System.out.println("Order placed successfully!");
    }

    
    public String getOrderTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderTime.format(formatter);
    }

    public int getOrderId() {
        return orderId;
    }

    public boolean isOrderPaid() {
        return isPaid;
    }

    public List<MenuItem> getOrderedItems() {
        return orderedItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
