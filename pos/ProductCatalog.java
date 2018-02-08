package pos;

import java.util.*;
import java.io.File;

import parameter_files.Constant;

public class ProductCatalog {
	
	private HashMap<String, Product> productList = new HashMap<String, Product>();
	
	ProductCatalog(HashMap<String, Product> productList){
	   this.productList = productList;
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
