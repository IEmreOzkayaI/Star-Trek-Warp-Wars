package Treasures.Constants;


import entities.Maze;
import tools.RandomCoordinateGenerator;

public class Three {
	private final String name = "3";
	private final int score = 15;
	private int coordinateX = 0;
	private int coordinateY = 0;

	public Three(Maze maze) {
		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(name, maze);
		setX(coordinate[0]);
		setY(coordinate[1]);
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setX(int x) {
		coordinateX = x;
	}

	public void setY(int y) {
		coordinateY = y;
	}	
}
