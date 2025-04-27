/**
 * Custom exception for order-related errors in the food ordering system.
 * This exception provides additional information about order processing errors
 * through error codes and formatted error messages.
 */
public class OrderException extends Exception {
    /** Error code identifying the specific type of order exception */
    private final int errorCode;

    /**
     * Constructs a new OrderException with specified error message and code.
     *
     * @param message   Detailed description of the error
     * @param errorCode Numeric code identifying the type of order error
     */
    public OrderException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Retrieves a formatted error message including the error code.
     *
     * @return Formatted string containing the error code and message
     */
    public String getErrorMessage() {
        return "Order Error " + errorCode + ": " + getMessage();
    }
}
