package pos;

public class Manager {
	
	public static PostSystem startUpStore() {
		ProductCatalog catalog = new ProductCatalog(ParserClass.productParser(PostSystem.getProductspath()));
		Store store = new Store(catalog);
		Cashier cashier = new Cashier("Joe Schmoe");
		PostSystem post = new PostSystem(store,cashier);
		return post;
	}

}
