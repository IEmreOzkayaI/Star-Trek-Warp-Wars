package Treasures.Constants;


import entities.Maze;
import tools.RandomCoordinateGenerator;

public class One   {



	private final String name = "1";
	private final int score = 1;
	private int coordinateX = 0;
	private int coordinateY = 0;

	public One(Maze maze) {
		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(this, maze);
		setX(coordinate[0]);
		setY(coordinate[1]);
	}
	
	public One() {
		// TODO Auto-generated constructor stub
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
