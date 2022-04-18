package entities;

import java.awt.Color;

import enigma.console.Console;
import enigma.console.TextAttributes;
import tools.ObjeComparator;

public class Maze {

	private Object[][] maze;
	public static TextAttributes player= new TextAttributes(Color.blue,Color.black);
	public static TextAttributes computer= new TextAttributes(Color.red,Color.black);
	public static TextAttributes template= new TextAttributes(Color.orange,Color.black);
	public static TextAttributes timer= new TextAttributes(Color.cyan,Color.black);
	public static TextAttributes movingNumbers= new TextAttributes(Color.green,Color.black);
	public static TextAttributes map= new TextAttributes(Color.LIGHT_GRAY,Color.black);
	public static TextAttributes elements= new TextAttributes(Color.LIGHT_GRAY,Color.black);
	public static TextAttributes white= new TextAttributes(Color.white,Color.black);
	public Maze() {

	}

	public void printMaze(Object[][] maze,Console cn) throws InterruptedException {
		this.maze = maze;
		System.out.println("\n");
		for (int i = 0; i < this.maze.length; i++) {
			System.out.print("  ");
			for (int j = 0; j < this.maze[i].length; j++) {
				if(ObjeComparator.objComparator(maze[i][j]).equals("P")) {
					int x = cn.getTextWindow().getCursorX();
					int y= cn.getTextWindow().getCursorY();
					cn.getTextWindow().setCursorPosition(x, y);
					cn.getTextWindow().output("P",player);
					continue;
				}
				
				if(ObjeComparator.objComparator(maze[i][j]).equals("C")) {
					int x = cn.getTextWindow().getCursorX();
					int y= cn.getTextWindow().getCursorY();
					cn.getTextWindow().setCursorPosition(x, y);
					cn.getTextWindow().output("C",computer);
					continue;
				}
				
				
				else {
					if(ObjeComparator.objComparator(maze[i][j]).equals(" ") || ObjeComparator.objComparator(maze[i][j]).equals("#") ) {
						int x = cn.getTextWindow().getCursorX();
						int y= cn.getTextWindow().getCursorY();
						cn.getTextWindow().setCursorPosition(x, y);
						cn.getTextWindow().output(ObjeComparator.objComparator(maze[i][j]),map);
//						System.out.print(ObjeComparator.objComparator(maze[i][j]));
					}else {
						int x = cn.getTextWindow().getCursorX();
						int y= cn.getTextWindow().getCursorY();
						cn.getTextWindow().setCursorPosition(x, y);
						if(ObjeComparator.objComparator(maze[i][j]).equals("4") || ObjeComparator.objComparator(maze[i][j]).equals("5")) {
							cn.getTextWindow().output(ObjeComparator.objComparator(maze[i][j]),movingNumbers);
						}
						else {
							cn.getTextWindow().output(ObjeComparator.objComparator(maze[i][j]),elements);
						}
						
					}

				}
				
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
	public boolean updateTreasur(int x, int y, Object value) {

		if (maze[y][x].getClass().getSimpleName().toString().equals("Trap") || maze[y][x].getClass().getSimpleName().toString().equals("Warp") ) {
			maze[y][x] = value;
			return true;
		} else {
			return false;
		}

	}

}
