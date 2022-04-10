package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

import tools.Console;
import tools.Stack;

public class Player {
	public int keypr;
	public KeyListener klis;
	public static int rkey;
	int defaultX;
	int defaultY;
	SplittableRandom splittableRandom = new SplittableRandom();
	int randomX = splittableRandom.nextInt(1, 55);
	int randomY = splittableRandom.nextInt(1, 23);
	Stack backpack;
	int life = 5;
	int energy = 50;
	
	private enigma.console.Console cn;
    private Maze maze;
	
	private int coordinateX = randomX;
	private int coordinateY = randomY;
	private final String name = "P";
	private final int score = 0;

	public Player() {
	}

//	public void CheckCoordinate() {
//		while(true) {
//			
//			Object[][] mazeMap = maze.getMaze();
//			if(mazeMap[randomY][randomX].equals(" "))
//				
//				break;
//		}
//		
//		
//	}
	public void EnergyAdd(int energyValue) {
		this.setEnergy(energyValue + this.getEnergy());
	}

	public void LifeRemove() {
		this.setLife(this.getLife() - 1);
	}

	public Player(enigma.console.Console cn, Maze maze) {
        this.maze = maze;
        this.cn = cn;
        playerMove();
    }
	public void playerMove() {


        Timer timer = new Timer();

        TimerTask task = new TimerTask() {


            @Override
            public void run() {

            	
                boolean isNull = false;
                int x = 0;
                int y = 0;
                //randMove();
                char direction = keyList();
                while (!isNull) {
                	Object[][] mazeMap = maze.getMaze();
                    if(direction == 'R') {
                    	if(mazeMap[coordinateY][coordinateX + 1].equals(" ")) {
                    		isNull = maze.updateMaze(x = coordinateX + 1, y = coordinateY, getName());
                    		mazeMap[coordinateY][coordinateX] = " ";
            				cn.getTextWindow().setCursorPosition(coordinateX + 2, coordinateY + 1);
                            System.out.print(" ");
            				coordinateX++;
                    	}
                    	reset();
        			}
        			else if(direction == 'L') {
        				if(mazeMap[coordinateY][coordinateX - 1].equals(" ")) {
        					isNull = maze.updateMaze(x = coordinateX - 1, y = coordinateY, getName());
        					mazeMap[coordinateY][coordinateX] = " ";
            				cn.getTextWindow().setCursorPosition(coordinateX + 2, coordinateY + 1);
                            System.out.print(" ");
            				coordinateY--;
        				}
        				reset();
        			}
        			else if(direction == 'U') {
        				if(mazeMap[coordinateY - 1][coordinateX].equals(" ")) {
        					isNull = maze.updateMaze(x = coordinateX, y = coordinateY - 1, getName());
        					mazeMap[coordinateY][coordinateX] = " ";
	        				cn.getTextWindow().setCursorPosition(coordinateX + 2, coordinateY + 1);
	                        System.out.print(" ");
	        				coordinateY--;
        				}	
        				reset();
        			}
        			else if(direction == 'B') {
        				if(mazeMap[coordinateY + 1][coordinateX].equals(" ")) {
        					isNull = maze.updateMaze(x = coordinateX, y = coordinateY + 1, getName());
        					mazeMap[coordinateY][coordinateX] = " ";
            				cn.getTextWindow().setCursorPosition(coordinateX + 2, coordinateY + 1);
                            System.out.print(" ");
            				coordinateY++;
        				}
        				reset();
        			}
                }
                isNull = false;


                cn.getTextWindow().setCursorPosition(x + 2, y + 1);
                System.out.print(getName());

            }

        };

        timer.schedule(task, 283, 1000);

    }
	public void CoordinateChange(char direction) {
		//U UP - R RIGHT - L LEFT - B DOWN
		if(direction == 'U') {
			this.setX(this.getX());
			this.setY(this.getY() + 1);
		}else if(direction == 'R') {
			this.setX(this.getX() + 1);
			this.setY(this.getY());
		}else if(direction == 'L') {
			this.setX(this.getX() - 1);
			this.setY(this.getY());
		}else if(direction == 'B') {
			this.setX(this.getX());
			this.setY(this.getY() - 1);
		}
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
	
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	public void reset() {
		keypr = 0; // last action
		rkey = 0;
		defaultX = 0;
		defaultY = 0;
		cn.getTextWindow().removeKeyListener(klis);
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
				Thread.sleep(200);
			} catch (Exception e) {
			}

			if (keypr == 1) { // if keyboard button pressed
				cn.getTextWindow().setCursorPosition(defaultX + 2, defaultY - 1);

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

}
