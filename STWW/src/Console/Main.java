package Console;

import entities.Maze;
import tools.ConsolePrinter;
import tools.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		ConsolePrinter cp = new ConsolePrinter();
		
		Maze maze = new Maze();
		FileReader file = new FileReader();
		maze.printMaze(file.readFile("maze.txt"));

	}
}
