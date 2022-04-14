package entities;

import java.util.SplittableRandom;

import tools.ComputerList;
import tools.RandomCoordinateGenerator;


public class Computer {

	private final String name = "C";
	private final int score = 0;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private static ComputerList computerList;
	private static int computerTotalScore = 0;

	public Computer() {


	}
	public Computer(boolean isManager) {

		computerList = new ComputerList();

	}
	public Computer( Maze maze ) {
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

	public void addComputer(Computer computer) {
		computerList.Add(computer);

	}

	public static Computer[] getComputerList() {
		return computerList.getList();
	}

	public static int getComputerTotalScore() {
		return computerTotalScore;
	}

	public static void setComputerTotalScore(int computerScore) {
		computerTotalScore += computerScore;
	}
}
