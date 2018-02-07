package post;

import java.util.ArrayList;

/**
 * Created by dennisi1 on 2/7/18.
 */
public class Transaction {

    private ArrayList<SalesLineItem> itemsPurchased;
    private double total;
    private Payment payType;

    public Transaction() {
        this.total = 0.0;
    }

    public boolean addLineItem(SalesLineItem newItem) {
        return true;
    }

    private void calculateTotal() {
        for (item : itemsPurchased)
            total += item.getSubtotal();

        total += TAX * total;
    }

    @Override
    public String toString() {

    }
}
