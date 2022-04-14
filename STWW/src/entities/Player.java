package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import tools.RandomCoordinateGenerator;
import tools.Stack;




public class Player {
	
	private int coordinateX ;
	private int coordinateY ;
	
	public int keypr;
	public KeyListener klis;
	public static int rkey;


	Stack backpack;
	int life = 5;
	int energy = 50;
	private final String name = "P";
	private final int score = 0;
	private enigma.console.Console cn;
    private Maze maze;
    
	public Player() {}
	

	public Player(Maze maze,enigma.console.Console cn) throws InterruptedException {

		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(name, maze);
		setX(coordinate[0]);
		setY(coordinate[1]);
        this.maze = maze;
        this.cn=cn;
        playerMove();

    }


	public void printPlayer() {
		boolean isNull = false;

		while (!isNull) {
			isNull = this.maze.updateMaze(this.getX(),this.getY(), this.getName());
		}
	}
	
	public void playerMove() {	

		Timer timer = new Timer();
		
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
                boolean isNull = false;
                int x = 0;
                int y = 0;
                char direction = keyList();
                while (!isNull) {
                	Object[][] mazeMap = maze.getMaze();
                	/*
                	int coordinateDirection = 0;
                	if(direction == 'R') {
                		coordinateDirectionX = 1;
                		coordinateDirectionY = 0;
                	}
                	if(direction == 'L') {
                		coordinateDirectionX = -1;
                	}
                	if(direction == 'U') {
                		coordinateDirection = 1;
                	}
                	if(direction == 'B') {
                		coordinateDirection = 1;
                	}
                	*/
                    if(direction == 'R') {
                    	isNull = maze.updateMaze(x = coordinateX + 1, y = coordinateY, getName());
                    	if(isNull) {
                    		mazeMap[coordinateY][coordinateX] = " ";
            				coordinateX++;
                    	}else if (mazeMap[coordinateY][coordinateX+1] != "#" && mazeMap[coordinateY][coordinateX+1] != "C") {
							mazeMap[coordinateY][coordinateX+1] = " ";
						}else if(mazeMap[coordinateY][coordinateX+1] == "#"){
							mazeMap[coordinateY][coordinateX] = getName();
							isNull = true;
						}
                    	reset();
        			}
        			else if(direction == 'L') {
        				isNull = maze.updateMaze(x = coordinateX - 1, y = coordinateY, getName());
                    	if(isNull) {
                    		mazeMap[coordinateY][coordinateX] = " ";
            				coordinateX--;
                    	}else if (mazeMap[coordinateY][coordinateX-1] != "#" && mazeMap[coordinateY][coordinateX-1] != "C") {
							mazeMap[coordinateY][coordinateX-1] = " ";
						}else if(mazeMap[coordinateY][coordinateX-1] == "#"){
							mazeMap[coordinateY][coordinateX] = getName();
							isNull = true;
						}
                    	reset();
        			}
        			else if(direction == 'U') {
        				isNull = maze.updateMaze(x = coordinateX, y = coordinateY - 1, getName());
                    	if(isNull) {
                    		mazeMap[coordinateY][coordinateX] = " ";
            				coordinateY--;
                    	}else if (mazeMap[coordinateY - 1][coordinateX] != "#" && mazeMap[coordinateY - 1][coordinateX] != "C") {
							mazeMap[coordinateY - 1][coordinateX] = " ";
						}else if(mazeMap[coordinateY - 1][coordinateX] == "#"){
							mazeMap[coordinateY][coordinateX] = getName();
							isNull = true;
						}
                    	reset();
        			}
        			else if(direction == 'B') {
        				isNull = maze.updateMaze(x = coordinateX, y = coordinateY + 1, getName());
                    	if(isNull) {
                    		mazeMap[coordinateY][coordinateX] = " ";
            				coordinateY++;
                    	}else if (mazeMap[coordinateY + 1][coordinateX] != "#" && mazeMap[coordinateY + 1][coordinateX] != "C") {
							mazeMap[coordinateY + 1][coordinateX] = " ";
						}else if(mazeMap[coordinateY + 1][coordinateX] == "#"){
							mazeMap[coordinateY][coordinateX] = getName();
							isNull = true;
						}
                    	reset();
        			}
                }
                isNull = false;
				}
                
        	};

        	timer.schedule(task, 750, 1000);
            }

	public char keyList() {
		rkey = 0;
		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		
		while (true) {

			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}

			if (keypr == 1) { // if keyboard button pressed

				for (int i = 0; i < 1; i++) {
					if (rkey == KeyEvent.VK_UP)
						return 'U';
					if (rkey == KeyEvent.VK_RIGHT)
						return 'R';
					if (rkey == KeyEvent.VK_LEFT)
						return 'L';
					if (rkey == KeyEvent.VK_DOWN) 
						return 'B';
				}
				keypr = 0;
			}
		}
	}
	
	public void reset() {
		keypr = 0; // last action
		rkey = 0;

		cn.getTextWindow().removeKeyListener(klis);
	}

	
	public int getX() {
		return coordinateX;
	}

	public int getY() {
		return coordinateY;
	
	}

	public void setX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	
	public void setY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
	

	public void EnergyAdd(int energyValue) {
		this.setEnergy(energyValue + this.getEnergy());
	}

	public void LifeRemove() {
		this.setLife(this.getLife() - 1);
	}
	
	public void BackpackAdd(int item) {
		backpack.push(item);
	}
	
	public void BackpackRemove() {
		backpack.pop();
	}
	
	public Stack getBackpack() {
		return backpack;
	}

	public void setBackpack(Stack backpack) {
		this.backpack = backpack;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}



	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}

}
