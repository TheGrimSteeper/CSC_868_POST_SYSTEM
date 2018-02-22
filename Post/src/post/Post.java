package post;

import java.util.ArrayDeque;


import com.storeentity.ProductCatalog;
import com.storeentity.Transactions;
import com.storeentity.TransactionLines;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author  Ian Dennis
 */

public class Post {
    
    // for when you want to get something from database
    /*
    ProductCatalog clientDbObject = client.find_XML(ProductCatalog.class, "0001");
        
        Transactions transactionDbObjecct = transactions.find_XML(Transactions.class, "5");
        TransactionLines transactionLinesDbObject = transactionLines.find_XML(TransactionLines.class, "5");//rememebr primary key is transaction id
    
    
    */
    
    //for when you want to post to database
    /*
    
    client.create_XML(clientDbObject);
    client.create_XML(transactionDbObjecct);
    client.create_XML(transactionLinesDbObject);
    
    */
    

    private ProductCatalogPost productsInStock;
    private ArrayDeque<TransactionPost> leftoverTransactions;
    private TransactionPost currentTransaction;
    private String storeName;
    private CatalogClient client = new CatalogClient(); 
    private TransactionsClient transactions = new TransactionsClient();
    private TransactionLinesClient transactionLines = new TransactionLinesClient();

    public Post(ProductCatalogPost products, String storeName) {

        this.productsInStock = products;
        this.leftoverTransactions = new ArrayDeque<>();
        this.storeName = storeName;

    }

    public void setProductCatalog(ProductCatalogPost updatedCatalog) { this.productsInStock = updatedCatalog; }

    public void startTransaction() {
        currentTransaction = new TransactionPost();
    }

    private void getCustomerIdentity(Customer newCustomer) {
        currentTransaction.setCustomerName(newCustomer.getName());
    }

    public void endTransaction(Customer newCustomer) {
        getCustomerIdentity(newCustomer);

        if (verifyPayment(newCustomer)) {
            leftoverTransactions.add(currentTransaction);
            printReceipt();
        }
        else {
            printReceipt();
            System.out.println("***CANCELLING THE TRANSACTION. Payment was insufficient.***");
        }
    }

    public boolean addItem(Item customerItem) {

        Product storeProduct;
        boolean successfulAdd = false;

        storeProduct = this.productsInStock.lookupProduct(customerItem.getUPC());

        if (storeProduct != null) {
            currentTransaction.addLineItem(new SalesLineItem(storeProduct, customerItem.getQuantity()));
            successfulAdd = true;
        }

        return successfulAdd;
    }

    private boolean verifyPayment(Customer newCustomer) {

        double amountDue = currentTransaction.getTotal();
        double changeDue = newCustomer.getPayType().payAmount(amountDue);
        boolean validPayment = true;

        if (changeDue < 0.0) {
            validPayment = false;
        }

        currentTransaction.setPayType(newCustomer.getPayType());
        currentTransaction.setChangeDue(changeDue);
        return validPayment;
    }

    private void printReceipt() {
        System.out.println("\n\n" + storeName + "\n");
        currentTransaction.printTransaction();
    }

    public void sendTransactionToDB(TransactionLog salesLog) {

        while (!leftoverTransactions.isEmpty()) {
            salesLog.addTransaction(leftoverTransactions.pop());
        }
    }

}
