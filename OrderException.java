
/**
 * Custom exception for order-related errors.
 */
public class OrderException extends Exception {
    private final int errorCode;

    public OrderException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return "Order Error " + errorCode + ": " + getMessage();
    }
}
