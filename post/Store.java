package post;

import java.util.ArrayList;

public class Store {

    private ProductCatalog storeProducts;
    private TransactionReader transactionReader;
    private TransactionLog salesLog;
    private PostSystem register;
    private ArrayList<Customer> customers;
    private String storeName;

    public Store() {
        this.register = null;
        this.storeName = "Lost Semicolon";
    }

    public void openStore(String transactionTxt, String catalogTxt) {

        this.storeProducts = new ProductCatalog();
        this.transactionReader = new TransactionReader();
        this.salesLog = new TransactionLog();
        this.customers = new ArrayList<>();

        storeProducts.parseFile(catalogTxt);
        customers = transactionReader.parseFile(transactionTxt);

        register = new PostSystem(storeProducts, storeName);

        for (Customer customer : customers) {
            register.startTransaction(customer);

            for (Item item : customer.getShoppingCart()) {
                register.addItem(item);
            }

            register.endTransaction(customer);
            register.sendTransactionToDB(salesLog);
        }

    }

    public void closeStore() {
        register = null;
        storeProducts = null;
        transactionReader = null;
        salesLog = null;
        customers = null;
    }
}
