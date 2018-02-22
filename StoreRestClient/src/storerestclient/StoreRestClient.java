/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storerestclient;

import com.storeentity.ProductCatalog;
import com.storeentity.Transactions;
import com.storeentity.TransactionLines;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author kingp
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
        Transactions bgg = transactions.findAll_XML(Transactions.class);
  //      System.out.println(bgg.getTotal());
        TransactionLines u = transactionLines.find_XML(TransactionLines.class, "5");//rememebr primary key is transaction id
        //TransactionLinesPK
        // note there is a findall_xml option so maybe that could help though not sure how would pick them out
        
        System.out.println("c price is = " + c.getPrice() );
        // teacher basically got a lsit of every item in a column and searched through it
        //so maybe see if you can get a list, if that fails then possibly just check if thing is null
        System.out.println("t name is = " + t.getCustomerName() );
        System.out.println("u quantity is = " + u.getQuantity() );
        // System.out.println("u upc is = " + u.getUpc().getPrice() );// transactionLines getUpc returns product catalog WHICH MEANS we can get data from object easily
         // apparantly also way to get product catalog
         System.out.println("u upc is = " + u.getUpc().getUpc());
         System.out.println("u transactionID is = " + u.getTransactionId() );
        System.out.println("u subtotal is = " + u.getSubtotal() );
         
         //int bdd =3;
        ProductCatalog g = new ProductCatalog("15", "wht", 7.6);// should have a way to check if entering same
        // type of info since believe an error would occcur, ALSO MAKE UPC UNIQUE IF ITS TYPE INT
        // SO ADD A CHECK POSSIBLY TO SEE IF ENTRY IS ALREADY CREATED THOUGH DO NOT WORRY ABOUT
        // THAT SINCE DOESN"T SEEM WE WILL ADD THINGS TO CATAOLOG AND THAT WON'T CHANGE OTHER CODE
       // Date date = new Date();
        String dateInString = "2008-11-11";
          Date result = null;// look into, maybe have it be a method
        try{
              SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
              result  = dateFormat.parse(dateInString);
            }

            catch(ParseException e){
              e.printStackTrace();

            }
        Transactions io = new Transactions( 7,"Bob", "billMe",result, 30.30);
        TransactionLines pl = new TransactionLines(7,5, 6.0);
        pl.setUpc(u.getUpc()); //need to do this since constructor doesn't have upc
        // format we have makes it easy to get products info (would have to do another search
        // to get product again I believe, would also require redoing tables.
        //LOOK INTO MAY HAVE TO CHANGE BASED ON HOW IAN'S CODE IS
        
        // JUST BE ADDING IT ON
        client.create_XML(g);
        transactions.create_XML(io);
        transactionLines.create_XML(pl);
        String y = client.countREST();
        String nm = transactions.countREST();
        String nl = transactionLines.countREST();
        System.out.println(s);
        System.out.println(y);
        System.out.println(nm);
        System.out.println(nl);
        
        client.close();
        transactions.close();
        transactionLines.close();
        //ProductCatalog g = new ProductCatalog();
        //System.out.println(g.getUpc());

    }
    
}
