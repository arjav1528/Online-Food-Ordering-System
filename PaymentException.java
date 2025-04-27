/**
 * Exception thrown when a payment operation fails.
 * This exception extends RuntimeException for unchecked exception behavior.
 */
public class PaymentException extends RuntimeException {
    /** The payment object that failed during processing */
    private final Payment failedPayment;

    /**
     * Constructs a new PaymentException with a detailed message and the failed payment.
     *
     * @param message Detailed description of the exception
     * @param payment The payment object that failed during processing
     */
    public PaymentException(String message, Payment payment) {
        super(message);
        this.failedPayment = payment;
    }

    /**
     * Retrieves the payment object that failed during processing.
     *
     * @return The payment object associated with this exception
     */
    public Payment getFailedPayment() {
        return failedPayment;
    }
}
