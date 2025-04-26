
public class PaymentException extends Exception {
    private final Payment failedPayment;

    public PaymentException(String message, Payment payment) {
        super(message);
        this.failedPayment = payment;
    }

    public Payment getFailedPayment() {
        return failedPayment;
    }
}
