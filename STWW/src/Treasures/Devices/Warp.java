package Treasures.Devices;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import entities.Maze;
import tools.RandomCoordinateGenerator;

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
	
}
