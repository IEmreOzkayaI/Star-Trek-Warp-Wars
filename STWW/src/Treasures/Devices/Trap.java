package Treasures.Devices;

import java.awt.event.KeyEvent;
import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import Treasures.Moves.Five;
import Treasures.Moves.Four;
import UI.Console;
import entities.Computer;
import entities.Maze;
import tools.RandomCoordinateGenerator;

public class Trap {
	private String name = "=";
	private int score = 0;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private boolean isActivated = false;
	private enigma.console.Console cn;
	private Maze maze;

	public Trap() {
	}

	public Trap(enigma.console.Console cn, Maze maze) {
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
	
	public void traping(int second) {
		int referencePointX = coordinateX;
		int referencePointY = coordinateY;
		Object[][] tempMaze = this.maze.getMaze();
		int[] arr = { -1,-1,-1,0,-1,1,0,-1,0,1,1,-1,1,0,1,1};
		for (int i = 0; i < arr.length-1; i+=2) {
			Object object = tempMaze[referencePointY + arr[i+1]][referencePointX + arr[i]];
			Object objectPackage = object.getClass().getPackage().getName();
			Object objectName = object.getClass().getSimpleName().toString();
			if ( objectPackage.equals("Treasures.Moves") || objectPackage.equals("entities")){
				
				if(objectName.equals("Four")) {
					Four four =(Four) object;
					four.numberFreeze(second);
				}
				if(objectName.equals("Five")) {
					Five five =(Five) object;
					five.numberFreeze(second);
				}
				if(objectName.equals("Computer")) {
					Computer computer = (Computer) object;
					computer.computerFreeze(second);
				}
			}
		}
	}
	
	private void representationTime() {
	Timer timer = new Timer();

	TimerTask task = new TimerTask() {

		int seconds = 0;

		@Override
		public void run() {

			if(isActivated()) {
				seconds++;
				traping(seconds);
				if(seconds == 25) {
					maze.updateTreasur(coordinateX, coordinateY, " ");
					timer.cancel();
				}
			}
			
		}
	};

	timer.schedule(task, 100, 1000);

}

}
