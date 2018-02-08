package pos;

public class ProductCatalog {
	private Product[] product;
	
	ProductCatalog(Product[] Product){
		this.product = Product;
	}
	
	public String toString() {
		String string = "";
		for(int i = 0; i < product.length; i++) {
			string += this.product[i].UPC + " " + this.product[i].productDescription + " " + this.product[i].price + "\n";
		}
		return string;
	}

	public Product[] getProduct() {
		return product;
	}

	private void setProduct(Product[] product) {
		this.product = product;
	}

}
