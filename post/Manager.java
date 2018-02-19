package post;

import parameter_files.Constant;

/**
 * @author  Ian Dennis
 */

public class Manager {

    private Store store;
    private Post register;
    private ProductCatalog storeProducts;
    private TransactionLog salesLog;
    private String storeName;

    public Manager(String storeName) {
        this.storeName = storeName;
        salesLog = new TransactionLog();
    }

    public void setupStore(String productFile, String transactionFile) {

        store = new Store(storeName);
        register = null;
        storeProducts = new ProductCatalog();

        //open store, setup post, put together product catalog
        storeProducts.buildCatalog(productFile);
        register = new Post(storeProducts, storeName);
        store.openStore(transactionFile, salesLog, register);
    }

    public void closeStore() {
        salesLog = store.closeStore();
        register.sendTransactionToDB(salesLog);
        register = null;
        storeProducts = null;
        store = null;
    }

    public static void main (String[] args) {
        Manager owner = new Manager(Constant.STORENAME);
        owner.setupStore(Constant.PRODUCTS, Constant.TRANSACTIONS);
        owner.closeStore();
        System.out.println("\n\nThank you for using POST 1.");
    }
}
