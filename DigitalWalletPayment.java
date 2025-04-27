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
     */
    @Override
    public boolean process(double amount) {
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
     */
    @Override
    public boolean process() {
        return process(this.amount);
    }
}
