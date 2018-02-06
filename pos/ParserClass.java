package pos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ParserClass {
	public static BufferedReader startBufferedReader(String relativeFilePath) {
		try {
			BufferedReader file = new BufferedReader(new FileReader(relativeFilePath));
			return file;
		}catch(FileNotFoundException e) {
			return null;
		}
	}
	
	
	public static int lineCounter(String relativeFilePath) {
		
			BufferedReader file = startBufferedReader(relativeFilePath);
			try {
				int numberOfLines = 0;
				String line;
				while((line = file.readLine()) != null) {
					numberOfLines++;
				}
				file.close();
				return numberOfLines;
			}catch(IOException e) {
				return 1;
			}
			
	}
	
	public static int nonEmptyLineCounter(String relativeFilePath) {
		
		BufferedReader file = startBufferedReader(relativeFilePath);
		int numberOfLines = 0;
		String line;
		try {
			while((line = file.readLine()) != null) {
				if(!"".equals(line.trim())){
			        numberOfLines++;
			    }
			}
			file.close();
		}catch(IOException e) {
			return 1;
		}
		return numberOfLines;
	}
	
	public static boolean isNotEmptyLine(String currentLine) {
				return (!"".equals(currentLine.trim())) ? true : false;
	}
	
	public static boolean hasMoreLines(int currentLine, String relativeFilePath) {
		int numberOfLines = lineCounter(relativeFilePath);
		return (numberOfLines < currentLine) ? false : true;
	}
	
	public static Product createProductObject(String currentLine) {
		String tmpUPC = "";
		String tmpProduct = "";
		double tmpPrice = 0.00;
		for(int i = 0; i < currentLine.split(" ").length; i++) {
			if(Regex.isItUPC(currentLine.split(" ")[i])) {
				tmpUPC = currentLine.split(" ")[i];
			}else if(Regex.isItProduct(currentLine.split(" ")[i].replaceAll("\\s+",""))) {
				tmpProduct += currentLine.split(" ")[i] + " ";
			}else if(Regex.isItPrice(currentLine.split(" ")[i])) {
				tmpPrice = Double.parseDouble(currentLine.split(" ")[i]);
			}
		}
		Product product = new Product(tmpUPC,tmpProduct,tmpPrice);
		return product;
	}
	
	
	
	public static Product[] productParser(String relativeFilePath) {
		try {
			File file = new File(relativeFilePath);
			Scanner scanner = new Scanner(file);
			int currentLineNum = 1;
			int posNumber = 0;
			String currentLine;
			Product[] catalog = new Product[nonEmptyLineCounter(relativeFilePath)];
			while(hasMoreLines(currentLineNum,relativeFilePath)) {
				currentLine = scanner.nextLine();
				if(isNotEmptyLine(currentLine)) {
					catalog[posNumber] = createProductObject(currentLine);
					posNumber++;
				}
				currentLineNum++;
			}
			scanner.close();
			return catalog;
		}catch(FileNotFoundException e) {
			System.out.println("Error: Config file not found." + e);
			return null;
		}
		
		
	}
	
	public static boolean nameMatches(String currentLine, String fullName) {
		String lineName = currentLine.split(" ")[0] + " " + currentLine.split(" ")[1];
		return fullName.equals(lineName);
	}
	
	public static boolean customerFound(String relativeFilePath, String fullName) {
		try {
			File file = new File(relativeFilePath);
			Scanner scanner = new Scanner(file);
			int currentLineNum = 1;
			String currentLine;
			boolean customerFound = false;
			while(hasMoreLines(currentLineNum,relativeFilePath)) {
				currentLine = scanner.nextLine();
				if(nameMatches(currentLine, fullName)) {
					customerFound = true;
					System.out.println(customerFound);
				}
				currentLineNum++;
			}
			return customerFound;
		}catch(FileNotFoundException e) {
			System.out.println("Error: Config file not found." + e);
			return false;
		}
	}
	
	
	public static HashMap<String, Integer> returnShoppingCart(String relativeFilePath, String fullName) {
		try {
			File file = new File(relativeFilePath);
			Scanner scanner = new Scanner(file);
			int currentLineNum = 1;
			String currentLine;
			HashMap<String, Integer> shoppingCart = new HashMap<String, Integer>();
			while(hasMoreLines(currentLineNum,relativeFilePath)) {
				currentLine = scanner.nextLine();
				if(nameMatches(currentLine, fullName)) {
					for(int i = 0; i < currentLine.split(" ").length; i++) {
						if(Regex.isItUPC(currentLine.split(" ")[i].replaceAll("\\s+",""))){
							System.out.println("dsfsdf");
							shoppingCart.put(currentLine.split(" ")[i].replaceAll("\\s+",""), Integer.parseInt(currentLine.split(" ")[i+1].replaceAll("\\s+","")));
						}
					}
				}
				currentLineNum++;
			}
			scanner.close();
			return shoppingCart;
		}catch(FileNotFoundException e) {
			System.out.println("Error: Config file not found." + e);
			return null;
		}
		
		
	}
	
	public static String cashOrCredit(String relativeFilePath, String fullName) {
		try {
			File file = new File(relativeFilePath);
			Scanner scanner = new Scanner(file);
			int currentLineNum = 1;
			String currentLine;
			String cashOrCredit = "NA";
			while(hasMoreLines(currentLineNum,relativeFilePath)) {
				currentLine = scanner.nextLine();
				if(nameMatches(currentLine, fullName)) {
					for(int i = 0; i < currentLine.split(" ").length; i++) {
						if(currentLine.split(" ")[i].replaceAll("\\s+","") == "CASH"){
							cashOrCredit = "CASH"; 
						}else if(currentLine.split(" ")[i].replaceAll("\\s+","") == "CREDIT") {
							cashOrCredit = "CREDIT";
						}
					}
				}
				currentLineNum++;
			}
			scanner.close();
			return cashOrCredit;
		}catch(FileNotFoundException e) {
			System.out.println("Error: Config file not found." + e);
			return null;
		}
	}
	
	public static double returnAmountPaid(String relativeFilePath, String fullName) {
		try {
			File file = new File(relativeFilePath);
			Scanner scanner = new Scanner(file);
			int currentLineNum = 1;
			String currentLine;
			double cashAmount = 0.00;
			while(hasMoreLines(currentLineNum,relativeFilePath)) {
				currentLine = scanner.nextLine();
				if(nameMatches(currentLine, fullName)) {
					for(int i = 0; i < currentLine.split(" ").length; i++) {
						if(Regex.isItPrice(currentLine.split(" ")[i].replaceAll("\\s+",""))){
							cashAmount = Double.parseDouble(currentLine.split(" ")[i].replaceAll("\\$\\s+",""));
						}
					}		
				}
				currentLineNum++;
			}
			scanner.close();
			return cashAmount;
		}catch(FileNotFoundException e) {
			System.out.println("Error: Config file not found." + e);
			return 0.00;
		}
	}
	
	public static String returnCreditCardNumber(String relativeFilePath, String fullName) {
		try {
			File file = new File(relativeFilePath);
			Scanner scanner = new Scanner(file);
			int currentLineNum = 1;
			String currentLine;
			String creditCardNumber = "NA";
			while(hasMoreLines(currentLineNum,relativeFilePath)) {
				currentLine = scanner.nextLine();
				if(nameMatches(currentLine, fullName)) {
					for(int i = 0; i < currentLine.split(" ").length; i++) {
						if(Regex.isItCreditCard(currentLine.split(" ")[i].replaceAll("\\s+",""))){
							creditCardNumber = currentLine.split(" ")[i].replaceAll("\\s+","");
						}
					}		
				}
				currentLineNum++;
			}
			scanner.close();
			return creditCardNumber;
		}catch(FileNotFoundException e) {
			System.out.println("Error: Config file not found." + e);
			return "NA";
		}
	}
}
