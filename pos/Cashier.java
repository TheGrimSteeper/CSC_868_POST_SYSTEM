package pos;

import java.text.ParseException;
import java.util.HashMap;

public class Cashier {
	private String name;
	
	Cashier(String name){
		this.name = name;
	}
	
	public Customer processCustomer(String relativePath, String name) throws ParseException {
		String firstName = name.split(" ")[0].replaceAll("\\s+","");
		String lastName = name.split(" ")[1].replaceAll("\\s+","");
		HashMap<String, Integer> shoppingCart = ParserClass.returnShoppingCart(relativePath,name);
		String cashOrCredit = ParserClass.cashOrCredit(relativePath, name);
		if(cashOrCredit.equals("CASH")) {
			double cashAmount = ParserClass.returnAmountPaid(relativePath,name);
			Customer customer =  new Customer(firstName,lastName,shoppingCart,cashOrCredit,cashAmount);
			return customer;
		}else if(cashOrCredit == "CREDIT") {
			String creditCardNumber = ParserClass.returnCreditCardNumber(relativePath,name);
			Customer customer =  new Customer(firstName,lastName,shoppingCart,cashOrCredit,creditCardNumber);
			return customer;
		}
		return null;
		
	}

}
