package entities;

import java.awt.Color;

import enigma.console.Console;
import enigma.console.TextAttributes;
import tools.ObjeComparator;

public class Maze {

	private Object[][] maze;
	public static TextAttributes player= new TextAttributes(Color.RED,Color.black);
	public static TextAttributes computer= new TextAttributes(Color.orange,Color.black);
	public static TextAttributes map= new TextAttributes(Color.LIGHT_GRAY,Color.black);
	public static TextAttributes elements= new TextAttributes(Color.LIGHT_GRAY,Color.black);

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
						cn.getTextWindow().output(ObjeComparator.objComparator(maze[i][j]),elements);
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

}
