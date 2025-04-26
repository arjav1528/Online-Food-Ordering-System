
public interface PaymentProcessor {
    boolean process(double amount);
    boolean refund(double amount);
    String getTransactionDetails();
}
