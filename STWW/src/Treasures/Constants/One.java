package Treasures.Constants;

import java.util.Random;
import java.util.SplittableRandom;

public class One {

	private final String name = "1";
	private final int score = 1;
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
		 return coordinateX = random ;
	}

	public int getY() {
		SplittableRandom splittableRandom = new SplittableRandom();
		int random = splittableRandom.nextInt(1, 23);
		 return coordinateY = random ;
	}


}
