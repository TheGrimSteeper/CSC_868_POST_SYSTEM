package pos;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PostSystem {
	Store store;
	Cashier cashier;
	Customer currentCustomer;
	String recipt;
	public static final String productsPath = "parameter_files/products.txt";
	public static final String transactionsPath = "parameter_files/transaction.txt";
	
	PostSystem(Store store, Cashier cashier){
		this.store = store;
		this.cashier = cashier;
	}
	
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public static String getProductspath() {
		return productsPath;
	}

	public static String getTransactionspath() {
		return transactionsPath;
	}

	
	public void readyForCustomer() throws ParseException {
		System.out.println("Welcome to " + store.getStoreName() + "!\n Please enter your full name:");
		Scanner scanner = new Scanner(System.in);
		String name = "";
		name = scanner.nextLine();
		if(ParserClass.customerFound(transactionsPath,name)) {
			if(cashier.proccessCustomer(transactionsPath,name) == null) {
				System.out.println("Something went wrong!");
				name = "";
			}else {
				System.out.println("Success");
				this.currentCustomer = cashier.proccessCustomer(transactionsPath,name);
				System.out.println(createRecipt(this.store.getCatalog().getProduct(),this.currentCustomer));
			}
		}else if(!ParserClass.customerFound(transactionsPath,name)){
			System.out.println("Name not found!");
			name = "";
			name = scanner.nextLine();
		}
	}
	
	public String createRecipt(Product[] catalog, Customer currentCustomer) {
		String recipt = this.store.getStoreName() + "\n\n" + currentCustomer.getFirstName() + " " + currentCustomer.getLastName() +  "\n";
		for(Map.Entry<String, Integer> entry : currentCustomer.shoppingCart.entrySet()) {
			String UPC = entry.getKey();
		    Integer amount = entry.getValue();
		    for(int i = 0; i < catalog.length; i++) {
		    	if(UPC == catalog[i].UPC) {
		    		recipt += "- " + catalog[i].productDescription + " QTY " + amount + " Price " + catalog[i].price + " Sub " + (catalog[i].price*amount);     
		    	}
		    }
		}
		return recipt;
	}
	
	public static void main(String[] arg) throws ParseException {
		PostSystem post = Manager.startUpStore();
		
		post.readyForCustomer();
		
	}
	
	
}
