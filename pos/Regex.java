package pos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	static final Pattern UPC_PATTERN = Pattern.compile("^[0-9]{4}$");
	static final Pattern PRODUCT_PATTERN = Pattern.compile("^[A-Z,0-9%]{1,30}$");
	static final Pattern PRICE_PATTERN = Pattern.compile("^[.0-9$]{7,8}$");
	static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]{1,20}$");
	static final Pattern CREDIT_PATTERN  = Pattern.compile("^[0-9]{5}$");
	
	
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
	
	public static boolean isItName(String string) {
		Matcher matcher = NAME_PATTERN.matcher(string);
		return matcher.matches();
	}
	
	public static boolean isItCreditCard(String string) {
		Matcher matcher = CREDIT_PATTERN.matcher(string);
		return matcher.matches();
	}

}
