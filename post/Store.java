package post;

import java.util.ArrayList;

/**
 * @author  Ian Dennis
 */

public class Store {

    static  TransactionLog salesLog = new TransactionLog();
    static  Post register = new Post();
    private ArrayList<Customer> customers;
    private Customer customer = new Customer(null);
    private String storeName;
    static POSTController postController = new POSTController();

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public void openStore(TransactionLog saleLog, Post post) {

        System.out.format("%s is now open for business. Welcome!\n\n\n", storeName);
        salesLog = saleLog;
        register = post;
        this.customers = new ArrayList<>();
        postController.populateViews(null, 0);   
    }
    
 public void updateTransaction(Customer customer) {
	 
	 register.startTransaction();
 	for (Item item : customer.getShoppingCart()){
         register.addItem(item);
   
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
