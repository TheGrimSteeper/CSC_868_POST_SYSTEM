package post;

import java.util.*;
import java.io.File;

import parameter_files.Constant;

public class ProductCatalog {

    private HashMap<String, Product> productList;

    public ProductCatalog(){
        this.productList = new HashMap<String, Product>();
    }

    public void buildCatalog(String filename) {
        productList = ProductReader.parseProducts(filename);
    }

    public Product lookupProduct(String UPC){
        Product value = productList.get(UPC);
        if(value == null){
            System.out.println("UPC NOT FOUND");
        }
        System.out.println("product found with UPC" + UPC + " = " + value );

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
}