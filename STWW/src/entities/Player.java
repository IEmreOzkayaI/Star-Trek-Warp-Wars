package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import tools.RandomCoordinateGenerator;
import tools.ScoreDefine;
import tools.Stack;




public class Player {
	
	private int coordinateX ;
	private int coordinateY ;
	private Object obj = this;
	public int keypr;
	public KeyListener klis;
	public static int rkey;


	private Stack backpack;
	int life = 5;
	int energy = 50;
	private final String name = "P";
	private int score = 0;
	private enigma.console.Console cn;
    private Maze maze;
    
	public Player() {}
	

	public Player(Maze maze,enigma.console.Console cn) throws InterruptedException {

		int[] coordinate = RandomCoordinateGenerator.generateRandomCoordinates(this, maze);
		setX(coordinate[0]);
		setY(coordinate[1]);
        this.maze = maze;
        this.cn=cn;
        backpack = new Stack(8);
        playerMove();
    }


	public void printPlayer() {
		boolean isNull = false;

		while (!isNull) {
			isNull = this.maze.updateMaze(this.getX(),this.getY(), this);
		}
	}
	
private void backpackRemover(char direction, int coordinateDirectionX, int coordinateDirectionY, Object backpackObject) {
	boolean isBackpackRemove = false;
	if(direction == 'W') {
		coordinateDirectionX = 0;
		coordinateDirectionY = -1;
		backpackObject = backpack.pop();
		isBackpackRemove = maze.updateMaze(coordinateX + coordinateDirectionX, coordinateY + coordinateDirectionY, backpackObject);
	}
	else if(direction == 'S') {
		coordinateDirectionX = 0;
		coordinateDirectionY = 1;
		backpackObject = backpack.pop();
		isBackpackRemove = maze.updateMaze(coordinateX + coordinateDirectionX, coordinateY + coordinateDirectionY, backpackObject);
	}
	else if(direction == 'D') {
		coordinateDirectionX = 1;
		coordinateDirectionY = 0;
		backpackObject = backpack.pop();
		isBackpackRemove = maze.updateMaze(coordinateX + coordinateDirectionX, coordinateY + coordinateDirectionY, backpackObject);
	}
	else if(direction == 'A') {
		coordinateDirectionX = -1;
		coordinateDirectionY = 0;
		backpackObject = backpack.pop();
		isBackpackRemove = maze.updateMaze(coordinateX + coordinateDirectionX, coordinateY + coordinateDirectionY, backpackObject);
	}
	if(!isBackpackRemove) {
		backpack.push(backpackObject);
	}
}
public void playerMove() {	
		
		Timer timer = new Timer();
		
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
                boolean isNull = false;
                boolean isBackpackRemoverWorked = false;
                Object backpackObject = 0;
                
                int x = 0;
                int y = 0;
                char direction = keyList();
                
                while (!isNull) {
                	
                	int coordinateDirectionX = 0;
            		int coordinateDirectionY = 0;
                	Object[][] mazeMap = maze.getMaze();
                	if(direction == 'R') {
                		coordinateDirectionX = 1;
                		coordinateDirectionY = 0;
                		isNull = maze.updateMaze(x = coordinateX + coordinateDirectionX,y = coordinateY + coordinateDirectionY, obj);
                	}
                	else if(direction == 'L') {
                		coordinateDirectionX = -1;
                		coordinateDirectionY = 0;
                		isNull = maze.updateMaze(x = coordinateX + coordinateDirectionX,y = coordinateY + coordinateDirectionY, obj);
                	}
                	else if(direction == 'U') {
                		coordinateDirectionX = 0;
                		coordinateDirectionY = -1;
                		isNull = maze.updateMaze(x = coordinateX + coordinateDirectionX,y = coordinateY + coordinateDirectionY, obj);
                	}
                	else if(direction == 'B') {
                		coordinateDirectionX = 0;
                		coordinateDirectionY = 1;
                		isNull = maze.updateMaze(x = coordinateX + coordinateDirectionX,y = coordinateY + coordinateDirectionY, obj);
                	}
                	else {
                		backpackRemover(direction, coordinateDirectionX, coordinateDirectionY, backpackObject);
                		isBackpackRemoverWorked = true;
                	}
					if (isNull) {
						mazeMap[coordinateY][coordinateX] = " ";
						coordinateX += coordinateDirectionX;
						coordinateY += coordinateDirectionY;
					}
					else if(isBackpackRemoverWorked) {
						isNull = true;
					}
					else if (mazeMap[coordinateY + coordinateDirectionY][coordinateX+ coordinateDirectionX] != "#" &&
							!(mazeMap[coordinateY + coordinateDirectionY][coordinateX+ coordinateDirectionX].getClass().getSimpleName().toString().equalsIgnoreCase("Computer")) && !isBackpackRemoverWorked) {
						backpack.push(mazeMap[coordinateY + coordinateDirectionY][coordinateX + coordinateDirectionX]);
						score +=  ScoreDefine.scoreDefinder(mazeMap[coordinateY + coordinateDirectionY][coordinateX + coordinateDirectionX]);
						mazeMap[coordinateY + coordinateDirectionY][coordinateX + coordinateDirectionX] = " ";
					} else if (mazeMap[coordinateY + coordinateDirectionY][coordinateX + coordinateDirectionX] == "#" || 
							mazeMap[coordinateY + coordinateDirectionY][coordinateX+ coordinateDirectionX].getClass().getSimpleName().toString().equalsIgnoreCase("Computer")  && !isBackpackRemoverWorked) {
						mazeMap[coordinateY][coordinateX] = obj;
						isNull = true;
					}
					reset();			
                }
                isNull = false;
				}
                
        	};

        	timer.schedule(task, 50, 1000);
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
					if (rkey == KeyEvent.VK_W)
						return 'W';
					if (rkey == KeyEvent.VK_A)
						return 'A';
					if (rkey == KeyEvent.VK_D)
						return 'D';
					if (rkey == KeyEvent.VK_S)
						return 'S';
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
