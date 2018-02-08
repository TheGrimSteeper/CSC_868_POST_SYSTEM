package pos;

public class Payment {
	
	double amount;
	String paymentMode;


	private double getAmount() {
		return amount;
	}

	private void setAmount(double amount) {
		this.amount = amount;
	}

	private String getPaymentMode() {
		return paymentMode;
	}

	private void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

}
