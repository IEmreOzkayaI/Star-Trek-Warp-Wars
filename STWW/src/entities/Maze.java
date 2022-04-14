package entities;

import tools.ObjeComparator;

public class Maze {

	private Object[][] maze;

	public Maze() {

	}

	public void printMaze(Object[][] maze) throws InterruptedException {
		this.maze = maze;
		System.out.println("\n");
		for (int i = 0; i < this.maze.length; i++) {
			System.out.print("  ");
			for (int j = 0; j < this.maze[i].length; j++) {

				System.out.print(ObjeComparator.objComparator(maze[i][j]));
				
			}
			System.out.println();

		}
	}

	public Object[][] getMaze() {
		return maze;
	}

	public boolean updateMaze(int x, int y, Object value) {

		if (maze[y][x].equals(" ")) {
			maze[y][x] = value;
			return true;
		} else {
			return false;
		}

	}

}
