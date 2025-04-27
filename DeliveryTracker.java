import java.util.HashMap;
import java.util.Map;

/**
 * DeliveryTracker is responsible for monitoring and updating the status of food delivery orders.
 * It implements Runnable to provide continuous status updates in a separate thread.
 */
public class DeliveryTracker implements Runnable {
    /** Stores the current status of each order, mapped by order ID */
    private final Map<String, String> orderStatus;
    /** Flag to control the tracking thread execution */
    private boolean isTracking;

    /**
     * Constructs a new DeliveryTracker with an empty status map and tracking disabled.
     */
    public DeliveryTracker() {
        this.orderStatus = new HashMap<>();
        this.isTracking = false;
    }

    /**
     * Updates the status of a specific order.
     *
     * @param orderId The unique identifier of the order to update
     * @param status The new status to set for the order
     */
    public void updateStatus(String orderId, String status) {
        orderStatus.put(orderId, status);
    }

    /**
     * Retrieves the current status of a specific order.
     *
     * @param orderId The unique identifier of the order to check
     * @return The current status of the order, or "Unknown" if the order is not found
     */
    public String getStatus(String orderId) {
        return orderStatus.getOrDefault(orderId, "Unknown");
    }

    /**
     * Begins tracking order statuses by starting a new thread.
     * The tracking thread will periodically output all order statuses.
     */
    public void startTracking() {
        isTracking = true;
        new Thread(this).start();
    }

    /**
     * Stops the tracking thread by setting the isTracking flag to false.
     */
    public void stopTracking() {
        isTracking = false;
    }

    /**
     * Thread execution method that periodically displays the status of all orders.
     * Runs continuously until stopTracking() is called.
     */
    @Override
    public void run() {
        while (isTracking) {
            for (String id : orderStatus.keySet()) {
                System.out.println("Order ID: " + id + " Status: " + orderStatus.get(id));
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Tracking interrupted!");
            }
        }
    }
}
