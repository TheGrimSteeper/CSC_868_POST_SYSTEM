package pos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ListIterator;

import parameter_files.Constant;

public class PostSystem {
	
	public static ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	public static HashMap<String, Product> productMap = new HashMap<String, Product>();
	

	public static void main(String args[]) {
		
		transactionList = TransactionReader.parseTransactions();
		productMap = ProductReader.parseProducts();
		
		generateInvoice(transactionList, productMap);
		
		
	}
	
	public static void generateInvoice(ArrayList<Transaction> transactionList, HashMap<String, Product> productMap) {
	
		for(Transaction transaction : transactionList) {
		System.out.println("-------------" +Constant.STORENAME+ "-------------");
		System.out.println();
		System.out.println("Customer Name:" + transaction.customerName);
		System.out.println("Billing Time : " + new Date());
		
		transaction.item = Item.poulateItemDetails(transaction.item, productMap);
		double totalPrice = 0.0;
		System.out.println();
		for(Item item : transaction.item) {
		System.out.println("Item: "+ item.productDescription + "  " + item.quantity + " * " + item.unitPrice+ " = $" + item.subTotal);
		totalPrice = totalPrice + item.subTotal;
		}
		System.out.println();
	    System.out.println("Total Amount: $" + totalPrice);
	    String paymentMode = transaction.getPayment().paymentMode;
		  if((Constant.CASH).equalsIgnoreCase(paymentMode)){
			  System.out.println("Amount Tendered: $" + transaction.getPayment().amount + " By CASH");
			  double amountReturned = (transaction.getPayment().amount) - totalPrice;
			  DecimalFormat df = new DecimalFormat(".##");
			  System.out.println("Amount Returned: $" + df.format(amountReturned));
		  }
		  else if((Constant.CREDIT).equalsIgnoreCase(paymentMode)) {
			  System.out.println("Amount Tendered: Credit Card " + (int)transaction.getPayment().amount);

		  }
	    System.out.println("Thank you! Visit us again.");
		System.out.println();
		System.out.println();
		}
		
		
	}
	
	
}
