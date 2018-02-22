package post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import parameter_files.Constant;

/**
 * @author  Alex Bautista
 */

public class TransactionLog {

    private HashMap <String, ArrayList<Transaction>> customerLog;
    private ArrayList<Transaction> allTransactions;

    public TransactionLog() {
        this.customerLog = new HashMap<>();
        this.allTransactions = new ArrayList<>();
    }

    public void addTransaction(Transaction newTransaction) {

        ArrayList<Transaction> customerTransaction;
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

    public void pushLogsToDB(Transaction transaction) {
        pushTransactiontoDB(transaction);
        pushSalesLineItemstoDB(transaction);
    }

    private String makeTransactionXMLString(Transaction transaction) {

        String paymentType = " ";
        Payment payment = transaction.getPayType();

        if (payment instanceof CashPayment)
            paymentType = "CASH";
        else if (payment instanceof CheckPayment)
            paymentType = "CHECK";
        else if (payment instanceof CreditPayment)
            paymentType = "CREDIT";

        return "<transactions> \n"
                        + "           <customerName>" + transaction.getCustomerName() + "</customerName> \n"
                        + "           <givenDate>" + transaction.getTransactionTime() + "</givenDate> \n"
                        + "           <paymentType>" + paymentType + "</paymentType> \n"
                        + "           <total>" + transaction.getTotal() + "</total> \n"
                        + "           <transactionId>" + transaction.getTransactionId() + "</transactionId> \n"
                        + "     </transactions> ";

    }

    private String makeSalesLineItemXMLString(Transaction transaction, SalesLineItem lineItem) {

        return "<transactionLines>  \n"
                + "              <quantity>" + lineItem.getQuantity() + "</quantity>  \n"
                + "              <subtotal>" + lineItem.getSubtotal() + "</subtotal>  \n"
                +                       makeTransactionXMLString(transaction)
                + "              <transactionLineId>" + lineItem.getLineItemId() + "</transactionLineId>  \n"
                + "              <upc>  \n"
                + "                     <givenName>" + lineItem.getLineItem().getProductDescription() + "</givenName>  \n"
                + "                     <price>" + lineItem.getLineItem().getPrice() + "</price>  \n"
                + "                     <upc>" + lineItem.getLineItem().getUpc() + "</upc>  \n"
                + "              </upc>  \n"
                + "      </transactionLines>";
    }

    private void pushTransactiontoDB(Transaction transaction) {

        try {
            URL urlTransactions = new URL("http://localhost:8080/StoreServer1/webresources/com.storeserver1entity.transactions");
            HttpURLConnection postConnTransactions = (HttpURLConnection) urlTransactions.openConnection();
            postConnTransactions.setDoOutput(true);
            postConnTransactions.setRequestMethod("POST");
            postConnTransactions.setRequestProperty("Content-Type", "application/xml");

            String newTransactionsString = this.makeTransactionXMLString(transaction);

            OutputStream postOutputStream = postConnTransactions.getOutputStream();
            postOutputStream.write(newTransactionsString.getBytes());
            postOutputStream.flush();

            if (postConnTransactions.getResponseCode() >= 400) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + postConnTransactions.getResponseCode());
            }

            postConnTransactions.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pushSalesLineItemstoDB(Transaction transaction) {

        try {
            URL urlTransactionLines = new URL("http://localhost:8080/StoreServer1/webresources/com.storeserver1entity.transactionlines");
            HttpURLConnection postConnTransactionLines = (HttpURLConnection) urlTransactionLines.openConnection();
            postConnTransactionLines.setDoOutput(true);
            postConnTransactionLines.setRequestMethod("POST");
            postConnTransactionLines.setRequestProperty("Content-Type", "application/xml");

            for (SalesLineItem lineItem : transaction.getItemsPurchased()) {
                String newTransactionLinesString = makeSalesLineItemXMLString(transaction, lineItem);

                OutputStream postOutputStream = postConnTransactionLines.getOutputStream();
                postOutputStream.write(newTransactionLinesString.getBytes());
                postOutputStream.flush();

                if (postConnTransactionLines.getResponseCode() >= 400) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + postConnTransactionLines.getResponseCode());
                }
            }

            postConnTransactionLines.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}