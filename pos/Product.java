package pos;

public class Product {
	
	public String  UPC = "";
	public String productDescription = "";
	public double price = 0;
	
	
	/*public Product(String UPC, String productDescription, float price) {
		super();
		this.UPC = UPC;
		this.productDescription = productDescription;
		this.price = price;
	}*/


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


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	
	
}
