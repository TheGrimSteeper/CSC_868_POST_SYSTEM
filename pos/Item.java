package pos;

import java.util.ArrayList;
import java.util.HashMap;

public class Item {
	
	String UPC;
	String productDescription;
	int quantity;
	double unitPrice = 0.0;
	double subTotal = 0.0;
	
	public String getUPC() {
		return UPC;
	}

	public void setUPC(String uPC) {
		UPC = uPC;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public static ArrayList<Item> poulateItemDetails(ArrayList<Item> itemList, HashMap<String, Product> productMap) {
		try {
		
		for(Item item : itemList) {
			String prodDesc = productMap.get(item.UPC).productDescription;
			double prodPrice = productMap.get(item.UPC).price;
			double subtotalPrice = prodPrice * item.quantity;
			item.setProductDescription(prodDesc);
			item.setUnitPrice(prodPrice);
			item.subTotal = subtotalPrice;
		}
		}
		catch(Exception e) {
			System.out.println("Error in populating Item Details: " +e);
		}
		
		return itemList;
		
		
	}
	
	

}
