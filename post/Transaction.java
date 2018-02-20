package post;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author  Ian Dennis
 */

public class Transaction {

    private ArrayList<SalesLineItem> itemsPurchased;
    private double total;
    private double changeDue;
    private Payment payType;
    private String customerName;
    private Date transactionTime;
    private int transactionId;
    private static int transactionCounter = 0;

    public Transaction() {

        total = 0.0;
        changeDue = 0.0;
        transactionTime = new Date();
        itemsPurchased = new ArrayList<>();
        payType = null;
        transactionId = ++transactionCounter;
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

    public void setPayType(Payment payType) { this.payType = payType; }

    public Double getTotal() { return total; }

    public void setChangeDue(Double change) { this.changeDue = change; }

    public double getChangeDue() { return changeDue; }

    public int getTransactionId() { return this.transactionId; }

    private void calculateTotal(SalesLineItem lineItem) {
        double subtotal = lineItem.getSubtotal();
        total += subtotal;
    }

    public void addLineItem(SalesLineItem newItem) {

        itemsPurchased.add(newItem);
        calculateTotal(newItem);
    }

    public void printTransaction() {

        System.out.format("%-20s  %s\n", customerName, transactionTime.toString());
        System.out.println();
        for (SalesLineItem lineItem : itemsPurchased)
            System.out.println(lineItem.toString());

        System.out.println("------");
        System.out.format("Total $%6.2f\n", total);
        System.out.println("Amount Tendered: " + payType.toString());
        System.out.format("Amount Returned:      %6.2f\n", changeDue);
    }

}
