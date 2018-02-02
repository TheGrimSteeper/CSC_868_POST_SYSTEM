package pos;

public class Main {

	public static void main(String[] args) {
		Customer Bob = new Customer(ParserClass.productParser("parameter_files/products.txt"));
		System.out.println(Bob.shoppingCart[0].key + " " + Bob.shoppingCart[0].value1 + "\n" + Bob.shoppingCart[1].key + " " + Bob.shoppingCart[1].value1);

	}

}
