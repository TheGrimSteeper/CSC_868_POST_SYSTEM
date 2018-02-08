package post;

import java.util.ArrayDeque;

public class PostSystem {

    private ProductCatalog productsInStock;
    private ArrayDeque<Transaction> leftoverTransactions;
    private Transaction currentTransaction;
    private String storeName;

    public PostSystem(ProductCatalog products, String storeName) {

        this.productsInStock = products;
        this.leftoverTransactions = new ArrayDeque<>();
        this.storeName = storeName;

    }

    public void setProductCatalog(ProductCatalog updatedCatalog) { this.productsInStock = updatedCatalog; }

    public Transaction startTransaction(Customer newCustomer) {
        currentTransaction = new Transaction();
    }

    private void getCustomerIdentity(Customer newCustomer) {
        currentTransaction.setCustomerName(newCustomer.getName());
    }

    public Transaction endTransaction(Customer newCustomer) {
        getCustomerIdentity(newCustomer);
        getPayment(newCustomer);
        printReceipt();
        leftoverTransactions.add(currentTransaction);
    }

    public boolean addItem(Item customerItem) {

        Product storeProduct;
        boolean successfulAdd = false;

        storeProduct = this.productsInStock.getProduct(customerItem.getUPC());

        if (storeProduct != null) {
            currentTransaction.addLineItem(new SalesLineItem(storeProduct, customerItem.getQuantity()));
            successfulAdd = true;
        }

        return successfulAdd;
    }

    private boolean getPayment(Customer newCustomer) {

        Payment customerPayment = newCustomer.getPayType();

        // process payment

        currentTransaction.addPayment(customerPayment);

    }

    private void printReceipt() {
        System.out.println(currentTransaction.toString());
    }

    public void sendTransactionToDB(TransactionLog salesLog) {

        while (!leftoverTransactions.isEmpty()) {
            salesLog.addTransaction(leftoverTransactions.pop());
        }
    }

}
