package pos;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import parameter_files.Constant;

public class TransactionReader {
	
	public static ArrayList<Transaction> parseTransactions() {
		Scanner transactionScanner = null;
		ArrayList <Transaction> transactionList = new ArrayList<Transaction>();

		  
	  try {
		  File transactionFile = new File(Constant.TRANSACTIONS);
		   transactionScanner = new Scanner(transactionFile); 

		    while(transactionScanner.hasNextLine()) {
				Transaction transaction = new  Transaction();
		    	transactionScanner.useDelimiter("\\s{2,}");
		    	transaction.setCustomerName(transactionScanner.next());
		    	String items = transactionScanner.next();
				  populateItems(transaction, items);

		    	String payment = transactionScanner.next();
				populatePayment(transaction, payment);
			
		    	transactionList.add(transaction);
		    //System.out.println(transaction.customerName+ " -" + item.UPC+ " -"+ payment);
	   } 
	}
	  catch(Exception e){
		  System.out.println("Error during parsing transactions: "+ e);
	  }
	  finally {
		  if(transactionScanner != null) {
		  transactionScanner.close();}
	  }
	return transactionList;
	  }

	private static void populatePayment(Transaction transaction, String payment) {
		Scanner paymentScanner = new Scanner(payment); 
		try{
		
		  while(paymentScanner.hasNext())
			{
			  Payment pay = new Payment();
			  String paymentMode = paymentScanner.next();
			  Double amount=0.0;
			  if((Constant.CASH).equalsIgnoreCase(paymentMode)){
				  String cashTendered = paymentScanner.next();
				   amount= Double.parseDouble(cashTendered.substring(1));
			  }
			  else if((Constant.CREDIT).equalsIgnoreCase(paymentMode)) {
				  amount  = Double.parseDouble(paymentScanner.next());
			  }
			  pay.setAmount(amount);
			  pay.setPaymentMode(paymentMode);
			  transaction.setPayment(pay);
			}
	}
	catch(Exception e) {
		System.out.println("Error in populating Payment: " +e);
	}
		finally {
			paymentScanner.close();
		}
	}

	private static void populateItems(Transaction transaction, String items) {
		Scanner itemScanner = new Scanner(items); 
		ArrayList <Item> itemList = new ArrayList<Item>();
		  try{
			  while(itemScanner.hasNext())
			{				
			  Item item = new Item();
			  item.setUPC(itemScanner.next());
			  item.setQuantity(Integer.parseInt(itemScanner.next()));
			  itemList.add(item);
			}
			  transaction.setItem(itemList);
	}
	catch(Exception e) {
		System.out.println("Error in populating Items: " + e);
	}
		  finally {
			  itemScanner.close();
		  }
	}
}




