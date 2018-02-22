package post;

import java.util.*;

import org.xml.sax.SAXException;
import parameter_files.Constant;

import javax.xml.parsers.ParserConfigurationException;

/**
 * @author  Alex Bautista
 */

public class ProductCatalog {

    private HashMap<String, Product> productList;
    static POSTJFrame post = new POSTJFrame();
    static POSTModel postModel = new POSTModel();

    public ProductCatalog(){
        this.productList = new HashMap<String, Product>();

    }

    public void buildCatalog(String filename) {
        productList = ProductReader.parseProducts(filename);
        post.setProductCatalog(this);
        postModel.getItemsForUPCMenu(productList);
    }
    
    

    public void buildCatalogfromDB() throws SAXException, ParserConfigurationException {
        productList = ProductReader.parseDBProducts();
        //TODO-- uncomment the below line
        //post.setProductCatalog(this);
    }

    public Product lookupProduct(String UPC){
        Product value = productList.get(UPC);
        if(value == null){
            System.out.println("UPC " + UPC + " NOT FOUND\n");
        }

        return value;
    }
    

    public void addItem(String UPC, Product product){

        productList.put(UPC, product);

    }
	 /*
	 public void print(HashMap<String, Product> productList){
		Set set = productList.entrySet();
		Iterator it = set.iterator();
		 while(it.hasNext()){
		    Map.Entry mentry = (Map.Entry)it.next();
		    System.out.println("UPC = " + mentry.getKey());
		    System.out.println("Price = " + productList.get();
	  }
	 }
	 */

	 // For Testing
	 /*public static void main(String[] args) {
	     ProductCatalog items = new ProductCatalog();
	     items.buildCatalog(Constant.PRODUCTS);

	     //Product tempProduct = items.lookupProduct("0004");
	     //tempProduct = items.lookupProduct("1010");
     }*/
}