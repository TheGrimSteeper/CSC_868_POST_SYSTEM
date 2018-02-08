package post;

public class Item {

    private String upc;
    private int quantity;

    public Item(String upc, int quantity) {
        this.upc = upc;
        this.quantity = quantity;
    }

    public String getUPC() {
        return upc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
