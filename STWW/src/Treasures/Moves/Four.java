package Treasures.Moves;

import java.util.Random;

public class Four {
	private final String name = "4";
	private final int score = 50;
	private int coordinateX = 0;
	private int coordinateY = 0;

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public int getX() {
		Random random = new Random();
		 return coordinateX = random.nextInt(54 - 3 + 1) + 3;
	}

	public int getY() {
		Random random = new Random();
		return coordinateY = random.nextInt(21 - 3 + 1) + 3;
	}
}
