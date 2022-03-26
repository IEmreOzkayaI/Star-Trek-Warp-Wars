package entities;

import java.util.Random;

import tools.Queue;

public class Maze {
	
	private Queue mazeQueue ;
	
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
		while(true) {
			int x = random.nextInt(41);
			if(1<= x && x>=12)
				mazeQueue.enqueue(1); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if(13<= x && x>=20)
				mazeQueue.enqueue(2); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if(21<= x && x>=26)
				mazeQueue.enqueue(3); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if(27<= x && x>=31)
				mazeQueue.enqueue(4); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if(32<= x && x>=35)
				mazeQueue.enqueue(5); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if(36<= x && x>=37)
				mazeQueue.enqueue("="); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if(38 == x )
				mazeQueue.enqueue("*"); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
			if(39<= x && x>=40)
				mazeQueue.enqueue("C"); // BU YAPILAR CLASSLAR OLUÞTURULDUÐUNDA DEÐÝÞTÝRÝLMELÝ
		}
	}
}
