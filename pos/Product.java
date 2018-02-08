package pos;

public class Product {
	
	public String  UPC = "";
	public String productDescription = "";
	public double price = 0;
		
	public Product(String UPC, String productDescription, double price) {
		super();
		this.UPC = UPC;
		this.productDescription = productDescription;
		this.price = price;
	}

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

	private double getPrice() {
		return price;
	}
	
	private void setPrice(double price) {
		this.price = price;
	}

}
