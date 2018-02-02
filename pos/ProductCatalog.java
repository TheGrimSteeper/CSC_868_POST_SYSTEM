package pos;

public class ProductCatalog {
	Products[] products;
	
	ProductCatalog(Products[] products){
		this.products = products;
	}
	
	public String toString() {
		String string = "";
		for(int i = 0; i < products.length; i++) {
			string += this.products[i].UPC + " " + this.products[i].product + " " + this.products[i].price + "\n";
		}
		return string;
	}

}
