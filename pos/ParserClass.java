package pos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
			int numberOfLines = 0;
			String line;
			try {
				while((line = file.readLine()) != null) {
					numberOfLines++;
				}
				file.close();
			}catch(IOException e) {
				return 1;
			}
			return numberOfLines;
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
	
	public static Products createProductObject(String currentLine) {
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
		Products product = new Products(tmpUPC,tmpProduct,tmpPrice);
		return product;
	}
	
	
	
	public static Products[] productParser(String relativeFilePath) {
		try {
			File file = new File(relativeFilePath);
			Scanner scanner = new Scanner(file);
			int currentLineNum = 1;
			int posNumber = 0;
			String currentLine;
			Products[] catalog = new Products[nonEmptyLineCounter(relativeFilePath)];
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
}
