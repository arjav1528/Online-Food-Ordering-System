

public class DigitalWalletPayment extends Payment implements PaymentProcessor {
    private final String walletId;
    private final String provider;

    public DigitalWalletPayment(double amount, String walletId, String provider) {
        super(amount);
        this.walletId = walletId;
        this.provider = provider;
    }

    @Override
    public boolean process(double amount) {
        isProcessed = true;
        System.out.println("Processing Wallet Payment of ₹" + amount);
        return true;
    }

    @Override
    public boolean refund(double amount) {
        System.out.println("Refunded ₹" + amount + " to Wallet ID " + walletId);
        return true;
    }

    @Override
    public String getTransactionDetails() {
        return "Wallet ID " + walletId + " (" + provider + ")";
    }

    @Override
    public boolean process() {
        return process(this.amount);
    }
}
