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
    public static ArrayList<Customer> parseTransactions(String transactionFile) {
        Scanner transactionScanner = null;
        ArrayList <Customer> customerList = new ArrayList<Customer>();

        try {
            File transactFile = new File(transactionFile);
            transactionScanner = new Scanner(transactFile);

            while(transactionScanner.hasNextLine()) {
                Customer newCustomer = new Customer(transactionScanner.nextLine());
                while(transactionScanner.hasNextLine()) {
                    String checkNextLine = transactionScanner.nextLine();
                    if(!((checkNextLine.contains(Constant.CASH)) || (checkNextLine.contains(Constant.CREDIT)) || (checkNextLine.contains(Constant.CHECK))))
                    {
                        populateItems(newCustomer, checkNextLine);
                    }
                    else {
                        String payment = checkNextLine;
                        populatePayment(newCustomer, payment);
                        break;
                    }
                }
                //transaction.setItem(itemList);

                customerList.add(newCustomer);
            }
        }
        catch(Exception e){
            System.out.println("Error during parsing transactions: "+ e);
        }
        finally {
            if(transactionScanner != null) {
                transactionScanner.close();}
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
                if((Constant.CASH).equalsIgnoreCase(paymentMode) || (Constant.CHECK).equalsIgnoreCase(paymentMode)){
                    String cashTendered = paymentScanner.next();
                    pay = new CashPayment(Constant.CASH, Double.parseDouble(cashTendered.substring(1)));
                }
                else if((Constant.CREDIT).equalsIgnoreCase(paymentMode)) {
                    pay = new CreditPayment(Constant.CREDIT, paymentScanner.next());
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

    // For Testing
    public static void main(String[] args) {
        ArrayList<Customer> customers = TransactionReader.parseTransactions(Constant.TRANSACTIONS);
    }
}




