package post;

import java.util.HashMap;

/**
 * Created by dennisi1 on 2/7/18.
 */
public class Customer {

    private String name;
    private Payment payType;
    private HashMap<String,Integer> shoppingCart;

    public String getName() { return this.name; }
    public Payment getPayType() { return  this.payType; }
    public HashMap<String, Integer> getShoppingCart() { return this.shoppingCart; }

    public boolean addToCart(String upc, Integer quantity) {
        return true;
    }

}
