package Treasures.Devices;

import java.awt.event.KeyEvent;
import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import UI.Console;
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

	public Trap(enigma.console.Console cn, Maze maze) {
		this.maze = maze;
		this.cn = cn;
		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(this, maze);
		setX(coordinate[0]);
		setY(coordinate[1]);
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
	
	
//	private void representationTime() {
//		Timer timer = new Timer();
//
//		TimerTask task = new TimerTask() {
//
//			int seconds = 0;
//
//			@Override
//			public void run() {
//
//				if (seconds >= 25) {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					cn.getTextWindow().setCursorPosition(coordinateX + 2, coordinateY + 1);
//					maze.updateMaze(getX(), getY(), " ");
//					System.out.print(" ");
//					timer.cancel();
//
//				}
//				seconds++;
//			}
//
//		};
//
//		timer.schedule(task, 100, 1000);
//
//	}

}
