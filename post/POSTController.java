package post;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

interface POSTDataSource {
    ArrayList<String> getItemsForUPCMenu(HashMap<String, Product> productList);
    String[] getPaymentTypes();
	ArrayList<String> getUPCList();
}

public class POSTController implements POSTDelegate {
    POSTModel model = new POSTModel();
    POSTDataSource dataSource = model;
    POSTJFrame post = new POSTJFrame();
	static Customer customer = new Customer(null);
	static ArrayList<Item> shoppingCart = new ArrayList<Item>();
    double total;

    POSTController() {
        post.setDelegate(this);
    }

    @Override
    public void userEnteredName(String name) {
    	
    }

    @Override
    public void userPressedEnter(Product product, int quantity) {
    	populateViews(product, quantity);
    }

    @Override
    public void userPressedPay(String name, POSTModel.PaymentType paymentType, double amount) {
        System.out.printf("Payment Type: %s Amount: %.2f\n",paymentType, amount);
        if(amount > total) {
        JOptionPane.showConfirmDialog(null, "Transaction Complete. Print receipt?");
        customer.setName(name);
        customer.setShoppingCart(shoppingCart);
        new Post().endTransaction(customer);
        }
        else {
        	JOptionPane.showMessageDialog(null, "Payment Failed. Please retry");
        }
    }
    

    public void populateViews(Product product, int quantity) {
        post.setQuantityMenuRange(1, 100);
        post.updateDateLabel();
        renderDataSourceToView(product);
        if(quantity !=0)
        renderTextArea(product, quantity);
    }

    public void renderDataSourceToView(Product product) {
        post.populatePaymentTypeMenu(dataSource.getPaymentTypes());
        post.populateUPCMenu(dataSource.getUPCList());
    }

    public void renderTextArea(Product product, int quantity) {
    	SalesLineItem lineItem = new SalesLineItem(product, quantity, product.getUpc());
    	post.updateInvoiceTextArea(lineItem.toString()+"\n");
    	double subtotal = lineItem.getSubtotal();
        total += subtotal;
        post.updateTotalPrice(total);
        Item item = new Item(product.getUpc(), quantity);
        shoppingCart.add(item);
    	
    }
}
