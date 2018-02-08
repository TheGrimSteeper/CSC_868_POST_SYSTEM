package post;

import java.util.ArrayList;

public class Customer {

    private String name;
    private Payment payType;
    private ArrayList<Item> shoppingCart;

    public Customer(String name) {
        this.name = name;
        this.shoppingCart = new ArrayList<>();
        this.payType = null;
    }

    public String getName() { return this.name; }

    public Payment getPayType() { return  this.payType; }

    public void setPayType(Payment payType) { this.payType = payType; }

    public ArrayList<Item> getShoppingCart() { return this.shoppingCart; }

    public Item getItem() {

        Item currentItem = null;

        if (!shoppingCart.isEmpty())
            currentItem = shoppingCart.remove(0);

        return currentItem;
    }

    public boolean addToCart(Item newItem) {

        if (shoppingCart.size() >= 100) {
             System.out.println("Could not add another item because the cart is full!");
             return false;
        }
        shoppingCart.add(newItem);
        return true;
    }

}
