package entities;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import UI.Console;
import tools.RandomMovingList;
import tools.ComputerList;
import tools.RandomCoordinateGenerator;

public class Computer {

	private final String name = "C";
	private final int score = 0;
	private int coordinateX = 0;
	private int coordinateY = 0;
	public static ComputerList computerList;
	private static int computerTotalScore = 0;
	private Object obj = this;
	private enigma.console.Console cn;
	private Maze maze;
	private Player player;
	
	public Computer() {


	}

	public Computer(boolean isManager) {

		computerList = new ComputerList();

	}
	
	public Computer(enigma.console.Console cn, Maze maze, Player player) {
		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(this, maze);
		setCoordinateX(coordinate[0]);
		setCoordinateY(coordinate[1]);
		this.maze = maze;
		this.cn = cn;
		this.player=player;
		computerMove();
	}


	public int[] findPlayer() {
		int[] arr= new int[2];
		Object[][] mazeArr=maze.getMaze();
		
		for(int j=0; j<mazeArr.length; j++) {
			for(int i=0; i<mazeArr[j].length; i++) {
				if(mazeArr[j][i].equals("P")) {
					arr[0]=j;
					arr[1]=i;
					return arr;
				}
			}
		}
		return arr;
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
		
		SplittableRandom splittableRandom = new SplittableRandom();
		int directionNumber=splittableRandom.nextInt(0, availableSquares.length());
		char[] arr= availableSquares.getList();
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
	
	public double calculateDistance(int compX, int compY, int playerX, int playerY) {
		return Math.sqrt(Math.pow(playerX-compX, 2)+ Math.pow(playerY-compY, 2));
	}
	
	public boolean computerUpdateMaze(int x, int y, Object value, Maze maze) {
		Object[][] maze2 = maze.getMaze();
		if (!maze2[y][x].equals("#")) {
			maze2[y][x] = value;
			return true;
		} else {
			return false;
		}

	}
	
	public void goToMove() {
		Object[][] mazeArray = maze.getMaze();
		RandomMovingList availableSquares= new RandomMovingList();
		char direction='X';
		int destinationX=player.getX();
		int destinationY=player.getY();
		
		if (!mazeArray[coordinateY][coordinateX + 1].equals("#") && !mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Computer")) {
			availableSquares.Add('R');
		}
		if (!mazeArray[coordinateY][coordinateX - 1].equals("#") && !mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Computer")) {
			availableSquares.Add('L');
		}
		if (!mazeArray[coordinateY + 1][coordinateX].equals("#") && !mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Computer")) {
			availableSquares.Add('D');
		}
		if (!mazeArray[coordinateY - 1][coordinateX].equals("#") && !mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Computer")) {
			availableSquares.Add('U');
		}
		
		double minDistance=100000;
		char minDistanceChar='X';
		if(calculateDistance(coordinateX+1, coordinateY, destinationX, destinationY)<minDistance) {
			minDistance=calculateDistance(coordinateX, coordinateY, destinationX, destinationY);
			minDistanceChar='R';

		}
		if(calculateDistance(coordinateX-1, coordinateY, destinationX, destinationY)<minDistance) {
			minDistance=calculateDistance(coordinateX, coordinateY, destinationX, destinationY);
			minDistanceChar='L';
		}
		if(calculateDistance(this.coordinateX, this.coordinateY+1, destinationX, destinationY)<minDistance) {
			minDistance=calculateDistance(coordinateX, coordinateY, destinationX, destinationY);
			minDistanceChar='D';
		}
		if(calculateDistance(this.coordinateX, this.coordinateY-1, destinationX, destinationY)<minDistance) {
			minDistance=calculateDistance(coordinateX, coordinateY, destinationX, destinationY);
			minDistanceChar='U';
		}
		char[] arr= availableSquares.getList();
		for(int i=0; i<arr.length; i++) {
			if(arr[i]=='R' && minDistanceChar=='R') {
					direction='R';
				
			}
			if(arr[i]=='L'  && minDistanceChar=='L') {
					direction='L';
				
			}
			if(arr[i]=='D' && minDistanceChar=='D') {
					direction='D';
				
			}
			if(arr[i]=='U'  && minDistanceChar=='U') {
					direction='U';
				
			}
		}
		if(direction=='X') {
			SplittableRandom splittableRandom = new SplittableRandom();
			int directionNumber=splittableRandom.nextInt(0, availableSquares.length());
			direction=arr[directionNumber];
		}

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
	
	

	
	
	
	public void computerMove() {
		
		Object[][] tempMaze = maze.getMaze();
		
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				
				boolean isNull = false;
				tempMaze[coordinateY][coordinateX]=" ";
				//randMove();
				goToMove();
				
				if(coordinateX==player.getX() && coordinateY==player.getY()) {
					player.LifeRemove();
				}
				
				while (!isNull) {
					isNull = computerUpdateMaze(coordinateX,coordinateY, obj, maze);
					if(isNull) {
						
					}
				}
			}

		};

		timer.schedule(task, 313, 1000);
	}
	
	public int getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
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
