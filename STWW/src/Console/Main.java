package Console;

import entities.Computer;
import entities.Maze;
import tools.Console;
import tools.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		FileReader file = new FileReader();
		Computer computer = new Computer(true);
		Console cp = new Console(file.readFile("maze.txt"),computer);
	
	}
}
