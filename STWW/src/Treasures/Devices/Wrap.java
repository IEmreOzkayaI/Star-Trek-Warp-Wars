package Treasures.Devices;

import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import entities.Maze;

public class Wrap {
	private final String name = "*";
	private final int score = 0;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private boolean isActivated = false;
	private enigma.console.Console cn;
	private Maze maze;

	public Wrap(enigma.console.Console cn, Maze maze) {
		this.maze = maze;
		this.cn = cn;
	}
	
	private void representationTime() {
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			int seconds = 0;

			@Override
			public void run() {

				if (seconds >= 5) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cn.getTextWindow().setCursorPosition(coordinateX + 2, coordinateY + 1);
					maze.updateMaze(getX(), getY(), " ");
					System.out.print(" ");
					timer.cancel();

				}
				seconds++;
			}

		};

		timer.schedule(task, 100, 1000);
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
		coordinateX = random ;
		return coordinateX;
	}

	public int getY() {
		SplittableRandom splittableRandom = new SplittableRandom();
		int random = splittableRandom.nextInt(1, 23);
		 coordinateY = random ;
		 return coordinateY;
			
	}
	
	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
}
