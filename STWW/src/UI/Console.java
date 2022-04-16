package UI;

import enigma.core.Enigma;
import entities.Computer;
import entities.Maze;
import entities.Player;
import tools.Queue;
import tools.Stack;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Treasures.Constants.One;
import Treasures.Constants.Three;
import Treasures.Constants.Two;
import Treasures.Devices.Trap;
import Treasures.Devices.Warp;
import Treasures.Moves.Five;
import Treasures.Moves.Four;

public class Console {

	public enigma.console.Console cn = Enigma.getConsole("Console", 80, 25, 20);

	int defaultX;
	int defaultY;
	public int keypr; // key pressed?
	private Maze maze = new Maze();
	private Queue consoleQueue = new Queue(15);
	Player player;
	public static int rkey; // key (for press/release)
	public KeyListener klis;
	private boolean isContinue = true;

	public static int time = 1;
	Stack tempBackpack = new Stack(8);
	int backpackCount = 0;
	public Console(Object[][] map, Computer computerManager) throws InterruptedException { // --- Constructor
<<<<<<< Updated upstream
		consoleQueue = new Queue(15);
		this.generatingQueueElement();
		maze.printMaze(map);
		player = new Player(maze, cn);
		this.template(player);
		this.printFirstTwenty(computerManager);

		while (isContinue) {
			time++;
			cn.getTextWindow().setCursorPosition(-1, -1);
			maze.printMaze(map);
			cn.getTextWindow().setCursorPosition(60, 3);
			this.printQueueToField();
			if(time%3==0) {
				this.continueQueue(computerManager);
=======

		
//		int menuSelection = menuScreen(new FileReader().readFile("menu.txt", false));
		int menuSelection=1;
		switch (menuSelection) {
		case 1: 
			consoleClear();
			templateClear();
			consoleQueue = new Queue(15);
			this.generatingQueueElement();
			maze.printMaze(map,cn);
			player = new Player(maze, cn); // First and Single Player
			computer=new Computer(cn,maze,player); // First Computer
			this.template(player);
			this.printFirstTwenty(computerManager);


			while (player.getLife()>0) { 
				time++;
				cn.getTextWindow().setCursorPosition(-1, -1);
				maze.printMaze(map,cn);
				cn.getTextWindow().setCursorPosition(60, 3);
				this.printQueueToField();
				if(time%3==0) {
					this.continueQueue(computerManager);
				}

				Thread.sleep(250);  

				consoleClear();
				this.template(player);
>>>>>>> Stashed changes
			}

			Thread.sleep(1000);

			consoleClear();
			this.template(player);
		}

	}

	private void consoleClear() throws InterruptedException {
		for (int i = 1; i < 24; i++) {
			for (int j = 2; j < 57; j++) {
				cn.getTextWindow().setCursorPosition(j, i);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	private void continueQueue(Computer computerManager) throws InterruptedException {
		Queue que = this.consoleQueue;
		String element = que.dequeue().toString();
		elementDecision(element, computerManager);
		this.generatingQueueElement();
		cn.getTextWindow().setCursorPosition(60, 3);
		printQueueToField();

	}

	public void printFirstTwenty(Computer computerManager) throws InterruptedException {

		for (int i = 0; i < 20; i++) {
			Queue qu = this.consoleQueue;
			String element = qu.dequeue().toString();
			this.generatingQueueElement();
			elementDecision(element, computerManager);
		}
	}

	public void elementDecision(String element, Computer computerManager) throws InterruptedException {
		boolean isNull = false;
		int x = 0;
		int y = 0;

		if (element.equalsIgnoreCase("1")) {
			One one = new One(maze);
		}
		if (element.equalsIgnoreCase("2")) {
			Two two = new Two(maze);
		}
		if (element.equalsIgnoreCase("3")) {
			Three three = new Three(maze);

		}

		if (element.equalsIgnoreCase("4")) {
			Four four = new Four(cn, maze);
		}

		if (element.equalsIgnoreCase("5")) {
			Five five = new Five(cn, maze);

		}
		if (element.equalsIgnoreCase("C")) {
			Computer computer = new Computer(cn,maze, player);
			computerManager.addComputer(computer);

		}
		if (element.equalsIgnoreCase("=")) {
			Trap trap = new Trap(cn, maze);

		}
		if (element.equalsIgnoreCase("*")) {
			Warp warp = new Warp(cn, maze);

		}
	}

	public void generatingQueueElement() {
		Random random = new Random();
		boolean flag = true;
		while (flag) {
			int x = random.nextInt(41);
			if (1 <= x && 12 >= x)
				consoleQueue.enqueue(1); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (13 <= x && 20 >= x)
				consoleQueue.enqueue(2); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (21 <= x && 26 >= x)
				consoleQueue.enqueue(3); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (27 <= x && 31 >= x)
				consoleQueue.enqueue(4); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (32 <= x && 35 >= x)
				consoleQueue.enqueue(5); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (36 <= x && 37 >= x)
				consoleQueue.enqueue("="); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (38 == x)
				consoleQueue.enqueue("*"); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (39 <= x && 40 >= x)
				consoleQueue.enqueue("C"); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (consoleQueue.isFull())
				flag = false;
		}
	}

	public void printQueueToField() {
		Queue tempQueue = new Queue(15);
		for (int i = 0; i < 15; i++) {
			Object obj = consoleQueue.dequeue();
			tempQueue.enqueue(obj);
			System.out.print(obj);
		}
		for (int i = 0; i < 15; i++) {
			consoleQueue.enqueue(tempQueue.dequeue());
		}

	}

	public void template(Player player) {
		int x = 60;
		int y = 1;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("Input");
		cn.getTextWindow().setCursorPosition(x, y + 1);
		System.out.println("<<<<<<<<<<<<<<<");

		cn.getTextWindow().setCursorPosition(x, y + 3);
		System.out.println("<<<<<<<<<<<<<<<");
	
		
		while(!player.getBackpack().isEmpty()) {
			backpackCount++;
			Object temp = player.getBackpack().pop();
			tempBackpack.push(temp);
		}
		while(!tempBackpack.isEmpty()) {
			Object temp = tempBackpack.pop();
			player.getBackpack().push(temp);
		}
		int spaceCount = 8 - backpackCount;
		for (int i = 0; i < spaceCount; i++) {
			cn.getTextWindow().setCursorPosition(x + 5, y + 5 + i);
			System.out.println("|   |");
		}
		for (int i = 0; i < backpackCount; i++) {
			cn.getTextWindow().setCursorPosition(x + 5, y + 5 + spaceCount + i);
			String temp = (String) player.getBackpack().pop();
			System.out.println("| " + temp +" |");
			tempBackpack.push(temp);
		}
		
		
		cn.getTextWindow().setCursorPosition(x + 5, y + 13);
		System.out.println("+---+");
		cn.getTextWindow().setCursorPosition(x + 3, y + 14);
		System.out.println("P.Backpack");
		cn.getTextWindow().setCursorPosition(x, y + 16);
		System.out.println("P.Energy :");
		cn.getTextWindow().setCursorPosition(x, y + 17);
		System.out.println("P.Score  :");
		cn.getTextWindow().setCursorPosition(x, y + 18);
		System.out.println("P.Life   :");
		cn.getTextWindow().setCursorPosition(x, y + 20);
		System.out.println("C.Score  :");
		cn.getTextWindow().setCursorPosition(x, y + 22);
		System.out.println("Time     :");
		cn.getTextWindow().setCursorPosition(x + 10, y + 22);
		System.out.print(time);

	}

	public char keyList() {

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
			}
		}
	}

	public void reset() {
		keypr = 0; // last action
		rkey = 0;
		defaultX = 0;
		defaultY = 0;
		cn.getTextWindow().removeKeyListener(klis);
	}

	public int time() {
		return time;
	}

}