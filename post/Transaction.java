package post;

import java.util.ArrayList;

public class Transaction {

    private ArrayList<SalesLineItem> itemsPurchased;
    private double total;
    private Payment payType;

    public Transaction() {

        this.total = 0.0;
        itemsPurchased = new ArrayList<>();
        payType = null;
    }

    public void addLineItem(SalesLineItem newItem) {

        itemsPurchased.add(newItem);
    }

    private void calculateTotal() {
        for ( SalesLineItem lineItem : itemsPurchased)
            total += lineItem.getSubtotal();

        total += TAX * total;
    }

    @Override
    public String toString() {

        System.out.println();
        System.out.println();

        for (SalesLineItem lineItem : itemsPurchased)
            System.out.println(lineItem.toString());

        System.out.println("------");
        System.out.println("Total $");
        System.out.println("Amount Tendered ");
        System.out.println("Amount Returned ");
    }
}
