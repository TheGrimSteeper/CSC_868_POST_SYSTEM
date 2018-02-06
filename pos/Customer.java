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

	public HashMap<String, Integer> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(HashMap<String, Integer> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCashOrCredit() {
		return cashOrCredit;
	}

	public void setCashOrCredit(String cashOrCredit) {
		this.cashOrCredit = cashOrCredit;
	}

	public double getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

}
