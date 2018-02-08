package post;

/**
 * @author  Ian Dennis
 */

public class CashPayment extends Payment {

    private double amountToPay;

    public CashPayment(String paymentMode, double amountToPay) {
        super(paymentMode);
        this.amountToPay = amountToPay;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }

    @Override
    public double payAmount(double amountDue) {
        return amountToPay - amountDue;
    }

    public String toString() {
        return "CASH " + amountToPay;
    }
}