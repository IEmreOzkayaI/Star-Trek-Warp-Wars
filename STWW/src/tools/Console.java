package tools;

import enigma.core.Enigma;
import entities.Computer;
import entities.Maze;
import entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Treasures.Constants.One;
import Treasures.Constants.Three;
import Treasures.Constants.Two;
import Treasures.Devices.Trap;
import Treasures.Devices.Wrap;
import Treasures.Moves.Five;
import Treasures.Moves.Four;

public class Console {

	public enigma.console.Console cn = Enigma.getConsole("Console", 85, 25, 15);

	int defaultX;
	int defaultY;
	public int keypr; // key pressed?
	private Maze maze = new Maze();
	private Queue consoleQueue;

	public static int rkey; // key (for press/release)
	public KeyListener klis;
	private boolean isContinue = true;
	
	public Console(Object[][] map, Computer computer) { // --- Constructor
		consoleQueue = new Queue(15);
		maze.printMaze(map);
		this.template();
		this.generatingQueueElement();
		this.printFirstTwenty(computer);
		this.printPlayer();
		cn.getTextWindow().setCursorPosition(60, 3);
		this.printQueueToField();
		this.continueQueue(computer);
	}

	private void continueQueue(Computer computerManager) {
		Queue que = this.consoleQueue;
		while(isContinue) {
			String element = que.dequeue().toString();
			this.generatingQueueElement();
			elementDecision(element,computerManager);
			try {
				cn.getTextWindow().setCursorPosition(60, 3);
				printQueueToField();
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void printPlayer() {
		Player player = new Player();
		boolean isNull = false;
		int x = 0;
		int y = 0;

		while (!isNull) {
			isNull = this.maze.updateMaze(x = player.getX(), y = player.getY(), player.getName());
		}
		isNull = false;
		cn.getTextWindow().setCursorPosition(x + 2, y + 1);
		System.out.print(player.getName());
	}

	public void printFirstTwenty(Computer computerManager) {
		
		for (int i = 0; i < 20; i++) {
			Queue qu = this.consoleQueue;
			String element = qu.dequeue().toString();
			this.generatingQueueElement();
			elementDecision(element,computerManager);
		}
	}
	
	public void elementDecision(String element,Computer computerManager) {
		boolean isNull = false;
		int x = 0;
		int y = 0;
		
		if (element.equalsIgnoreCase("1")) {
			One one = new One();
			while (!isNull) {
				isNull = this.maze.updateMaze(x = one.getX(), y = one.getY(), one.getName());
			}
			isNull = false;
			cn.getTextWindow().setCursorPosition(x + 2, y + 1);
			System.out.print(one.getName());

		}
		if (element.equalsIgnoreCase("2")) {
			Two two = new Two();
			while (!isNull) {
				isNull = this.maze.updateMaze(x = two.getX(), y = two.getY(), two.getName());
			}
			isNull = false;
			cn.getTextWindow().setCursorPosition(x + 2, y + 1);

			System.out.print(two.getName());

		}
		if (element.equalsIgnoreCase("3")) {

			Three three = new Three();
			while (!isNull) {
				isNull = this.maze.updateMaze(x = three.getX(), y = three.getY(), three.getName());
			}
			isNull = false;
			cn.getTextWindow().setCursorPosition(x + 2, y + 1);

			System.out.print(three.getName());
		}

		if (element.equalsIgnoreCase("4")) {
			Four four = new Four();
			while (!isNull) {
				isNull = this.maze.updateMaze(x = four.getX(), y = four.getY(), four.getName());
			}
			isNull = false;
			cn.getTextWindow().setCursorPosition(x + 2, y + 1);
			System.out.print(four.getName());
		}

		if (element.equalsIgnoreCase("5")) {
			Five five = new Five();
			while (!isNull) {
				isNull = this.maze.updateMaze(x = five.getX(), y = five.getY(), five.getName());
			}
			isNull = false;
			cn.getTextWindow().setCursorPosition(x + 2, y + 1);

			System.out.print(five.getName());
		}
		if (element.equalsIgnoreCase("C")) {
			Computer computer = new Computer();
			computerManager.addComputer(computer);
			while (!isNull) {
				isNull = this.maze.updateMaze(x = computer.getX(), y = computer.getY(), computer.getName());
			}
			isNull = false;
			cn.getTextWindow().setCursorPosition(x + 2, y + 1);

			System.out.print(computer.getName());
		}
		if (element.equalsIgnoreCase("=")) {
			Trap trap = new Trap();
			while (!isNull) {
				isNull = this.maze.updateMaze(x = trap.getX(), y = trap.getY(), trap.getName());
			}
			isNull = false;
			cn.getTextWindow().setCursorPosition(x + 2, y + 1);

			System.out.print(trap.getName());
		}
		if (element.equalsIgnoreCase("*")) {
			Wrap wrap = new Wrap();
			while (!isNull) {
				isNull = this.maze.updateMaze(x = wrap.getX(), y = wrap.getY(), wrap.getName());
			}
			isNull = false;
			cn.getTextWindow().setCursorPosition(x + 2, y + 1);

			System.out.print(wrap.getName());
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

	public void template() {

		int x = defaultX + 60;
		int y = defaultY + 1;

		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("Input");
		cn.getTextWindow().setCursorPosition(x, y + 1);
		System.out.println("<<<<<<<<<<<<<<<");

		cn.getTextWindow().setCursorPosition(x, y + 3);
		System.out.println("<<<<<<<<<<<<<<<");

		for (int i = 0; i < 8; i++) {
			cn.getTextWindow().setCursorPosition(x + 5, y + 5 + i);
			System.out.println("|   |");
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
		cn.getTextWindow().setCursorPosition(x, y + 22);
		this.time();

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

	public void time() {

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			int seconds = 0;

			@Override
			public void run() {

				int x = defaultX + 72;
				int y = defaultY + 23;
				if (seconds >= 10) {
					cn.getTextWindow().setCursorPosition(x, y);
					System.out.print(seconds++);

					if (rkey == KeyEvent.VK_A || rkey == KeyEvent.VK_B || rkey == KeyEvent.VK_C || rkey == KeyEvent.VK_D
							|| rkey == KeyEvent.VK_E) {
						timer.cancel();
						cn.getTextWindow().setCursorPosition(defaultX - 31, defaultY);

					}

					if (rkey == KeyEvent.VK_J || rkey == KeyEvent.VK_K) {
						cn.getTextWindow().setCursorPosition(defaultX - 31, defaultY + 10);

					}
				}

				if (seconds < 10) {

					cn.getTextWindow().setCursorPosition(x, y);
					System.out.print("0" + seconds++);

					if (rkey == KeyEvent.VK_A || rkey == KeyEvent.VK_B || rkey == KeyEvent.VK_C || rkey == KeyEvent.VK_D
							|| rkey == KeyEvent.VK_E) {
						cn.getTextWindow().setCursorPosition(defaultX - 31, defaultY);
						timer.cancel();

					}
					if (rkey == KeyEvent.VK_J || rkey == KeyEvent.VK_K) {
						cn.getTextWindow().setCursorPosition(defaultX - 31, defaultY + 10);

					}
				}

			}

		};

		timer.schedule(task, 100, 1000);

	}

}