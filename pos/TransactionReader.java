package pos;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import parameter_files.Constant;

public class TransactionReader {
	
	public static void main(String args[]) {
		parseTransactions();
	}
	
	public static void parseTransactions() {
		Scanner transactionScanner = null;
	  try {
		  Transaction transaction = new  Transaction();
		  ArrayList <Transaction> transactionList = new ArrayList<Transaction>();
		  Item item = new Item();
		  File transactionFile = new File(Constant.TRANSACTIONS);
		   transactionScanner = new Scanner(transactionFile); 

		    while(transactionScanner.hasNextLine()) {
		    	transactionScanner.useDelimiter("\\s{2,}");
		    	transaction.setCustomerName(transactionScanner.next());
		    	String items = transactionScanner.next();
				  populateItems(transaction, item, items);

		    	String payment = transactionScanner.next();
				populatePayment(transaction, payment);
			
		    	transactionList.add(transaction);
			    //System.out.println(transaction.customerName + " "+ transaction.item[0]+ " "+ transaction.payment);
		    System.out.println(transaction.customerName+ " -" + item.UPC+ " -"+ payment);
	   } 
	}
	  catch(Exception e){
		  System.out.println("Error during parsing transactions.");
	  }
	  finally {
		  if(transactionScanner != null) {
		  transactionScanner.close();}
	  }
	  }

	private static void populatePayment(Transaction transaction, String payment) {
		Scanner paymentScanner = new Scanner(payment); 
		try{
		
		  while(paymentScanner.hasNext())
			{
			  transaction.payment.setPaymentMode(paymentScanner.next());
			  transaction.payment.setAmount(Double.parseDouble(paymentScanner.next()));
			}
	}
	catch(Exception e) {
	}
		finally {
			paymentScanner.close();
		}
	}

	private static void populateItems(Transaction transaction, Item item, String items) {
		Scanner itemScanner = new Scanner(items); 
		  try{
			  while(itemScanner.hasNext())
			{
			  item.setUPC(itemScanner.next());
			  item.setQuantity(Integer.parseInt(itemScanner.next()));
			  transaction.item.put(item.getUPC(), item);
			}
	}
	catch(Exception e) {
		System.out.println("Error in populating Items.");
	}
		  finally {
			  itemScanner.close();
		  }
	}
}




