package pos;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import parameter_files.Constant;

public class ProductReader {
	
	public static void main(String args[]) {
		parseProducts();
	}
	
	public static void parseProducts() {

	  try {
		  Product product = new  Product();
		  HashMap<String, Product> productList = new HashMap<String, Product>();
		  File productFile = new File(Constant.PRODUCTS);
	 
		  Scanner productScanner = new Scanner(productFile); 

		    while(productScanner.hasNextLine()) {
		    	productScanner.useDelimiter("\\s{2,}");
		    	product.setUPC(productScanner.next());
		    	product.setProductDescription(productScanner.next());
		    	product.setPrice(Double.parseDouble(productScanner.next()));  
		    	productList.put(product.getUPC(), product);
		    System.out.println(product.UPC + " "+ product.productDescription+ " "+ productList.values());
	   } 
	}
	  catch(Exception e){
		  
	  }
	  }
	
}



