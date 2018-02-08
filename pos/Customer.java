package pos;

import java.util.HashMap;

public class Customer {
	HashMap<String, Integer> shoppingCart = new HashMap<String, Integer>();
	String firstName;
	String lastName;
	String cashOrCredit;
	double cashAmount;
	String creditCardNumber;
		
	Customer(String firstName, String lastName, HashMap<String, Integer> shoppingCart, String cashOrCredit, double cashAmount) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.shoppingCart = shoppingCart;
		this.cashOrCredit = cashOrCredit;
		this.cashAmount = cashAmount;
		this.creditCardNumber = null;
	}
	
	Customer(String firstName, String lastName, HashMap<String, Integer> shoppingCart, String cashOrCredit, String creditCardNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.shoppingCart = shoppingCart;
		this.cashOrCredit = cashOrCredit;
		this.cashAmount = 0;
		this.creditCardNumber = creditCardNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCashOrCredit() {
		return cashOrCredit;
	}

	public double getCashAmount() {
		return cashAmount;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	private HashMap<String, Integer> getShoppingCart() {
		return shoppingCart;
	}

	private void setShoppingCart(HashMap<String, Integer> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	private void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}
	
	private void setCashOrCredit(String cashOrCredit) {
		this.cashOrCredit = cashOrCredit;
	}
	
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	private void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

}
