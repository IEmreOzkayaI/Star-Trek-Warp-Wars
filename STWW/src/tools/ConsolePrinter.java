package tools;

import enigma.core.Enigma;
import entities.Maze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


public class ConsolePrinter {
	
	public enigma.console.Console cn = Enigma.getConsole("Console", 80, 25 ,15);
	
	Maze maze ;
	public KeyListener klis;

	// ------ Standard variables for keyboard ------
	int defaultX;
	int defaultY;
	public int keypr; // key pressed?
	public static int rkey; // key (for press/release)
	static int secondHolder = 999;

	public ConsolePrinter(String[] fileName)  { // --- Constructor
		maze = new Maze();
		maze.printMaze(fileName);
		this.template();
		
	}

	public void getCoordinatPoint() {
		defaultX = cn.getTextWindow().getCursorX();
		defaultY = cn.getTextWindow().getCursorY();
	}

	public void template() {

		int x = defaultX + 60;
		int y = defaultY+1;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("Input");
		cn.getTextWindow().setCursorPosition(x, y+1);
		System.out.println("<<<<<<<<<<<<<<<");

		cn.getTextWindow().setCursorPosition(x, y + 3);
		System.out.println("<<<<<<<<<<<<<<<");
		
		for (int i = 0; i < 8; i++) {
			cn.getTextWindow().setCursorPosition(x+5, y + 5 +i);
			System.out.println("|   |");
		}
		cn.getTextWindow().setCursorPosition(x+5, y + 13);
		System.out.println("+---+");
		cn.getTextWindow().setCursorPosition(x+3, y + 14);
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

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}

			}

			public void keyReleased(KeyEvent e) {

			}
		};
		cn.getTextWindow().addKeyListener(klis);
		// ----------------------------------------------------

		while (true) {

			try {
				Thread.sleep(200);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (keypr == 1) { // if keyboard button pressed

				char rckey = (char) rkey;
				cn.getTextWindow().setCursorPosition(defaultX + 2, defaultY - 1);

				for (int i = 0; i < 1; i++) {

					if (secondHolder != (-1)) {
						if (rkey == KeyEvent.VK_UP)
							return 'U';

						if (rkey == KeyEvent.VK_RIGHT)
							return 'R';

						if (rkey == KeyEvent.VK_LEFT)
							return 'L';

						if (rkey == KeyEvent.VK_DOWN)
							return 'B';

					} else {
						return 'n';
					}
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
		secondHolder = 999;

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			int seconds = 0;

			@Override
			public void run() {

				int x = defaultX + 72;
				int y = defaultY +23;
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

					if (seconds == -1) {
						secondHolder = seconds;
						cn.getTextWindow().setCursorPosition(defaultX - 31, defaultY);
						System.out.println("   Time is Up , Press F");
						timer.cancel();
					} else {
						if (rkey == KeyEvent.VK_A || rkey == KeyEvent.VK_B || rkey == KeyEvent.VK_C
								|| rkey == KeyEvent.VK_D || rkey == KeyEvent.VK_E) {
							cn.getTextWindow().setCursorPosition(defaultX - 31, defaultY);
							timer.cancel();

						}
						if (rkey == KeyEvent.VK_J || rkey == KeyEvent.VK_K) {
							cn.getTextWindow().setCursorPosition(defaultX - 31, defaultY + 10);

						}
					}

				}
			}
		};

		timer.schedule(task, 100, 1000);

	}

}