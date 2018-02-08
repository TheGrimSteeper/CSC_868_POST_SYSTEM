package pos;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import parameter_files.Constant;

/**
 * 
 * @author shalaka
 *Reads the Transaction file and parses it and provides the transaction list
 */
public class TransactionReader {
	
	TransactionReader (Store store, String transactionFile) {
		
		parseTransactions(transactionFile);
		
	}
	
	/**
	 * 
	 * @param transactionFile
	 * @return
	 */
	public static ArrayList<Transaction> parseTransactions(String transactionFile) {
		Scanner transactionScanner = null;
		ArrayList <Transaction> transactionList = new ArrayList<Transaction>();
		  
	  try {
		  File transactFile = new File(transactionFile);
		   transactionScanner = new Scanner(transactFile); 

		    while(transactionScanner.hasNextLine()) {
				Transaction transaction = new  Transaction();
		    	transaction.setCustomerName(transactionScanner.nextLine());
	    		ArrayList <Item> itemList = new ArrayList<Item>();
		    	while(transactionScanner.hasNextLine()) {
			    	String checkNextLine = transactionScanner.nextLine();
		    	if(!((checkNextLine.contains(Constant.CASH)) || (checkNextLine.contains(Constant.CREDIT))))
		    	{		    	
		    		populateItems( transaction,  checkNextLine, itemList);
		    		}
		    	else {
		    		String payment = checkNextLine;
					populatePayment(transaction, payment);
					break;

		    	}
		    	}
		    	transaction.setItem(itemList);

		    	
			
		    	transactionList.add(transaction);
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

	private static void populateItems(Transaction transaction, String items, ArrayList<Item> itemList) {
		Scanner itemScanner = new Scanner(items); 
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




