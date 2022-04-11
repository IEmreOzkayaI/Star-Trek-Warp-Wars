package Treasures.Moves;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import entities.Maze;

public class Four {
	private final String name = "4";
	private final int score = 50;
	private int coordinateX = 0;
	private int coordinateY = 0;

	private enigma.console.Console cn;
	private Maze maze;

	public Four(enigma.console.Console cn, Maze maze) {
		this.maze = maze;
		this.cn = cn;
		fourMove();
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
				int x = 0;
				int y = 0;
				
				cn.getTextWindow().setCursorPosition(coordinateX + 2, coordinateY + 1);
				System.out.print(" ");
				tempMaze[coordinateY][coordinateX]=" ";

				randMove();
				
				while (!isNull) {
					isNull = maze.updateMaze(x = coordinateX, y = coordinateY, getName());
				}
				isNull = false;

				cn.getTextWindow().setCursorPosition(x + 2, y + 1);
				System.out.print(getName());

			}

		};

		timer.schedule(task, 313, 1000);
	}
}
