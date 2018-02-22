package post;

/**
 * @author  Ian Dennis
 */

public class CheckPayment extends Payment {

    private double amountToPay;

    public CheckPayment(double amountToPay) {
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
        return String.format("CHECK %6.2f",amountToPay);
    }
}
