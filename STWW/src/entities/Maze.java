package entities;


public class Maze {

	private Object[][] maze;

	public Maze() {

	}

	public void printMaze(Object[][] maze) {
		this.maze = maze;
		System.out.println("\n");
		for (int i = 0; i < this.maze.length; i++) {
			System.out.print("  ");
			for (int j = 0; j < this.maze[i].length; j++) {

				System.out.print(this.maze[i][j]);
			}
			System.out.println();

		}
	}

	public Object[][] getMaze() {
		return maze;
	}

	public boolean updateMaze(int x, int y, String value) {

		if (maze[y][x].equals(" ")) {
			maze[y][x] = value;
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return true;
		} else {
			return false;
		}

	}

}
