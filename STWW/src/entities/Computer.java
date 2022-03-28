package entities;

import java.util.SplittableRandom;

import tools.ComputerList;

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

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public int getX() {
		SplittableRandom splittableRandom = new SplittableRandom();
		int random = splittableRandom.nextInt(1, 55);
		coordinateX = random;
		return coordinateX;
	}

	public int getY() {
		SplittableRandom splittableRandom = new SplittableRandom();
		int random = splittableRandom.nextInt(1, 23);
		coordinateY = random;
		return coordinateY;

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
