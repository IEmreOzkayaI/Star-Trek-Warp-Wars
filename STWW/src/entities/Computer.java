package entities;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;


import Treasures.Moves.Five;
import Treasures.Moves.Four;

import tools.RandomMovingList;
import tools.ScoreDefine;
import tools.RandomCoordinateGenerator;

public class Computer {

	private final String name = "C";
	private final int score = 300;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private static int computerTotalScore = 0;
	private Object obj = this;
	private Maze maze;
	private Player player;
	private boolean live = true;
	private boolean isFreeze = false;
	private int freezingTime = 0 ;
	public Computer() {

	}



	public Computer(enigma.console.Console cn, Maze maze, Player player) {
		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(this, maze);
		setCoordinateX(coordinate[0]);
		setCoordinateY(coordinate[1]);
		this.maze = maze;
		this.player = player;
		computerMove();
	}

	public int[] findPlayer() {
		int[] arr = new int[2];
		Object[][] mazeArr = maze.getMaze();

		for (int j = 0; j < mazeArr.length; j++) {
			for (int i = 0; i < mazeArr[j].length; i++) {
				if (mazeArr[j][i].equals("P")) {
					arr[0] = j;
					arr[1] = i;
					return arr;
				}
			}
		}
		return arr;
	}

	public void randMove() {
		Object[][] mazeArray = maze.getMaze();
		RandomMovingList availableSquares = new RandomMovingList();
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
		int directionNumber = splittableRandom.nextInt(0, availableSquares.length());
		char[] arr = availableSquares.getList();
		char direction = arr[directionNumber];
		if (direction == 'R') {
			coordinateX++;
		} else if (direction == 'L') {
			coordinateX--;
		} else if (direction == 'U') {
			coordinateY--;
		} else if (direction == 'D') {
			coordinateY++;
		}
	}

	public int calculateDistance(int compX, int compY, int playerX, int playerY) {

		int y = compY - playerY;
		if (y < 0) {
			y *= -1;
		}
		int x = compX - playerX;
		if (x < 0) {
			x *= -1;
		}
		return x + y;
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
		char direction = 'X';
		int destinationX = player.getX();
		int destinationY = player.getY();

		int[] distances = new int[4];
		char[] distancesChar = new char[4];

		// 0=up, 1=right, 2=down , 3=left
		distances[0] = calculateDistance(coordinateX, coordinateY - 1, destinationX, destinationY);
		distancesChar[0] = 'U';
		distances[1] = calculateDistance(coordinateX + 1, coordinateY, destinationX, destinationY);
		distancesChar[1] = 'R';
		distances[2] = calculateDistance(coordinateX, coordinateY + 1, destinationX, destinationY);
		distancesChar[2] = 'D';
		distances[3] = calculateDistance(coordinateX - 1, coordinateY, destinationX, destinationY);
		distancesChar[3] = 'L';

		for (int i = 0; i < distances.length; i++) {
			for (int j = i + 1; j < distances.length; j++) {
				int tmp = 0;
				char holder;
				if (distances[i] > distances[j]) {
					tmp = distances[i];
					holder = distancesChar[i];

					distances[i] = distances[j];
					distancesChar[i] = distancesChar[j];

					distances[j] = tmp;
					distancesChar[j] = holder;
				}
			}
		}

		for (int i = 0; i < distancesChar.length; i++) {
			if (distancesChar[i] == 'R' && !mazeArray[coordinateY][coordinateX + 1].equals("#")
					&& !mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Computer")) {
				direction = 'R';
				break;
			}
			if (distancesChar[i] == 'L' && !mazeArray[coordinateY][coordinateX - 1].equals("#")
					&& !mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("Computer")) {
				direction = 'L';
				break;
			}
			if (distancesChar[i] == 'D' && !mazeArray[coordinateY + 1][coordinateX].equals("#")
					&& !mazeArray[coordinateY + 1][coordinateX].getClass().getSimpleName().equals("Computer")) {
				direction = 'D';
				break;
			}
			if (distancesChar[i] == 'U' && !mazeArray[coordinateY - 1][coordinateX].equals("#")
					&& !mazeArray[coordinateY - 1][coordinateX].getClass().getSimpleName().equals("Computer")) {
				direction = 'U';
				break;
			}

		}

		if (direction == 'R') {
			scoreFunction('R');
			coordinateX++;

		} else if (direction == 'L') {
			scoreFunction('L');
			coordinateX--;

		} else if (direction == 'U') {
			scoreFunction('U');
			coordinateY--;

		} else if (direction == 'D') {
			scoreFunction('D');
			coordinateY++;

		}

	}

	public void scoreFunction(char direction) {
		Object[][] mazeArray = maze.getMaze();
		if (direction == 'U') {

			if (mazeArray[coordinateY - 1][coordinateX].getClass().getSimpleName().equals("Warp")
					|| mazeArray[coordinateY - 1][coordinateX].getClass().getSimpleName().equals("Trap")) {
				setComputerTotalScore(300);
			} else {
				setComputerTotalScore(ScoreDefine.scoreDefinder(mazeArray[coordinateY - 1][coordinateX]) * 2);
				
				Object object = mazeArray[coordinateY-1][coordinateX];
				Object objectPackage = object.getClass().getPackage().getName();
				Object objectName = object.getClass().getSimpleName().toString();
				if(objectPackage.equals("Treasures.Moves")){
					if(objectName.equals("Four")) {
						Four four = (Four) object;
						four.numberDie();
					}
					if(objectName.equals("Five")) {
						Five five = (Five) object;
						five.numberDie();
					}
				}
			}
			if (mazeArray[coordinateY - 2][coordinateX].getClass().getSimpleName().equals("Player")) {
				player.getBackpack().pop();
				player.getBackpack().pop();
			}
		}

		if (direction == 'R') {

			if (mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Warp")
					|| mazeArray[coordinateY][coordinateX + 1].getClass().getSimpleName().equals("Trap")) {
				setComputerTotalScore(300);
			} else {
				setComputerTotalScore(ScoreDefine.scoreDefinder(mazeArray[coordinateY][coordinateX + 1]) * 2);
				
				Object object = mazeArray[coordinateY][coordinateX+1];
				Object objectPackage = object.getClass().getPackage().getName();
				Object objectName = object.getClass().getSimpleName().toString();
				if(objectPackage.equals("Treasures.Moves")){
					if(objectName.equals("Four")) {
						Four four = (Four) object;
						four.numberDie();
					}
					if(objectName.equals("Five")) {
						Five five = (Five) object;
						five.numberDie();
					}
				}
			}
			if (mazeArray[coordinateY][coordinateX + 2].getClass().getSimpleName().equals("Player")) {
				player.getBackpack().pop();
				player.getBackpack().pop();
			}

		}

		if (direction == 'D') {
			if (mazeArray[coordinateY + 1][coordinateX].getClass().getSimpleName().equals("Warp")
					|| mazeArray[coordinateY + 1][coordinateX].getClass().getSimpleName().equals("Trap")) {
				setComputerTotalScore(300);
			} else {
				setComputerTotalScore(ScoreDefine.scoreDefinder(mazeArray[coordinateY + 1][coordinateX]) * 2);
				
				Object object = mazeArray[coordinateY+1][coordinateX];
				Object objectPackage = object.getClass().getPackage().getName();
				Object objectName = object.getClass().getSimpleName().toString();
				if(objectPackage.equals("Treasures.Moves")){
					if(objectName.equals("Four")) {
						Four four = (Four) object;
						four.numberDie();
					}
					if(objectName.equals("Five")) {
						Five five = (Five) object;
						five.numberDie();
					}
				}
			}
			if (mazeArray[coordinateY + 2][coordinateX].getClass().getSimpleName().equals("Player")) {
				player.getBackpack().pop();
				player.getBackpack().pop();
			}
		}

		if (direction == 'L') {

			if (mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("Warp")
					|| mazeArray[coordinateY][coordinateX - 1].getClass().getSimpleName().equals("Trap")) {
				setComputerTotalScore(300);
			} else {
				setComputerTotalScore(ScoreDefine.scoreDefinder(mazeArray[coordinateY][coordinateX - 1]) * 2);
				
				Object object = mazeArray[coordinateY][coordinateX-1];
				Object objectPackage = object.getClass().getPackage().getName();
				Object objectName = object.getClass().getSimpleName().toString();
				if(objectPackage.equals("Treasures.Moves")){
					if(objectName.equals("Four")) {
						Four four = (Four) object;
						four.numberDie();
					}
					if(objectName.equals("Five")) {
						Five five = (Five) object;
						five.numberDie();
					}
				}
			}
			if (mazeArray[coordinateY][coordinateX - 2].getClass().getSimpleName().equals("Player")) {
				player.getBackpack().pop();
				player.getBackpack().pop();
			}
		}
	}



	public void computerMove() {

		Object[][] tempMaze = maze.getMaze();

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
			int second = 0;

			@Override
			public void run() {

				if (isLiving()) {
					if (!isFreeze()) {
						boolean isNull = false;
						if(tempMaze[coordinateY][coordinateX].getClass().getSimpleName().equals("Computer")) {
							tempMaze[coordinateY][coordinateX] = " ";
						}

						goToMove();

						if (coordinateX == player.getX() && coordinateY == player.getY()) {
							player.LifeRemove();
							player.setEnergy(50);
							player.updateCoordinates();
							while(!player.getBackpack().isEmpty()) {
								player.getBackpack().pop();
							}
						}

						while (!isNull) {
							isNull = computerUpdateMaze(coordinateX, coordinateY, obj, tempMaze);
						}
					} else {
						if(25-freezingTime == second) {
							isFreeze = false;
						}
					}
				} else {
					timer.cancel();
				}
			}

		};

		timer.schedule(task, 100, 1000);
	}

	public void computerDie() {
		live = false;
	}

	public boolean isLiving() {
		return live;
	}

	public void computerFreeze(int freezeTime) {
		this.freezingTime = freezeTime;
		isFreeze = true;

	}

	public boolean isFreeze() {
		return isFreeze;
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

	public static int getComputerTotalScore() {
		return computerTotalScore;
	}

	public static void setComputerTotalScore(int computerScore) {
		computerTotalScore += computerScore;
	}

}