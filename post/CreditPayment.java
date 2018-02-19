package post;

/**
 * @author  Ian Dennis
 */

public class CreditPayment extends Payment {

    private String cardNumber;

    public CreditPayment(String cardNumber) {
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
        return 0.0;
    }

    public String toString() {
        return "CREDIT " + cardNumber;
    }
}
