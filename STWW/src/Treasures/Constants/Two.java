package Treasures.Constants;


import entities.Maze;
import tools.RandomCoordinateGenerator;

public class Two {
	private final String name = "2";
	private final int score = 5;
	private int coordinateX = 0;
	private int coordinateY = 0;

	public Two(Maze maze) {
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