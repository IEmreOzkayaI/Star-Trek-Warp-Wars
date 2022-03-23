import tools.ConsolePrinter;
import tools.FileReader;

public class Main {
  
	public static void main(String[] args) throws Exception {
    
		ConsolePrinter cp = new ConsolePrinter();
		FileReader file = new FileReader();
    
		String [] map = file.readFile("maze.txt");
    
		for (int i = 0; i < map.length; i++) {
      
			System.out.println(map[i]);
      
		}
    
	}
}
