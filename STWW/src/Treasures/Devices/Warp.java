package Treasures.Devices;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import Treasures.Moves.Five;
import Treasures.Moves.Four;
import entities.Computer;
import entities.Maze;
import entities.Player;
import tools.RandomCoordinateGenerator;
import tools.ScoreDefine;

public class Warp {
	private final String name = "*";
	private final int score = 0;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private boolean isActivated = false;
	private enigma.console.Console cn;
	private Maze maze;

	public Warp() {

	}

	public Warp(enigma.console.Console cn, Maze maze) {
		this.maze = maze;
		this.cn = cn;
		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(this, maze);
		setX(coordinate[0]);
		setY(coordinate[1]);
		representationTime();
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

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;

	}

	public void warping() {

		int referencePointX = coordinateX;
		int referencePointY = coordinateY;
		Object[][] tempMaze = this.maze.getMaze();
		int[] arr = { -1,-1,-1,0,-1,1,0,-1,0,1,1,-1,1,0,1,1};
		for (int i = 0; i < arr.length-1; i+=2) {
			Object object = tempMaze[referencePointY + arr[i+1]][referencePointX + arr[i]];
			Object objectPackage = object.getClass().getPackage().getName();
			Object objectName = object.getClass().getSimpleName().toString();
			if (objectPackage.equals("Treasures.Constants") || objectPackage.equals("Treasures.Moves")
					) {
				if(objectName.equals("Four")) {
					Four four =(Four) object;
					four.numberDie();
				}
				if(objectName.equals("Five")) {
					Five five =(Five) object;
					five.numberDie();
				}
				Player.score += ScoreDefine.scoreDefinder(object);
				tempMaze[referencePointY + arr[i+1]][referencePointX + arr[i]]=" ";
			}
			if(objectName.equals("Computer")) {
				Player.score += ScoreDefine.scoreDefinder(object);
				tempMaze[referencePointY + arr[i+1]][referencePointX + arr[i]]=" ";
				Computer computer = (Computer) object;
				computer.computerDie();
			}
		}

	}

	private void representationTime() {
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			int seconds = 0;

			@Override
			public void run() {

				if (isActivated()) {
					seconds++;
					warping();
					if (seconds == 25) {
						maze.updateTreasur(coordinateX, coordinateY, " ");
						timer.cancel();
					}
				}

			}
		};

		timer.schedule(task, 100, 1000);

	}

}
