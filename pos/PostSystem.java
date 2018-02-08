package pos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PostSystem {
	Store store;
	Cashier cashier;
	Customer currentCustomer;
	String recipt;
	public static final String productsPath = "C:/Users/Isak/Documents/Programmering/Java/POS_SYSTEM/parameter_files/products.txt";
	public static final String transactionsPath = "C:/Users/Isak/Documents/Programmering/Java/POS_SYSTEM/parameter_files/transaction.txt";
	public static final String logPath = "C:/Users/Isak/Documents/Programmering/Java/POS_SYSTEM/parameter_files/log.txt";
	PostSystem(Store store, Cashier cashier){
		this.store = store;
		this.cashier = cashier;
	}

	public static String getProductspath() {
		return productsPath;
	}

	private Store getStore() {
		return store;
	}

	private void setStore(Store store) {
		this.store = store;
	}

	private Cashier getCashier() {
		return cashier;
	}

	private void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	private Customer getCurrentCustomer() {
		return currentCustomer;
	}

	private void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	private static String getTransactionspath() {
		return transactionsPath;
	}
	

	private boolean readyForCustomer(){
		System.out.println("                                         Want to close the store? Type; CLOSE and hit ENTER");
		System.out.println("Please enter your full name:");

		Scanner scanner = new Scanner(System.in);
		String name = "";
		boolean stillOpen = true;
		while(name == "") {
			name = scanner.nextLine();
			if(ParserClass.customerFound(transactionsPath,name)) {
				searchForCustomer(name);
			}else if(name.equals("CLOSE")) {
				stillOpen = false;
			}else if(!ParserClass.customerFound(transactionsPath,name)){
				System.out.println("Name not found! Try again!");
			}
		}
		return stillOpen;
	}
	private void stillOpen() {
		System.out.println("Welcome to " + Store.getStoreName() + "!\n");
		boolean stillOpen = true;
		while(stillOpen) {
			stillOpen = readyForCustomer();
		}
		System.out.println("\nAnd now your watch is ended!");
	}
	
	private boolean searchForCustomer(String name) {
		boolean everythingWentOk = true;
		
			try {
				if(cashier.processCustomer(transactionsPath,name) == null) {
					System.out.println("Something went wrong!");
					everythingWentOk = false;
				}else {
					this.currentCustomer = cashier.processCustomer(transactionsPath,name);
					System.out.println(createRecipt(this.store.getCatalog().getProduct(),this.currentCustomer));
				}
				}catch (ParseException e) {
				e.printStackTrace();
				}
			return everythingWentOk;
		
	}
	
	private String createRecipt(Product[] catalog, Customer currentCustomer) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		decimalFormat.setRoundingMode(RoundingMode.CEILING);
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat ("MM.ddy.yyyy");
		String recipt = "\n\n-----" + Store.getStoreName() + "-----"; 
		recipt += "\n\n" + currentCustomer.getFirstName() + " " + currentCustomer.getLastName() + "           ";
		recipt += dateFormat.format(date) + "\n";
		recipt += "-----------------------------------\n";
		double total = 0;
		for(Map.Entry<String, Integer> entry : currentCustomer.shoppingCart.entrySet()) {
			String UPC = entry.getKey();
		    Integer amount = entry.getValue();
		    for(int i = 0; i < catalog.length; i++) {
		    	if(UPC.equals(catalog[i].UPC)) {
		    		recipt += "- " + catalog[i].productDescription + ", QTY " + decimalFormat.format(amount) + ", Price " + decimalFormat.format(catalog[i].price) + ", SubTotal " + (decimalFormat.format(catalog[i].price*amount) + "\n");
		    		total += (catalog[i].price*amount);
		    	}
		    }
		}
		recipt += "------------\n";
		recipt += "Total $" + decimalFormat.format(total) + "\n\n";
		if(currentCustomer.getCashOrCredit().equals("CREDIT")) {
			recipt += "Credit Card: " + currentCustomer.getCreditCardNumber();
		}
		if(currentCustomer.getCashOrCredit().equals("CASH")) {
			recipt += "Amount Tendered: " + currentCustomer.getCashAmount() + "\n";
			recipt += "Amount Returned: " + decimalFormat.format(currentCustomer.getCashAmount()-total) + "\n";
		}
		writeToLog(recipt);
		return recipt;
	}
	
	private void writeToLog(String recipt) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logPath,true));
			PrintWriter out = new PrintWriter(bufferedWriter);
			out.println(recipt);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] arg) throws ParseException {
		PostSystem post = Manager.startUpStore();
		
		post.stillOpen();
		
	}
	
}
