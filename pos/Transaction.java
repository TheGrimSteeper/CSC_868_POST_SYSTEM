package pos;

import java.util.HashMap;

public class Transaction {

	String customerName;
	HashMap<String, Item> item;
	Payment payment;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}
