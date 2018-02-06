package pos;

public class Store {
	private static final String storeName = "Super Duper Shopper Saver";
	private ProductCatalog catalog;
	
	Store(ProductCatalog catalog){
		this.catalog = catalog;
	}

	public ProductCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(ProductCatalog catalog) {
		this.catalog = catalog;
	}

	public static String getStoreName() {
		return storeName;
	}
	
	

}
