package post;

public class Item {

    String upc;
    int quantity;

    public Item(String upc, int quantity) {
        this.upc = upc;
        this.quantity = quantity;
    }

    public String getUpc() {
        return upc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
