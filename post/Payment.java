package post;

public abstract class Payment {

    private double amountToPay;
    private String paymentMode;

    public Payment(String paymentMode, double amountToPay) {

        this.amountToPay = amountToPay;
        this.paymentMode = paymentMode;
    }

    public abstract double payAmount();

    public void setAmount(double amount) {
        this.amountToPay = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

}
