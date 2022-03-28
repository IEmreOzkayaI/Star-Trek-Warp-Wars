package entities;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import tools.Queue;

public class Maze {

	private Queue mazeQueue;
	private Object[][] map;

	public Maze(Object[][] maze) {
		mazeQueue = new Queue(15);
		this.map = maze;

	}

	public void printMaze() {
		System.out.println("\n");
		for (int i = 0; i < this.map.length; i++) {
			System.out.print("  ");
			for (int j = 0; j < this.map[i].length; j++) {
				if(this.map[i][j].equals("0"))
						System.out.print(" ");
				else
				System.out.print(this.map[i][j]);
			}
			System.out.println();

		}
	}

	public void generatingQueueElement() {
		Random random = new Random();
		boolean flag = true;
		while (flag) {
			int x = random.nextInt(41);
			if (1 <= x && 12 >= x)
				mazeQueue.enqueue(1); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (13 <= x && 20 >= x)
				mazeQueue.enqueue(2); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (21 <= x && 26 >= x)
				mazeQueue.enqueue(3); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (27 <= x && 31 >= x)
				mazeQueue.enqueue(4); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (32 <= x && 35 >= x)
				mazeQueue.enqueue(5); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (36 <= x && 37 >= x)
				mazeQueue.enqueue("="); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (38 == x)
				mazeQueue.enqueue("*"); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (39 <= x && 40 >= x)
				mazeQueue.enqueue("C"); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if (mazeQueue.isFull())
				flag = false;
		}
	}

	public void printQueueToField() {
		Queue tempQueue = new Queue(15);
		for (int i = 0; i < 15; i++) {
			Object obj = mazeQueue.dequeue();
			tempQueue.enqueue(obj);
			System.out.print(obj);
		}
		for (int i = 0; i < 15; i++) {
			mazeQueue.enqueue(tempQueue.dequeue());
		}

	}

	public Queue printQueueElemetToMaze() {
		return this.mazeQueue;
	}

	public Queue getMazeQueue() {
		return mazeQueue;
	}

	public void setMazeQueue(Queue mazeQueue) {
		this.mazeQueue = mazeQueue;
	}

	public Object[][] getMap() {
		return map;
	}

	public boolean updateMap(int x, int y, String value) {
	
		if (map[y][x].equals(" ")) {
			map[y][x] = value;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}else {
			return false;
		}

	}

}
