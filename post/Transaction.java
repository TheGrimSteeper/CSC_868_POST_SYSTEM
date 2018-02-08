package post;

import java.util.ArrayList;
import parameter_files.Constant;

/**
 * @author  Ian Dennis
 */

public class Transaction {

    private ArrayList<SalesLineItem> itemsPurchased;
    private double total;
    private Payment payType;
    private String customerName;

    public Transaction() {

        this.total = 0.0;
        itemsPurchased = new ArrayList<>();
        payType = null;
    }

    public void addLineItem(SalesLineItem newItem) {

        itemsPurchased.add(newItem);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Payment getPayType() {
        return payType;
    }

    public void setPayType(Payment payType) {
        this.payType = payType;
    }

    private void calculateTotal() {
        for ( SalesLineItem lineItem : itemsPurchased)
            total += lineItem.getSubtotal();

        total += Constant.TAXRATE * total;
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

        return null;
    }

}
