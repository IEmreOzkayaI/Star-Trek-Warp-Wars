package entities;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import tools.Queue;

public class Maze {

	private Queue mazeQueue;

	public Maze(Object[][] maze) {
		mazeQueue = new Queue(15);
		this.printMaze(maze);
	}

	public void printMaze(Object[][] maze) {
		System.out.println("\n");
		for (int i = 0; i < maze.length; i++) {
			System.out.print("  ");
			for (int j = 0; j < maze[i].length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();

		}
	}

	public void generatingQueueElement() {
		Random random = new Random();
		boolean flag = true;
		while (flag) {
			int x = random.nextInt(41);
			if (1 <= x &&  12 >= x)
				mazeQueue.enqueue(1); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (13 <= x && 20 >= x )
				mazeQueue.enqueue(2); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (21 <= x && 26 >= x)
				mazeQueue.enqueue(3); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (27 <= x && 31 >= x)
				mazeQueue.enqueue(4); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (32 <= x && 35 >= x)
				mazeQueue.enqueue(5); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (36 <= x && 37 >= x)
				mazeQueue.enqueue("="); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (38 == x)
				mazeQueue.enqueue("*"); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (39 <= x && 40 >= x)
				mazeQueue.enqueue("C"); // BU YAPILAR CLASSLAR OLUŞTURULDUĞUNDA DEĞİŞTİRİLMELİ
			if (mazeQueue.isFull())
				flag = false;
		}
	}

	public void printQueueToField() {
		for (int i = 0; i < 15; i++) {
			Object obj = mazeQueue.dequeue();
			System.out.print(obj.toString());
		}

	}

}
