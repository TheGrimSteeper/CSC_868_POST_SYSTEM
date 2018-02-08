package post;

/**
 * @author  Ian Dennis
 */

public class SalesLineItem {

    private Product item;
    private int quantity;
    private double subtotal;

    SalesLineItem(Product itemToBuy, int numItem) {

        this.item = itemToBuy;
        this.quantity = numItem;
        subtotal = 0.0;

        calcSubtotal();
    }

    public double getSubtotal() { return subtotal; }

    public Product getLineItem() { return item; }

    public int getQuantity() { return quantity; }

    private void calcSubtotal() { subtotal = item.getPrice() * quantity; }

    @Override
    public String toString() {
        return String.format("%s %d @ %.2f %.2f", item.getProductDescription(), quantity, item.getPrice(), subtotal);
    }
}
