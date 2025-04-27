/**
 * Represents a credit card payment method.
 * Extends Payment class and implements PaymentProcessor interface.
 */
public class CreditCardPayment extends Payment implements PaymentProcessor {
    /** The credit card number */
    private String cardNumber;

    /**
     * Constructs a new CreditCardPayment with the specified details.
     *
     * @param amount The payment amount
     * @param cardNumber The credit card number
     * @param cardHolderName The name of the card holder
     * @param cvv The card verification value
     */
    public CreditCardPayment(double amount, String cardNumber, String cardHolderName, String cvv) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    /**
     * Constructs a new CreditCardPayment with the specified details and empty CVV.
     *
     * @param amount The payment amount
     * @param cardNumber The credit card number
     * @param cardHolderName The name of the card holder
     */
    public CreditCardPayment(double amount, String cardNumber, String cardHolderName) {
        this(amount, cardNumber, cardHolderName, "");
    }

    /**
     * Processes the credit card payment for the specified amount.
     *
     * @param amount The amount to process
     * @return true if the payment was processed successfully, false otherwise
     * @throws PaymentException 
     */
    @Override
    public boolean process(double amount) throws PaymentException {
        // Fail for a specific test card number
        if (cardNumber.equals("0000 0000 0000 0000")) {
            isProcessed = false;
            
           throw new PaymentException("Invalid card number", this);
        }
        
        isProcessed = true;
        System.out.println("Processing Credit Card Payment of ₹" + amount);
        return true;
    }

    /**
     * Processes a refund to the credit card.
     *
     * @param amount The amount to refund
     * @return true if the refund was processed successfully, false otherwise
     */
    @Override
    public boolean refund(double amount) {
        System.out.println("Refunded ₹" + amount + " to Credit Card.");
        return true;
    }

    /**
     * Gets the transaction details for the credit card payment.
     *
     * @return A string containing the transaction details
     */
    @Override
    public String getTransactionDetails() {
        return "Credit Card ending with " + cardNumber.substring(cardNumber.length() - 4);
    }

    /**
     * Processes the credit card payment with the amount specified during construction.
     *
     * @return true if the payment was processed successfully, false otherwise
     * @throws PaymentException 
     */
    @Override
    public boolean process() throws PaymentException {
        return process(this.amount);
    }
}
