import tools.ConsolePrinter;
import tools.FileReader;

public class Main {
<<<<<<< Updated upstream
	public static void main(String[] args) {
		System.out.println("Hello World");

<<<<<<< Updated upstream
          System.out.println("Hello World Baran");
=======
>>>>>>> Stashed changes
=======
	public static void main(String[] args) throws Exception {
		ConsolePrinter cp = new ConsolePrinter();
		FileReader file = new FileReader();
		String [] map = file.readFile("maze.txt");
		for (int i = 0; i < map.length; i++) {
			System.out.println(map[i]);
		}
		
>>>>>>> Stashed changes
	}
}
