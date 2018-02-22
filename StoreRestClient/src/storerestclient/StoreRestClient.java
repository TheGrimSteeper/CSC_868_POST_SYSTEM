/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storerestclient;

import com.storeentity.ProductCatalog;
import com.storeentity.Transactions;
import com.storeentity.TransactionLines;
import com.storeentity.TransactionLinesPK;//LOOK INTO

/**
 *
 * @author Matthew Berkman
 */
public class StoreRestClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         // TODO code application logic here
        CatalogClient client = new CatalogClient(); 
        TransactionsClient transactions = new TransactionsClient();
        TransactionLinesClient transactionLines = new TransactionLinesClient();
        // above object types are from classes in this package which you NEED since they are what help itneract with server
        
        String s = client.countREST();// WOULD NEED TO CONVERT SA TO A INT THEN USE IT FOR LOOP
        //THE PRODUCT CATALOG C WILL BE IN LOOP ALONG WITH CHECKING UPC TO SEE IF IT MATCHES
        // ONE PASSEDD IN THOUGH THAT DETAIL MIGHT BE REVISED BY IAN
        
        String q = transactions.countREST();
        String w = transactionLines.countREST();
        System.out.println(s);
        System.out.println(q);
        System.out.println(w);
        
        // below object types are from storeentity
        ProductCatalog c = client.find_XML(ProductCatalog.class, "0001");// figure out this issue since upc is now a string
        //not sure if we would need to loop to print product contents
        
        Transactions t = transactions.find_XML(Transactions.class, "5");
        TransactionLines u = transactionLines.find_XML(TransactionLines.class, "0001", "5");// check on since two primary keys and also PK class
        // seems need to create PK first then transactionlines to store quantity and subtotal
        //TransactionLinesPK
        // note there is a findall_xml option so maybe that could help though not sure how would pick them out
        
        System.out.println("c price is = " + c.getPrice() );
        // teacher basically got a lsit of every item in a column and searched through it
        //so maybe see if you can get a list, if that fails then possibly just check if thing is null
        System.out.println("t name is = " + t.getCustomerName() );
        System.out.println("u quantity is = " + u.getQuantity() );
        // System.out.println("u upc is = " + u.getUpc() );// look into
         // apparantly also way to get product catalog
         //System.out.println("u transactionID is = " + u.getTransactionLinesPK().getTransactionId() );
        //  System.out.println("u subtotal is = " + u.getSubtotal() );
         
         
        ProductCatalog g = new ProductCatalog("15", "wht", 7.6);// should have a way to check if entering same
        // type of info since believe an error would occcur, ALSO MAKE UPC UNIQUE IF ITS TYPE INT
        // SO ADD A CHECK POSSIBLY TO SEE IF ENTRY IS ALREADY CREATED THOUGH DO NOT WORRY ABOUT
        // THAT SINCE DOESN"T SEEM WE WILL ADD THINGS TO CATAOLOG AND THAT WON'T CHANGE OTHER CODE
        // JUST BE ADDING IT ON
        client.create_XML(g);
        String y = client.countREST();
        System.out.println(s);
        System.out.println(y);
        client.close();
        //ProductCatalog g = new ProductCatalog();
        //System.out.println(g.getUpc());
    }
    
}
