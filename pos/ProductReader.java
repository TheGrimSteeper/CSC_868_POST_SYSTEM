package pos;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import parameter_files.Constant;

public class ProductReader {
	
	
	public static HashMap<String, Product> parseProducts() {
		
		  HashMap<String, Product> productList = new HashMap<String, Product>();

	  try {
		  File productFile = new File(Constant.PRODUCTS);
	 
		  Scanner productScanner = new Scanner(productFile); 
	    	productScanner.useDelimiter("\\s{2,}");
		    while(productScanner.hasNextLine()) {
				Product product = new  Product();
		    	product.setUPC(productScanner.next());
		    	product.setProductDescription(productScanner.next());
		    	product.setPrice(Double.parseDouble(productScanner.next()));  
		    	productList.put(product.UPC, product);
		    //System.out.println(product.UPC + " "+ product.productDescription);
	   } 
	}
	  catch(Exception e){
		  
	  }
	return productList;
	  }
	
}



