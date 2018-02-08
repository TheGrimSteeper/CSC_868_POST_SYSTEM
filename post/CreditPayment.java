package post;

/**
 * Created by Ian Dennis
 */

public class CreditPayment extends Payment {

    private String cardNumber;

    public CreditPayment(String paymentMode, String cardNumber) {
        super(paymentMode);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public double payAmount(double amountDue) {
        return amountDue;
    }
}
