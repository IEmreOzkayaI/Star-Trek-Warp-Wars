package entities;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import Treasures.Constants.One;
import UI.Console;
import tools.RandomMovingList;
import tools.ScoreDefine;
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
	private boolean isEscapeNeeded=false;

	
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
	
	public boolean computerUpdateMaze(int x, int y, Object value, Object[][] maze) {
		if (!maze[y][x].equals("#")) {
			maze[y][x] = value;
			return true;
		} else {
			return false;
		}

	}
	
	public void goToMove() {
		Object[][] mazeArray = maze.getMaze();
		char direction='X';
		int destinationX=player.getX();
		int destinationY=player.getY();
		

		int[] distances=new int[4];
		char[] distancesChar= new char[4];
		
		// 0=up, 1=right, 2=down , 3=left
		distances[0]=calculateDistance(coordinateX, coordinateY-1, destinationX, destinationY);
		distancesChar[0]='U';
		distances[1]=calculateDistance(coordinateX+1, coordinateY,destinationX, destinationY);
		distancesChar[1]='R';
		distances[2]=calculateDistance(coordinateX, coordinateY+1, destinationX, destinationY);
		distancesChar[2]='D';
		distances[3]=calculateDistance(coordinateX-1, coordinateY, destinationX, destinationY);
		distancesChar[3]='L';
		
		for (int i = 0; i < distances.length; i++){  
			for (int j = i + 1; j < distances.length; j++){  
				int tmp = 0;  
				char holder;
				if (distances[i] > distances[j]){  
					tmp = distances[i];  
					holder=distancesChar[i];
					
					distances[i] = distances[j];  
					distancesChar[i]=distancesChar[j];
					
					distances[j] = tmp;
					distancesChar[j]=holder;
				}  
			}  
		}	
			
		
		for(int i=0; i<distancesChar.length; i++) {
			if (distancesChar[i]=='R' && !mazeArray[coordinateY][coordinateX + 1].equals("#") && !mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Computer")) {
				direction='R';
				break;
			}
			if (distancesChar[i]=='L' && !mazeArray[coordinateY][coordinateX - 1].equals("#") && !mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Computer")) {
				direction='L';
				break;
			}
			if (distancesChar[i]=='D' && !mazeArray[coordinateY + 1][coordinateX].equals("#") && !mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Computer")) {
				direction='D';
				break;
			}
			if (distancesChar[i]=='U' && !mazeArray[coordinateY - 1][coordinateX].equals("#") && !mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Computer")) {
				direction='U';
				break;			
		
			if(direction=='R') {
				scoreFunction('R');
				coordinateX++;
			}
			else if(direction=='L') {
				scoreFunction('L');
				coordinateX--;
			}
			else if(direction=='U') {
				scoreFunction('U');
				coordinateY--;
			}
			else if(direction=='D') {
				scoreFunction('D');
				coordinateY++; 
			}

		if(direction=='R') {
			coordinateX++;
		}
	
	public void scoreFunction(char direction) {
		Object[][] mazeArray = maze.getMaze();
		if(direction=='U') {
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("One")) {
				setComputerTotalScore(2);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Two")) {
				setComputerTotalScore(10);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Three")) {
				setComputerTotalScore(30);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Warp")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Trap")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Player")) {
				player.getBackpack().pop();
				player.getBackpack().pop();
			}
		}
		
		if(direction=='R') {
			if(mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("One")) {
				setComputerTotalScore(2);
			}
			if(mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Two")) {
				setComputerTotalScore(10);
			}
			if(mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Three")) {
				setComputerTotalScore(30);
			}
			if(mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Four")) {
				setComputerTotalScore(100);
			}
			if(mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Five")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Warp")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Trap")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY][coordinateX+1].getClass().getSimpleName().equals("Player")) {
				player.getBackpack().pop();
				player.getBackpack().pop();
			}
		}
		
		if(direction=='D') {
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("One")) {
				setComputerTotalScore(2);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Two")) {
				setComputerTotalScore(10);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Three")) {
				setComputerTotalScore(30);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Four")) {
				setComputerTotalScore(100);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Five")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Warp")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Trap")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY+1][coordinateX].getClass().getSimpleName().equals("Player")) {
				player.getBackpack().pop();
				player.getBackpack().pop();
			}
		}
		
		if(direction=='L') {
			if(mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("One")) {
				setComputerTotalScore(2);
			}
			if(mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("Two")) {
				setComputerTotalScore(10);
			}
			if(mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("Three")) {
				setComputerTotalScore(30);
			}
			if(mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("Four")) {
				setComputerTotalScore(100);
			}
			if(mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("Five")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("Warp")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("Trap")) {
				setComputerTotalScore(300);
			}
			if(mazeArray[coordinateY][coordinateX-1].getClass().getSimpleName().equals("Player")) {
				player.getBackpack().pop();
				player.getBackpack().pop();
			}
		}
	}
	


//	public String decideDirection() {
//		double tanValue=(player.getY())/();
//		if(coordinateX==player.getX() && coordinateY<player.getY()) {
//			return "down";
//		}
//		if(coordinateX==player.getX() && coordinateY>player.getY()) {
//			return "up";
//		}
//		if(coordinateY==player.getY() && coordinateX<player.getX()) {
//			return "right";
//		}
//		if(coordinateY==player.getY() && coordinateX>player.getX()) {
//			return "left";
//		}
//		
//	}
//	
//	public void escapeFunction() {
//		
//	}


	
	
	
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
					player.updateCoordinates();
				}
				
				while (!isNull) {
					isNull = computerUpdateMaze(coordinateX,coordinateY, obj, tempMaze);
				}
			}

		};

		timer.schedule(task, 100, 1000);
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
