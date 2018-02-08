package post;

import java.util.ArrayList;

public class Customer {

    private String name;
    private Payment payType;
    private ArrayList<Item> shoppingCart;

    public Customer(String name) {
        this.name = name;
        this.shoppingCart = new ArrayList<Item>();
        this.payType = null;
    }

    public String getName() { return this.name; }
    public Payment getPayType() { return  this.payType; }
    public void setPayType(Payment payType) { this.payType = payType; }
    public ArrayList<Item> getShoppingCart() { return this.shoppingCart; }

    public boolean addToCart(Item newItem) {
        shoppingCart.add(newItem);
    }

}
