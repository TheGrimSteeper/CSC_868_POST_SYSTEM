package pos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	static final Pattern UPC_PATTERN = Pattern.compile("[0-9]{4}");
	static final Pattern PRODUCT_PATTERN = Pattern.compile("^[A-Z,0-9%]{1,30}$");
	static final Pattern PRICE_PATTERN = Pattern.compile("[.0-9]{7}");
	
	
	public static boolean isItUPC(String string) {
		Matcher matcher = UPC_PATTERN.matcher(string);
		return matcher.matches();
	}
	
	public static boolean isItProduct(String string) {
		Matcher matcher = PRODUCT_PATTERN.matcher(string);
		return matcher.matches();
	}
	
	public static boolean isItPrice(String string) {
		Matcher matcher = PRICE_PATTERN.matcher(string);
		return matcher.matches();
	}

}
