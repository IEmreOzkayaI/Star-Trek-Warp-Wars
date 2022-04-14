package tools;

import java.util.SplittableRandom;

import entities.Maze;

public class RandomCoordinateGenerator {
	
	public static int[] generateRandomCoordinates(String name, Maze maze) {
		SplittableRandom splittableRandom = new SplittableRandom();
		int[] coordinate = new int[2];
		int x = 0 ;
		int y = 0 ;
		boolean isNull = false;
		while (!isNull) {
			 x = splittableRandom.nextInt(1, 55);
			 y = splittableRandom.nextInt(1, 23);
			isNull =  maze.updateMaze(x,y, name);
		}
		coordinate[0]=x;
		coordinate[1]=y;
		return coordinate;
	}
}
