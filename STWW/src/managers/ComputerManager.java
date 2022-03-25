package managers;

import java.util.Random;

import entities.Computer;
import tools.ComputerList;

public class ComputerManager {

	private static int computerTotalScore = 0;
	private ComputerList list;
	Random random = new Random();
	int boardOfBorderY= 2;
	int boardOfBorderX= 3;

	public ComputerManager() {
		list = new ComputerList();
	}

	public void addComputer() {
		Computer computer = new Computer();
		int coordinateX = random.nextInt(21 - boardOfBorderX + 1) + boardOfBorderX;
		int coordinateY = random.nextInt(54 - boardOfBorderX + 1) + boardOfBorderX;
		computer.setCx(coordinateX);
		computer.setCy(coordinateY);
		// KONTROL : BOÞ ALAN
		
	}

//	public void changeCoordinate(char direction) {
//		if (direction == 'U') {
//			comp.setCy(comp.getCy() + 1);
//		} else if (direction == 'B') {
//			comp.setCy(comp.getCy() - 1);
//		} else if (direction == 'L') {
//			comp.setCx(comp.getCx() - 1);
//		} else if (direction == 'R') {
//			comp.setCx(comp.getCy() + 1);
//		}
//	}

//	public void increaseComouterScore(char treasure) {
//		Boolean flag = Character.isDigit(treasure);
//		if (flag) {
//			Computer.setComputerTotalScore(Computer.getComputerTotalScore() + ((int) treasure * 2));
//		} else {
//			if (treasure == '=') {
//				Computer.setComputerTotalScore(Computer.getComputerTotalScore() + 300);
//			} else if (treasure == '*') {
//				Computer.setComputerTotalScore(Computer.getComputerTotalScore() + 300);
//			}
//		}
//
//	}

	public static int getComputerTotalScore() {
		return computerTotalScore;
	}

	public static void setComputerTotalScore(int computerScore) {
		ComputerManager.computerTotalScore += computerScore;
	}
}