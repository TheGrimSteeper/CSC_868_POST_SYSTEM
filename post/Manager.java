package post;

import org.xml.sax.SAXException;
import parameter_files.Constant;

import javax.xml.parsers.ParserConfigurationException;

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
        try {
        	//TODO-- Comment or remove storeProducts.buildCatalog(productFile);
            storeProducts.buildCatalogfromDB();
            //storeProducts.buildCatalog(productFile);

        } catch (SAXException | ParserConfigurationException e) {
            System.out.println("Could not fetch the product catalog to initialize the POST!");
            System.exit(1);
        }

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
        System.out.println("\n\nThank you for using POST 2.");
    }
}
