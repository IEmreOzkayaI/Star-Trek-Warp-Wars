package Treasures.Moves;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import entities.Maze;
import tools.RandomCoordinateGenerator;

public class Four {
	private final String name = "4";
	private final int score = 50;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private Object obj = this;

	private enigma.console.Console cn;
	private Maze maze;

	public Four(enigma.console.Console cn, Maze maze) throws InterruptedException {
		this.maze = maze;
		this.cn = cn;
		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(this, this.maze);
		setX(coordinate[0]);
		setY(coordinate[1]);
		fourMove();


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


	public void randMove() {
		boolean isFilledUp=false;
		boolean isFilledDown=false;
		boolean isFilledRight=false;
		boolean isFilledLeft=false;
		
		Object[][] mazeArray=maze.getMaze();
		
		if(mazeArray[coordinateY][coordinateX+1].equals(" ")) {
			 isFilledRight=true;
		}
		if(mazeArray[coordinateY][coordinateX-1].equals(" ")) {
			isFilledLeft=true;
		}
		if(mazeArray[coordinateY+1][coordinateX].equals(" ")) {
			isFilledDown=true;
		}
		if(mazeArray[coordinateY-1][coordinateX].equals(" ")) {
			isFilledUp=true;
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
	public void fourMove() {
		
		Object[][] tempMaze = maze.getMaze();
		
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				
				boolean isNull = false;
				tempMaze[coordinateY][coordinateX]=" ";
				randMove();
				while (!isNull) {
					isNull = maze.updateMaze(coordinateX,coordinateY,obj);
				}
			}

		};

		timer.schedule(task, 313, 1000);
	}
}
