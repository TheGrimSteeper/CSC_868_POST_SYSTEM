package post;

import java.util.ArrayList;

/**
 * @author  Ian Dennis
 */

public class Store {

    private TransactionLog salesLog;
    private Post register;
    private ArrayList<Customer> customers;
    private String storeName;

    public Store(String storeName) {
        this.register = null;
        this.storeName = storeName;
    }

    public void openStore(String transactionTxt, TransactionLog salesLog, Post register) {

        System.out.format("%s is now open for business. Welcome!\n\n\n", storeName);

        this.salesLog = salesLog;
        this.register = register;
        this.customers = new ArrayList<>();

        customers = TransactionReader.parseTransactions(transactionTxt);

        for (Customer customer : customers) {
            register.startTransaction();

            for (Item item : customer.getShoppingCart()) {
                register.addItem(item);
            }

            register.endTransaction(customer);
            register.sendTransactionToDB(salesLog);
        }

    }

    public TransactionLog closeStore() {

        System.out.format("\n\n\n%s is now closed. Please come again!\n\n", storeName);
        register = null;
        customers = null;

        return salesLog;
    }
}
