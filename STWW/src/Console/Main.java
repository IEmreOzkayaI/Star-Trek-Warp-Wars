package Console;

import entities.Maze;
import tools.ConsolePrinter;
import tools.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		FileReader file = new FileReader();
		ConsolePrinter cp = new ConsolePrinter(file.readFile("maze.txt"));
		cp.printMapElements();
	}
}
