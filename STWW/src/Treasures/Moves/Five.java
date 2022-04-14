package Treasures.Moves;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import entities.Maze;
import tools.RandomCoordinateGenerator;

public class Five {
	private final String name = "5";
	private final int score = 50;
	private int coordinateX = 0;
	private int coordinateY = 0;

	private enigma.console.Console cn;
	private Maze maze;

	public Five(enigma.console.Console cn, Maze maze) {
		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(name, maze);
		setX(coordinate[0]);
		setY(coordinate[1]);
		this.maze = maze;
		this.cn = cn;
		fiveMove();

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

//	public void randomMove() {
//		while (true) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			boolean isNull = false;
//			int x = 0;
//			int y = 0;
//			while (!isNull) {
//				isNull = this.maze.updateMaze(x = this.getX(), y = this.getY(), this.getName());
//			}
//			isNull = false;
//			cn.getTextWindow().setCursorPosition(x + 2, y + 1);
//			System.out.print(this.getName());
//
//		}
//
//	}

	public void randMove() {
		boolean isFilledUp = false;
		boolean isFilledDown = false;
		boolean isFilledRight = false;
		boolean isFilledLeft = false;

		Object[][] mazeArray = maze.getMaze();

		if (mazeArray[coordinateY][coordinateX + 1].equals(" ")) {
			isFilledRight = true;
		}
		if (mazeArray[coordinateY][coordinateX - 1].equals(" ")) {
			isFilledLeft = true;
		}
		if (mazeArray[coordinateY + 1][coordinateX].equals(" ")) {
			isFilledDown = true;
		}
		if (mazeArray[coordinateY - 1][coordinateX].equals(" ")) {
			isFilledUp = true;
		}

		if (isFilledUp) {
			coordinateY--;

		} else if (isFilledRight) {
			coordinateX++;

		} else if (isFilledDown) {
			coordinateY++;

		} else if (isFilledLeft) {
			coordinateX--;
		} else {

		}

	}

	public void fiveMove() {
		
		Object[][] tempMaze = maze.getMaze();
		
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				
				boolean isNull = false;
				tempMaze[coordinateY][coordinateX]=" ";
				randMove();
				while (!isNull) {
					isNull = maze.updateMaze(coordinateX,coordinateY, getName());
				}
			}

		};

		timer.schedule(task, 313, 1000);
	}
}
