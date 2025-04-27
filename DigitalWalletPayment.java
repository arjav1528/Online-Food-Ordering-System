/**
 * Represents a payment method using digital wallets like PayTM, GPay, etc.
 * This class extends the Payment base class and implements the PaymentProcessor interface
 * to handle wallet-specific payment operations.
 */
public class DigitalWalletPayment extends Payment implements PaymentProcessor {
    /** The unique identifier of the user's digital wallet */
    private final String walletId;
    
    /** The name of the wallet service provider (e.g., PayTM, GPay) */
    private final String provider;

    /**
     * Constructs a new digital wallet payment.
     *
     * @param amount The payment amount to be processed
     * @param walletId The unique identifier of the user's digital wallet
     * @param provider The name of the wallet service provider
     */
    public DigitalWalletPayment(double amount, String walletId, String provider) {
        super(amount);
        this.walletId = walletId;
        this.provider = provider;
    }

    /**
     * Processes a payment with the specified amount through the digital wallet.
     *
     * @param amount The amount to be processed
     * @return true if the payment was processed successfully, false otherwise
     * @throws PaymentException if the wallet ID is invalid or payment processing fails
     * @throws IllegalArgumentException if amount is less than or equal to zero
     */
    @Override
    public boolean process(double amount) throws PaymentException, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        
        // Fail for a specific test wallet ID
        if (walletId.equals("INVALID")) {
            isProcessed = false;
            throw new PaymentException("Invalid wallet ID", this);
        }
        
        // Fail if provider is not supported
        if (provider.equalsIgnoreCase("unsupported")) {
            isProcessed = false;
            throw new PaymentException("Unsupported wallet provider", this);
        }
        
        isProcessed = true;
        System.out.println("Processing Wallet Payment of ₹" + amount);
        return true;
    }

    /**
     * Processes a refund to the user's digital wallet.
     *
     * @param amount The amount to be refunded
     * @return true if the refund was processed successfully, false otherwise
     */
    @Override
    public boolean refund(double amount) {
        System.out.println("Refunded ₹" + amount + " to Wallet ID " + walletId);
        return true;
    }

    /**
     * Gets the transaction details specific to this digital wallet payment.
     *
     * @return A string containing the wallet ID and provider information
     */
    @Override
    public String getTransactionDetails() {
        return "Wallet ID " + walletId + " (" + provider + ")";
    }

    /**
     * Processes the payment using the amount specified during object construction.
     * This implementation calls the overloaded process method with the stored amount.
     *
     * @return true if the payment was processed successfully, false otherwise
     * @throws PaymentException if the payment processing fails
     */
    @Override
    public boolean process() throws PaymentException {
        return process(this.amount);
    }
}
