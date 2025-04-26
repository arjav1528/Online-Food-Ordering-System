
public abstract class Payment {
    protected double amount;
    protected boolean isProcessed;

    public Payment(double amount) {
        this.amount = amount;
        this.isProcessed = false;
    }

    public abstract boolean process();
    
    public String getReceipt() {
        return "Payment of ₹" + amount + " processed successfully.";
    }
}
