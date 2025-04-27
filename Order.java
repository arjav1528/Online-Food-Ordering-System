import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents an order placed by a user in the online food ordering system.
 * This class handles order creation, payment processing, and maintains order details.
 */
public class Order {
    private final int orderId;
    private final List<MenuItem> orderedItems;
    private final double totalPrice;
    private PaymentProcessor paymentMethod;
    private boolean isPaid;
    private final LocalDateTime orderTime;

    /**
     * Constructs a new Order with specified details.
     *
     * @param orderId       The unique identifier for this order
     * @param customer      The user who placed this order
     * @param orderedItems  The list of menu items ordered by the customer
     */
    public Order(int orderId, User customer, List<MenuItem> orderedItems) {
        this.orderId = orderId;
        this.orderedItems = orderedItems;
        this.totalPrice = calculateTotal();
        this.isPaid = false;
        this.orderTime = LocalDateTime.now();
    }

    /**
     * Calculates the total price of all items in the order.
     *
     * @return The sum of prices for all ordered items
     */
    private double calculateTotal() {
        double total = 0;
        for (MenuItem item : orderedItems) {
            total += item.getPrice(); 
        }
        return total;
    }

    /**
     * Sets the payment method to be used for this order.
     *
     * @param paymentMethod The payment processor to handle the transaction
     */
    public void assignPaymentMethod(PaymentProcessor paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Attempts to place the order by processing payment.
     * Marks the order as paid if payment is successful.
     *
     * @throws OrderException If payment method is not set (code 401) or if payment fails (code 402)
     * @throws PaymentException 
     */
    public void placeOrder() throws OrderException, PaymentException {
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

    /**
     * Returns the order creation time in a formatted string.
     *
     * @return A formatted string representation of the order time (yyyy-MM-dd HH:mm:ss)
     */
    public String getOrderTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderTime.format(formatter);
    }

    /**
     * Returns the unique identifier for this order.
     *
     * @return The order ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Checks if the order has been paid for.
     *
     * @return true if the order is paid, false otherwise
     */
    public boolean isOrderPaid() {
        return isPaid;
    }

    /**
     * Gets the list of items in this order.
     *
     * @return The list of ordered menu items
     */
    public List<MenuItem> getOrderedItems() {
        return orderedItems;
    }

    /**
     * Gets the total price of the order.
     *
     * @return The total price of all items in the order
     */
    public double getTotalPrice() {
        return totalPrice;
    }
}
