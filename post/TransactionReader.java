package post;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import parameter_files.Constant;

/**
 *
 * @author shalaka
 *Reads the Transaction file and parses it and provides the transaction list
 */
public class TransactionReader {
/*
    TransactionReader (Store store, String transactionFile) {

        parseTransactions(transactionFile);

    }
*/
    /**
     *
     * @param transactionFile
     * @return
     */
    public static ArrayList<Customer> parseTransactions(Customer newCustomer) {
        ArrayList <Customer> customerList = new ArrayList<Customer>();

        try {
            
                customerList.add(newCustomer);
            
        }
        catch(Exception e){
            System.out.println("Error adding  customer: "+ e);
        }
        
        return customerList;
    }

    private static void populatePayment(Customer newCustomer, String payment) {
        Scanner paymentScanner = new Scanner(payment);
        try{

            while(paymentScanner.hasNext())
            {
                Payment pay = null;
                String paymentMode = paymentScanner.next();
                if((Constant.CASH).equalsIgnoreCase(paymentMode)) {
                    String cashTendered = paymentScanner.next();
                    pay = new CashPayment(Double.parseDouble(cashTendered.substring(1)));
                }
                else if((Constant.CREDIT).equalsIgnoreCase(paymentMode)) {
                    pay = new CreditPayment(paymentScanner.next());
                }
                else if((Constant.CHECK).equalsIgnoreCase(paymentMode)) {
                    String cashTendered = paymentScanner.next();
                    pay = new CheckPayment(Double.parseDouble(cashTendered.substring(1)));
                }


                newCustomer.setPayType(pay);
            }
        }
        catch(Exception e) {
            System.out.println("Error in populating Payment: " +e);
        }
        finally {
            paymentScanner.close();
        }
    }

    private static void populateItems(Customer newCustomer, String items) {
        Scanner itemScanner = new Scanner(items);
        try{
            while(itemScanner.hasNext())
            {
                String upc = itemScanner.next();
                Integer quantity = Integer.parseInt(itemScanner.next());
                newCustomer.addToCart(new Item(upc, quantity));
            }
        }
        catch(Exception e) {
            System.out.println("Error in populating Items: " + e);
        }
        finally {
            itemScanner.close();
        }
    }

}




