package tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	
	public String[] readFile(String fileName) { 
		String[] elements = new String[detectSize(fileName)]; // detected size before reading
		int i = 0;
		try {
			Scanner file = new Scanner(new File(fileName));
			while (file.hasNext()) { 				//read one by one and store them line by line
				elements[i++] = file.nextLine();
			}

			file.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}

		return elements; // return line
	}
	
	
	
	private static int detectSize(String fileName) { // firstly read all lines and detected line amount
		int lineAmount = 0;
		try {
			Scanner dictionaryFile = new Scanner(new File(fileName));
			while (dictionaryFile.hasNext()) {
				dictionaryFile.nextLine();
				lineAmount++;

			}

			dictionaryFile.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}

		return lineAmount;
	}
	
	
}
