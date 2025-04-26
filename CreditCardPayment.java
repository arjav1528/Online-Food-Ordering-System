
public class CreditCardPayment extends Payment implements PaymentProcessor {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;  // Added CVV field

    public CreditCardPayment(double amount, String cardNumber, String cardHolderName, String cvv) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }

    public CreditCardPayment(double amount, String cardNumber, String cardHolderName) {
        this(amount, cardNumber, cardHolderName, "");
    }

    @Override
    public boolean process(double amount) {
        isProcessed = true;
        System.out.println("Processing Credit Card Payment of ₹" + amount);
        return true;
    }

    @Override
    public boolean refund(double amount) {
        System.out.println("Refunded ₹" + amount + " to Credit Card.");
        return true;
    }

    @Override
    public String getTransactionDetails() {
        return "Credit Card ending with " + cardNumber.substring(cardNumber.length() - 4);
    }

    @Override
    public boolean process() {
        return process(this.amount);
    }
}
