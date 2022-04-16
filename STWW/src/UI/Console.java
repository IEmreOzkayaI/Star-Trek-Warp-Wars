package UI;

import enigma.console.TextAttributes;
import enigma.core.Enigma;
import entities.Computer;
import entities.Maze;
import entities.Player;
import tools.FileReader;
import tools.ObjeComparator;
import tools.Queue;
import tools.Stack;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
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

	public static enigma.console.Console cn = Enigma.getConsole("Console", 80, 25, 30);
	
//	static int selection = 1 ;

	static int defaultX;
	static int defaultY;
	public static int keypr; // key pressed?
	private static Maze maze = new Maze();
	private Queue consoleQueue = new Queue(15);
	private Player player;
	Computer computer;
	public static int rkey; // key (for press/release)
	public static KeyListener klis;
	private boolean isContinue = true;

	int time = 1;
	Stack tempBackpack = new Stack(8);
	int backpackCount = 0;
	static Scanner scan = new Scanner(System.in);
	
	public Console(Object[][] map, Computer computerManager) throws InterruptedException { // --- Constructor

		
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

				Thread.sleep(1000);  

				consoleClear();
				this.template(player);
			}
			
			endScreen();
			break;
		case 2:
				System.out.println("Burada oyunun nasýl oynandýðý anlatýlacak");
			break;
		default:
			System.out.println("Unexpected value: " + menuSelection);
		}
	

	}

	public void endScreen() throws InterruptedException {
		consoleClear();
		templateClear();

		// Detection of Winer
		Computer[] numberOfComputer = Computer.getComputerList();
		int totalComputerScore = 0;
		for (int i = 0; i < numberOfComputer.length; i++) {
			totalComputerScore += numberOfComputer[i].getScore();
		}
		int winnerScore = player.getScore() - totalComputerScore;

		cn.getTextWindow().setCursorPosition(30, 10);
		System.out.println("Winner and Their Score");
		cn.getTextWindow().setCursorPosition(30, 11);

		System.out.println("----------------------");
		cn.getTextWindow().setCursorPosition(30, 12);
		if (winnerScore < 0)
			System.out.println("Computer " + " : " + totalComputerScore);
		else
			System.out.println("Player " + " : " + player.getScore());

	}

	public static void menuScreen(Object[][] dashBoard) throws InterruptedException {
		cn.getTextWindow().setCursorPosition(38, 6);
		System.out.print("MENU");
		cn.getTextWindow().setCursorPosition(34, 10);
		System.out.print("1 - PLAY");
		cn.getTextWindow().setCursorPosition(34, 11);
		System.out.print("2 - HOW TO PLAY");
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				for (int i = 0; i < dashBoard.length; i++) {
					for (int j = 0; j < dashBoard[1].length; j++) {

						if (i == 0 || i == dashBoard.length - 1) {
							cn.getTextWindow().setCursorPosition(17 + j, 7 + i);
							System.out.print(dashBoard[i][j].toString());
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						if (j == 0 || j == dashBoard[1].length - 1) {
							cn.getTextWindow().setCursorPosition(17 + j, 7 + i);
							System.out.print(dashBoard[i][j].toString());
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
					System.out.println();
				}
				for (int i = 0; i < dashBoard.length; i++) {
					for (int j = 1; j < dashBoard[1].length; j++) {

						if (i == 0 || i == dashBoard.length - 1) {

							cn.getTextWindow().setCursorPosition(17 + j - 1, 7 + i);
							System.out.print(" ");
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							cn.getTextWindow().setCursorPosition(17 + j, 7 + i);
							System.out.print(dashBoard[i][j].toString());
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							cn.getTextWindow().setCursorPosition(17 + j - 1, 7 + i);
							System.out.print(dashBoard[i][j - 1].toString());
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
					

				}
				int num = 0 ; 
				num = keyList();
				reset();
				if(num!=0)
					timer.cancel();

			}

		};
		
		timer.schedule(task, 1, 10);

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

	private void templateClear() throws InterruptedException {
		for (int i = 1; i < 24; i++) {
			for (int j = 60; j < 75; j++) {
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

		if (element.equalsIgnoreCase("1")) {
			new One(maze);

		}
		if (element.equalsIgnoreCase("2")) {
			new Two(maze);
		}
		if (element.equalsIgnoreCase("3")) {
			new Three(maze);

		}

		if (element.equalsIgnoreCase("4")) {
			new Four(cn, maze);
		}

		if (element.equalsIgnoreCase("5")) {
			new Five(cn, maze);

		}
		if (element.equalsIgnoreCase("C")) {
			Computer computer = new Computer(cn, maze, player);
			computerManager.addComputer(computer);

		}
		if (element.equalsIgnoreCase("=")) {
			new Trap(cn, maze);

		}
		if (element.equalsIgnoreCase("*")) {
			new Warp(cn, maze);

		}
	}

	public void generatingQueueElement() {
		Random random = new Random();
		boolean flag = true;
		while (flag) {
			int x = random.nextInt(41);
			if (1 <= x && 12 >= x)
				consoleQueue.enqueue(1); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (13 <= x && 20 >= x)
				consoleQueue.enqueue(2); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (21 <= x && 26 >= x)
				consoleQueue.enqueue(3); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (27 <= x && 31 >= x)
				consoleQueue.enqueue(4); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (32 <= x && 35 >= x)
				consoleQueue.enqueue(5); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (36 <= x && 37 >= x)
				consoleQueue.enqueue("="); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (38 == x)
				consoleQueue.enqueue("*"); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (39 <= x && 40 >= x)
				consoleQueue.enqueue("C"); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
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
		printBackpack(x, y, player);
		cn.getTextWindow().setCursorPosition(x + 5, y + 13);
		System.out.println("+---+");
		cn.getTextWindow().setCursorPosition(x + 3, y + 14);
		System.out.println("P.Backpack");
		cn.getTextWindow().setCursorPosition(x, y + 16);
		System.out.println("P.Energy : " + player.getEnergy());
		cn.getTextWindow().setCursorPosition(x, y + 17);
		System.out.println("P.Score  : " + player.getScore());
		cn.getTextWindow().setCursorPosition(x, y + 18);
		System.out.println("P.Life   : " + player.getLife());
		cn.getTextWindow().setCursorPosition(x, y + 20);
		System.out.println("C.Score  :");
		cn.getTextWindow().setCursorPosition(x, y + 22);
		System.out.println("Time     : " + time);

	}

	public void printBackpack(int x, int y, Player player) {
		backpackCount=0;
		while (!player.getBackpack().isEmpty()) {
			backpackCount++;
			Object temp = player.getBackpack().pop();
			tempBackpack.push(temp);
		}
		while (!tempBackpack.isEmpty()) {
			Object temp = tempBackpack.pop();
			player.getBackpack().push(temp);
		}
		int spaceCount = 8 - backpackCount;
		
		for (int i = 0; i < backpackCount; i++) {
			cn.getTextWindow().setCursorPosition(x + 5, y + 5 + spaceCount + i);
			Object temp = player.getBackpack().pop();

			System.out.println("| " + ObjeComparator.objComparator(temp) + " |");
			tempBackpack.push(temp);
		}
		for (int i = 0; i < spaceCount; i++) {
			cn.getTextWindow().setCursorPosition(x + 5, y + 5 + i);
			System.out.println("|   |");
		}
		while (!tempBackpack.isEmpty()) {
			Object temp = tempBackpack.pop();
			player.getBackpack().push(temp);
		}
	}

	public static char keyList() {

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
				cn.getTextWindow().setCursorPosition(cn.getTextWindow().getCursorX(),cn.getTextWindow().getCursorY());

				for (int i = 0; i < 1; i++) {
					if (rkey == KeyEvent.VK_1)
						return '1';
					if (rkey == KeyEvent.VK_2)
						return '2';
				}
			}
		}
	}

	public static void reset() {
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