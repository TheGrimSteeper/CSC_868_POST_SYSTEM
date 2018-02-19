package post;

import java.util.ArrayDeque;

/**
 * @author  Ian Dennis
 */

public class Post {

    private ProductCatalog productsInStock;
    private ArrayDeque<Transaction> leftoverTransactions;
    private Transaction currentTransaction;
    private String storeName;

    public Post(ProductCatalog products, String storeName) {

        this.productsInStock = products;
        this.leftoverTransactions = new ArrayDeque<>();
        this.storeName = storeName;

    }

    public void setProductCatalog(ProductCatalog updatedCatalog) { this.productsInStock = updatedCatalog; }

    public void startTransaction() {
        currentTransaction = new Transaction();
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
