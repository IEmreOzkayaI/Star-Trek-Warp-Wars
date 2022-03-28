package Treasures.Moves;

import java.util.SplittableRandom;

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
		SplittableRandom splittableRandom = new SplittableRandom();
		int random = splittableRandom.nextInt(1, 55);
		coordinateX = random ;
		return coordinateX;
	}

	public int getY() {
		SplittableRandom splittableRandom = new SplittableRandom();
		int random = splittableRandom.nextInt(1, 23);
		 coordinateY = random ;
		 return coordinateY;
	}
}
