package post;

/**
 * @author  Ian Dennis
 */

public abstract class Payment {

    private String paymentMode;

    public Payment(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public abstract double payAmount(double amountDue);

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

}
