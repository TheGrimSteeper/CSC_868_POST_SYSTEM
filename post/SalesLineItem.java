package post;

/**
 * Created by dennisi1 on 2/7/18.
 */
public class SalesLineItem {

    private Product item;
    private int quantity;
    private double subtotal;

    SalesLineItem(Product itemToBuy, int numItem) {

        this.item = itemToBuy;
        this.quantity = numItem;
        calcSubtotal();
    }

    private void calcSubtotal() { subtotal = item.getUnitPrice() * quantity; }
}
