package tools;

import enigma.core.Enigma;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


public class ConsolePrinter {
	public enigma.console.Console cn = Enigma.getConsole("Console", 140, 40, 13);

	public KeyListener klis;

	// ------ Standard variables for mouse and keyboard ------
	public int keypr; // key pressed?
	public static int rkey; // key (for press/release)
	int defaultX;
	int defaultY;
	static int secondHolder = 999;

	public ConsolePrinter() throws Exception { // --- Contructor

	}

	public void getCoordinatPoint() {
		defaultX = cn.getTextWindow().getCursorX();
		defaultY = cn.getTextWindow().getCursorY();
	}

	public void template() {

		int x = defaultX + 75;
		int y = defaultY - 5;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("--------------------------");
		cn.getTextWindow().setCursorPosition(x, y + 1);
		for (int i = 0; i < 4; i++) {
			cn.getTextWindow().setCursorPosition(x, y + 1 + i);
			System.out.println("|                        |");
		}
		cn.getTextWindow().setCursorPosition(x, y + 5);
		System.out.println("--------------------------");

	}

	public void template2() {

		int x = defaultX + 75;
		int y = defaultY - 5;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("--------------------------");
		cn.getTextWindow().setCursorPosition(x, y + 1);
		for (int i = 0; i < 4; i++) {
			cn.getTextWindow().setCursorPosition(x, y + 1 + i);
			System.out.println("|                        |");
		}
		cn.getTextWindow().setCursorPosition(x, y + 5);
		System.out.println("--------------------------");

		cn.getTextWindow().setCursorPosition(defaultX, defaultY);
	}

//	public void printBox(Box box) {
//
//		cn.getTextWindow().setCursorPosition(defaultX + 76, defaultY - 5);
//		System.out.println("Money : $" + box.getMoney());
//		cn.getTextWindow().setCursorPosition(defaultX + 76, defaultY - 4);
//		System.out.println("Time  : ");
//		cn.getTextWindow().setCursorPosition(defaultX + 76, defaultY - 3);
//		System.out.println(box.getPercentage());
//		cn.getTextWindow().setCursorPosition(defaultX + 76, defaultY - 2);
//		System.out.print(box.getDoubleDip());
//		cn.getTextWindow().setCursorPosition(defaultX - 31, defaultY - 1);
//
//	}

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
						if (rkey == KeyEvent.VK_A)
							return rckey;

						if (rkey == KeyEvent.VK_B)
							return rckey;

						if (rkey == KeyEvent.VK_C)
							return rckey;

						if (rkey == KeyEvent.VK_D)
							return rckey;

						if (rkey == KeyEvent.VK_E)
							return rckey;

						if (rkey == KeyEvent.VK_J)
							return rckey;

						if (rkey == KeyEvent.VK_K)
							return rckey;
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

	public void reset2() {
		keypr = 0; // last action
		rkey = 0;
		cn.getTextWindow().removeKeyListener(klis);
	}

	public void time() {
		secondHolder = 999;

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			int seconds = 20;

			@Override
			public void run() {

				int x = defaultX + 84;
				int y = defaultY - 4;
				if (seconds >= 10) {
					cn.getTextWindow().setCursorPosition(x, y);
					System.out.print(seconds--);

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
					System.out.print("0" + seconds--);

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

		timer.schedule(task, 0, 1000);

	}

}