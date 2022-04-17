package Treasures.Moves;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import UI.Console;
import entities.Maze;
import tools.RandomCoordinateGenerator;
import tools.RandomMovingList;

public class Four {
	private final String name = "4";
	private final int score = 50;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private Object obj = this;
	private boolean live =true;
	private boolean isFreeze = false;
	private enigma.console.Console cn;
	private Maze maze;
	private int freezingTime =0 ;

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
		Object[][] mazeArray = maze.getMaze();
		RandomMovingList availableSquares= new RandomMovingList();
		if (mazeArray[coordinateY][coordinateX + 1].equals(" ")) {
			availableSquares.Add('R');
		}
		if (mazeArray[coordinateY][coordinateX - 1].equals(" ")) {
			availableSquares.Add('L');
		}
		if (mazeArray[coordinateY + 1][coordinateX].equals(" ")) {
			availableSquares.Add('D');
		}
		if (mazeArray[coordinateY - 1][coordinateX].equals(" ")) {
			availableSquares.Add('U');
		}
		
		char[] arr= availableSquares.getList();
		if(arr.length!=0) {
		int directionNumber= Console.time % arr.length;
		char direction=arr[directionNumber];
		if(direction=='R') {
			coordinateX++;
		}
		else if(direction=='L') {
			coordinateX--;
		}
		else if(direction=='U') {
			coordinateY--;
		}
		else if(direction=='D') {
			coordinateY++; 
		}
		}
	}
	
	public void numberDie() {
		live = false;
	}
	
	public void numberFreeze(int freezeTime) {
		this.freezingTime=freezeTime;
		isFreeze=true;
	}
	
	public boolean isLiving() {
		return live;
	}
	public boolean isFreeze() {
		return isFreeze;
	}
	
	
	public void fourMove() {
		
		Object[][] tempMaze = maze.getMaze();
		
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
			int second = 0 ;
			@Override
			public void run() {
				
				if(isLiving()) {
					if(!isFreeze()) {
						boolean isNull = false;
						tempMaze[coordinateY][coordinateX]=" ";
						randMove();
						while (!isNull) {
							isNull = maze.updateMaze(coordinateX,coordinateY,obj);
						}
					}else {
						if(25-freezingTime == second) {
							isFreeze=false;
						}
						
					}
				}
				else {
					timer.cancel();
				}
			}

		};

		timer.schedule(task, 1200, 1000);
	}
}
