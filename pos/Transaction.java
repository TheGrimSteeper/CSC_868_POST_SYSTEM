package pos;

import java.util.ArrayList;

public class Transaction {

	String customerName;
	ArrayList<Item> item;
	Payment payment;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public ArrayList<Item> getItem() {
		return item;
	}
	public void setItem(ArrayList<Item> item) {
		this.item = item;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}
