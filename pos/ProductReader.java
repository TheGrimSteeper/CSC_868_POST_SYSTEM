package pos;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import parameter_files.Constant;

/**
 * 
 * @author shalaka
 *Gets filename as an input and parses the file into productMap
 */
public class ProductReader {
	
	
	ProductReader(String productFile){
		
		parseProducts(productFile);
		
	}
	
	public static HashMap<String, Product> parseProducts(String productFile) {
		
		  HashMap<String, Product> productMap = new HashMap<String, Product>();

	  try {
		  File prodFile = new File(productFile);
	 
		  @SuppressWarnings("resource")
		Scanner productScanner = new Scanner(prodFile); 
	    	productScanner.useDelimiter("\\s{2,}");
		    while(productScanner.hasNextLine()) {
				Product product = new  Product();
		    	product.setUPC(productScanner.next());
		    	product.setProductDescription(productScanner.next());
		    	product.setPrice(Double.parseDouble(productScanner.next()));  
		    	productMap.put(product.UPC, product);
	   } 
	}
	  catch(FileNotFoundException ex){
		  System.out.print("Products file is not found" + ex);
	  }
	return productMap;
	  }
	
}