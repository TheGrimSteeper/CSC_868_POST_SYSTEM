package post;

import java.util.ArrayList;
import java.util.HashMap;
import parameter_files.Constant;

/**
 * @author  Alex Bautista
 */

public class TransactionLog {

    private HashMap <String, ArrayList<TransactionPost>> customerLog;
    private ArrayList<TransactionPost> allTransactions;

    public TransactionLog() {
        this.customerLog = new HashMap<>();
        this.allTransactions = new ArrayList<>();
    }

    public void addTransaction(TransactionPost newTransaction) {

        ArrayList<TransactionPost> customerTransaction;
        String customerName = newTransaction.getCustomerName();

        allTransactions.add(newTransaction);

        if (customerLog.containsKey(customerName)) {
            customerTransaction = customerLog.get(customerName);
        }
        else {
            customerTransaction = new ArrayList<>();
        }

        customerTransaction.add(newTransaction);
        customerLog.put(customerName, customerTransaction);
    }

/*
    public static void generateTransactionLog(ArrayList<Transaction> transactionList, HashMap<String, Product> productMap){

        for(TransactionPost transaction: transactionList){
            System.out.println("-------------" +Constant.STORENAME+ "TransactionPost Log-------------");
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
*/

}