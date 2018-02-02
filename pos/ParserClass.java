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
	
	public static boolean hasMoreLines(int currentLine, String relativeFilePath) {
		int numberOfLines = lineCounter(relativeFilePath);
		return (numberOfLines == currentLine) ? false : true;
	}
	
	public static KeyValuePairs[] productParser(String relativeFilePath) {
		try {
			File file = new File(relativeFilePath);
			Scanner scanner = new Scanner(file);
			int currentLineNum = 1;
			String currentLine;
			KeyValuePairs[] vares = new KeyValuePairs[3];
			while(hasMoreLines(currentLineNum,relativeFilePath)) {
				currentLine = scanner.nextLine();
				for(int i = 0; i < currentLine.split(" ").length; i++) {
					if(Regex.isItUPS(currentLine.split(" ")[i])) {
						KeyValuePairs UPS = new KeyValuePairs("UPS",currentLine.split(" ")[i].replaceAll("\\s+",""));
						vares[0] = UPS;
					}else if(Regex.isItProduct(currentLine.split(" ")[i])) {
						KeyValuePairs product = new KeyValuePairs("Product",currentLine.split(" ")[i].replaceAll("\\s+",""));
						vares[1] = product;
					}else if(Regex.isItPrice(currentLine.split(" ")[i])) {
						KeyValuePairs price = new KeyValuePairs("Price",currentLine.split(" ")[i].replaceAll("\\s+",""));
						vares[2] = price;
					}
				}
				
				currentLineNum++;
			}
			return vares;
		}catch(FileNotFoundException e) {
			System.out.println("Error: Config file not found." + e);
			return null;
		}
		
		
	}
}
