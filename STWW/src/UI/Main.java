 package UI;

import Treasures.Constants.One;
import entities.Computer;
import tools.FileReader;

public class Main {


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		FileReader reader = new FileReader();
		Computer computer = new Computer(true);
		Console console = new Console(reader.readFile("maze.txt"),computer);


	}

}
