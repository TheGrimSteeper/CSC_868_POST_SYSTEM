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
	
	public static KeyValuePairs[] confParser(String relativeFilePath) {
		try {
			File file = new File(relativeFilePath);
			Scanner scanner = new Scanner(file);
			int currentLineNum = 1;
			String currentLine;
			KeyValuePairs[] confSettings = new KeyValuePairs[8];
			while(hasMoreLines(currentLineNum,relativeFilePath)) {
				currentLine = scanner.nextLine();
				switch(currentLine.split(" ")[0]) {
				case "ServerRoot":
					KeyValuePairs tmp1 = new KeyValuePairs(currentLine.split(" ")[0],currentLine.split(" ")[1]);
					confSettings[0] =  tmp1;
					break;
				case "Listen":
					KeyValuePairs tmp2 = new KeyValuePairs(currentLine.split(" ")[0],currentLine.split(" ")[1]);
					confSettings[1] =  tmp2;
					break;
				case "DocumentRoot":
					KeyValuePairs tmp3 = new KeyValuePairs(currentLine.split(" ")[0],currentLine.split(" ")[1]);
					confSettings[2] =  tmp3;
					break;	
				case "LogFile":
					KeyValuePairs tmp4 = new KeyValuePairs(currentLine.split(" ")[0],currentLine.split(" ")[1]);
					confSettings[3] =  tmp4;
					break;
				case "Alias":
					KeyValuePairs tmp5 = new KeyValuePairs(currentLine.split(" ")[0],currentLine.split(" ")[1],currentLine.split(" ")[2]);
					confSettings[4] =  tmp5;
					break;
				case "ScriptAlias":
					KeyValuePairs tmp6 = new KeyValuePairs(currentLine.split(" ")[0],currentLine.split(" ")[1],currentLine.split(" ")[2]);
					confSettings[5] =  tmp6;
					break;
				case "AccessFileName":
					KeyValuePairs tmp7 = new KeyValuePairs(currentLine.split(" ")[0],currentLine.split(" ")[1]);
					confSettings[6] =  tmp7;
					break;
				case "DirectoryIndex":
					KeyValuePairs tmp8 = new KeyValuePairs(currentLine.split(" ")[0],currentLine.split(" ")[1]);
					confSettings[7] =  tmp8;
					break;	
				}
				currentLineNum++;
			}
			return confSettings;
		}catch(FileNotFoundException e) {
			System.out.println("Error: Config file not found." + e);
			return null;
		}
		
		
	}
}
