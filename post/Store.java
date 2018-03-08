package post;

import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * @author  Ian Dennis
 */

public class Store {

    static  TransactionLog salesLog = new TransactionLog();
    public Post register = new Post();
    private ArrayList<Customer> customers;
    private Customer customer = new Customer(null);
    private String storeName;

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public void openStore(TransactionLog saleLog, Post post) {

        System.out.format("%s is now open for business. Welcome!\n\n\n", storeName);
        salesLog = saleLog;
        register = post;
        this.customers = new ArrayList<>();
        new POSTController().populateViews(null, 0);   
    }
    
 public void updateTransaction(Customer customer) {
     ProductCatalog storeProducts = new ProductCatalog();
     try {
		storeProducts.buildCatalogfromDB();
	} catch (SAXException | ParserConfigurationException e) {
		e.printStackTrace();
	}
	register.setProductCatalog(storeProducts);
	 register.startTransaction();
 	for (Item item : customer.getShoppingCart()){
         register.addItem(item);
         }
     register.endTransaction(customer);
     register.sendTransactionToDB(salesLog);

    }

    public TransactionLog closeStore() {

        System.out.format("\n\n\n%s is now closed. Please come again!\n\n", storeName);
        register = null;
        customers = null;

        return salesLog;
    }
}
