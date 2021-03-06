package tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	public Object[][] readFile(String fileName,boolean isMap) {
		Object[][] map = new Object[23][55]; // detected size before reading
		Object[][] menu = new Object[8][46]; 
		int i = 0;
		try {
			Scanner file = new Scanner(new File(fileName));
			while (file.hasNextLine()) { // read one by one and store them line by line
				String x = file.nextLine();
				char[] line = x.toCharArray();
			
				for (int j = 0; j < line.length; j++) {
					if(isMap)
						map[i][j]=Character.toString(line[j]);
					else {
						menu[i][j]=Character.toString(line[j]);

					}
				}
				i++;
			}

			file.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}

		if(isMap)
			return map;
		else
			return menu;
	}

}
