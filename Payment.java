/**
 * Abstract base class representing a payment in the food ordering system.
 * This class provides the foundation for different payment methods.
 */
public abstract class Payment {
    /** The payment amount in rupees */
    protected double amount;
    
    /** Flag indicating whether the payment has been processed successfully */
    protected boolean isProcessed;

    /**
     * Constructs a new Payment with the specified amount.
     *
     * @param amount The payment amount in rupees
     */
    public Payment(double amount) {
        this.amount = amount;
        this.isProcessed = false;
    }

    /**
     * Processes the payment.
     *
     * @return true if the payment was processed successfully, false otherwise
     */
    public abstract boolean process();
    
    /**
     * Generates a receipt for the processed payment.
     *
     * @return A string containing the receipt information
     */
    public String getReceipt() {
        return "Payment of ₹" + amount + " processed successfully.";
    }
}
