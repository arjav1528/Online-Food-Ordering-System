/**
 * Defines the contract for payment processing functionality in the food ordering system.
 * Implementations of this interface handle different payment methods (credit cards,
 * digital wallets, etc.) using a common set of operations.
 */
public interface PaymentProcessor {
    /**
     * Processes a payment transaction for the specified amount.
     *
     * @param amount The monetary amount to be processed, must be greater than zero
     * @return true if the payment was processed successfully, false otherwise
     * @throws PaymentException 
     */
    boolean process(double amount) throws PaymentException;
    
    /**
     * Processes a refund for the specified amount.
     *
     * @param amount The monetary amount to be refunded, must be greater than zero
     * @return true if the refund was processed successfully, false otherwise
     */
    boolean refund(double amount);
    
    /**
     * Retrieves details about the most recent transaction.
     *
     * @return A string containing transaction details (ID, timestamp, status, etc.)
     */
    String getTransactionDetails();
}
