 package UI;

import tools.FileReader;

public class Main {


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		
		FileReader reader = new FileReader();
		Console console = new Console(reader.readFile("maze.txt",true));


	}

}
