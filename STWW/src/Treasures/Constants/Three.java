package Treasures.Constants;

import java.util.Random;

public class Three {
	private final String name = "3";
	private final int score = 15;
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
