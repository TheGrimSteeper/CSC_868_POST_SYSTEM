package pos;

public class Main {

	public static void main(String[] args) {
		ProductCatalog catalog = new ProductCatalog(ParserClass.productParser("parameter_files/products.txt"));
		System.out.println(catalog.toString());

	}

}
