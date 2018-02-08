package pos;

import java.util.ArrayList;

public class Transaction {

	String customerName;
	ArrayList<Item> item;
	Payment payment;
	
	private String getCustomerName() {
		return customerName;
	}
	
	private void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	private ArrayList<Item> getItem() {
		return item;
	}
	
	private void setItem(ArrayList<Item> item) {
		this.item = item;
	}
	
	private Payment getPayment() {
		return payment;
	}
	
	private void setPayment(Payment payment) {
		this.payment = payment;
	}
		
}
