

import java.util.HashMap;
import java.util.Map;


public class DeliveryTracker implements Runnable {
    private final Map<String, String> orderStatus;
    private boolean isTracking;

    public DeliveryTracker() {
        this.orderStatus = new HashMap<>();
        this.isTracking = false;
    }

    public void updateStatus(String orderId, String status) {
        orderStatus.put(orderId, status);
    }

    public String getStatus(String orderId) {
        return orderStatus.getOrDefault(orderId, "Unknown");
    }

    public void startTracking() {
        isTracking = true;
        new Thread(this).start();
    }

    public void stopTracking() {
        isTracking = false;
    }

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
