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

	private int getQuantity() {
		return quantity;
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private double getUnitPrice() {
		return unitPrice;
	}

	private void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	private double getSubTotal() {
		return subTotal;
	}

	private void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	private static ArrayList<Item> populateItemDetails(ArrayList<Item> itemList, HashMap<String, Product> productMap) {
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
