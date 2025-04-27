/**
 * Defines the contract for payment processing functionality in the food ordering system.
 */
public interface PaymentProcessor {
    /**
     * Processes a payment transaction for the specified amount.
     *
     * @param amount The monetary amount to be processed, must be greater than zero
     * @return true if the payment was processed successfully, false otherwise
     * @throws PaymentException if the payment processing fails
     * @throws IllegalArgumentException if amount is less than or equal to zero
     */
    boolean process(double amount) throws PaymentException, IllegalArgumentException;
    
    /**
     * Processes a refund for the specified amount.
     *
     * @param amount The monetary amount to be refunded, must be greater than zero
     * @return true if the refund was processed successfully, false otherwise
     * @throws IllegalArgumentException if amount is less than or equal to zero
     */
    boolean refund(double amount) throws IllegalArgumentException;
    
    /**
     * Retrieves details about the most recent transaction.
     *
     * @return A string containing transaction details (ID, timestamp, status, etc.)
     */
    String getTransactionDetails();
}
