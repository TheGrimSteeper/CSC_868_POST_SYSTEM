package pos;

import java.util.ArrayList;
import java.util.HashMap;

import parameter_files.Constant;

public class TransactionLog {

	
	
	
	public static void generateTransactionLog(ArrayList<Transaction> transactionList, HashMap<String, Product> productMap){
		
		for(Transaction transaction: transactionList){
			System.out.println("-------------" +Constant.STORENAME+ "Transaction Log-------------");
			System.out.println();
			System.out.println("Identifying information: " + transaction.customerName);
			
	    transaction.item = Item.poulateItemDetails(transaction.item, productMap);
	
		for(Item item : transaction.item){
			if(item.getQuantity() > 1){
			System.out.println("Item:          " + item.getQuantity());//Columns 10? (10 spaces)
		}
			System.out.println("Item:" + item.getUPC());//columns 1-4
		}
		
		String paymentMode = transaction.getPayment().paymentMode;
		
		if((Constant.CASH).equalsIgnoreCase(paymentMode)){
			System.out.println("Payment: Cash/Check $" + transaction.getPayment().amount);
		}
		else if((Constant.CREDIT).equalsIgnoreCase(paymentMode)){
		    System.out.println("Payment: Credit " + (int)transaction.getPayment().amount);
		}
		    System.out.println();
		    System.out.println();
	    }
       }
	

}
